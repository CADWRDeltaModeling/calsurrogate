package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DisaggregateMonthsDaysToOpsTest {

	@Test
	void testApply() {
        /*
		DisaggregateMonths toOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);

		double[] dataRev = { 30., 31., 20., 14., 0. }; // nov,oct,sep,aug,jul
		double[] result = toOps.apply(2009, 11, dataRev);

		//for (int i = 0; i < result.length; i++) {
		//	System.out.println("i " + i + " res " + result[i]);
		//}
		assertEquals(result[0], 0.);
		assertEquals(result[10], 0.);
		assertEquals(result[31], 1.);
		assertEquals(result[44], 1.);
		assertEquals(result[45], 0.);
		assertEquals(result[62], 1.);
        */
	}
	
	@Test
	void testApplyJuly() {
        // This is a regression test for an index error
		DisaggregateMonths toOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);

		double[] dataRev = { 0., 30., 20., 14., 31. }; // jul,jun,may,apr,mar
		double[] result = toOps.apply(1922, 7, dataRev);


	}	
	

}
