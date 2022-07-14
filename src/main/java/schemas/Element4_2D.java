package  schemas;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Element4_2D {
    List<Result> ksi = new ArrayList<Result>();
    List<Result> eta = new ArrayList<Result>();
    public  void calculateShapeFunctions(GaussTable t) {
        for(int i = 0; i <t.quadrat.pointsNum; i++) {
            for(int j = 0; j < t.quadrat.pointsNum; j++) {
                ksi.add(new Result( -(1.0 / 4.0) * (1 - t.quadrat.points[i]), (1.0 / 4.0) * (1 -  t.quadrat.points[i] ),
                        (1.0 / 4.0) * (1 + t.quadrat.points[i]  ), -(1.0 / 4.0) * (1 + t.quadrat.points[i])));
                eta.add(new Result(-(1.0 / 4.0) * (1 - t.quadrat.points[j]),-(1.0 / 4.0) * (1 + t.quadrat.points[j]),
                        (1.0 / 4.0) * (1 + t.quadrat.points[j] ),(1.0 / 4.0) * (1 -t.quadrat.points[j])));
            }
        }
    };
}