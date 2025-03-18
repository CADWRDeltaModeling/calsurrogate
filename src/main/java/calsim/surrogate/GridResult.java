package calsim.surrogate;

import java.util.ArrayList;

public class GridResult {
    private double[] gridInput0;
    private double[] gridInput1;
    private ArrayList<double[][]> monthlyInputs;
    private double[][][] result;  // nsac x nexp x nANNLocations

    public GridResult(double[] gridInput0, double[] gridInput1, ArrayList<double[][]> monthlyInputs) {
        this.gridInput0 = gridInput0;
        this.gridInput1 = gridInput1;
        this.monthlyInputs = monthlyInputs;
    }

    public double[] getGridInput0() {
        return gridInput0;
    }

    public double[] getGridInput1() {
        return gridInput1;
    }

    public ArrayList<double[][]> getMonthlyInputs() {
        return monthlyInputs;
    }

    public double[][][] getResult() {
        return result;
    }

    public void setResult(double[][][] resultFullDim) {
        this.result = resultFullDim;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        double[] x0 = getGridInput0();   // Array of x0 values
        double[] x1 = getGridInput1();   // Array of x1 values
        double[][][] res = getResult();  // 3D array: [i][j][stationIndex]
        
        // Choose the output index for display; for example, site index 0.
        int siteIndex = 8;
        
        // Iterate over all combinations of x0 and x1.
        for (int i = 0; i < x0.length; i++) {
            for (int j = 0; j < x1.length; j++) {
                sb.append("x0=").append(x0[i])
                  .append(" x1=").append(x1[j])
                  .append(" res=");
  
                // Check if the value is unset (assumed to be NaN)
                if (res == null) {
                    sb.append("NA");
                } else {
                	double value = res[i][j][siteIndex];
                    sb.append(value);
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }    
}