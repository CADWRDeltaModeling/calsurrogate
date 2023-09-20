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
		DisaggregateMonths disagg = new DisaggregateMonthsSpline(nMonthHist);
		AggregateMonths agg = AggregateMonths.AggType.MONTHLY_MEAN.getAggregator();
		SurrogateMonth annMonth = new SurrogateMonth(disagg, mock, agg); 

		//double[][] sac = new double[1][5];
		//double[][] exp = new double[1][5]];
		double[][] sac = {{10000.,20000.,10000.,15000,20000},{10000.,20000.,10000.,15000,20000}};
		double[][] exp = {{4000.,4000.,12000.,12000.,12000.},{4000.,4000.,4000.,12000.,12000.}};
		double[][] mix0 = {{1.,1.,0.33,0.33,0.33},{0.,0.,0.3,0.3,0.3}};
		double[][] mix1 = {{0.,0.,0.33,0.33,0.33},{1.,1.,0.6,0.6,0.6}};
		double[][] mix2 = {{0.,0.,0.,0.,0.},{0.,0.,0.6,0.6,0.6}};	
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(mix0);
		floatInput.add(mix1);
		floatInput.add(mix2);
		
		float[][] est = mock.estimate(floatInput,null);
		for (int ibatch = 0; ibatch<2 ; ibatch++) {
			for (int jtime = 0; jtime<4; jtime++) {
				System.out.println(est[ibatch].length);
				System.out.println("Out " + ibatch + "," + jtime + "  out: " + est[ibatch][jtime]);
			}
		}
		

		int loc = 0;
		int year = 2010;
		int month = 10;
		int cycle = 22;
		double[][] monthly =  annMonth.annMonth(floatInput, loc, year, month, cycle);
		System.out.println("ok");	
		System.out.println(monthly.length);
		System.out.println(monthly[1].length);
		
	}

}
