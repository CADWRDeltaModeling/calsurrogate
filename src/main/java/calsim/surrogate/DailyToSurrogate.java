package calsim.surrogate;

/**
 * Base interface for converting daily input data into the surrogate input format expected 
 * by different ANN architectures.
 *
 * <p>The surrogate input format may involve grouping individual daily values or creating 
 * block averages over a specified number of days. For example, the traditional CalSim ANN 
 * for MLPs uses a backward-looking input composed of several individual daily values followed 
 * by aggregated (averaged) blocks of days. In contrast, recurrent neural networks such as 
 * GRU/LSTM may use a simple pass-through of the daily history in forward chronological order.</p>
 *
 * <p>Implementations of this interface should pay attention to the order of data. 
 * By tradition, monthly data are passed in from CalSim backward. 
 * They are disaggregated to daily using the DisaggregateMonths interface, 
 * at which time values are stored as arrays of daily data. The present
 * step takes that array and forms the input data, assuming that the day being
 * evaluated in the ANN lies at the currentIndex. Often we will be marching over
 * time (say, over the current month).
 */
public interface DailyToSurrogate {

    /**
     * Converts a daily history into the surrogate input vector used by the ANN.
     *
     * <p>The conversion may include selecting individual days, grouping and averaging blocks 
     * of days, or simply reversing the order of the daily data. For example, the traditional 
     * CalSim ANN packaging for MLPs uses a set of individual daily values followed by block 
     * averages computed over historical periods.</p>
     *
     * @param data         the array containing the full daily history.
     * @param currentIndex the index in the array corresponding to the current day.
     * @return a surrogate input vector for the ANN.
     */
	public double[] dailyToSurrogateInput(double[] data, int currentIndex);

}
