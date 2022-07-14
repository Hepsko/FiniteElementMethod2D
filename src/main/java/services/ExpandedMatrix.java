package services;

import lombok.*;

import static utils.Utils.roundTpDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpandedMatrix {
    public ExpandedMatrix(int nodeQuantity) {
        this.nodeQuantity = nodeQuantity;
        matrixExpand = new double[nodeQuantity][nodeQuantity +1];
    }

    int nodeQuantity;
    private double[][] matrixExpand;

    public  void createExpandMatrix(double [][]HGLOBAL, double[] PGLOBAL )
    {
        for(int p = 0; p< nodeQuantity; p++){
            for(int k = 0; k< nodeQuantity; k++){
              matrixExpand[p][k] =  HGLOBAL[p][k];
            }

        int last = nodeQuantity;

        int counter = 0;
        for (double p_tmp : PGLOBAL) {
        this.matrixExpand[counter][last] = p_tmp;
        counter++;
    }
    }




}

    public void printExpandedMatirix(){
        for(int p = 0; p< nodeQuantity; p++){
            for(int k = 0; k< nodeQuantity + 1; k++){
                System.out.print(roundTpDecimal((matrixExpand[p][k]) )+ " ");
            }
            System.out.println();
        };
        System.out.println("#############################################################################");
    }
}

