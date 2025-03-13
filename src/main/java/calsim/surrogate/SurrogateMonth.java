package calsim.surrogate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;


/**
 * Apply daily surrogate by disaggregating input, running the surrogate and
 * summarizing to a monthly statistic. The full workflow is shown in *
 * <img src="./doc-files/ANNMonth.png" alt='ANN Month design' width=600 />.
 */
public class SurrogateMonth {

	private DisaggregateMonths[] disagg;
	private AggregateMonths agg;
	public AggregateMonths getAgg() {
		return agg;
	}

	private Surrogate daily;
    private List<ExogTimeSeriesAssignment> assignments;
    private DisaggregateMonths firstNonNullDisagg; // non-null used for length calculations

    /**
     * Constructs a SurrogateMonth
     * All null entries in the assignments list are replaced by UNASSIGNED.
     *
     * @param disagg      Array of disaggregators (at least one must be non-null)
     * @param daily       The surrogate (ANN) that performs the estimation
     * @param agg         Aggregator for monthly values
     * @param assignments List of exogenous assignments. nulls will be reassigned to UNASSIGNED.
     */
    public SurrogateMonth(DisaggregateMonths[] disagg, 
            Surrogate daily, 
            AggregateMonths agg,
            List<ExogTimeSeriesAssignment> assignments) {

    	boolean allNull = true;

    	for (DisaggregateMonths d : disagg) {
    		if (d != null) {
    			allNull = false;
    			firstNonNullDisagg = d;
    		}
    	}
    	if (allNull) {
    		throw new NullPointerException(
    				"Not all disaggregators can be null. If using all exogenous data, use a dummy disaaggregator for one member");
    	}
    	
        // Validate that all DisaggregateMonths have the same history length (nMonth)
        validateDisaggregateMonths(disagg);
        
    	this.disagg = disagg;
    	this.daily = daily;
    	this.agg = agg;

        // If the entire assignments list is null, use default assignments.
        if (assignments == null) {
            assignments = createDefaultAssignments();
        }    	
    	
    	// Replace any null assignments with UNASSIGNED.
    	for (int i = 0; i < assignments.size(); i++) {
    	    if (assignments.get(i) == null) {
    	        assignments.set(i, ExogTimeSeriesAssignment.UNASSIGNED);
    	    }
    	}
    	this.assignments = assignments;

    	// Check and resolve names/indexes for all concrete assignments.
    	resolveAssignments(this.assignments, ExogenousTimeSeries.getInstance(), daily); 	

    }

    /**
     * Second constructor with all UNASSIGNED time series.
     *
     * @param disagg Array of disaggregators (at least one must be non-null)
     * @param daily  The surrogate (ANN) that performs the estimation
     * @param agg    Aggregator for monthly values
     */
    public SurrogateMonth(DisaggregateMonths[] disagg, Surrogate daily, AggregateMonths agg) {
        // Delegate to the main constructor with a null assignments list.
        this(disagg, daily, agg, null);
    }

    
    /**
     * Creates a default assignments list that contains a single UNASSIGNED assignment.
     *
     * @return List of ExogTimeSeriesAssignment with default UNASSIGNED entries.
     */
    private static List<ExogTimeSeriesAssignment> createDefaultAssignments() {
        List<ExogTimeSeriesAssignment> defaultList = new ArrayList<>();
        defaultList.add(ExogTimeSeriesAssignment.UNASSIGNED);
        return defaultList;
    }    
    
    /**
     * Resolves all exogenous time series assignments by checking file name consistency and
     * querying the necessary indices from ExogenousTimeSeries and the surrogate.
     *
     * @param assignments List of exogenous assignments.
     * @param ets         The singleton instance of ExogenousTimeSeries.
     * @param surrogate   The surrogate used to resolve ANN input indices.
     */
    private void resolveAssignments(List<ExogTimeSeriesAssignment> assignments, 
                                    ExogenousTimeSeries ets, 
                                    Surrogate surrogate) {
        String expectedFileName = ets.getFileName();
        for (ExogTimeSeriesAssignment assign : assignments) {
            if (assign != ExogTimeSeriesAssignment.UNASSIGNED) {
                // Check file name consistency.
                //if (!assign.getFileName().equals(expectedFileName)) {
                //    throw new IllegalArgumentException("Assignment file name " + assign.getFileName() +
                //        " does not match expected " + expectedFileName);
                //}
                // Resolve file column index.
                int colIndex = ets.colIndexForVariable(assign.getFileColumn());
                assign.setFileColumnIndex(colIndex);
                // Resolve ANN input index.
                String assignedInput = assign.getAnnInputName();
                int annIndex = surrogate.getInputIndex(assignedInput);
                if (annIndex < 0) {
                    throw new IllegalArgumentException("ANN input name " + assign.getAnnInputName() + 
                            " not found in surrogate.");
                }
                assign.setAnnInputIndex(annIndex);
            }
        }
    }    
    
