package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//2018-09-01,4.11
//2018-09-02,4.58
//2018-09-03,5.00
//2018-09-04,5.45


class ExogenousTimeSeriesTest
{

	@Test
	void testExogenousTimeSeries() {
			ExogenousTimeSeries ext = ExogenousTimeSeries.getInstance();
			double[] slice = ext.dailyDataSlice(0, 2018, 9, 1, 4);
			for (int i = 0; i < slice.length; i++) {
				System.out.println(slice[i]);
			}

	}

}
