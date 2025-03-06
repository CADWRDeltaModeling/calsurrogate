package calsim.surrogate;



/**
 * Implementation of {@link DailyToSurrogate} that produces the traditional CalSim ANN input 
 * for MLPs.
 *
 * <p>This implementation packages the daily data as follows:
 * <ul>
 *   <li><strong>Individual Days:</strong> The first portion of the output consists of a specified 
 *       number of individual daily values taken in reverse chronological order (i.e., current day, 
 *       then yesterday, etc.).</li>
 *   <li><strong>Block Averages:</strong> Following the individual daily values, the remaining 
 *       portion of the output contains block averages. Each block is computed by averaging a fixed 
 *       number of days (the block length) from a further back portion of the history. The blocks 
 *       are also considered in reverse chronological order (the earliest days in the block are 
 *       averaged first, but the resulting average is positioned so that later (more recent) blocks 
 *       appear first in the output).</li>
 * </ul>
 * </p>
 *
 * <p>For example, a typical configuration might use 7 individual daily values and 10 blocks, each 
 * representing an 11-day average. In this case, the output vector will have a total length of 
 * 7 + 10 = 17, where indices 0 to 6 represent the individual days (current day at index 0) and 
 * indices 7 to 16 represent the block averages.</p>
 */
public class DailyToSurrogateBlocked implements DailyToSurrogate {

	private int nDaily;
	private int nBlock;
	private int blockLen;

	public DailyToSurrogateBlocked(int nDaily, int nBlock, int blockLen) {
		this.nDaily = nDaily;
		this.nBlock = nBlock;
		this.blockLen = blockLen;
	}

	/**
	 * The traditional CalSIM ANN packages the daily data as 7 individual daily
	 * values plus 10 aggregations of 11 days apiece.
	 * 
	 * @param history
	 * @param extData
	 * @param currentIndex
	 * @returns
	 */
	public double[] dailyToSurrogateInput(double[] input, int currentIndex) {
		// Number of predictors that are CalSIM values, (e.g. 6-7 flows at different
		// locations)

		// Copies the 7 preceding days in reverse order, from current day back
		double[] outData = new double[nDaily + nBlock];
		for (int ndxRev = 0; ndxRev < nDaily; ndxRev++) {
			int ndxDaily = currentIndex - ndxRev;
			outData[ndxRev] = input[ndxDaily];
		}
		// Now go through blocks of history. The blocks are traversed
		// backwards in time, but the averages are created using iterators that move
		// forward within the block
		for (int iblock = 0; iblock < nBlock; iblock++) {
			// blockStart is the index at the beginning (early side) of the block
			int blockStart = currentIndex - nDaily - blockLen * (iblock + 1) + 1;
			// And average them. The sums are done earlier to later but the output
			// is always packed in reverse chronological
			double blockSum = 0.;
			int blockStop = blockStart + blockLen;
			for (int ndxDaily = blockStart; ndxDaily < blockStop; ndxDaily++) {
				blockSum += input[ndxDaily];
			}
			outData[nDaily + iblock] = blockSum / ((double) blockLen);
		}
		return outData;
	}

}
