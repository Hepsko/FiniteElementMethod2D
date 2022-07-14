package services;

import data.Data;
import lombok.*;
import schemas.Element;
import schemas.GaussTable;

import static utils.Utils.roundTpDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculateCMatrix {
        private double[][] C = new double[4][4] ;

    public void CMatrix(Element element)
    {
        int c = 0;
        for(int j = 0; j < Data.DATA_points; j++) {
            double PC1 = new GaussTable(Data.DATA_points).quadrat.points[j];

            for(int k = 0; k < Data.DATA_points; k++) {
                double PC2  = new GaussTable(Data.DATA_points).quadrat.points[k];

                 double[] computeShapes = new double[]{
                        (1.0 / 4.0) * (1.0 - (PC2)) * (1.0 - (PC1)),
                        (1.0 / 4.0) * (1.0 + (PC2)) * (1.0 - (PC1)),
                        (1.0 / 4.0) * (1.0 + (PC2)) * (1.0 + (PC1)),
                        (1.0 / 4.0) * (1.0 - (PC2)) * (1.0 + (PC1))
                };
//                for (double xd : computeShapes){
//                    System.out.print(xd + " ");
//
//                }
//                System.out.println();
                CMatrixIntegration( new GaussTable(Data.DATA_points).quadrat.weight[j] *
                        new GaussTable(Data.DATA_points).quadrat.weight[k], c, computeShapes,  element);
                c++;
            }
        }
    }

    private void CMatrixIntegration(double weight, int i, double[] computeShapes, Element element) {

        for(int j = 0; j < 4; j++) {
            for(int k = 0; k < 4; k++) {
                this.C[j][k] +=
                        computeShapes[j] * computeShapes[k]
                                * Data.RO * Data.C * weight
                                * element.getJacobian()[i].detJacobian;
            }
        }

    }

    public void printC(){
        for(int p = 0; p<4; p++){
            for(int k = 0; k<4; k++){
                System.out.print(roundTpDecimal((C[p][k]) )+ " ");
            }
            System.out.println();
        };
        System.out.println("#############################################################################");
    }
}
