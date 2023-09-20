package calsim.surrogate;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class DisaggregateMonthsTest {


	@Test
	void testOffsetFirstMonth() {
	    DisaggregateMonths dis = new DisaggregateMonthsSpline(5);
	    int[] test=dis.daysMonth(1976,5);
	    int zeroIndex = dis.offsetFirstMonth(2020, 5);
        assertTrue(zeroIndex == 121); 
	}
	
	@Test
	void testGetNMonth() {
		int stdBufSize = 5;  // Interpolant will cover the present month and four past
		DisaggregateMonths dis = new DisaggregateMonthsSpline(stdBufSize);
		assertTrue(dis.getNMonth() == 5);
	}
	
	
	/**
	 * This is really no more than an example
	 */
	@Test
	void testApply() {
		
		int stdBufSize = 5;  // Interpolant will cover the present month and four past
		DisaggregateMonths dis = new DisaggregateMonthsSpline(stdBufSize);

		// These are data for Apr, Mar, Feb, Jan, Dec
		double[] dataRev = {2000.,2235.,2400.,500.,800.};
		double[][] ts = dis.asIrregArray(1976,4,dataRev);
				
		double[] fit = dis.apply(1976, 4, dataRev);
	}
	

	
}
