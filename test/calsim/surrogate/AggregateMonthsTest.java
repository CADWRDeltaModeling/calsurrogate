package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AggregateMonthsTest {

	@Test
	void testAggregateMonths() {
        
		double[] data = new double[118];
		for (int i=0; i < data.length; i++) {
			data[i]=(double) i;
		}
		AggregateMonths mave = AggregateMonths.AggType.MONTHLY_MEAN.getAggregator();
		System.out.println(mave.aggregate(data,85, 1, 20) );
		
		
		AggregateMonths mmax = AggregateMonths.AggType.MONTHLY_MAX.getAggregator();
		System.out.println(mmax.aggregate(data,85, 1, 20) );
		
		AggregateMonths mmax14 = AggregateMonths.AggType.MONTHLY_MAX_14D.getAggregator();
		System.out.println(mmax14.aggregate(data,85, 1, 20) );		
	}

}
