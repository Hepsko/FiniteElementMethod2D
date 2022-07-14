package services;

import data.Data;
import lombok.*;
import schemas.Element;
import schemas.Grid;

import static utils.Utils.roundTpDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculateAggregation {

    private int nodeQuantity;
    private double[][] HGlobal;
    private double[][] CGlobal;
    private double[] P;

    public CalculateAggregation(int nodeQuantity) {
        this.nodeQuantity = nodeQuantity;
        this.HGlobal =  new double[nodeQuantity ][nodeQuantity];
        this.CGlobal =  new double[nodeQuantity ][nodeQuantity];
        this.P = new double[nodeQuantity];
    }


    public  void aggregateHAndPAndCMatrix(Element element, Grid grid)
    {

        for(int j = 0; j < 4; j++) {
        for(int k = 0; k < 4; k++) {
            HGlobal[element.id[j] -1][element.id[k]- 1] +=
            element.getH()[j][k]
                    + element.getHbc()[j][k] + (element.getC()[j][k]/ Data.DELTA_T);
            CGlobal[element.id[j] -1][element.id[k]- 1] +=
                    element.getC()[j][k] ;
        }

    }
        for(int j = 0; j <4; j++) {
            double tmp = 0.0;
            for(int k = 0; k < 4; k++) {
                tmp += element.getC()[j][k] / Data.DELTA_T
                        * grid.nodes[element.getId()[k] -1].getTempT0();
            }
        P[element.id[j]-1] += element.getP()[j] + tmp;
    }


    }



    public void printPGlobal(){
        for(int p = 0; p< nodeQuantity; p++){
                System.out.print(roundTpDecimal((P[p])) + " " );
        };
        System.out.println();
        System.out.println("#############################################################################");
    }

    public void printHGlobal(){
        for(int p = 0; p< nodeQuantity; p++){
            for(int k = 0; k< nodeQuantity; k++){
                System.out.print(roundTpDecimal((HGlobal[p][k]) )+ " ");
            }
            System.out.println();
        };
        System.out.println("#############################################################################");
    }

    public void printCGlobal(){
        for(int p = 0; p< nodeQuantity; p++){
            for(int k = 0; k< nodeQuantity; k++){
                System.out.print(roundTpDecimal((CGlobal[p][k]) )+ " ");
            }
            System.out.println();
        };
        System.out.println("#############################################################################");
    }
}