    /**
     * Validates that all non-null DisaggregateMonths in the array have the same history length (nMonth).
     *
     * @param disagg Array of DisaggregateMonths to validate.
     * @throws IllegalArgumentException if any non-null DisaggregateMonths have a different history length.
     */
    private void validateDisaggregateMonths(DisaggregateMonths[] disagg) {
        int expectedHistoryLength = firstNonNullDisagg.getNMonth();
        for (DisaggregateMonths d : disagg) {
            if (d != null && d.getNMonth() != expectedHistoryLength) {
                throw new IllegalArgumentException("Mismatch in DisaggregateMonths history lengths. " +
                        "Expected: " + expectedHistoryLength + ", but found: " + d.getNMonth());
            }
        }
    }    

	public int bufferNDay(int year, int month) {
		return firstNonNullDisagg.getNDay(year, month);
	}

	/**
	 * Loads exogenous daily input data for the specified variable into a batch of arrays.
	 *
	 * <p>This method extracts a daily slice of exogenous data from the singleton instance of
	 * {@link ExogenousTimeSeries} for the variable corresponding to {@code ivar}. Instead of always
	 * using the current month as the starting point, the method calculates the start date based on the
	 * multi-month history length specified by the first non-null disaggregator. For instance, if the history
	 * spans 5 months and the current month is May 2025, the exogenous data will be fetched starting from
	 * January 1, 2025. The total length of the data fetched is {@code nDay}, which should match the
	 * length of the disaggregated daily history.</p>
	 *
	 * <p>The fetched exogenous slice is then copied identically across all batch members, ensuring that the
	 * unnamed source of variation is handled correctly.</p>
	 *
	 * @param arr a two-dimensional array where each sub-array represents a batch of daily exogenous data.
	 *            On method exit, each sub-array will contain the same daily exogenous data slice.
	 * @param ivar the index of the surrogate input corresponding to the exogenous variable.
	 *             This is used to determine the correct column in the exogenous time series.
	 * @param year the current year (associated with the current month, which is the final month in the history).
	 * @param month the current month (1-based) which is the last month in the multi-month history.
	 * @param nDay the total number of days spanning the multi-month history (as computed by the disaggregator).
	 *
	 * @see ExogenousTimeSeries#dailyDataSlice(int, int, int, int, int)
	 * @see #getExogInputIndex(int)
	 * @see #bufferNDay(int, int)
	 */
	public void loadExogenous(double[][] arr, int ivar, int year, int month, int nDay) {
		
        ExogTimeSeriesAssignment assign = getAssignmentForAnnInput(ivar);
        if (assign == ExogTimeSeriesAssignment.UNASSIGNED) {
            throw new IllegalArgumentException("No exogenous assignment found for ANN input " + ivar);
        }
        int fileColIndex = assign.getFileColumnIndex();
        
        // Verify file name consistency.
        String expectedFileName = ExogenousTimeSeries.getInstance().getFileName();
        //if (!assign.getFileName().equals(expectedFileName)) {
        //    throw new IllegalArgumentException("Assignment file name " + assign.getFileName() +
        //        " is inconsistent with expected " + expectedFileName);
        //}        
        
	    int nbatch = arr.length; // array will end up being nBatch x nDay

	    // Compute the starting year and month based on the multi-month history.
	    // The current month (year, month) is the final month in the history.
	    // Thus, the starting month is (current month minus (nMonth - 1)) with day set to 1.
	    int monthsHistory = firstNonNullDisagg.getNMonth();
	    java.time.YearMonth startYM = java.time.YearMonth.of(year, month).minusMonths(monthsHistory - 1);
	    
	    // Fetch the exogenous daily slice matching timing of the file data and array at the beginning of the slice.
	    // The result of this fetch is a multi-month (usually 5) array of daily values. 
	    // For a five month file the result will be somewhere around 150 days.
	    // The index of the first of the current month will be somewhere around 30 values from the end around index 120
	    // You can get that index from the Disaggregation class.
	    double[] dailySlice = ExogenousTimeSeries.getInstance().dailyDataSlice(fileColIndex, 
	                                            startYM.getYear(), startYM.getMonthValue(), 1, nDay);
	    
	    // Copy the same exogenous slice to each member of the batch.
	    for (int j = 0; j < nbatch; j++) {
	        arr[j] = new double[dailySlice.length];
	        System.arraycopy(dailySlice, 0, arr[j], 0, dailySlice.length);
	    }
	}


