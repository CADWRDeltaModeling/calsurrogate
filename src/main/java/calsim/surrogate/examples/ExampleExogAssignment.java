package calsim.surrogate.examples;

import calsim.surrogate.ExogTimeSeriesAssignment;

/**
 * Enum containing example exogenous assignments for testing and demonstration.
 * To get a list use Arrays.asList(ExampleExogAssignment.TIDE.getAssignment());
 */
public enum ExampleExogAssignment {
    
    /**
     * Exogenous assignment for tide.
     * File name: "sf_tide.csv"
     * File column: "sf_tidal_range"
     * ANN input: "tide"
     */
    TIDE("calsim/surrogate/sf_tide.csv", "sf_tidal_range", "serving_default_tide_input:0");

    private final ExogTimeSeriesAssignment assignment;

    private ExampleExogAssignment(String fileName, String fileColumn, String annInputName) {
        this.assignment = new ExogTimeSeriesAssignment(fileName, fileColumn, annInputName);
    }

    /**
     * Returns the example exogenous assignment.
     *
     * @return the ExogTimeSeriesAssignment instance.
     */
    public ExogTimeSeriesAssignment getAssignment() {
        return assignment;
    }
}