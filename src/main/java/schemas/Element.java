package schemas;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import data.Data;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Element {

    public int id[] = new  int[4];
    private double[][] H = new double[4][4];
    private double[][] C = new double[4][4];
    private double[][] Hbc = new double[4][4];
    private double[] P = new double[4];
    private Jacobian jacobian[];

    public Element(int[] id) {
        this.id = id;
    }
}
