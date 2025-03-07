package calsim.surrogate;

/**
 * Implementation of {@link DailyToSurrogate} that provides a direct pass-through of the daily history.
 *
 * <p>This implementation is intended for architectures such as GRU or LSTM where the full sequence of 
 * daily data is used as input without additional grouping or averaging. The data can be returned either 
 * in reverse chronological order (with the current day first) or in forward chronological order, depending 
 * on the value of the {@code reverse} flag.
 * </p>
 *
 * <p>When {@code reverse} is set to {@code true}, the method returns the last {@code lenHistory} values 
 * in reverse order, starting from the current day and moving backwards. When {@code reverse} is {@code false}, 
 * the method returns the days in forward order from the oldest day in the history window to the current day.
 * </p>
 */
public class DailyToSurrogateDefault implements DailyToSurrogate {

	private int lenHistory;
	private boolean reverse;
	
    /**
     * Constructs a new instance for passing through daily history.
     *
     * @param nHistory the number of daily values expected for the ANN
     * @param rev      if {@code true} the output sequence will be in reverse chronological order 
     *                 (current day first); if {@code false} the output sequence will be in forward 
     *                 chronological order (oldest day first).
     */
	public DailyToSurrogateDefault(int nHistory, boolean rev) {
		this.lenHistory = nHistory;
		this.reverse = rev;
	}

    /**
     * Converts the daily history into a surrogate input vector by either reversing the order or 
     * returning it in chronological order.
     *
     * <p>If {@code reverse} is {@code true}, the output will have the current day at index 0, 
     * followed by preceding days. If {@code reverse} is {@code false}, the output will be ordered 
     * from the oldest day in the history window to the current day.</p>
     *
     * @param data         the array containing the full daily history.
     * @param currentIndex the index corresponding to the current day.
     * @return a surrogate input vector containing the daily history in the specified order.
     */
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
    
    /**
     * Returns the length of the history window (number of days).
     *
     * @return the number of days included in the output sequence.
     */
	public int getLenHistory() {
		return lenHistory;
	}

	 /**
     * Returns the current ordering mode.
     *
     * @return {@code true} if the output is in reverse chronological order; {@code false} if in forward order.
     */
	public boolean isReverse() {
		return reverse;
	}


}
