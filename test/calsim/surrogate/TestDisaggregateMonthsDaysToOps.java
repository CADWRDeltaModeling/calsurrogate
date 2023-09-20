package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDisaggregateMonthsDaysToOps {

	@Test
	void testApply() {
		
        DisaggregateMonths toOps = new DisaggregateMonthsDaysToOps(5);
        
        double[] dataRev = {30.,31.,20.,14.,0.};
        
        double[] result = toOps.apply(2009,11,dataRev);

		/** Disaggregate monthly data looking back in time
		 *  If that is a bad direction we can fix it.
		 */
	}

}
