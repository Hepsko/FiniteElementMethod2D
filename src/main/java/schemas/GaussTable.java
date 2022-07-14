package schemas;

public class GaussTable{

    public Quadrat quadrat;
    public  GaussTable(int i)
    {
        if (i == 2 ){
            this.quadrat  =  new Quadrat(new double[]{-1.0 / Math.sqrt(3), 1 / Math.sqrt(3) }, new double[]{1,1}, 2);
        }
        if (i == 3 ){
            this.quadrat  =  new Quadrat(new double[]{-Math.sqrt(3.0 / 5.0), 0.0, Math.sqrt(3.0 / 5.0) }, new double[]{5.0 / 9.0, 8.0 / 9.0, 5.0 / 9.0}, 3);
        }
        if (i == 4 ){
            this.quadrat  =  new Quadrat(new double[]{-0.861136, -0.339981, 0.339981, 0.861136}, new double[]{0.347855, 0.652145, 0.652145, 0.347855}, 4);
        }
    }


}

