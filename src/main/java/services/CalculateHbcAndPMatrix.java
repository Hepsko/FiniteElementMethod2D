package services;

import data.Data;
import lombok.*;
import schemas.GaussTable;
import schemas.Grid;
import schemas.Node;

import static utils.Utils.roundTpDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculateHbcAndPMatrix {

    double[][] HBC =new double[4][4];
    double[] P =new double[4];
    public void HbcAndPMatrix(Grid grid, int elementNumber)
    {
        for(int i = 0; i < 4; i++) {
            double ksi;
            double eta;
            int i_2 = (i + 1) > 3? 0 : i + 1;
           Node node = grid.nodes[grid.elements[elementNumber].id[i] -1];
           Node node2 = grid.nodes[grid.elements[elementNumber].id[i_2] -1];


            if(!node.isBc()  || !node2.isBc()) {
                continue;
            }
            double detJac = Math.sqrt((Math.pow((node.getX() - node2.getX()) , 2)) + (Math.pow((node.getY() - node2.getY()), 2))) / 2.0;


            for(int j = 0; j < Data.DATA_points ; j++) {
                double[] compute_functions =new double[]{0,0,0,0};

                double[] ksiSign =new double[]{-1.0, 1.0, 1.0, -1.0};
                double[] etaSign =new double[]{-1.0, -1.0, 1.0, 1.0};

                ksi = (i == 1 || i == 3) ? 1 : new GaussTable(Data.DATA_points).quadrat.points[j];
                eta = (i == 0 || i == 2) ? 1 : new GaussTable(Data.DATA_points).quadrat.points[j];

                ksi = i >= 2  ? ksi * -1 : ksi;
                eta = (i == 3) || (i == 0) ? (eta * -1) : eta;

                compute_functions[i] = (1.0/4.0)
                        * (1.0 + (ksiSign[i] * ksi))
                        * (1.0 + (etaSign[i] * eta));

                compute_functions[i_2] = (1.0/4.0)
                        * (1.0 + (ksiSign[i_2] * ksi))
                        * (1.0 + (etaSign[i_2] * eta));
                HbcIntegration(new GaussTable(Data.DATA_points).quadrat.weight[j], detJac, compute_functions);
                PIntegration(j, new GaussTable(Data.DATA_points).quadrat.weight[j], compute_functions, detJac);
            }
        }
    }

    private void HbcIntegration(double weight, double detJac, double[] shapeFunctions) {
       int  c = shapeFunctions.length;
        for(int j = 0; j < c; j++) {
            this.HBC[0][j] += shapeFunctions[0] * shapeFunctions[j] * Data.ALFA * weight * detJac;
            this.HBC[1][j] += shapeFunctions[1] * shapeFunctions[j] * Data.ALFA * weight * detJac;
            this.HBC[2][j] += shapeFunctions[2] * shapeFunctions[j] * Data.ALFA * weight * detJac;
            this.HBC[3][j] += shapeFunctions[3] * shapeFunctions[j] * Data.ALFA *weight * detJac;
        }
    }

    private void PIntegration(int wall, double weight, double[] computeShapes, double detJac)
    {

        for(int i = 0; i < 4; i++ ) {
            this.P[i] +=  computeShapes[i] * Data.ALFA * detJac * Data.CONST_T_1[wall] * weight;
        }
    }

    public void printHBCMatrix(){
        for(int p = 0; p<4; p++){
            for(int k = 0; k<4; k++){
                System.out.print(roundTpDecimal((HBC[p][k]) )+ " ");
            }
            System.out.println();
        };
        System.out.println("#############################################################################");
    }
    public void printPMatrix(){
        for(int i = 0; i<4; i++){

                System.out.print((Math.round(this.P[i])) + "  ");
                if(i % 2 != 0){
                    System.out.println("");
                }
        };
        System.out.println("#############################################################################");
    }
}
