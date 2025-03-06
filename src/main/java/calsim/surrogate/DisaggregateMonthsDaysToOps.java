package calsim.surrogate;

/**
 * Disaggregates monthly data representing operating day counts into a daily time series.
 * 
 * <p>The monthly data (provided as a reverse-chronological array) represents the number of days 
 * within each month during which operations were active. This class converts that information into 
 * a daily series by assigning specific values to days based on whether they fall within the operating 
 * period or outside of it.</p>
 * 
 * <p><strong>Clarification on Parameters:</strong>
 * <ul>
 *   <li><code>startOp</code>: This is the value assigned to each day during the operating period.
 *       For a given month, the number of operating days is determined by the monthly value (from 
 *       the input array). The first N days of the month (where N equals this operating day count) 
 *       are assigned the <code>startOp</code> value.</li>
 *   <li><code>endOp</code>: This is the value assigned to the remaining days of the month, which 
 *       are considered non-operational. Once the operating period is complete, all remaining days 
 *       in that month receive the <code>endOp</code> value.</li>
 * </ul>
 * </p>
 * 
 * <p>Internally, the method {@link #asIrregArray(int, int, double[])} (inherited from 
 * {@link DisaggregateMonths}) is used to convert the reverse-chronologically ordered monthly data 
 * into a forward time series. This reordering ensures that the daily series output aligns correctly 
 * with the calendar month boundaries.</p>
 * 
 * <p>Example usage:
 * <pre>
 *   // Assume dataRev contains monthly operating day counts in reverse order.
 *   DisaggregateMonthsDaysToOps disagg = new DisaggregateMonthsDaysToOps(5, 1.0, 0.0);
 *   double[] dailyData = disagg.apply(2025, 3, dataRev);
 * </pre>
 * </p>
 *
 */
public class DisaggregateMonthsDaysToOps extends DisaggregateMonths {

	private double startOp;
	private double endOp;
	
    /**
     * Constructs a new instance for disaggregating monthly operating day counts into a daily series.
     *
     * @param nMon    the number of months to include (e.g., 5 months of data)
     * @param startOp the value assigned to each day during the operating period (active days)
     * @param endOp   the value assigned to each day outside the operating period (inactive days)
     */
	public DisaggregateMonthsDaysToOps(int nMon, double startOp, double endOp) {
		super(nMon);
		this.startOp = startOp;
		this.endOp = endOp;
	}

    /**
     * Disaggregates the reverse-chronologically ordered monthly data into a daily time series.
     *
     * <p>This method first calls {@link #asIrregArray(int, int, double[])} to transform the input 
     * data (provided in reverse chronological order) into an irregular time series. The resulting 
     * array contains:
     * <ul>
     *   <li>A first row with cumulative day boundaries corresponding to month start dates.</li>
     *   <li>A second row with monthly data values in forward chronological order (i.e., oldest to newest).</li>
     * </ul>
     * </p>
     *
     * <p>For each month:
     * <ul>
     *   <li>The first <em>N</em> days (where <em>N</em> is the number of operating days as per the monthly data)
     *       are assigned the <code>startOp</code> value.</li>
     *   <li>The remaining days in the month are assigned the <code>endOp</code> value.</li>
     * </ul>
     * </p>
     *
     * @param year    the current year (e.g., 2025)
     * @param month   the current month (1 for January, 12 for December)
     * @param dataRev an array of monthly operating day counts in reverse chronological order
     *                (current month at index 0)
     * @return an array representing the disaggregated daily time series
     */
    @Override
	public double[] apply(int year, int month, double[] dataRev) {
		double[][] ts = asIrregArray(year, month, dataRev);
		int xNewMax = (int) ts[0][this.getNMonth()];
		int nCoarse = ts[0].length;
		double xNewMin = ts[0][0];
		double[] out = new double[xNewMax];
		for (int iCoarse = 0; iCoarse < (nCoarse - 1); iCoarse++) {
			int istart = (int) ts[0][iCoarse];
			int ilast = istart + (int) ts[1][iCoarse];
			int ilen = (int) ts[0][iCoarse + 1];
			for (int iFine = istart; iFine < ilast; iFine++) {
				out[iFine] = getStartOp();
			}
			for (int iFine = ilast; iFine < ilen; iFine++) {
				out[iFine] = getEndOp();
			}
		}

		return out;
	}

    /**
     * Returns the operational value assigned to days during the operating period.
     *
     * @return the start operation value
     */    
	public double getStartOp() {
		return startOp;
	}

    /**
     * Sets the operational value for days during the operating period.
     *
     * @param startOp the new start operation value
     */	
	public void setStartOp(double startOp) {
		this.startOp = startOp;
	}

    /**
     * Returns the value assigned to days outside the operating period.
     *
     * @return the end operation value
     */	
	public double getEndOp() {
		return endOp;
	}

    /**
     * Sets the value for days outside the operating period.
     *
     * @param endOp the new end operation value
     */	
	public void setEndOp(double endOp) {
		this.endOp = endOp;
	}

}
