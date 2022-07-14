package schemas;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Jacobian {

    public double jacobian[][] = new double[2][2];
    public double jacobianInv[][] =  new double[2][2];
    public double detJacobian = 0;
    public void printJacobian(){
        for(int p = 0; p<2; p++){
            for(int k = 0; k<2; k++){
                System.out.print(jacobian[p][k] + " ");
            }
            System.out.println();
        }
    }

    public void generateJacobian(int i, int j, Element4_2D element, Grid grid) {
        jacobian[0][0] =
                element.getKsi().get(j).valueN1 * grid.nodes[grid.elements[i].id[0]-1].getX()
                        + element.getKsi().get(j).valueN2 * grid.nodes[grid.elements[i].id[1]-1].getX()
                        + element.getKsi().get(j).valueN3 * grid.nodes[grid.elements[i].id[2]-1].getX()
                        + element.getKsi().get(j).valueN4 * grid.nodes[grid.elements[i].id[3]-1].getX();

        jacobian[0][1] =
                element.getEta().get(j).valueN1 * grid.nodes[grid.elements[i].id[0]-1].getX()
                        +   element.getEta().get(j).valueN2 * grid.nodes[grid.elements[i].id[1]-1].getX()
                        +   element.getEta().get(j).valueN3 * grid.nodes[grid.elements[i].id[2]-1].getX()
                        +   element.getEta().get(j).valueN4 * grid.nodes[grid.elements[i].id[3]-1].getX();


        jacobian[1][0] =
                element.getKsi().get(j).valueN1 * grid.nodes[grid.elements[i].id[0]-1].getY()
                        +  element.getKsi().get(j).valueN2 * grid.nodes[grid.elements[i].id[1]-1].getY()
                        + element.getKsi().get(j).valueN3 * grid.nodes[grid.elements[i].id[2]-1].getY()
                        + element.getKsi().get(j).valueN4 * grid.nodes[grid.elements[i].id[3]-1].getY();



        jacobian[1][1] =
                element.getEta().get(j).valueN1 * grid.nodes[grid.elements[i].id[0]-1].getY()
                        +   element.getEta().get(j).valueN2 * grid.nodes[grid.elements[i].id[1]-1].getY()
                        +  element.getEta().get(j).valueN3 * grid.nodes[grid.elements[i].id[2]-1].getY()
                        +  element.getEta().get(j).valueN4 * grid.nodes[grid.elements[i].id[3]-1].getY();

        detJacobian = (jacobian[0][0] * jacobian[1][1]) - (jacobian[1][0] * jacobian[0][1]);
        jacobianInv[0][0] = jacobian[1][1] / detJacobian;
        jacobianInv[1][1] = jacobian[0][0] / detJacobian;
        jacobianInv[1][0] = - jacobian[0][1] / detJacobian;
        jacobianInv[0][1] = - jacobian[1][0] / detJacobian;

    }

};