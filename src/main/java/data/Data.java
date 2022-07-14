package data;


import schemas.Element;
import schemas.Node;

public  class Data {
    //  Punkty całkowani,
    public  static int DATA_points  = 3;

    //  izotoropwy współczynnik przewodzenia ciepła,
    public  static double CONST_K  = 25.0;

    //   Stała temperaturowa T1 - maksymalna temperatura końcowa
    public  static double[] CONST_T_1 = new double[] {1200.0, 1200.0, 1200.0, 1200.0};

    //   Stała temperaturowa T0 - temperatura początkowa
    public   static double CONST_T_0 = 100;

    //   Współczynnik konwekcyjnej wymiany ciepła
    public   static double ALFA = 300.0;

    //   Zmiana czasu
    public   static double DELTA_T = 50.0;

    //  Wysokość siatki
    public static double  GRID_H = 0.1;

    // Szerokość siatki
    public static double GRID_B =  0.1;


    //Ilość węzłów w osi Y
    public static int GRID_NH =  4;


    //  Ilość węzłów w osi Y
    public static int GRID_NB = 4;
    //gestosc
    public static double RO =  7800.0;
    //cieplo wlasciwe
    public static double C =  700.0;

    //  Czas operacji
    public static double TIME = 500.0;


    // dane  dla MixGrid
    public static Node[]  mixGridNodes = new Node[]{
            new Node(0,  0.100000001, 0.00499999989),
            new Node( 1, 0.0546918176, 0.00499999989),
            new Node( 2, 0.0226540919, 0.00499999989),
            new Node( 3,0., 0.00499999989),
            new Node( 4,0.100000001,-0.0403081849),
            new Node(5, 0.0623899326, -0.0326100662),
            new Node(6, 0.0303522106, -0.0253522098),
            new Node(7,0.,-0.0176540911),
            new Node( 8,  0.100000001, -0.072345905),
            new Node(9,  0.069647789, -0.0646477863),
            new Node(10, 0.0376100652, -0.0573899336),
            new Node(11,0., -0.0496918149),
            new Node(12,  0.100000001, -0.0949999988),
            new Node(13, 0.0773459077, -0.0949999988),
            new Node(14, 0.0453081839, -0.0949999988),
            new Node(15,0., -0.0949999988),
    };

    public  static Element [] mixGridElements = new Element[]{
            new Element(new int[]{1,  2,  6,  5}),
            new Element(new int[]{2,  3,  7,  6}),
            new Element(new int[]{3,  4,  8,  7}),
            new Element(new int[]{5,  6, 10,  9}),
            new Element(new int[]{6,  7, 11, 10}),
            new Element(new int[]{7,  8, 12, 11}),
            new Element(new int[]{9, 10, 14, 13}),
            new Element(new int[]{10, 11, 15, 14}),
            new Element(new int[]{11, 12, 16, 15}),

    };

    public  static int[] mixGridBC = new int[]{
            0, 1, 2, 3, 4, 7, 8, 11, 12, 13, 14, 15
    };
}





