package calsim.surrogate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.FloatBuffer;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.Session.Runner;

import org.tensorflow.Session;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * Generic wrapper for a TensorFlow saved model. Some assumptions on features
 * and dimensionality.
 */
public class TensorWrapper implements Surrogate {

	SavedModelBundle model;
	Session s;
	Runner runner;
	String[] tensorNames;
	String[] tensorNamesInt;
	String outName;
	double[] rawData;
	int nFeatures;
	int nFeaturesInt;
	DailyToSurrogate dayToANN;
	Tensor<Long> inputInt;

	String fpath;


	/**
	 * Create a TensorWrapper given the path to a directory containing
	 * saved_bundle.pb Please keep this code free of complex dependencies and
	 * locations that would make it harder to test.
	 * 
	 * @param fpath          path containing the saved model.
	 * @param tensorNames    names used to access input names for float values
	 *                       inputs
	 * @param tensorNamesInt names used to access input names for integer valued
	 *                       inputs
	 * @param outName        name of the output to be querieds
	 */
	public TensorWrapper(String fpath, String[] tensorNames, 
			String[] tensorNamesInt, String outName,
			DailyToSurrogate dayToSurrogate) {
		model = SavedModelBundle.load(fpath, "serve");
		s = model.session();
		this.tensorNames = tensorNames;
		this.tensorNamesInt = tensorNamesInt;
		nFeatures = tensorNames.length;
		nFeaturesInt = tensorNamesInt.length;
		this.outName = outName;
		this.dayToANN = dayToSurrogate;
		this.fpath = fpath;

	}

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
	@Override
	public float[][] estimate(ArrayList<double[][]> rawData, ArrayList<long[][]> rawDataInt) {		

		runner = this.s.runner();

		int nBatch = 0;
		// Store tensors for cleanup
		List<Tensor<?>> tensorsToClose = new ArrayList<>();

		nBatch = rawData.get(0).length;

		for (int i = 0; i < nFeatures; i++) {
			int featureLen = rawData.get(i)[0].length;   // Number of times
			float[][] featureData = new float[nBatch][featureLen];
			for (int k = 0; k < nBatch; k++) {
				for (int j = 0; j < featureLen; j++) {
					featureData[k][j] = (float) rawData.get(i)[k][j];
				}
			}
			System.out.println("Feeding tensor: " + tensorNames[i] + " with shape [" 
					+ featureData.length + ", " + featureData[0].length + "]");

			Tensor<Float> input = Tensor.create(featureData,Float.class);
			runner.feed(tensorNames[i], input);
			tensorsToClose.add(input); 
		}

		List<Tensor<?>> results = runner.fetch(outName).run();

		try {
			Tensor<?> outputTensor = results.get(0);
			long[] outShape = outputTensor.shape();
			int nOut = (int) outShape[1];
			float[][] out = new float[nBatch][nOut];
			
			
			
			outputTensor.copyTo(out);

			//TODO This hardwires flooring to zero. Should eliminate and put this in TensorFlow
			double xLowBound = 0.; //Small enough to be a floor for X2 and for EC
			for (int i = 0; i < out.length; i++) {
				for (int j = 0; j < out[i].length; j++) {
					out[i][j] = (float) Math.max(xLowBound, out[i][j]);
				}
			}	
			return out;
		} finally {
			// **Close and clear all stored tensors**
			for (Tensor<?> t : tensorsToClose) {
				t.close();
			}
			tensorsToClose.clear();  // Clear the list after closing

			for (Tensor<?> result : results) {
				result.close();
			}
		}
	}



	// Helper method to check for differences across batches for a given feature.
	private void checkDifferencesAcrossBatches(float[][] featureData, int featureIndex) {
		int nBatch = featureData.length;
		int featureLen = featureData[0].length;
		for (int t = 0; t < featureLen; t++) {
			float refValue = featureData[0][t];
			boolean differenceFound = false;
			for (int b = 1; b < nBatch; b++) {
				if (Math.abs(featureData[b][t] - refValue) > 1e-6) {
					System.out.println("Difference in feature " + featureIndex +
							" at time index " + t + ": batch 0 = " + refValue +
							", batch " + b + " = " + featureData[b][t]);
					differenceFound = true;
				}
			}
			if (!differenceFound) {
				System.out.println("Feature " + featureIndex + ", time index " + t +
						" is consistent across all batches with value " + refValue);
			}
		}
	}

	public String getName() {
		return tensorNames[0];
	}

	@Override
	public DailyToSurrogate getDailyToSurrogate() {
		return dayToANN;
	}

	public String identifier() {return this.fpath;}

	/**
	 * Returns the index of the input matching the given name among the float inputs.
	 * 
	 * @param inputName The name of the ANN input.
	 * @return the index of the input if found.
	 * @throws IllegalArgumentException if the input name is not found among the available tensor names.
	 */
	@Override
	public int getInputIndex(String inputName) {
		for (int i = 0; i < tensorNames.length; i++) {
			if (tensorNames[i].equals(inputName)) {
				return i;
			}
		}
		throw new IllegalArgumentException("Input name '" + inputName +
				"' not found among available tensor names: " + java.util.Arrays.toString(tensorNames));
	}

	/**
	 * Returns the expected number of features (i.e. the length of the floatInputs array)
	 * that this surrogate requires.
	 */
	@Override
	public int getNFeatures() {
		return this.nFeatures;
	}

	private static float[] flatten(float[][] array) {
	    int rows = array.length;
	    int cols = array[0].length;
	    float[] flatArray = new float[rows * cols];

	    int index = 0;
	    for (int i = 0; i < rows; i++) {
	        for (int j = 0; j < cols; j++) {
	            flatArray[index++] = array[i][j];
	        }
	    }
	    return flatArray;
	}	
	
	private static ByteBuffer toByteBuffer(float[] data) {
	    ByteBuffer buffer = ByteBuffer.allocateDirect(data.length * Float.BYTES);
	    buffer.order(ByteOrder.nativeOrder());
	    for (float value : data) {
	        buffer.putFloat(value);
	    }
	    buffer.rewind();
	    return buffer;
	}

	
	public void close() {
		if (s != null) {
			s.close();
			s = null;
		}
	}
}
