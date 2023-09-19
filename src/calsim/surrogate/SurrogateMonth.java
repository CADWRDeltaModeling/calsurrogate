package calsim.surrogate;

import java.util.ArrayList;

/**
 * Apply daily surrogate by disaggregating input, running the surrogate and summarizing to a monthly statistic.
 * The full workflow is shown in 
 * <img src="./doc-files/ANNMonth.png" alt='ANN Month design' width=600 />
 */
public class SurrogateMonth
 {

	private DisaggregateMonths disagg;
	private AggregateMonths agg;
	private Surrogate daily;
	
	
	public SurrogateMonth(DisaggregateMonths disagg,Surrogate daily, AggregateMonths agg) {
		this.disagg = disagg;
		this.daily = daily;
		this.agg = agg;
	}

	
	
	/**
	 * Complete application of the ANN or other surrogate. 
	 * Disaggregates monthly inputs to daily, marches through days repackaging the inputs
	 * into the time aggregation/treatment expected by the ANN and applying the ANN output. Then
	 * re-aggregates to an array over the batch size with one monthly statistic per batch
	 * @param monthlyInputs
	 * @param year
	 * @param month
	 * @param cycle
	 * @return 2D Array of monthly aggregated results with dimensions of batch size by number of stations predicted 
	 */
	public double[][] annMonth(ArrayList<double[][]> monthlyInputs, int location, int year, int month, int cycle) {
		
	
		ArrayList<double[][]> dailyInputs = new ArrayList<double[][]>();
		int nvar = monthlyInputs.size();
		int nbatch = monthlyInputs.get(0).length;
		
		// Disaggregate monthly to daily for each feature/input. monthly comes in reversed
		// but will be put forward in time
		for (int ivar=0; ivar<nvar; ivar++) {
			double[][] newInput = new double[nbatch][];
			for (int jbatch=0; jbatch<nbatch; jbatch++) {
				newInput[jbatch] =  disagg.apply(year, month, monthlyInputs.get(ivar)[jbatch]);
			}
			dailyInputs.add(newInput);
		}
        
		// append exogenous tide to last slot in the array in the array
		double[][] tide = new double[nbatch][];
		tide[0] = SFTide.getInstance().arrayFrom(year, month, 1, cycle); // why cycle??
        for (int jbatch=1; jbatch<nbatch; jbatch++) {
        	tide[jbatch] = new double[tide[0].length];
		    System.arraycopy(tide[0], 0, tide[jbatch], 0, tide[0].length);
        }        
        int indexStart = disagg.offsetFirstMonth(year, month);
        
        // Slide window on the daily inputs and generate daily output
        // The indexes in the ArrayList represent stations or output locations
        // The first index of the double[][] represent the original input batches
        // The second index of the double[][], which will be collapsed in the next step,
        // represent days in the output where indexStart again represents the first of the month
        ArrayList<double[][]> dailyOutputs = timeStep(dailyInputs,indexStart,location,year,month);
        int nLoc = dailyOutputs.size();  // others are original batch times days in month
        int daysInMonth = 30;    // TODO hardwire
        double[][] monthlyOut=new double[nbatch][nLoc];
        // Perform requested summary statistic that recovers a monthly value
        for (int iLoc = 0; iLoc < nLoc; iLoc++) {
             for (int ibatch = 0; ibatch<nbatch; ibatch++) {
         		System.out.println("iloc "+iLoc + " "+ibatch+" ");	
                 monthlyOut[ibatch][iLoc] = agg.aggregate(dailyOutputs.get(iLoc)[ibatch], indexStart,1,daysInMonth);            	 
             }
        }
        return monthlyOut;
	}
	
	// March through time from the first to the end of the month, 
	// repacking the daily data over the window into the form expected by the ANN
	// applying the ANN, and storing the output as an array of daily outputs
	public ArrayList<double[][]> timeStep(ArrayList<double[][]> dailyInputs,
			int startDayIndex,int location,int year,int month) {    
		int daysInMonth = disagg.daysMonth(year, month)[0];
		int nbatch = dailyInputs.get(0).length; //TODO safety check
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
		int nBigBatch = nbatch*daysInMonth;   // The new larger batch size
		for (int ivar=0; ivar< nvar; ivar++) {
			double[][] inputs = dailyInputs.get(ivar);  // dims: original batch size by total time history (several months)
			double[][] bigInput = new double[nBigBatch][]; 
			int iBatchBig = 0;
			for (int ibatch = 0; ibatch<nbatch; ibatch++) {
				for (int ioff = startDayIndex; ioff < stopIndex; ioff++) {
					bigInput[iBatchBig] = daily.dailyToSurrogateInput(inputs[ibatch], ioff);
					iBatchBig++;
				}
			}
            expandedDaily.add(bigInput);
		}
		// out is dimensioned nBigBatch x nOutput where nOutput is number of stations predicted
		float[][] out = daily.estimate(expandedDaily, null); 

		// For dailyOuputs
		// ArrayList dim of dailyOutputs is over output stations
		// Then the next two are original batch size and
		// Days of the month
		ArrayList<double[][]> dailyOutputs=new ArrayList<double[][]>();     

		int nLoc = 1; 
		for (int iLoc = 0; iLoc<nLoc; iLoc++){
			double[][] dailyOut = new double[nbatch][daysInMonth];
			for (int ibatch=0; ibatch<nbatch ; ibatch++) {
				for (int jdate = 0; jdate < daysInMonth; jdate++) {
					System.out.println("final " + ibatch + " "+jdate);
					System.out.println(out[ibatch*daysInMonth+jdate][0]);
					dailyOut[ibatch][jdate] = (double) out[ibatch*daysInMonth+jdate][iLoc];  //TODO make double?

				}
			}
			dailyOutputs.add(dailyOut);            
		}	
		return dailyOutputs;
	}	
	

	
	
	
}
