package calsim.surrogate;


/**
 * AggregateMonths provides methods to combine daily surrogate data into monthly scalars,
 * such as means, maximums, and specialized statistics (e.g., maximum 14‐day running average).
 *
 * <p>The aggregation methods use three key parameters to define the aggregation window:
 * <ul>
 *   <li><strong>firstMonthIndex:</strong> The index in the daily data array corresponding to the first day 
 *       of the current month. This serves as an offset into the daily array.</li>
 *   <li><strong>startDayOfMonth:</strong> The starting day (1‐based) of the month to begin aggregation. 
 *       Typically, this is 1.</li>
 *   <li><strong>endDayOfMonth:</strong> The ending day (1‐based) of the month to aggregate. For example, if 
 *       the month has 31 days, this value is 31. The aggregation is performed on the daily data corresponding 
 *       to indices from <code>firstMonthIndex + startDayOfMonth - 1</code> up to (but not including) 
 *       <code>firstMonthIndex + endDayOfMonth</code>.</li>
 * </ul>
 * </p>
 *
 * <p>For the 14‐day running average (used in MONTHLY_MAX_14D), the method computes, for each day i in the 
 * aggregation window, the average of daily values from <code>i - 14 + 1</code> through <code>i</code>. 
 * To avoid negative indexing (i.e., using a day before the start of the current month), the method skips any 
 * day for which <code>i - 14 + 1</code> is less than <code>firstMonthIndex</code>.
 * </p>
 */

public enum AggregateMonths {


	/**
	 * Aggregates daily data into a monthly mean.
	 *
	 * <p>This method computes the mean by summing the daily values over the window defined by:
	 * <code>firstMonthIndex + startDayOfMonth - 1</code> (inclusive) up to 
	 * <code>firstMonthIndex + endDayOfMonth</code> (exclusive), and dividing by the number of days in 
	 * that window.
	 * </p>
	 *
	 * <p>
	 * <strong>Note:</strong> For a "true" monthly mean, the aggregation should cover the entire calendar month.
	 * This requires that <code>startDayOfMonth</code> is set to 1 and <code>endDayOfMonth</code> is set to the
	 * number of calendar days in the month (e.g., 28, 30, or 31). If different values are provided, the mean
	 * will be computed over a partial period.
	 * </p>
	 *
	 * @param daily           the array of daily surrogate data.
	 * @param firstMonthIndex the index in the daily array corresponding to the first day of the current month.
	 * @param startDayOfMonth the starting day (1-based) within the month for aggregation.
	 * @param endDayOfMonth   the ending day (1-based) within the month for aggregation.
	 * @return the mean of the daily values in the specified window.
	 */
	MONTHLY_MEAN(1,"monthly average") {

		@Override
		public double aggregate(double[] daily, int firstMonthIndex, 
				int startDayOfMonth, int endDayOfMonth) {

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
	 * Aggregates daily data by selecting the maximum daily value over the specified monthly window.
	 *
	 * <p>This method finds the maximum value among the daily values in the window defined by:
	 * <code>firstMonthIndex + startDayOfMonth - 1</code> (inclusive) up to 
	 * <code>firstMonthIndex + endDayOfMonth</code> (exclusive).
	 * </p>
	 *
	 * <p>
	 * <strong>Note:</strong> For a "true" monthly maximum, the aggregation should include all days of the month.
	 * This requires that <code>startDayOfMonth</code> is set to 1 and <code>endDayOfMonth</code> is equal to the 
	 * total number of calendar days in that month. Using other values will compute the maximum over a partial period.
	 * </p>
	 *
	 * @param daily           the array of daily surrogate data.
	 * @param firstMonthIndex the index in the daily array corresponding to the first day of the current month.
	 * @param startDayOfMonth the starting day (1-based) within the month for aggregation.
	 * @param endDayOfMonth   the ending day (1-based) within the month for aggregation.
	 * @return the maximum daily value in the specified window.
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
	 * Computes the maximum of a 14-day backward-looking running average over the specified period.
	 *
	 * <p>The aggregation is computed for each day <code>i</code> in the window defined by:
	 * <code>firstMonthIndex + startDayOfMonth - 1</code> (inclusive) to 
	 * <code>firstMonthIndex + endDayOfMonth</code> (exclusive). For each day <code>i</code>, a 14-day average is 
	 * computed over the window from <code>i - 14 + 1</code> to <code>i</code>. To avoid negative indexing (i.e., using 
	 * days prior to the first day of the current month), any day for which <code>i - 14 + 1</code> is less than 
	 * <code>firstMonthIndex</code> is skipped.
	 * </p>
	 *
	 * <p>This means that, for example, if the aggregation window is defined as 
	 * (<code>firstMonthIndex=85, startDayOfMonth=1, endDayOfMonth=20</code>), the effective window is indices 85 
	 * through 104, and the 14-day average will only be computed for days <code>i >= 98</code> (since 85 + 14 - 1 = 98).
	 * </p>
	 *
	 * @param daily           the array of daily surrogate data.
	 * @param firstMonthIndex the index in the daily array corresponding to the first day of the current month.
	 * @param startDayOfMonth the starting day (1-based) within the month for aggregation.
	 * @param endDayOfMonth   the ending day (1-based) within the month for aggregation.
	 * @return the maximum 14-day running average computed over valid days, or a default value if no valid average exists.
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
