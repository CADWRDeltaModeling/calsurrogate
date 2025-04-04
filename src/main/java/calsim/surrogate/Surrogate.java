package calsim.surrogate;

import java.util.ArrayList;

/**
 * Generic representation of a surrogate that estimates salinity at one or more
 * locations given a set of inflows and gate operations. The traditional
 * implementation is an ANN or gmodel. A surrogate may be univariate (estimates
 * one location) or multivariate (estimates several).
 */
public interface Surrogate extends DailyToSurrogate {

	/**
	 * Run the ANN and return results.
	 * 
	 * @param rawData    Data in the form of doubles. The ArrayList enumerates
	 *                   features, the first array dimension is batch index and the
	 *                   second is reserved for dimensionality of the input (e.g.
	 *                   time).
	 * @param rawDataInt Same as RawData but for long integers. Can be null
	 * @return Result of prediction. First dimension is the batch number and second
	 *         is Tensor dimension of the output (e.g. station)
	 */
	public float[][] estimate(ArrayList<double[][]> rawData, ArrayList<long[][]> rawDataInt);

	/**
	 * Return a class string or file name that helps identify the surrogate
	 * @return
	 */
	public default String identifier() {return "None";}

	/**
	 * Return a class string or file name that helps identify the surrogate
	 * @return
	 */
	public default String getName() {return "None";}	
	
	
	/**
	 * Converts a forward-in-time time history with day of forecast at currentIndex
	 * into whatever vector of aggregated history (blocks of averages, convolutions,
	 * etc) used by the surrogate
	 * 
	 * @param dailyData
	 * @param currentIndex
	 * @return
	 */
	public default double[] dailyToSurrogateInput(double[] dailyData, int currentIndex) {
		return getDailyToSurrogate().dailyToSurrogateInput(dailyData, currentIndex);
	};

    /**
     * Returns the input index corresponding to the given input name.
     * Concrete surrogate implementations should override this method.
     *
     * @param inputName the name of the ANN input
     * @return the index for the input, or a negative value if not found.
     */
    default int getInputIndex(String inputName) {
        throw new UnsupportedOperationException("getInputIndex not implemented for surrogate " + identifier());
    }	
	public DailyToSurrogate getDailyToSurrogate();
	
    /**
     * Returns the expected number of features (i.e. the length of the float inputs ArrayList)
     * that this surrogate requires.
     */
    int getNFeatures();	

}