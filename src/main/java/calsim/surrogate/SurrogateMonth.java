package calsim.surrogate;

import java.util.ArrayList;
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
    private int[] exogInputsNdx;  // TODO: hardwired logic
    private DisaggregateMonths firstNonNullDisagg; // non-null used for length calculations

    /**
     * Constructor that leaves exogInputsNdx open. This is preferred as we vary the architectures
     * @param disagg
     * @param daily
     * @param agg
     * @param exogInputsNdx
     */
    public SurrogateMonth(DisaggregateMonths[] disagg, 
    		Surrogate daily, 
    		AggregateMonths agg,
    		int[] exogInputsNdx) {

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

    	this.disagg = disagg;
    	this.daily = daily;
    	this.agg = agg;
    	this.exogInputsNdx = exogInputsNdx;

    }

    
    /**
     * this hardwires the exogenous column to input index 5 (usual spots for tgetides in legacy ANNs)
     * @param disagg
     * @param daily
     * @param agg
     */
	public SurrogateMonth(DisaggregateMonths[] disagg, 
			              Surrogate daily, 
			              AggregateMonths agg) {
		// for legacy reasons, {5} is the usual tide index, which is exogenous
 		this(disagg,daily,agg,null);
 	    int[] exogNdx = {5};
 		this.exogInputsNdx = exogNdx;
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
	    int ndx = this.getExogInputIndex(ivar);
	    int nBatch = arr.length; // array will end up being nBatch x nDay

	    // Compute the starting year and month based on the multi-month history.
	    // The current month (year, month) is the final month in the history.
	    // Thus, the starting month is (current month minus (nMonth - 1)) with day set to 1.
	    int monthsHistory = firstNonNullDisagg.getNMonth();
	    System.out.println("months history "+monthsHistory);
	    java.time.YearMonth startYM = java.time.YearMonth.of(year, month).minusMonths(monthsHistory - 1);
	    
	    // Fetch the exogenous daily slice starting at the correct historical beginning.
	    System.out.println("Loading code ndx: " + ndx + " year: " + year + " month "+month + " nDay " + nDay);
	    System.out.println(startYM);
	    arr[0] = ExogenousTimeSeries.getInstance()
	                     .dailyDataSlice(ndx, startYM.getYear(), startYM.getMonthValue(), 1, nDay);

	    // Copy the same exogenous data across all batch entries.
	    for (int jbatch = 1; jbatch < nBatch; jbatch++) {
	        arr[jbatch] = new double[arr[0].length];
	        System.arraycopy(arr[0], 0, arr[jbatch], 0, arr[0].length);
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
	
	
    public GridResult evaluateOnGrid(ArrayList<double[][]> monthlyInputs, int year, int month,
    		double loBound0, double hiBound0, int nx0, double loBound1, double hiBound1, int nx1) {
    	double[] x0 = new double[nx0];
    	double[] x1 = new double[nx1];
    	double nx0m1 = (double) (nx0-1);
    	double nx1m1 = (double) (nx1-1);
    	// These are the gridded values on variable 0 and variable 1 that will be evaluated
    	for (int i = 0; i < nx0; i++) {
    		double iFloat = (double) i;
    		x0[i] = loBound0 + (iFloat/nx0m1)*(hiBound0 - loBound0);
    	}
    	for (int j = 0; j < nx1; j++) {
    		double iFloat = (double) j;
    		x1[j] = loBound1 + (iFloat/nx1m1)*(hiBound1 - loBound1);
    	}
    	// Create a batch the size of all the gridded inputs
    	// and copy the original inputs into every batch member 
    	int nBatch = nx0*nx1;
    	ArrayList<double[][]> gridded = new ArrayList<double[][]>();
    	for (int iVar = 0; iVar < monthlyInputs.size(); iVar++) {
    	    int arrDim = monthlyInputs.get(iVar)[0].length;
    	    double[][] batched = new double[nBatch][arrDim];
    	    for (int ibatch = 0; ibatch < nBatch; ibatch++) {
    	    	batched[ibatch] = new double[arrDim];
    	    	// Duplicate the original inputs for every batch member
    	    	System.arraycopy(monthlyInputs.get(iVar)[0], 0, batched[ibatch], 0, arrDim); 
    	    }
    	    gridded.add(batched);
    	    
    	}
    	// Now for variable 0 and 1, go back and replace the first index (batch 0 lag 0) 
    	// of variables 0 and 1 using the gridded values. 
    	int iBatch = 0;
    	for (int i = 0; i<nx0; i++) {
    		for (int j = 0; j<nx1; j++) {
    			gridded.get(0)[iBatch][0] = x0[i];
    			gridded.get(1)[iBatch][0] = x1[j];
    			iBatch++;
    		}
    	}
    	
    	double[][] result = this.annMonth(gridded, year, month);
    	double[][][] resultFullDim = expandLinear(result,nx0,nx1);
   	
    	return new GridResult(x0, x1, gridded, resultFullDim, month, year);
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
	 *         by number of stations predicted
	 */
	public double[][] annMonth(ArrayList<double[][]> monthlyInputs, int year, int month) {
        
		ArrayList<double[][]> dailyInputs = new ArrayList<double[][]>();
		int nvar = monthlyInputs.size();
		int nbatch = monthlyInputs.get(0).length;
		int nday = this.disagg[0].getNDay(year, month); // Total number of days in history

        if(monthlyInputs.size() == 3) {
		  DataDumper dumper = new DataDumper();
		  System.out.println("Dumpling Monthly " + year + " " + month);
		  dumper.dumpInputs(monthlyInputs);
		  //System.out.println("Dumping Daily " + year + " " + month);
		  //dumper.dumpInputs(dailyInputs);
		  //System.out.println("Done");
        }		
		
		// Disaggregate monthly to daily for each feature/input. monthly comes in
		// reversed
		// but will be put forward in time
		for (int ivar = 0; ivar < nvar; ivar++) {
			double[][] newInput = new double[nbatch][];
			if (isExogenous(ivar)){
				System.out.println("annMonth " + year + " "+month);
				loadExogenous(newInput,ivar,year,month,nday);
				System.out.println("Loaded");
			}else {
				for (int jbatch = 0; jbatch < nbatch; jbatch++) {
					newInput[jbatch] = disagg[ivar].apply(year, month, monthlyInputs.get(ivar)[jbatch]);
				}
			}
			dailyInputs.add(newInput);			
		}

		// This is the starting datum for the sliding window below
		int indexStart = disagg[0].offsetFirstMonth(year, month);

		
		// Slide window on the daily inputs and generate daily output
		// The indexes in the output ArrayList represent stations or output locations
		// The first dimension of the double[][] represent the original input batches
		// The second index of the double[][], which will be collapsed in the next step,
		// represent days within the output where indexStart again represents the first of
		// the month
		ArrayList<double[][]> dailyOutputs = timeStep(dailyInputs, indexStart, year, month);
		int nLoc = dailyOutputs.size(); // others are original batch times days in month
		int daysInMonth = numberOfDays(month, year); 
		double[][] monthlyOut = new double[nbatch][nLoc];
		// Perform requested summary statistic that recovers a monthly value
		for (int iLoc = 0; iLoc < nLoc; iLoc++) {
			for (int ibatch = 0; ibatch < nbatch; ibatch++) {
				monthlyOut[ibatch][iLoc] = agg.aggregate(dailyOutputs.get(iLoc)[ibatch], indexStart, 1, daysInMonth);
			}
		}
		if (monthlyInputs.size()==3) {System.out.println("ANN month returning: "+monthlyOut[0][0]);}
		return monthlyOut;
	}

	// March through time from the first to the end of the month,
	// repacking the daily data over the window into the form expected by the ANN
	// applying the ANN, and storing the output as an array of daily outputs
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
			double[][] inputs = dailyInputs.get(ivar); // dims: original batch size by total time history (several
														// months)
			double[][] bigInput = new double[nBigBatch][];
			int iBatchBig = 0;
			for (int ibatch = 0; ibatch < nbatch; ibatch++) {
				for (int ioff = startDayIndex; ioff < stopIndex; ioff++) {
					bigInput[iBatchBig] = daily.dailyToSurrogateInput(inputs[ibatch], ioff);
					iBatchBig++;
				}
			}
			expandedDaily.add(bigInput);
		}

		// out is dimensioned nBigBatch x nOutput where nOutput is number of stations
		// predicted
		float[][] out = daily.estimate(expandedDaily, null);

		// For dailyOuputs
		// ArrayList dim of dailyOutputs is over output stations
		// Then the next two are original batch size and
		// Days of the month
		ArrayList<double[][]> dailyOutputs = new ArrayList<double[][]>();

		int nLoc = 1;
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

	public boolean isExogenous(int ivar) {
		return getExogInputIndex(ivar) >= 0;
	}
	
	/**
	 * Returns the index within the exogenous input time series that pertains to variable 
	 * @param ivar
	 * @return column index within exogenous time series
	 */
	public int getExogInputIndex(int ivar) {
		for (int i = 0; i < exogInputsNdx.length; i++) {			
			if (this.exogInputsNdx[i] == ivar) {
				return i;
			}
		}
		return -1;
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