	/**
	 * Takes output for which variation in variable 0 and 1 as a grid has been flattened 
	 * to a 1D batch and puts it back in 2D for each of the input dims.
	 * The third dimension in the output is for output dimensions (for instance
	 * if surrogate makes multivariate predictions)
	 * @param arr The flattened version, with dimensions for batch dim and output dims
	 * @param nx0 Number of gridded values of input variable 0, which will occupy index 0 in result
	 * @param nx1 Number of gridded values of input variable 1, which will be index 1 in result
	 */
	private double[][][] expandLinear(double[][] arr,int nx0, int nx1){
		int nBatch = arr.length;
		int nOut = arr[0].length;
		double[][][] out = new double[nx0][nx1][nOut];
		int iBatch = 0;
		for (int i = 0; i < nx0 ; i++) {
			for (int j=0; j < nx1; j++) {
				System.arraycopy(arr[iBatch], 0, out[i][j], 0, arr[iBatch].length);
				iBatch++;
			}
		}

		return out;
	}

	
    /**
     * Evaluate the surrogate on a grid defined by the bounds for the first two features.
     * This method first calls imposeGrid, then evaluates the surrogate and reshapes
     * the surrogate's output into a 3-D array with dimensions [nx0][nx1][timeSteps].
     */
    public GridResult evaluateOnGrid(ArrayList<double[][]> monthlyInputs, int year, int month,
                                     double loBound0, double hiBound0, int nx0,
                                     double loBound1, double hiBound1, int nx1) {
        // Impose the grid on the monthly inputs.
        GridResult grid = this.imposeGrid(monthlyInputs, loBound0, hiBound0, 
        		nx0, loBound1, hiBound1, nx1);

        // result has one batch member per grid member, second dim is ANN output location
       	double[][] result = this.annMonth(grid.getMonthlyInputs(), year, month);
       	// expand to ANN output location
    	double[][][] resultFullDim = expandLinear(result,nx0,nx1);
    	grid.setResult(resultFullDim); 
        //System.out.println("In evaluateOnGrid");
    	//System.out.println(grid.toString());
        return grid;
    }
	
