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
			for (int i = startIndex; i < stopIndex; i++) {
				total += daily[i];
			}
			return total / ((double) (endDayOfMonth - startDayOfMonth + 1));
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
			for (int i = startIndex+TRUNCATION; i < stopIndex; i++) {
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
	};	

	public final int calsimCode;
	public final String description;
	
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
