package calsim.surrogate;

import java.time.YearMonth;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Abstract class for disaggregating reverse-chronologically ordered monthly data
 * into a daily time series.
 *
 * <p>CalSim exports monthly data in reverse chronological order where the current month
 * is at index 0 and older months follow. This class provides the framework for converting
 * that reverse-ordered data into a forward-in-time daily series. The helper method 
 * {@link #asIrregArray(int, int, double[])} is used to transform the input into a time series
 * with correct calendar month boundaries.</p>
 *
 * <p>Subclasses implement different strategies for disaggregation:
 * <ul>
 *   <li>{@link DisaggregateMonthsDaysToOps}: Disaggregates by assigning specific operational 
 *       values for parts of the month.</li>
 *   <li>{@link DisaggregateMonthsRepeat}: Repeats the monthly value across all days in the month.</li>
 *   <li>{@link DisaggregateMonthsSpline}: Uses a conservative spline (rational histospline) to
 *       interpolate daily values.</li>
 * </ul>
 * </p>
 */
public abstract class DisaggregateMonths {

	private int nMonth;

	/**
	 * Constructor based on number of months in memory, current month inclusive.
	 * Current practice is typically 5
	 * 
	 * @param nMon
	 */
	public DisaggregateMonths(int nMon) {
		nMonth = nMon;
	}

	public int getNMonth() {
		return nMonth;
	}


	public int offsetFirstMonth(int year, int month) {
		int[] daysMonth = this.daysMonth(year, month);
		int cum = 0;
		for (int i = 1; i < daysMonth.length; i++) {
			cum += daysMonth[i];
		}
		return cum;
	}

	/**
	 * Disaggregates monthly data into a daily time series.
	 *
	 * <p><strong>Note:</strong> The input array {@code dataRev} is expected to be in reverse 
	 * chronological order (with the current month at index 0). Internally, the method calls 
	 * {@link #asIrregArray(int, int, double[])} to reverse the order into a forward chronological 
	 * series before performing the disaggregation.</p>
	 *
	 * @param year    the current year
	 * @param month   the current month (January = 1)
	 * @param dataRev an array of monthly data in reverse chronological order
	 * @return a forward daily time series derived from the monthly data
	 */
	public abstract double[] apply(int year, int month, double[] dataRev);

	/**
	 * Lists the number of days in month for the nMonth most recent months ending in
	 * month, in decending order (
	 * 
	 * @param year  to be analyzed
	 * @param month latest in time entry in list (index 0), using Jan=1
	 * @return integer array of number of days
	 */
	public int[] daysMonth(int year, int month) {
		YearMonth yMonth = YearMonth.of(year, month);
		int[] out = new int[nMonth];
		for (int iMonth = 0; iMonth < nMonth; iMonth++) {
			YearMonth minus = yMonth.minusMonths((long) iMonth);
			out[iMonth] = minus.lengthOfMonth();
		}
		return out;
	}
	
	/**
	 * Calculate the length of daily buffer time series that will result from the disaggregation
	 * The buffer will span the number of months returned by getNMonth() including the current month
	 * @param year Current year
	 * @param month Current month
	 * @return total length of buffer
	 */
	public int getNDay(int year, int month) {
		int[] array = daysMonth(year, month);
		return Arrays.stream(array).sum();
	}

	/**
	 * Constructs an irregular time series represented as a two-dimensional array.
	 *
	 * <p>The first row (ts[0]) contains cumulative day boundaries marking the start of each month,
	 * with an extra entry for the start of the following month. The second row (ts[1]) contains
	 * the monthly data values in forward chronological order.</p>
	 *
	 * <p><strong>Note:</strong> The input array {@code dataRev} must be provided in reverse 
	 * chronological order (i.e., index 0 is the current month). This method reverses the order to 
	 * produce a forward time series that aligns with calendar month boundaries.</p>
	 *
	 * @param year    the current year
	 * @param month   the current month (January = 1)
	 * @param dataRev an array of monthly data in reverse chronological order
	 * @return a two-dimensional array where ts[0] holds day boundaries and ts[1] holds the data values
	 */
	public double[][] asIrregArray(int year, int month, double[] dataRev) {
		double[][] ts = new double[2][nMonth + 1];
		ts[0][0] = 0.;
		int[] dMon = daysMonth(year, month);
		for (int iMonth = 0; iMonth < nMonth; iMonth++) {
			int revIndex = nMonth - 1 - iMonth;
			ts[0][iMonth + 1] = ts[0][iMonth] + (double) dMon[revIndex];
			ts[1][iMonth] = dataRev[revIndex];
		}
		// This populates the undefined value in last index of data
		ts[1][nMonth] = dataRev[0];
		return ts;
	}

}
