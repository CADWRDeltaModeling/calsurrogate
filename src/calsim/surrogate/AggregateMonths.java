package calsim.surrogate;

/**
 * Classes that combine daily surrogate output into monthly summary statistics like means used by CalSIM constraints.
 * The choices are an enum, each choice in which is a class. So instead of instantiating these with a constructor
 * you use the enum:
 * AggregateMonths agg = AggregateMonths.MONTHLY_MEAN; 
 */
public enum AggregateMonths {
        /**
         * Aggregates to monthly mean.
         */
		MONTHLY_MEAN {
			@Override
			/**
			 * Computes the mean of all daily values in the month
			 */
			public double aggregate(double[] daily,int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

				int startIndex = firstMonthIndex + startDayOfMonth - 1;
				int stopIndex = firstMonthIndex + endDayOfMonth;

				double total = 0.;
				startIndex = 0;
				stopIndex =endDayOfMonth; // TODO THis won't work for averages that reach back before first
				for (int i=startIndex; i<stopIndex; i++) {
					total +=daily[i];
				}
				return total/((double) (endDayOfMonth-startDayOfMonth +1));
			}
		},
        /**
         * Computes the maximum of all daily values in the month
         */
		MONTHLY_MAX {
			@Override
			public double aggregate(double[] daily,int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

				int startIndex = firstMonthIndex + startDayOfMonth - 1;
				int stopIndex = firstMonthIndex + endDayOfMonth;

				double max = -9999;
				for (int i=startIndex; i<stopIndex; i++) {
					if (daily[i] > max) max=daily[i];
				}
				return max;	
			}
		},		
		/**
		 * Computes a 14-day backward-looking running average then takes the max of those. Currently doesn't really
		 * do that because the 4-month history will not allow enough days to compute a 14-day running average near
		 * the first of the month.
		 */
		MONTHLY_MAX_14D {
			@Override
			public double aggregate(double[] daily,int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

				int startIndex = firstMonthIndex + startDayOfMonth - 1;
				int stopIndex = firstMonthIndex + endDayOfMonth;

				double max = -9999;
				for (int i=startIndex; i<stopIndex; i++) {
					double ave14 = 0.;
				    for (int j = i-14+1; j<=i ; j++) {
					    ave14 += daily[j];
				    }
				    ave14/=14;
				    if (ave14 > max) max=ave14;
				}
				return max;	
			}
		};
	    
		public double aggregate(double[] daily,int firstMonthIndex,int startDayOfMonth, int endDayOfMonth) {
	    	return -99999.;
	    }

}
