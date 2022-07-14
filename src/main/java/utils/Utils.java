package utils;

public class Utils {
    public static double roundTpDecimal(double val){
        return Math.round(val * 100.0) / 100.0;
    }

    public static double[] gaussEquation(double[][] arrayToEquation)
    {
        int n = arrayToEquation[0].length -1;

        double[] x  = new double[n];
        for(int k=0; k <= n - 1; k++)
        {
            for(int i = k + 1; i < n; i++)
            {
                double p = arrayToEquation[i][k]/arrayToEquation[k][k];
                for(int j = k; j <= n; j++)
                {
                    arrayToEquation[i][j] -= (p * arrayToEquation[k][j]);
                }
            }
        }

        x[n - 1] = arrayToEquation[n - 1][n] / arrayToEquation[n - 1][n - 1];
        for(int i = n - 2; i >= 0; i--)
        {
            double s = 0;
            for(int j = i + 1; j < n; j++)
            {
                s += (arrayToEquation[i][j] * x[j]);
                x[i] = (arrayToEquation[i][n]-s) / arrayToEquation[i][i];
            }
        }

        return x;
    }
}
