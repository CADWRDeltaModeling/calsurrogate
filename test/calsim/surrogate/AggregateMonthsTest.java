package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class AggregateMonthsTest {

	@Test
	void testAggregateMonths() {
        
		double[] data = new double[118];
		for (int i=0; i < data.length; i++) {
			data[i]=(double) i;
		}
		AggregateMonths mave = AggregateMonths.MONTHLY_MEAN;
		System.out.println(mave.aggregate(data,85, 1, 20) );
		
		
		AggregateMonths mmax = AggregateMonths.MONTHLY_MAX;
		System.out.println(mmax.aggregate(data,85, 1, 20) );
		
		AggregateMonths mmax14 = AggregateMonths.MONTHLY_MAX_14D;
		System.out.println(mmax14.aggregate(data,85, 1, 20) );		
	}

}
