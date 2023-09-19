package calsim.surrogate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RunRecord {

	ArrayList<double[][]> floatInput;
	ArrayList<long[][]> intInput;
	int year;
	int month;
	int cycle;
	int[] hashBatch = { 0 };
	int[] hashIndex = { 0, 1 };
	int[] hashFeatures = { 0 };

	public RunRecord(ArrayList<double[][]> floatInput, ArrayList<long[][]> intInput, int year, int month, int cycle) {
		this.floatInput = floatInput;
		this.intInput = intInput;
		this.year = year;
		this.cycle = cycle;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
		result = prime * result + month;
		result = prime * result + cycle;
		if (floatInput != null) {
			if (floatInput.size() > 0) {
				int icap = hashFeatures.length;
				for (int i = 0; i < icap; i++) { // TODO size safety
					int ib = hashFeatures[i];
					int jcap = hashIndex.length;
					for (int k = 0; k < hashBatch.length; k++) {
						int kb = hashBatch[k];

						for (int j = 0; j < jcap; j++) {
							int jh = hashIndex[j];
							System.out.println("j " + jcap + " " + this.floatInput.get(ib)[0][jh]);
							result = prime * result + Double.hashCode(this.floatInput.get(i)[kb][jh]);
						}
					}
				}
			}
		}
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
		return cycle == other.cycle && Objects.equals(floatInput, other.floatInput)
				&& Objects.equals(intInput, other.intInput) && month == other.month && year == other.year;
	}

	public static void main(String[] argv) {
		double[][] x0 = { { 1., 2., 7. }, { 3., 4., 5. } };
		double[][] x1 = { { 1., 2., 0. }, { 3., 4., 10. } };
		ArrayList<double[][]> floatInput0 = new ArrayList<double[][]>(Arrays.asList(x0, x1));
		RunRecord r0 = new RunRecord(floatInput0, null, 2001, 2, 15);
		System.out.println(r0.hashCode());
		floatInput0.get(0)[0][0]=100.;
		System.out.println(r0.hashCode());
		floatInput0.get(0)[0][0]=1.;
		System.out.println(r0.hashCode());
		
		
		double[][] x2 = { { 1., 2., 7. }, { 3., 4., 5. } };
		double[][] x3 = { { 1., 2., 0. }, { 3., 5., 11. } };
		ArrayList<double[][]> floatInput1 = new ArrayList<double[][]>(Arrays.asList(x2, x3));
		RunRecord r1 = new RunRecord(floatInput1, null, 2001, 2, 15);

		System.out.println(r1.hashCode());

	}
}
