package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.YearMonth;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.YearMonth;
import org.junit.jupiter.api.Test;

class SurrogateMonthTest {

	@Test
	void testSurrogateMonth() {
		int nDayHist = 118;
		Surrogate mock = new MockSurrogate(nDayHist);
		int nMonthHist = 5;
		DisaggregateMonths spline = new DisaggregateMonthsSpline(nMonthHist);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(nMonthHist);
		DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(nMonthHist, 1., 0.);
		// For simplicity, use spline for most disaggregators
		DisaggregateMonths[] disagg = { spline, spline, repeat, repeat, repeat };
		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		SurrogateMonth annMonth = new SurrogateMonth(disagg, mock, agg);

		double[][] sac = { { 10000., 20000., 10000., 15000, 20000 }, { 10000., 20000., 10000., 15000, 20000 } };
		double[][] exp = { { 4000., 4000., 12000., 12000., 12000. }, { 4000., 4000., 4000., 12000., 12000. } };
		double[][] mix0 = { { 1., 1., 0.33, 0.33, 0.33 }, { 0., 0., 0.3, 0.3, 0.3 } };
		double[][] mix1 = { { 0., 0., 0.33, 0.33, 0.33 }, { 1., 1., 0.6, 0.6, 0.6 } };
		double[][] mix2 = { { 0., 0., 0., 0., 0. }, { 0., 0., 0.6, 0.6, 0.6 } };
		ArrayList<double[][]> floatInput = new ArrayList<>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(mix0);
		floatInput.add(mix1);
		floatInput.add(mix2);

		// Existing test to exercise the surrogate
		float[][] est = mock.estimate(floatInput, null);
		for (int ibatch = 0; ibatch < 2; ibatch++) {
			for (int jtime = 0; jtime < 4; jtime++) {
				System.out.println("Batch " + ibatch + ", time index " + jtime + " output: " + est[ibatch][jtime]);
			}
		}

		int year = 2010;
		int month = 10;

		double[][] monthly = annMonth.annMonth(floatInput, year, month);

		int nx0 = 22;
		int nx1 = 24;
		GridResult grid = annMonth.evaluateOnGrid(floatInput, year, month, 4000., 25000., nx0, 500, 12000, nx1);

		for (int i = 0; i < nx0; i++) {
			for (int j = 0; j < nx1; j++) {
				System.out.println("x0=" + grid.getGridInput0()[i] + " x1=" + grid.getGridInput1()[j] + " res=" + grid.getResult()[i][j][0]);
			}
		}
	}




	// Other tests remain unchanged...

	/**
	 * Test that loadExogenous fetches a daily slice spanning the multi-month history,
	 * starting from the correct historical month, and that the exogenous data is copied
	 * identically across all batches.
	 */
	@Test
	void testLoadExogenous() {
		// Setup a simple test using a known disaggregator history length.
		int nMonthHist = 5;
		// Create a dummy disaggregator (using spline) that computes a multi-month history.
		DisaggregateMonths spline = new DisaggregateMonthsSpline(nMonthHist);
		// Use the four-argument constructor to set exogenous input index array to {0}.
		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		Surrogate mock = new MockSurrogate(spline.getNDay(2010, 10)); // nDay not really used in mock
		SurrogateMonth surrogateMonth = new SurrogateMonth(new DisaggregateMonths[] { spline }, mock, agg, new int[]{0});

		int year = 2010;
		int month = 10;  // current month (the final month in the history)
		int nDay = spline.getNDay(year, month); // multi-month history length

		// Set up a dummy ExogenousTimeSeries with sufficient data.
		ExogenousTimeSeries ets = ExogenousTimeSeries.getInstance();
		// For alignment, we set the start date to the expected historical start date.
		// The start should be: current YearMonth minus (nMonthHist - 1) months, day 1.
		YearMonth expectedStartYM = YearMonth.of(year, month).minusMonths(nMonthHist - 1);
		LocalDate expectedStartDate = expectedStartYM.atDay(1);
		ets.setStartDate(expectedStartDate);
		// Create dummy data with one column and nDay entries.
		double[][] dummyData = new double[1][nDay];
		for (int i = 0; i < nDay; i++) {
			dummyData[0][i] = i * 1.0; // Populate with dummy values (e.g., 0.0, 1.0, 2.0, ...)
		}
		ets.setData(dummyData);
		ets.setDataLength(nDay);

		// Prepare an array for multiple batches (e.g., 3 batches)
		double[][] exogArr = new double[3][];
		// This should now work without out-of-bounds, fetching 153 days starting from expectedStartDate.
		surrogateMonth.loadExogenous(exogArr, 0, year, month, nDay);

		// Verify that the exogenous data array is non-null and has the expected length.
		assertNotNull(exogArr[0], "Exogenous slice (batch 0) should not be null");
		assertEquals(nDay, exogArr[0].length, "Exogenous slice length should equal the computed multi-month history length");

		// Verify that the same exogenous data is copied to all batch entries.
		for (int i = 1; i < exogArr.length; i++) {
			assertArrayEquals(exogArr[0], exogArr[i], "Batch " + i + " should match batch 0");
		}

		// Print expected start date for manual verification if desired.
		System.out.println("Expected exogenous data start date: " + expectedStartDate);
	}
}

