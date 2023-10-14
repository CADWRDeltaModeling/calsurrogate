package calsim.surrogate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class used to produce a hash key for surrogate evaluations. It tracks the year and month and cycle
 * as well as a couple pieces of input data such as the most recent Sac and Export flow. 
 */
public class RunRecord {

	int year;
	int month;
	int cycle;
	int[] hashBatch = { 0 };
	int[] hashIndex = { 0, 1 };
	int[] hashFeatures = { 0 };
	private double floatInput0;
	private double floatInput1;
	private int intInput0;
	private int intInput1;
	private int aveType;
	private Surrogate surrogate;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		result = prime * result + month;
		result = prime * result + cycle;
		result = prime* result + intInput0;
		result = prime* result + intInput1;
		result = prime* result + aveType;
		result = prime* result + Double.hashCode(floatInput0);
		result = prime* result + Double.hashCode(floatInput1);		
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RunRecord other = (RunRecord) obj;
		return cycle == other.cycle
			&& Double.doubleToLongBits(floatInput0) == Double.doubleToLongBits(other.floatInput0)
			&& Double.doubleToLongBits(floatInput1) == Double.doubleToLongBits(other.floatInput1)
            && intInput0 == other.intInput0
			&& intInput1 == other.intInput1 
			&& aveType == other.aveType
			&& month == other.month && Objects.equals(surrogate, other.surrogate)
			&& year == other.year;
	}




	public RunRecord( Surrogate surrogate,
			double float0, double float1, 
			int int0, int int1, 
			int year, int month, int cycle, int aveType) {
		this.floatInput0 = float0;
		this.floatInput1 = float1;
		this.intInput0 = int0;
		this.intInput1 = int1;
		this.year = year;
		this.cycle = cycle;
		this.aveType = aveType;
		this.surrogate = surrogate;

	}




	public static void main(String[] argv) {
        double x0 = 1.;
        double x1 = 2.;
        double x2 = 1.;
        double x3 = 2.;
        
        Surrogate surrogate = new MockSurrogate(118);
        
		RunRecord r0 = new RunRecord(surrogate, x0, x1, 0, 0, 2001, 2, 15,1);
		System.out.println(r0.hashCode());
		
		
		RunRecord r1 = new RunRecord(surrogate, x2, x3, 0, 0, 2001, 2, 15,1);
		System.out.println(r1.hashCode());
		
		boolean same = (r0.equals(r1));
		System.out.println(same);
		
		

	}
}
