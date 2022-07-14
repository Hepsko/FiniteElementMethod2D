package services;


import data.Data;
import lombok.*;
import schemas.Element;
import schemas.Element4_2D;
import schemas.GaussTable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CalculateMatrixH {
    public double[][] H= new double[4][4];
    double[] dn_dX;
    double[] dn_dY;

    public void CalculateMatrixH( Element4_2D el, Element element)
    {
        for(int i = 0; i < Math.pow(Data.DATA_points,2); i++) {
             dn_dX = new double[]{
                    (element.getJacobian()[i].jacobianInv[0][0] * el.getKsi().get(i).valueN1) + (element.getJacobian()[i].jacobianInv[0][1] * el.getEta().get(i).valueN1),
                    (element.getJacobian()[i].jacobianInv[0][0] * el.getKsi().get(i).valueN2) + (element.getJacobian()[i].jacobianInv[0][1] * el.getEta().get(i).valueN2),
                    (element.getJacobian()[i].jacobianInv[0][0] * el.getKsi().get(i).valueN3) + (element.getJacobian()[i].jacobianInv[0][1] * el.getEta().get(i).valueN3),
                    (element.getJacobian()[i].jacobianInv[0][0] * el.getKsi().get(i).valueN4) + (element.getJacobian()[i].jacobianInv[0][1] * el.getEta().get(i).valueN4)

            };
            dn_dY = new double[]{
            ( element.getJacobian()[i].jacobianInv[1][0] *el.getKsi().get(i).valueN1  ) + ( element.getJacobian()[i].jacobianInv[1][1] * el.getEta().get(i).valueN1 ),
            ( element.getJacobian()[i].jacobianInv[1][0] *el.getKsi().get(i).valueN2  ) + ( element.getJacobian()[i].jacobianInv[1][1] *el.getEta().get(i).valueN2 ),
            ( element.getJacobian()[i].jacobianInv[1][0] *el.getKsi().get(i).valueN3  ) + ( element.getJacobian()[i].jacobianInv[1][1] * el.getEta().get(i).valueN3),
            ( element.getJacobian()[i].jacobianInv[1][0] * el.getKsi().get(i).valueN4  ) + ( element.getJacobian()[i].jacobianInv[1][1] * el.getEta().get(i).valueN4 )
            };


        HMatrixIntegration
                ((new GaussTable(Data.DATA_points).quadrat.weight[(i % Data.DATA_points)] *
                        new GaussTable(Data.DATA_points).quadrat.weight[(i / Data.DATA_points)]),
                i, dn_dX, dn_dY, element);
    }
    }

    private void HMatrixIntegration(double weight, int point, double[] dn_dX, double[] dn_dY, Element element) {
        double tmp[][]= new double[4][4];
        for(int l = 0; l < 4; l++) {
            for(int p = 0; p < 4; p++) {
                tmp[l][p] +=
                        ((dn_dX[l] * dn_dX[p]) + (dn_dY[l] * dn_dY[p]))
                                * weight * Data.CONST_K
                                * element.getJacobian()[point].getDetJacobian();
                this.H[l][p] += tmp[l][p];
            }
        }
    }
    public void printHMatrix(){
        for(int p = 0; p<4; p++){
            for(int k = 0; k<4; k++){
                System.out.print((H[p][k]) + " ");
            }
            System.out.println();
        };
        System.out.println("#############################################################################");
    }


}
