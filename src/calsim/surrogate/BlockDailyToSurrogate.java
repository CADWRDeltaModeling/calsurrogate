package calsim.surrogate;

/**
 * This repackages the daily flow history in the aggregation common for CalSIM ANNs,
 * which is 8 single days and 10 11-day averages looking back in time.
 */
public class BlockDailyToSurrogate implements DailyToSurrogate{


	private int nDaily;
	private int nBlock;
	private int blockLen;

	public BlockDailyToSurrogate(int nDaily, int nBlock, int blockLen) {
		this.nDaily = nDaily;
		this.nBlock = nBlock;
		this.blockLen = blockLen;
	}


	/**
	 * The traditional CalSIM ANN packages
	 *  the daily data as 7 individual daily values plus 10 
	 * aggregations of 11 days apiece.
	 * 
	 * @param history
	 * @param extData
	 * @param currentIndex
	 * @returns
	 */
	public double[] dailyToSurrogateInput(double[] input,  int currentIndex) {
		// Number of predictors that are CalSIM values, (e.g. 6-7 flows at different locations)

		// Copies the 7 preceding days in reverse order, from current day back
		double[] outData = new double[nDaily+nBlock];
		for (int ndxRev = 0 ; ndxRev<nDaily ; ndxRev++){
			int ndxDaily = currentIndex - ndxRev;
			outData[ndxRev] = input[ndxDaily];				
		}
		// Now go through blocks of history. The blocks are traversed
		// backwards in time, but the averages are created using iterators that move
		// forward within the block
		for (int iblock = 0; iblock < nBlock; iblock++) {
			// blockStart is the index at the beginning (early side) of the block
			int blockStart = currentIndex - nDaily - blockLen*(iblock+1) + 1;
			// And average them. The sums are done earlier to later but the output
			// is always packed in reverse chronological 
			double blockSum = 0.;
			int blockStop = blockStart+blockLen;
			for (int ndxDaily = blockStart; ndxDaily<blockStop; ndxDaily++) {
				blockSum += input[ndxDaily];
			}
			outData[nDaily+iblock] = blockSum/((double)blockLen);
		}
		return outData;
	}

}
