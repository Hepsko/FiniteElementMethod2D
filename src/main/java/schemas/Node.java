package schemas;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Node {
    private int id;
    private double x;
    private double y;
    private double tempT0;
    private boolean bc = false;


    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
}