    /**
     * Create a grid over the first two features of the monthlyInputs.
     * It generates gridInput0 and gridInput1 via linspace and then creates
     * new monthlyInputs where:
     * - Feature 0 is replaced by the corresponding value from gridInput0 (by row)
     * - Feature 1 is replaced by the corresponding value from gridInput1 (by column)
     * - Other features are replicated from the original (assumed to be 1×time)
     */
    public GridResult imposeGrid(ArrayList<double[][]> monthlyInputs,
                                 double loBound0, double hiBound0, int nx0,
                                 double loBound1, double hiBound1, int nx1) {
        double[] gridInput0 = linspace(loBound0, hiBound0, nx0);
        double[] gridInput1 = linspace(loBound1, hiBound1, nx1);

        int nBatchOrig = monthlyInputs.get(0).length;
        //System.out.println("Original batch size: "+ nBatchOrig);
        // Assume each feature in monthlyInputs is a 1 x time array.
        int time = monthlyInputs.get(0)[0].length;
        System.out.println("time "+time);
        int gridBatch = nx0 * nx1;
        ArrayList<double[][]> newMonthlyInputs = new ArrayList<>();
        System.out.println("Imposing");
        // For each feature, create a new array with gridBatch rows.
        for (int f = 0; f < monthlyInputs.size(); f++) {
            double[][] original = monthlyInputs.get(f);
            double[][] newFeature = new double[gridBatch][time];
            for (int i = 0; i < gridBatch; i++) {
                // Decode the grid row and column indices.
                int ix = i / nx1;
                int iy = i % nx1;
                for (int t = 0; t < time; t++) {
                    if ((f == 0) & (t==0)) {
                        // First feature gets its value from gridInput0 based on the row index.
                        newFeature[i][t] = gridInput0[ix];
                    } else if ((f == 1) & (t==0)) {
                        // Second feature gets its value from gridInput1 based on the column index.
                        newFeature[i][t] = gridInput1[iy];
                    } else {
                        // All other features remain the same (replicate the single row)
                        newFeature[i][t] = original[0][t];
                    }
                }
            }
            newMonthlyInputs.add(newFeature);
        }

        return new GridResult(gridInput0, gridInput1, newMonthlyInputs);
    }	
	
    
    
    
    
    
    
    
    // Helper function: create a linearly spaced array between lo and hi.
    private double[] linspace(double lo, double hi, int num) {
        double[] result = new double[num];
        double step = (hi - lo) / (num - 1);
        for (int i = 0; i < num; i++) {
            result[i] = lo + i * step;
        }
        return result;
    }    
    
    
    
    
    
    
    
	
	/**
	 * Complete application of the ANN or other surrogate. Disaggregates monthly
	 * inputs to daily, marches through days repackaging the inputs into the time
	 * aggregation/treatment expected by the ANN and applying the ANN output. Then
	 * re-aggregates to an array over the batch size with one monthly statistic per
	 * batch
	 * 
	 * @param monthlyInputs
	 * @param year
	 * @param month
	 * @param cycle
	 * @return 2D Array of monthly aggregated results with dimensions of batch size
	 *         by number of stations (output features) predicted
	 */
	public double[][] annMonth(ArrayList<double[][]> monthlyInputs, int year, int month) {
        
		ArrayList<double[][]> dailyInputs = new ArrayList<double[][]>();
		int nvar = monthlyInputs.size();
		int nbatch = monthlyInputs.get(0).length;
		int nday = this.disagg[0].getNDay(year, month); // Total number of days in history
		
		
		// Disaggregate monthly to daily for each feature/input. monthly comes in
		// reversed
		// but will be put forward in time
		for (int ivar = 0; ivar < nvar; ivar++) {
			double[][] newInput = new double[nbatch][];
			if (isExogenous(ivar)){
				loadExogenous(newInput,ivar,year,month,nday);
			}else {
				for (int jbatch = 0; jbatch < nbatch; jbatch++) {
					if (nbatch==30 & ivar < 4 ) {
   					  double[] dailyBatchVar = monthlyInputs.get(ivar)[jbatch];
   					  int lend = dailyBatchVar.length;
					  System.out.println("batch " +jbatch+" len "+lend+" 1st/Last sac/exp " 
   					  + ivar + ": "+dailyBatchVar[0]  +" "+ dailyBatchVar[1] 
   							  +" "+dailyBatchVar[lend-2]+ " " + dailyBatchVar[lend-1]);
					}
;					newInput[jbatch] = disagg[ivar].apply(year, month, monthlyInputs.get(ivar)[jbatch]);
				}
			}
			dailyInputs.add(newInput);			
		}

		//System.out.println("\n\n**********************\nDump "+nbatch);
		//DataDumper dump = new DataDumper();
	    //dump.dumpInputs(dailyInputs);
        
		// This is the starting datum for the sliding window below
		int indexStart = disagg[0].offsetFirstMonth(year, month);

		
		// Slide window on the daily inputs and generate daily output
		// The indexes in the output ArrayList represent stations or output locations
		// The first dimension of the double[][] represent the original input batches
		// The second index of the double[][], which will be collapsed in the next step,
		// represent days within the output where indexStart again represents the first of
		// the month
		ArrayList<double[][]> dailyOutputs = timeStep(dailyInputs, indexStart, year, month);
		
		// Process the output
		int nLoc = dailyOutputs.size(); // Number of output locations from ANN
		// daysIn Month should match the second double[nbatch][daysInMonth] Not sure why we recalculate
		// when we aggregate with something like a monthly average, this dimension will be reduced away
		int daysInMonth = numberOfDays(month, year); 
		double[][] monthlyOut = new double[nbatch][nLoc];

		//System.out.println("annMonth dims " + year + " "+month + " nLoc="+nLoc+" nbatch="+nbatch+" ");
		// Perform requested summary statistic that recovers a monthly value
		for (int iLoc = 0; iLoc < nLoc; iLoc++) {
			//System.out.println("iLoc="+iLoc+" "+" nbatch="+nbatch);
			for (int ibatch = 0; ibatch < nbatch; ibatch++) {
				monthlyOut[ibatch][iLoc] = agg.aggregate(dailyOutputs.get(iLoc)[ibatch], indexStart, 1, daysInMonth);
			}
		}

		return monthlyOut;
	}

