package calsim.surrogate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RunRecord {


	private int year;
    private int month;
    private int cycle;
    // Array of most recent feature values (from batch 0)
    private double[] floatInputs;
    // Context-specific integer (e.g., from static codes in SalinitySurrogateManager)
    private int intContext;
    // Additional context integers: intInput0 is for location (or 0 if not location-specific),
    // intInput1 for other context such as nbatch/nth.
    private int intInput0;
    private int intInput1;
    private int aveType;
    private Surrogate surrogate;

    /**
     * Primary constructor.
     *
     * @param surrogate    The surrogate model. Its getNFeatures() will be used to check the inputs.
     * @param floatInputs  Array of the most recent feature values.
     * @param intInput0    Typically stores location (or zero if not location-specific).
     * @param intInput1    Additional context (such as nbatch/nth).
     * @param intContext   Context-specific code (from static codes).
     * @param year         Year of evaluation.
     * @param month        Month of evaluation.
     * @param cycle        Cycle (will be zero for now).
     * @param aveType      Averaging type.
     * @throws IllegalArgumentException if floatInputs length does not match surrogate.getNFeatures().
     */
    public RunRecord(Surrogate surrogate, double[] floatInputs, int intInput0, int intInput1, int intContext,
                     int year, int month, int cycle, int aveType) {
        this.surrogate = surrogate;
        if (surrogate != null && floatInputs.length != surrogate.getNFeatures()) {
            throw new IllegalArgumentException("Mismatch: input features (" + floatInputs.length 
                    + ") vs surrogate expected (" + surrogate.getNFeatures() + ").");
        }
        this.floatInputs = floatInputs;
        this.intInput0 = intInput0;
        this.intInput1 = intInput1;
        this.intContext = intContext;
        this.year = year;
        this.month = month;
        this.cycle = cycle;
        this.aveType = aveType;
    }

    /**
     * Convenience constructor that accepts an ArrayList of double[][] and extracts the latest feature values.
     *
     * @param surrogate    The surrogate model.
     * @param inputs       ArrayList of double[][], one per feature.
     * @param intInput0    Typically stores location (or zero if not location-specific).
     * @param intInput1    Additional context (such as nbatch/nth).
     * @param intContext   Context-specific code.
     * @param year         Year of evaluation.
     * @param month        Month of evaluation.
     * @param cycle        Cycle (will be zero for now).
     * @param aveType      Averaging type.
     */
    public RunRecord(Surrogate surrogate, ArrayList<double[][]> inputs, int intInput0, int intInput1, int intContext,
                     int year, int month, int cycle, int aveType) {
        this(surrogate, extractLatestFeatures(inputs), intInput0, intInput1, intContext, year, month, cycle, aveType);
    }

    /**
     * Static helper to extract the most recent feature values from an ArrayList of double[][].
     * Each element of the list represents one feature with shape [nbatch][nhist]; the method returns
     * the [0][0] entry (most recent) for each feature.
     *
     * @param inputs ArrayList of double[][].
     * @return double[] array of latest feature values.
     * @throws IllegalArgumentException if any input is null or empty.
     */
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
                Arrays.equals(floatInputs, other.floatInputs) &&
                Objects.equals(surrogate, other.surrogate);
    }

    @Override
    public String toString() {
         String surrogateStr = (surrogate != null) ? surrogate.toString() : "null";
         return "RunRecord[" +
                "year=" + year +
                ", month=" + month +
                ", cycle=" + cycle +
                ", intContext=" + intContext +
                ", intInput0=" + intInput0 +
                ", intInput1=" + intInput1 +
                ", aveType=" + aveType +
                ", floatInputs=" + Arrays.toString(floatInputs) +
                ", surrogate=" + surrogateStr +
                "]";
    }
    
    public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}
    
}
