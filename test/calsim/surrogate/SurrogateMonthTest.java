package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class SurrogateMonthTest {

	@Test
	void testSurrogateMonth() {
		int nDayHist = 118;
		Surrogate mock = new MockSurrogate(nDayHist);
		int nMonthHist = 5;
		DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
		DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);
		DisaggregateMonths[] disagg = { spline, spline, repeat, repeat, repeat };
		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		SurrogateMonth annMonth = new SurrogateMonth(disagg, mock, agg);

		double[][] sac = { { 10000., 20000., 10000., 15000, 20000 }, { 10000., 20000., 10000., 15000, 20000 } };
		double[][] exp = { { 4000., 4000., 12000., 12000., 12000. }, { 4000., 4000., 4000., 12000., 12000. } };
		double[][] mix0 = { { 1., 1., 0.33, 0.33, 0.33 }, { 0., 0., 0.3, 0.3, 0.3 } };
		double[][] mix1 = { { 0., 0., 0.33, 0.33, 0.33 }, { 1., 1., 0.6, 0.6, 0.6 } };
		double[][] mix2 = { { 0., 0., 0., 0., 0. }, { 0., 0., 0.6, 0.6, 0.6 } };
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(mix0);
		floatInput.add(mix1);
		floatInput.add(mix2);

		float[][] est = mock.estimate(floatInput, null);
		for (int ibatch = 0; ibatch < 2; ibatch++) {
			for (int jtime = 0; jtime < 4; jtime++) {
				System.out.println(est[ibatch].length);
				System.out.println("Out " + ibatch + "," + jtime + "  out: " + est[ibatch][jtime]);
			}
		}

		int year = 2010;
		int month = 10;

		double[][] monthly = annMonth.annMonth(floatInput, year, month);
		
		int nx0 = 22;
		int nx1 = 24;
		GridResult grid = annMonth.evaluateOnGrid(floatInput, year, month, 4000.,25000.,nx0, 500, 12000,  nx1);
		
		for (int i = 0; i<nx0; i++) {
			for (int j=0; j<nx1; j++) {
				System.out.println("x0="+grid.getGridInput0()[i]+" x1="+grid.getGridInput1()[j]+" res="+grid.getResult()[i][j][0]);			
			}
		}
		for (int i = 0; i<nx0; i++) {
			for (int j=0; j<nx1; j++) {
				System.out.println(grid.getGridInput0()[i]+","+grid.getGridInput1()[j]+","+grid.getResult()[i][j][0]);			
			}
		}		
		
	}



}
