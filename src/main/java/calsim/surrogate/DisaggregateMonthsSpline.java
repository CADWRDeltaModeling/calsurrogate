package calsim.surrogate;

/**
 * Monthly to daily disaggregation using a conservative spline (rational
 * histospline) //TODO the spline paramter pq is kind of burrowed.
 */
public class DisaggregateMonthsSpline extends DisaggregateMonths {

	public DisaggregateMonthsSpline(int nMon) {
		super(nMon);
	}

	/**
	 * Disaggregates monthly data into a daily time series using a conservative spline
	 *
	 * <p><strong>Note:</strong> The input array {@code dataRev} is expected to be in reverse 
	 * chronological order (with the current month at index 0). Internally, the method calls 
	 * {@link #asIrregArray(int, int, double[])} to reverse the order into a forward chronological 
	 * series before performing the disaggregation.</p>
	 *
	 * @param year    the current year
	 * @param month   the current month (January = 1)
	 * @param dataRev an array of monthly data in reverse chronological order
	 * @return a daily time series derived from the monthly data
	 */
	public double[] apply(int year, int month, double[] dataRev) {
		double[][] ts = asIrregArray(year, month, dataRev);
		double pqScalar = 10;
		double[] pq = { pqScalar, pqScalar, pqScalar, pqScalar, pqScalar, pqScalar }; // TODO
		double y0 = ts[1][0];
		double yn = ts[1][this.getNMonth()];
		double ymin = -0.00000001; // TODO fix the minimum reinforcement
		ConservativeSpline spline = new ConservativeSpline(ts[0], ts[1], pq, y0, yn, ymin);
		double xNewMax = ts[0][this.getNMonth()];
		int xNewLen = (int) xNewMax + 1;
		double[] xnew = new double[xNewLen];
		double[] out = new double[xNewLen];
		for (int ix = 0; ix < xNewLen; ix++) {
			xnew[ix] = (double) ix;
			try {
				out[ix] = spline.rh2val(xnew[ix]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return out;
	}

}
