package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import calsim.surrogate.examples.ExampleExogAssignment;

class SalinitySurrogateManagerTest {

	@Test
	void testSalinityManager() {
		SalinitySurrogateManager ssm = SalinitySurrogateManager.INSTANCE;
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

		int year = 2010;
		int month = 10;	
		int cycle = 13;
		int monthly = 0;

		RunRecord rec = new RunRecord(mock, sac[0][0],exp[0][0],0,0,year,month,cycle,monthly);
		ssm.logInputs(annMonth, rec, floatInput, false,"","");
		
	}
	
	
	@Test
	void testSurrogateForSite() {
		SalinitySurrogateManager ssm = SalinitySurrogateManager.INSTANCE;
		int nDayHist = 118;
		Surrogate mock = new MockSurrogate(nDayHist);
		//int nMonthHist = 5;
		DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
		//DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);
		DisaggregateMonths[] disagg = { spline, spline, repeat, repeat, repeat };
		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		List<ExogTimeSeriesAssignment> assigns = null;
			
		SurrogateMonth annMonth00 = new SurrogateMonth(disagg, mock, agg, assigns);
		SurrogateMonth annMonth01 = new SurrogateMonth(disagg, mock, agg, assigns);
		SurrogateMonth annMonth10 = new SurrogateMonth(disagg, mock, agg, assigns);
		SurrogateMonth annMonth11 = new SurrogateMonth(disagg, mock, agg, assigns);
        ssm.setSurrogateForSite(0, 0, annMonth00);
        ssm.setSurrogateForSite(0, 1, annMonth01);
        ssm.setSurrogateForSite(1, 0, annMonth10);
        ssm.setSurrogateForSite(1, 1, annMonth11);
        assertTrue(ssm.hasSurrogateForSite(1, 0));
        assertTrue(!ssm.hasSurrogateForSite(2, 0));
		assertTrue(ssm.getSurrogateForSite(0, 0) != annMonth11);        
		assertTrue(ssm.getSurrogateForSite(0, 0) == annMonth00);   //must be == not equals()
		assertTrue(ssm.getSurrogateForSite(0, 1) == annMonth01);
		assertTrue(ssm.getSurrogateForSite(1, 0) == annMonth10);
		assertTrue(ssm.getSurrogateForSite(1, 1) == annMonth11);

	}	
	
	/*
	@Test
	void testSurrogateMonthLogHeader() {
		int nDayHist = 118;
		Surrogate mock = new MockSurrogate(nDayHist);
		int nMonthHist = 5;
		DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
		DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);
		DisaggregateMonths[] disagg = { spline, spline, repeat, repeat, repeat };
		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		SurrogateMonth annMonth = new SurrogateMonth(disagg, mock, agg);

		// double[][] sac = new double[1][5];
		// double[][] exp = new double[1][5]];
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

		InputSizeInfo sizeInfo = new InputSizeInfo(floatInput);
		String header = annMonth.inputLogHeader(sizeInfo);
		System.out.println(header);
		// (Surrogate surrogate, double float0, double float1, int int0, int int1, int
		// year, int month, int cycle, int aveType)
		RunRecord rec = new RunRecord(mock, sac[0][0], exp[0][0], 0, 0, 2010, 5, 13, 0);
		String entry = annMonth.inputLogEntry(rec, floatInput, sizeInfo, true);
		System.out.println(entry);

	} */	

}
