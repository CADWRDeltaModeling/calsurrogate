package calsim.surrogate;

import java.time.YearMonth;
import java.io.*;
import java.util.Random;

/**
 * Base class and default implementation for 
 * taking a number (usually) of present and past monthly averages
 * and obtaining a daily history. The default implementation is 
 * based on ConservativeSpline, a rational histospline from Splath
 * that is also implemented in vtools for python. 
 * 
 * The CalSIM use of the spline has two issues. First, ConservativeSpline was
 * used for consistency with DSM2, but the DSM2 use is always used on
 * the full series whereas the situation in CalSIM is usually one sided,
 * meaning that we know the past and have a guess at the present but don't
 * know the future. So the sought-after consistency isn't going to be 
 * achieved (or even close). Second, the use for both DSM2 and CalSIM uses
 * a very high penalty which means that the usefulness of the spline for
 * being more accurate is severely tapped down and it really just rounds some corners 
 * and still has a stairstep look This means that relative to an accurate spline
 * the stairstepping will produce sawtooth residuals of a period of one month. 
 */
public class DisaggregateMonths{

	private int nMonth;
	
	/**
	 * Constructor based on number of months in memory, current month inclusive. 
	 * Current practice is typically 5
	 * @param nMon
	 */
	public DisaggregateMonths(int nMon) {
		nMonth = nMon;
	}
	
	public int getNMonth() { return nMonth; }
	
	// Is this what we want? Is it exact?
	public int offsetFirstMonth(int year, int month) {
		int[] daysMonth = this.daysMonth(year, month);
		int cum=0;
		for (int i=1; i< daysMonth.length; i++) {
			cum += daysMonth[i];
		}
		return cum;
	}
	
	/** Disaggregate monthly data looking back in time
	 *  If that is a bad direction we can fix it.
	 */
	public double[] apply(int year, int month, double[] dataRev) {
		double[][] ts = asIrregArray(year,month,dataRev);
		double pqScalar = 2;
		double[] pq =  {pqScalar,pqScalar,pqScalar,pqScalar,pqScalar,pqScalar};  //TODO
		double y0 = ts[1][0]; 
		double yn = ts[1][nMonth];
		double ymin = -0.00000001; //TODO fix the minimum reinforcement
		ConservativeSpline spline = new ConservativeSpline(ts[0],ts[1],
				                                           pq,y0,yn,ymin);
	    double xNewMax = ts[0][nMonth];
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


	
	/** Lists the number of days in month for the nMonth most
	 * recent months ending in month, in decending order (
	 * @param year to be analyzed
	 * @param month latest in time entry in list (index 0), using Jan=1
	 * @return integer array of number of days
	 */
	public int[] daysMonth(int year, int month) {
		YearMonth yMonth = YearMonth.of(year,month);
		int[] out = new int[nMonth];
		for (int iMonth = 0; iMonth<nMonth; iMonth++) {
			YearMonth minus = yMonth.minusMonths((long) iMonth);
		    out[iMonth] = minus.lengthOfMonth();
		}
		
		return out;
	}

	/**
	 * Returns a time series indexed by days where each time entry is the
	 * start (Jan 1 0:00) of the month. An entry is appended representing
	 * the start of the next (month+1) month. The data 
	 * @param year
	 * @param month
	 * @param dataRev data to populate array starting with index 0 
	 *        for month in call and looking back
	 * @return irreg[][] where the entries in irreg[0][:] are the times 
	 *                    and irreg[1] are the data. The last entry in irreg[1][:]
	 *                    is not defined, but populated in such a way as to repeat
	 *                    the previous value.
	 */
	public double[][] asIrregArray(int year, int month, double[] dataRev) {
		double[][] ts = new double[2][nMonth+1];
		Random rand = new Random();
		rand.setSeed(19);
		ts[0][0] = 0.;
		int[] dMon = daysMonth(year,month);
		for (int iMonth = 0; iMonth<nMonth; iMonth++) {
			int revIndex = nMonth - 1 - iMonth;
		    ts[0][iMonth+1] = ts[0][iMonth] + (double)dMon[revIndex];
		    ts[1][iMonth] = dataRev[revIndex]; 
		}
		// This populates the undefined value in last index of data
		ts[1][nMonth] = dataRev[0];
		return ts;
	}

	
}


