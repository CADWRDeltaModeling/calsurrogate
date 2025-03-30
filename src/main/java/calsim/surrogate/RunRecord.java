package calsim.surrogate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RunRecord {

    private int year;
    private int month;
    private int cycle;
    private double[] floatInputs;
    private int intContext;
    private int intInput0;
    private int intInput1;
    private int aveType;
    private double aveParam;
    private Surrogate surrogate;

    public static int LOC_UNSPEC = -1;
    public static final double NO_AVG_PARAM = 0.0;

    public RunRecord(Surrogate surrogate, double[] floatInputs, int intInput0, int intInput1, int intContext,
                     int year, int month, int cycle, int aveType) {
        this(surrogate, floatInputs, intInput0, intInput1, intContext, year, month, cycle, aveType, NO_AVG_PARAM);
    }

    public RunRecord(Surrogate surrogate, double[] floatInputs, int intInput0, int intInput1, int intContext,
                     int year, int month, int cycle, int aveType, double aveParam) {
        this.surrogate = surrogate;
        if (surrogate != null && floatInputs.length != surrogate.getNFeatures()) {
            throw new IllegalArgumentException("Mismatch: input features (" + floatInputs.length 
                    + ") vs surrogate expected (" + surrogate.getNFeatures() 
                    + " for surrogate " + surrogate.getName()+ " ).");
        }
        this.floatInputs = floatInputs;
        this.intInput0 = intInput0;
        this.intInput1 = intInput1;
        this.intContext = intContext;
        this.year = year;
        this.month = month;
        this.cycle = cycle;
        this.aveType = aveType;
        this.aveParam = aveParam;
    }

    public RunRecord(Surrogate surrogate, ArrayList<double[][]> inputs, int intInput0, int intInput1, int intContext,
                     int year, int month, int cycle, int aveType) {
        this(surrogate, extractLatestFeatures(inputs), intInput0, intInput1, intContext, year, month, cycle, aveType);
    }

    public RunRecord(Surrogate surrogate, ArrayList<double[][]> inputs, int intInput0, int intInput1, int intContext,
                     int year, int month, int cycle, int aveType, double aveParam) {
        this(surrogate, extractLatestFeatures(inputs), intInput0, intInput1, intContext, year, month, cycle, aveType, aveParam);
    }

    public static double[] extractLatestFeatures(ArrayList<double[][]> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            return new double[0];
        }
        double[] features = new double[inputs.size()];
        for (int i = 0; i < inputs.size(); i++) {
            double[][] data = inputs.get(i);
            if (data == null || data.length == 0 || data[0].length == 0) {
                throw new IllegalArgumentException("Input for feature " + i + " is empty or null.");
            }
            features[i] = data[0][0];
        }
        return features;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public double getAveParam() {
        return aveParam;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + year;
        result = prime * result + month;
        result = prime * result + cycle;
        result = prime * result + intContext;
        result = prime * result + intInput0;
        result = prime * result + intInput1;
        result = prime * result + aveType;
        result = prime * result + Arrays.hashCode(floatInputs);
        long temp = Double.doubleToLongBits(aveParam);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (surrogate == null ? 0 : surrogate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        RunRecord other = (RunRecord) obj;
        return year == other.year &&
               month == other.month &&
               cycle == other.cycle &&
               intContext == other.intContext &&
               intInput0 == other.intInput0 &&
               intInput1 == other.intInput1 &&
               aveType == other.aveType &&
               Double.doubleToLongBits(aveParam) == Double.doubleToLongBits(other.aveParam) &&
               Arrays.equals(floatInputs, other.floatInputs) &&
               Objects.equals(surrogate, other.surrogate);
    }

    @Override
    public String toString() {
        String surrogateStr = (surrogate != null) ? surrogate.getName() : "null";
        return "RunRecord[" +
               "year=" + year +
               ", month=" + month +
               ", cycle=" + cycle +
               ", intContext=" + intContext +
               ", intInput0=" + intInput0 +
               ", intInput1=" + intInput1 +
               ", aveType=" + aveType +
               ", aveParam=" + aveParam +
               ", floatInputs=" + Arrays.toString(floatInputs) +
               ", surrogate=" + surrogateStr +
               "]";
    }
}
