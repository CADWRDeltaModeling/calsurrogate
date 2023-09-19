package calsim.surrogate;

public class DefaultDailyToSurrogate implements DailyToSurrogate {

	private int lenHistory;
	private boolean reverse;

	public DefaultDailyToSurrogate(int nHistory, boolean rev) {
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
