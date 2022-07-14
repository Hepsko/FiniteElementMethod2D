package schemas;

public class Result {

    public double valueN1;
    public  double valueN2;
    public  double valueN3;
    public double valueN4;

    public Result(double valueN1, double valueN2, double valueN3, double valueN4) {
        this.valueN1 = valueN1;
        this.valueN2 = valueN2;
        this.valueN3 = valueN3;
        this.valueN4 = valueN4;
    }

    public void printResults(){
        System.out.print(valueN1 + " ");
        System.out.print(valueN2 + " ");
        System.out.print(valueN3 + " ");
        System.out.print(valueN4+ " ");
        System.out.println();
    }
}