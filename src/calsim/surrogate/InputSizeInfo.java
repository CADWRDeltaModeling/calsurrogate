package calsim.surrogate;

import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * The generic
 */
public class InputSizeInfo {
    
	private ArrayList<double[][]> inputs;
	public int minSize = 9999999;
	public int maxSize = -1;
	public int batchLen = -1;
    public int[] innerDim;
	public int nVar=-1;
    
	/**
	 * Creates the InputSizeInfo and also checks consistency of batch size across variables and inner index length across batches
	 * @param inputs
	 */
	public InputSizeInfo(ArrayList inputs) {
		this.inputs = inputs;
		this.nVar = inputs.size();
		innerDim = new int[this.nVar];
		countSizes();
	}
	
	private void countSizes(){
		int newMax = -1;
		int batchLen = inputs.get(0).length;

		
		for (int ivar=0; ivar<inputs.size(); ivar++) {
            double[][] arr =inputs.get(ivar);
			if (arr.length != batchLen) {
				throw new InputMismatchException("Batch index sizes inconsistent. See ArrayList index 0 vs "+ivar);
			}
			int arrLen = arr[0].length; // Use first batch index of the variable as standard for size chck
			for (int ibatch = 0; ibatch < batchLen; ibatch++) {
				if (arr[ibatch].length != arrLen)
					throw new InputMismatchException("Batches have uneven inner dimensionality. See variable "+ivar);
			}

			int thisSize=arr[0].length;
			innerDim[ivar]=thisSize;
			if(thisSize>maxSize) this.maxSize = thisSize;
			if(thisSize<minSize) this.minSize = thisSize;
			this.batchLen=batchLen;
		}
	}
	
	/**
	 * Returns the maximum inner index across variables (ArrayIndex) and batches
	 * @return
	 */
	public int getMaxSize() {
	    return maxSize;
	}

	/**
	 * Returns the minimum inner index across variables (ArrayIndex) and batches
	 * @return
	 */
	public int getMinSize() {
	    return minSize;
	}
	
	
	
}
