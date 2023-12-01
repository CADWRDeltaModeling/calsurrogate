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
	private Surrogate daily;
    private int[] exogInputsNdx = {5};  // TODO: hardwired logic
    private DisaggregateMonths firstNonNullDisagg; // non-null used for length calculations

    

    
    
	public SurrogateMonth(DisaggregateMonths[] disagg, 
			              Surrogate daily, 
			              AggregateMonths agg) {

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
		
	}

	public int bufferNDay(int year, int month) {
		return firstNonNullDisagg.getNDay(year, month);
	}

	/**
	 * 
	 * @param ivar Index in surrogate of the input
	 * @param arr array to be filled. On entry first dimension will be nBatch 
	 *        and second is ignored. On exit dim should be nBatch x nDay
	 */
	public void loadExogenous(double[][] arr, int ivar, int year, int month, int nDay) {

	    int ndx = this.getExogInputIndex(ivar);	
	    int nBatch = arr.length; // array will end up being nBatch x nDay
        arr[0] = ExogenousTimeSeries.getInstance().dailyDataSlice(ndx, year, month, 1, nDay);
		
		// Then copy to the other members of the batch
		for (int jbatch = 1; jbatch < nBatch; jbatch++) {
			arr[jbatch] = new double[bufferNDay(year,month)];
			System.arraycopy(arr[0], 0, arr[jbatch], 0, arr[0].length);
		}
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
		int nday = this.disagg[0].getNDay(year, month); 

		// Disaggregate monthly to daily for each feature/input. monthly comes in
		// reversed
		// but will be put forward in time
		for (int ivar = 0; ivar < nvar; ivar++) {
			double[][] newInput = new double[nbatch][];
			if (isExogenous(ivar)){
				loadExogenous(newInput,ivar,year,month,nday);
			}else {
				for (int jbatch = 0; jbatch < nbatch; jbatch++) {
					newInput[jbatch] = disagg[ivar].apply(year, month, monthlyInputs.get(ivar)[jbatch]);
				}
			}
			dailyInputs.add(newInput);			
		}

		int indexStart = disagg[0].offsetFirstMonth(year, month);

		// Slide window on the daily inputs and generate daily output
		// The indexes in the ArrayList represent stations or output locations
		// The first index of the double[][] represent the original input batches
		// The second index of the double[][], which will be collapsed in the next step,
		// represent days in the output where indexStart again represents the first of
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
		// by including each rotated/advanced step as a separate batch index.
		// From this point on the batch size will
		// be the original batch size times number of days of the month.
		// The batches are organized so that the advancing days of the month
		// within one original batch are contiguous.
		// There is also time structure in the second index.
		// This is the point where we transform the a history in days into
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
