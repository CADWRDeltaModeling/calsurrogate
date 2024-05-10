package calsim.surrogate;

/**
 * Implementation of DailyToSurrogate that either returns daily data as-is or
 * reversed in tiem, no groupings. Although this is called "default", it is not
 * the traditional CalSIM ANN grouping
 */
public class DailyToSurrogateDefault implements DailyToSurrogate {

	private int lenHistory;
	private boolean reverse;

	public DailyToSurrogateDefault(int nHistory, boolean rev) {
		this.lenHistory = nHistory;
		this.reverse = rev;
	}

	@Override
	public double[] dailyToSurrogateInput(double[] data, int currentIndex) {
		double[] out = new double[lenHistory];
		if (isReverse()) {
			for (int i = 0; i < lenHistory; i++) {
				out[i] = data[currentIndex - i];
			}
		} else {
			for (int i = 0; i < lenHistory; i++) {
				out[i] = data[currentIndex - lenHistory + i + 1]; // TODO test whether off by one
			}
		}
		return out;
	}

	public int getLenHistory() {
		return lenHistory;
	}

	public void setLennHistory(int nHistory) {
		this.lenHistory = nHistory;
	}

	public boolean isReverse() {
		return reverse;
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

}
