package calsim.surrogate;



class MonthlyMax14Day extends AggregateMonths{

	public double aggregate(double[] daily,int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

		int startIndex = firstMonthIndex + startDayOfMonth - 1;
		int stopIndex = firstMonthIndex + endDayOfMonth;

		double max = -9999;
		for (int i=startIndex; i<stopIndex; i++) {
			double ave14 = 0.;
		    for (int j = i-14+1; j<=i ; j++) {
			    ave14 += daily[j];
		    }
		    ave14/=14;
		    if (ave14 > max) max=ave14;
		}
		return max;	
	}
}


/////////////////////////////////////////////////////////////////////////////////////////////////////



class MonthlyMax extends AggregateMonths{

	public double aggregate(double[] daily,int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

		int startIndex = firstMonthIndex + startDayOfMonth - 1;
		int stopIndex = firstMonthIndex + endDayOfMonth;

		double max = -9999;
		for (int i=startIndex; i<stopIndex; i++) {
			if (daily[i] > max) max=daily[i];
		}
		return max;	
	}
}


/////////////////////////////////////////////////////////////////////////////////////////////////////

class MonthlyMean extends AggregateMonths{

	public double aggregate(double[] daily,int firstMonthIndex, int startDayOfMonth, int endDayOfMonth) {

		int startIndex = firstMonthIndex + startDayOfMonth - 1;
		int stopIndex = firstMonthIndex + endDayOfMonth;

		double total = 0.;
		startIndex = 0;
		stopIndex =endDayOfMonth; // TODO THis won't work for averages that reach back before first
		for (int i=startIndex; i<stopIndex; i++) {
			System.out.println(daily[i]);
			total +=daily[i];
		}
		return total/((double) (endDayOfMonth-startDayOfMonth +1));
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

public abstract class AggregateMonths {

	public enum AggType {
		MONTHLY_MEAN {
			@Override
			public AggregateMonths getAggregator() {
				return new MonthlyMean();
			}
		},
		MONTHLY_MAX {
			@Override
			public AggregateMonths getAggregator() {
				return new MonthlyMax();
			}
		},		
		MONTHLY_MAX_14D {
			@Override
			public AggregateMonths getAggregator() {
				return new MonthlyMax14Day();
			}
		},		
		;

		public abstract AggregateMonths getAggregator();

	};

	public AggregateMonths getAggregator() {
		return null;
	}

	public abstract double aggregate(double[] daily, int firstMonthIndex, int startDayOfMonth, int endDayOfMonth);
}



