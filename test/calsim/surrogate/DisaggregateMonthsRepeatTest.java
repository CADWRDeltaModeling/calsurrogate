package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DisaggregateMonthsRepeatTest {

	@Test
	void testApply() {

		DisaggregateMonths toOps = new DisaggregateMonthsRepeat(5);

		double[] dataRev = { 3., 1., 0., 2., 1. }; // nov,oct,sep,aug,jul
		double[] result = toOps.apply(2009, 11, dataRev);

		for (int i = 0; i < result.length; i++) {
			System.out.println("i " + i + " res " + result[i]);
		}
		assertEquals(result.length, 153);
		assertEquals(result[0], 1.);
		assertEquals(result[30], 1.);
		assertEquals(result[31], 2.);
		assertEquals(result[61], 2.);
		assertEquals(result[62], 0.);

	}

}
