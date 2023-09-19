package calsim.surrogate;

public interface DailyToSurrogate {
	
	
	/**
	 * Take daily inputs and create the actual values used in the surrogate such as ANN.
	 * The output is particular to the ANN. For example, the traditional
	 * CalSIM ANN packages the daily data as 7 individual daily values plus 11 aggregations of 10 days apiece.
     * 
	 * @param history
	 * @param extData
	 * @param currentIndex
	 * @return
	 * //TODO it would be nice if the ANN wrapper took on this very specific task
	 */
	public double[] dailyToSurrogateInput(double[] data, int currentIndex); 
	
}
