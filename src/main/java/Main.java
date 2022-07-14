import data.Data;
import schemas.Element4_2D;
import schemas.Grid;
import schemas.Jacobian;
import services.CalculateMatrixH;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
     Grid grid = new Grid(Data.GRID_H, Data.GRID_B, Data.GRID_NH, Data.GRID_NB);
    Grid grid2 = new Grid( Data.mixGridNodes, Data.mixGridElements, Data.mixGridBC);
        System.out.println("4x4 standard");
        for(double i = Data.DELTA_T; i <= Data.TIME; i+=Data.DELTA_T) {
            grid.changeTemp();
         }
        System.out.println("4x4 mixGrid");
        for(double i = Data.DELTA_T; i <= Data.TIME; i+=Data.DELTA_T) {
            grid2.changeTemp();
        }
    }
}
