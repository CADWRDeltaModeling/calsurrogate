package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class InverseSurrogateMonthTest {

	

	@Test
	void testInverseSurrogateMonth() {
		int nDayHist = 118;
		Surrogate mock = new MockSurrogate(nDayHist);
		int nMonthHist = 5;
		DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
		DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);
		DisaggregateMonths[] disagg = { spline, spline, repeat, repeat, repeat };
		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		SurrogateMonth annMonth = new SurrogateMonth(disagg, mock, agg);

		double[][] sac = { {10000., 12000., 10000., 20000, 20000. }};
		double[][] exp = { { 4000., 4000., 12000., 12000.,12000. } };
		double[][] mix0 = { { 1., 1., 0.33, 0.33,0.33 } };
		double[][] mix1 = { { 0., 0., 0.33, 0.33,0.33 } };
		double[][] mix2 = { { 0., 0., 0., 0., 0. } };
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(mix0);
		floatInput.add(mix1);
		floatInput.add(mix2);

		System.out.println("/////////////////////");		
		double target=1000.;
		int searchIndex = 0;
		double loBound = 4000;
		double hiBound = 24000.;
		int year = 1990;
		int month = 2;
		int iLoc = 1;
		InverseSurrogateMonth inverse = new InverseSurrogateMonth(annMonth);
		double out0 = inverse.invert(target, floatInput, searchIndex,loBound, hiBound,year,month,iLoc);
		assertEquals(out0,10000.,500.);

		System.out.println("Adjustment using index 0 (1): " + out0);
		
		System.out.println("/////////////////////");	
        target = 300;
		floatInput.get(0)[0][0] = 10000.;
		floatInput.get(1)[0][0] = 6000.; //exp
		floatInput.get(2)[0][0] = 0.0;
		floatInput.get(3)[0][0] = 1.0;
		floatInput.get(4)[0][0] = 0.0;
		searchIndex = 0;		
		loBound = 4000;
		hiBound = 24000.;
		
		double out2 = inverse.invert(target, floatInput, searchIndex,loBound, hiBound,year,month,iLoc);
		System.out.println("Adjustment using index 0 (2): " + out2);		
		
		System.out.println("/////////////////////");			
		loBound = 800;
		hiBound = 12000.;
		searchIndex = 1;
		floatInput.get(0)[0][0] = 10000.;
		floatInput.get(1)[0][0] = 6000.;
		double out1 = inverse.invert(target, floatInput, searchIndex ,loBound, hiBound,year,month,iLoc);		
		System.out.println("Adjustment using index 1: " + out1);	
		//TODO make into separate test with fixture
		
		
		
		double[][] monthly = annMonth.annMonth(floatInput, year, month);
		System.out.println("ok");
		System.out.println(monthly.length);

	}
}
