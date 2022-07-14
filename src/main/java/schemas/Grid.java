package schemas;
import data.Data;
import lombok.*;
import services.*;

import java.util.Arrays;

import static utils.Utils.gaussEquation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
 public class Grid{
    public  double h;
    public  double b;
    public  int nH;
    public int nB;
    public int nN;
    public int nE;
    public Node[] nodes ;
    public Element[] elements;
    public Grid(double h, double b, int nH, int nB) {
        this.h = h;
        this.b = b;
        this.nH = nH;
        this.nB = nB;
        this.nN = (int)(this.nH * this.nB);
        this.nE = (int)((this.nH - 1) * (this.nB - 1));
        this.nodes=  new Node[nH *nB];
        this.elements = new Element[(nH-1) * (nB-1)];
        for( int i = 0; i < nH *nB; i++){
            this.nodes[i] = new Node();
        }
        for( int i = 0; i < (nH-1) * (nB-1); i++){
            this.elements[i] = new Element();
        }
        for (int i = 0; i < nB; i++){
            for (int j=0; j< nH; j++){
                boolean bc = (b / (nB - 1)) * i == 0.0 || (h / (nH - 1)) * j == 0.0 || (b / (nB - 1)) * i == this.b || (h / (nH - 1)) * j == this.h;
                nodes[(i* nH) + j].setId( i * nH + j);
                nodes[(i* nH) + j].setX((b/ (nB -1)) *i);
                nodes[(i* nH) + j].setY((h/ (nH -1)) *j);
                nodes[(i* nH) + j].setBc(bc);
                nodes[(i* nH) + j].setTempT0(Data.CONST_T_0);

            }
        }
        for (int i = 0; i< (nB-1); i++){
            for (int j = 0; j< (nH-1); j++){
                elements[i* (nH -1) +j].id[0] = (i * nH) + j + 1;
                elements[i* (nH -1) +j].id[1] = elements[i* (nH -1) +j].id[0] + nH ;
                elements[i* (nH -1) +j].id[2] = elements[i* (nH -1) + j].id[1] + 1;
                elements[i* (nH -1) +j].id[3] = elements[i* (nH -1) + j].id[0] + 1;
            }
        }

    }

    public Grid( Node[] nodes, Element[] elements, int[] bc) {

        this.nN = nodes.length;
        this.nE = elements.length;
        this.nodes=  nodes;
        this.elements =elements;


            for (Node n : nodes){
               n.setTempT0(Data.CONST_T_0);

            }


          for (int bcEl : bc){
              nodes[bcEl].setBc(true);
          }

    }





    public void changeTemp(){
        //SYMULACJA

    //OBJ DECLARATION
        Element4_2D el = new Element4_2D();
        el.calculateShapeFunctions(new GaussTable(Data.DATA_points));
        CalculateAggregation Agr = new CalculateAggregation(nodes.length);
        ExpandedMatrix exp = new ExpandedMatrix(nodes.length);
        for(int i = 0; i < this.nE ; i++ ) {

    //OBJ DECLARATION
            Jacobian[] jacobian = new Jacobian[(int)Math.pow(Data.DATA_points,2)];
            CalculateHbcAndPMatrix hbc = new CalculateHbcAndPMatrix();
            CalculateMatrixH H = new CalculateMatrixH();
            CalculateCMatrix C = new CalculateCMatrix();
            Jacobian[] jacTab = new Jacobian[(int)Math.pow(Data.DATA_points,2)];

            for(int a = 0; a < Math.pow(Data.DATA_points, 2); a++) {
                jacobian[a] = new Jacobian();
                jacobian[a].generateJacobian(i, a, el, this);
                jacTab[a] = jacobian[a];


            }

            this.elements[i].setJacobian(jacTab);
            H.CalculateMatrixH( el, elements[i]);
            hbc.HbcAndPMatrix( this , i);
            C.CMatrix(elements[i]);
            this.elements[i].setH(H.H);
            this.elements[i].setHbc(hbc.getHBC());
            this.elements[i].setP(hbc.getP());
            this.elements[i].setC(C.getC());
            Agr.aggregateHAndPAndCMatrix(elements[i], this);
            exp.createExpandMatrix(Agr.getHGlobal(), Agr.getP());

    //DEBUG PRINTS

          //  H.printHMatrix();
      // hbc.printHBCMatrix();
           //hbc.printPMatrix();
          //   C.printC();
        }


    //DEBUG PRINTS
    //  Agr.printHGlobal();
  // Agr.printPGlobal();
        // exp.printExpandedMatirix();
        // Agr.printHGlobal();
        // Agr.printPGlobal();
      //   Agr.printCGlobal();
        // System.out.println(Arrays.deepToString(Agr.getHGobal()));


        double[] temperatures = gaussEquation(exp.getMatrixExpand());

        int tempQuantity = temperatures.length;
        for (int j = 0; j < tempQuantity; j++) {
//            System.out.println(temperatures[j]);
            this.nodes[j].setTempT0(temperatures[j]);
        }
        double minTemperature =  (Arrays.stream(temperatures).min().isPresent() ? Arrays.stream(temperatures).min().getAsDouble() : 0 );
        double maxTemperature =  (Arrays.stream(temperatures).max().isPresent() ? Arrays.stream(temperatures).max().getAsDouble() : 0 );
     System.out.print("MIN: " + minTemperature);
     System.out.print("      ");
     System.out.println("MAX: " + maxTemperature);


    }

}