	/**
	 * Processes a month’s worth of daily data into a format suitable for ANN evaluation and then
	 * converts the ANN output back into a daily output structure.
	 * <p>
	 * The method performs the following steps:
	 * <ol>
	 *   <li>Determines the number of days in the target month (using the provided year and month).
	 *   <li>Repackages the input daily data into a larger "batch" where each original batch entry is expanded 
	 *       to include a separate row for each day of the month.
	 *   <li>The original {@code dailyInputs} is an {@code ArrayList<double[][]>} where each element corresponds to a 
	 *       particular feature or variable. For each feature, the {@code double[][]} has dimensions:
	 *       <ul>
	 *         <li><b>nbatch</b> - the number of independent cases (or original batches)
	 *         <li><b>nhist</b> - the length of the historical time series (covering several months)
	 *       </ul>
	 *   <li>This method selects a window from the history starting at {@code startDayIndex} and spanning the number 
	 *       of days in the month, so that the new batch size becomes: <b>nBigBatch = nbatch * daysInMonth</b>.
	 *   <li>Each row in the new repackaged input (the "big input") is generated by calling
	 *       {@code daily.dailyToSurrogateInput()} on the original input for that batch and the appropriate offset.
	 *   <li>The repackaged data (an {@code ArrayList<double[][]>} with each element of dimensions 
	 *       <b>nBigBatch x ?</b> where "?" is the ANN input length) is passed to {@code daily.estimate()}, which 
	 *       returns ANN predictions as a {@code float[][]} with dimensions:
	 *       <ul>
	 *         <li><b>nBigBatch</b> x <b>nOutput</b>, where nOutput is the number of output stations.
	 *       </ul>
	 *   <li>The method then rebuilds an {@code ArrayList<double[][]>} of daily outputs such that:
	 *       <ul>
	 *         <li>Each element in the output list corresponds to a different output station.
	 *         <li>Each {@code double[][]} is dimensioned as: <b>nbatch x daysInMonth</b>,
	 *             where the outputs for each original batch are arranged contiguously by day.
	 *       </ul>
	 * </ol>
	 *
	 * @param dailyInputs an {@code ArrayList<double[][]>} where each element represents a feature with dimensions:
	 *                    nbatch x nhist. The data should cover multiple days, and the first index in the time dimension
	 *                    corresponds to the most recent time step.
	 * @param startDayIndex the starting index within the historical time series that corresponds to the first day
	 *                      of the month being processed.
	 * @param year the year for which the month’s data is being processed.
	 * @param month the month for which the daily data is to be transformed (e.g., 1 for January, 2 for February, etc.).
	 * @return an {@code ArrayList<double[][]>} where each element corresponds to one output station. Each
	 *         {@code double[][]} is arranged with dimensions nbatch x daysInMonth, containing the ANN-predicted
	 *         daily outputs.
	 */	
	public ArrayList<double[][]> timeStep(ArrayList<double[][]> dailyInputs, int startDayIndex, int year, int month) {
		int daysInMonth = disagg[0].daysMonth(year, month)[0];
		int nbatch = dailyInputs.get(0).length; // TODO safety check
		int nvar = dailyInputs.size();
		int stopIndex = startDayIndex + daysInMonth;
		
		
		
		// The advancing window of days of the month will be handled in TensorFlow
		// by including each  step as a separate batch index.
		// From this point on the batch size will
		// be the original batch size (for other reasons) times number of days of the month.
		// The batches are organized so that the advancing days of the month
		// within one original batch are contiguous.
		// There is also time structure in the second index.
		// This is the point where we transform the daily history into
		// any other averages or aggregations specific to the the surrogate
		ArrayList<double[][]> expandedDaily = new ArrayList<double[][]>();
		int nBigBatch = nbatch * daysInMonth; // The new larger batch size

		for (int ivar = 0; ivar < nvar; ivar++) {
			// dims: original nbatch size doesn't include time marching
			double[][] inputs = dailyInputs.get(ivar); 
			// dims: bigInput has batch with variations and also time marching 
			double[][] bigInput = new double[nBigBatch][];
			int iBatchBig = 0;
			for (int ibatch = 0; ibatch < nbatch; ibatch++){
				for (int ioff = startDayIndex; ioff < stopIndex; ioff++) {
					bigInput[iBatchBig] = daily.dailyToSurrogateInput(inputs[ibatch], ioff);
					iBatchBig++;
				}
			}
			expandedDaily.add(bigInput);
		}
		System.out.println("\n\n**********************\nDump Big"+nBigBatch);
        DataDumper dumper = new DataDumper();
        dumper.dumpInputs(expandedDaily);

        
		// out is dimensioned nBigBatch x nOutput where nOutput is number of stations
		// predicted
		float[][] out = daily.estimate(expandedDaily, null);
		
		// For dailyOuputs
		// ArrayList dim of dailyOutputs is over output stations
		// Then the double[][] dims are original batch size (before it got multiplied by num days) 
		// and days of the month
		ArrayList<double[][]> dailyOutputs = new ArrayList<double[][]>();

		int nLoc = out[0].length;     // number of output stations in ANN
		for (int iLoc = 0; iLoc < nLoc; iLoc++) {
			double[][] dailyOut = new double[nbatch][daysInMonth];
			for (int ibatch = 0; ibatch < nbatch; ibatch++) {
				for (int jdate = 0; jdate < daysInMonth; jdate++) {
					dailyOut[ibatch][jdate] = (double) out[ibatch * daysInMonth + jdate][iLoc]; // TODO make double?
				}
			}
			dailyOutputs.add(dailyOut);
		}
		return dailyOutputs;
	}

