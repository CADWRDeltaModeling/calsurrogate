package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AggregateMonthsTest {

	@Test
	void testAggregateMonths() {

		double[] data = new double[118];
		for (int i = 0; i < data.length; i++) {
			data[i] = (double) i;
		}
		AggregateMonths mave = AggregateMonths.MONTHLY_MEAN;
		System.out.println(mave.aggregate(data, 85, 1, 20));

		AggregateMonths mmax = AggregateMonths.MONTHLY_MAX;
		System.out.println(mmax.aggregate(data, 85, 1, 20));

		AggregateMonths mmax14 = AggregateMonths.MONTHLY_MAX_14D_TRUNCATED;
		System.out.println(mmax14.aggregate(data, 85, 1, 20));
        
		AggregateMonths nth = AggregateMonths.NTH_SMALLEST;
		nth.setN(5);
		System.out.println(nth.aggregate(data,0,1,31));
		
		AggregateMonths count = AggregateMonths.COUNTBELOW;
		count.setThreshold(7.);
		
		System.out.println(count.aggregate(data, 0, 1, 31));
		
		double[] data2 = { 62.,66.,72.,85.,91.,90.,88.,84.,86.,91.};
        
		count.setThreshold(86.);
		System.out.println(count.aggregate(data2, 0, 1, 10 )); // 6
		
		// ordered: 62, 66, 72, 84, *85*, 86, 88, 90, 91, 91 
		System.out.println(nth.aggregate(data2, 0, 1, 10)); // 85
		
		System.out.println("**");
		System.out.println(mmax14.calsimCode);

		
		
		
		for (AggregateMonths agg : AggregateMonths.values()) {
		    System.out.println(agg.calsimCode);
		}
	}
	
	

}
