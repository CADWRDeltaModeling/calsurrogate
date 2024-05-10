package calsim.surrogate;


/*
 *    AVE_TYPE KEY:
      1 = monthly average
      2 = first day of month value
      3 = last day of month value
      4 = maximum daily value
      5 = minimum daily vlaue
      6 = maximum 14-day value
      7 = average for first 15 days
      8 = average for last 15 days
      37 = average for last 7 day
 */


/**
 * Classes that combine daily surrogate output into monthly summary statistics
 * like means used by CalSIM constraints. The choices are an enum, each choice
 * in which is a class. So instead of instantiating these with a constructor you
 * use the enum: AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
 */
public enum AggregateMonths {
    
	
	/**
	 * Aggregates to monthly mean.
	 */
	MONTHLY_MEAN(1,"monthly average") {
		
		/**
		 * Computes the mean of all daily values in the month
		 */
		@Override
		public double aggregate(double[] daily, int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

			int startIndex = firstMonthIndex + startDayOfMonth - 1;
			int stopIndex = firstMonthIndex + endDayOfMonth;

			double total = 0.;
			startIndex = 0;
			stopIndex = endDayOfMonth; // TODO THis won't work for averages that reach back before first
			double fnday = (double)(stopIndex-startIndex);
			
			for (int i = startIndex; i < stopIndex; i++) {
				total += daily[i];
			}
			return total/fnday;
		}
	},
	/**
	 * Computes the maximum of all daily values in the month
	 */
	MONTHLY_MAX(4,"maximum daily value") {
		
		@Override
		public double aggregate(double[] daily, int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

			int startIndex = firstMonthIndex + startDayOfMonth - 1;
			int stopIndex = firstMonthIndex + endDayOfMonth;

			double max = -9999;
			for (int i = startIndex; i < stopIndex; i++) {
				if (daily[i] > max)
					max = daily[i];
			}
			return max;
		}
	},
	/**
	 * Computes a 14-day backward-looking running average then takes the max of
	 * those. Currently the surrogates are so long that the 4-month history will not
	 * allow enough days to compute a 14-day running average near the first of the
	 * month. So this will likely produce an error.
	 */
	MONTHLY_MAX_14D(66,"maximum 14-day running") {
		@Override
		public double aggregate(double[] daily, int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

			int startIndex = firstMonthIndex + startDayOfMonth - 1;
			int stopIndex = firstMonthIndex + endDayOfMonth;

			double max = -9999;
			for (int i = startIndex; i < stopIndex; i++) {
				double ave14 = 0.;
				for (int j = i - 14 + 1; j <= i; j++) {
					ave14 += daily[j];
				}
				ave14 /= 14;
				if (ave14 > max)
					max = ave14;
			}
			return max;
		}
	},

	
	
	/**
	 * Computes a 14-day backward-looking running average then takes the max of
	 * those. Currently doesn't really do that because the 4-month history will not
	 * allow enough days to compute a 14-day running average near the first of the
	 * month, so this one starts on day 14. That matches how the problem is handled
	 * in previous versions of CalSIM
	 */
	MONTHLY_MAX_14D_TRUNCATED(6,"max 14-day running average starting day 14") {
		@Override
		public double aggregate(double[] daily, int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

			int startIndex = firstMonthIndex + startDayOfMonth - 1;
			int stopIndex = firstMonthIndex + endDayOfMonth;
			int TRUNCATION = 14;

			double max = -9999;
			startIndex = 0;  //TODO hardwired ... something is wrong here about indexes
			stopIndex = endDayOfMonth;
			for (int i = (startIndex+TRUNCATION); i < stopIndex; i++) {
				double ave14 = 0.;
				for (int j = i - 14 + 1; j <= i; j++) {
					ave14 += daily[j];
				}
				ave14 /= 14.;
				if (ave14 > max)
					max = ave14;
			}
			return max;
		}
	},
		/**
		 * Compute the nth smallest/largest value.
		 * Must be intercepted to set nth value.
		 * This is for a loss function for calculating days of X2 < target
		 * in such a way that it has a robust gradient 
		 * Mminimizing the max violation, or min-max, is a common
		 * tool in optimal control -- any direct metric in "number of days" is integer
		 * based and doesn't have a clean gradient. 
		 * So if the number of days of 
		 * X2 < km_chs (Chipps Island or 74km) is supposed to be 16, 
		 * this aggregator would be set to return 
		 * the 16th smallest value of X2 for the month. If that value is < Roe, then
		 * there are at least 16 days meeting X2. Since X2 is a continuous variable,
		 * its derivative with respect to flow is well defined. 
		 */
		NTH_SMALLEST(77,"value of the nt-th extreme value") {
            

			@Override
			public double aggregate(double[] daily, int firstMonthIndex, 
	                int startDayOfMonth, int endDayOfMonth) {
                
				int startIndex = 0; //firstMonthIndex + startDayOfMonth - 1;
				int stopIndex = endDayOfMonth;
				// stopIndex = firstMonthIndex + endDayOfMonth -1;
	            int nval = stopIndex - startIndex;
           
	            double[] ordered = new double[nval]; 
	            System.arraycopy(daily,startIndex,ordered,0,nval);

	            java.util.Arrays.sort(ordered);
	            double ret = ordered[this.num-1];
                return ret;
			}

		},
		/**
		 * Compute the number of values below a threshold for the month. 
		 * This can be used, for instance to count number of days X2 is below
		 * a certain kilometer threshold.
		 * Must be intercepted to set the threshold 
		 */
		COUNTBELOW(99,"number of days below threshold") {

			@Override
			public double aggregate(double[] daily, 
					int firstMonthIndex, 
	                int startDayOfMonth, int endDayOfMonth) {
                
				int startIndex = firstMonthIndex + startDayOfMonth - 1;
				int stopIndex = firstMonthIndex + endDayOfMonth;
				double count = 0.;
				startIndex = 0;  //TODO hardwired ... something is wrong here about indexes
				stopIndex = endDayOfMonth;
				for (int i = (startIndex); i < stopIndex; i++) {
					if (daily[i] <= this.threshold+1e-13){
						count += 1;
					}
				}
				return count;
			}

		};		

	public final int calsimCode;
	public final String description;
	public int num;
	public double threshold;
	
			public void setThreshold(double thresh) {
				this.threshold = thresh;
			}
	
			/**
			 * Set the n part, as in nth smallest value
			 * @param nth
			 */
			public void setN(int n) {
				this.num = n;
			}			
			
	AggregateMonths(int calsimCode, String description){
		this.calsimCode = calsimCode;
		this.description = description;
	}
	
	
	public double aggregate(double[] daily, int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {
		return -99999.;
	}


	public  AggregateMonths aggForCalsimCode(int calsimCode) {
		for (AggregateMonths agg : AggregateMonths.values()) {
			if (agg.calsimCode==calsimCode) return agg;
		}
		return null;
	}
	

}
