package calsim.surrogate;

public class DisaggregateMonthsSpline extends DisaggregateMonths {

	public DisaggregateMonthsSpline(int nMon) {
		super(nMon);
	}

	/** Disaggregate monthly data looking back in time
	 *  If that is a bad direction we can fix it.
	 */
	public double[] apply(int year, int month, double[] dataRev) {
		double[][] ts = asIrregArray(year,month,dataRev);
		double pqScalar = 2;
		double[] pq =  {pqScalar,pqScalar,pqScalar,pqScalar,pqScalar,pqScalar};  //TODO
		double y0 = ts[1][0]; 
		double yn = ts[1][this.getNMonth()];
		double ymin = -0.00000001; //TODO fix the minimum reinforcement
		ConservativeSpline spline = new ConservativeSpline(ts[0],ts[1],
				                                           pq,y0,yn,ymin);
	    double xNewMax = ts[0][this.getNMonth()];
	    int xNewLen = (int)xNewMax+1;
		double[] xnew = new double[xNewLen];
		double[] out = new double[xNewLen]; 
		for(int ix = 0; ix < xNewLen; ix++) {
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