	public Surrogate getDailySurrogate() {
		return daily;
	}

	public void setDailySurrogate(Surrogate daily) {
		this.daily = daily;
	}

	/**
	 * Returns true if the specified ANN input index corresponds to an exogenous variable.
	 * In other words, if there is a concrete assignment (i.e. not UNASSIGNED) for the input.
	 *
	 * @param ivar the ANN input index to check.
	 * @return true if there is an exogenous assignment for ivar; false otherwise.
	 */
	public boolean isExogenous(int ivar) {
	    ExogTimeSeriesAssignment assignment = getAssignmentForAnnInput(ivar);
	    return assignment != ExogTimeSeriesAssignment.UNASSIGNED;
	}
	
    /**
     * Helper method to retrieve the assignment corresponding to a given ANN input index.
     * Returns UNASSIGNED if no assignment is found.
     */
    private ExogTimeSeriesAssignment getAssignmentForAnnInput(int annInput) {
        for (ExogTimeSeriesAssignment assign : assignments) {
            if (assign.getAnnInputIndex() == annInput) {
                return assign;
            }
        }
        return ExogTimeSeriesAssignment.UNASSIGNED;
    }	
    
	public int numberOfDays(int month, int year){
		int days;
		if (month==1 || month==3 || month==5 || month==7 
				||month==8 || month==10 ||month==12){
			days=31;
		}else if (month==4|| month==6 || month==9 || month==11){ 
			days=30;
		}else {
			if (isLeapYear(year)){
				days=29;
			}else{
				days=28;
			}
		}
		return days;
	}
	
	public static boolean isLeapYear(int year){
		if (year % 4 == 0) {
		    if (year % 100 != 0) {
		    	return true;
		    }else if (year % 400 == 0) {
		    	return true;
		    }else {
		    	return false;
		    }
		}else{
			return false;
		}
	}
	
	 
	

}
