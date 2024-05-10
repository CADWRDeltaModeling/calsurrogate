package calsim.surrogate;

/**
 * Disaggregation that repeats the monthly value through the month
 */
public class DisaggregateMonthsRepeat extends DisaggregateMonths {

	public DisaggregateMonthsRepeat(int nMon) {
		super(nMon);
	}

	@Override
	public double[] apply(int year, int month, double[] dataRev) {
		double[][] ts = asIrregArray(year, month, dataRev);
		int xNewMax = (int) ts[0][this.getNMonth()];
		int nCoarse = ts[0].length;
		double xNewMin = ts[0][0];
		double[] out = new double[xNewMax];
		for (int iCoarse = 0; iCoarse < (nCoarse - 1); iCoarse++) {
			int istart = (int) ts[0][iCoarse];
			int ilen = (int) ts[0][iCoarse + 1];
			double repVal = ts[1][iCoarse];
			for (int iFine = istart; iFine < ilen; iFine++) {
				out[iFine] = repVal;
			}
		}

		return out;
	}

}
