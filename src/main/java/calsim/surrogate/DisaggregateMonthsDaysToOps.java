package calsim.surrogate;

/**
 * Disaggregation that receives monthly values as "number of days opererating"
 * and installs the startOp for that many days and endOp for the rest of the
 * days in the month
 */
public class DisaggregateMonthsDaysToOps extends DisaggregateMonths {

	private double startOp;
	private double endOp;

	public DisaggregateMonthsDaysToOps(int nMon, double startOp, double endOp) {
		super(nMon);
		this.startOp = startOp;
		this.endOp = endOp;
	}

	/**
	 * Disaggregate monthly data looking back in time If that is a bad direction we
	 * can fix it.
	 */
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

	public double getStartOp() {
		return startOp;
	}

	public void setStartOp(double startOp) {
		this.startOp = startOp;
	}

	public double getEndOp() {
		return endOp;
	}

	public void setEndOp(double endOp) {
		this.endOp = endOp;
	}

}
