package calsim.surrogate;

import java.util.ArrayList;

import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import org.tensorflow.Session.Runner;

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
	public TensorWrapper(String fpath, String[] tensorNames, String[] tensorNamesInt, String outName,
			DailyToSurrogate dayToSurrogate) {
		model = SavedModelBundle.load(fpath, "serve");
		s = model.session();

		this.tensorNames = tensorNames;
		this.tensorNamesInt = tensorNamesInt;
		nFeatures = tensorNames.length;
		nFeaturesInt = tensorNamesInt.length;
		this.outName = outName;
		this.dayToANN = dayToSurrogate;

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
		if (nFeatures > 0) {
			System.out.println("N Feature"+nFeatures);
			nBatch = rawData.get(0).length;
			for (int i = 0; i < nFeatures; i++) {
				int featureLen = rawData.get(i)[0].length;
				float[][] featureData = new float[nBatch][featureLen];
				for (int j = 0; j < featureLen; j++) {
					for (int k = 0; k < nBatch; k++) {
						featureData[k][j] = (float) rawData.get(i)[k][j];
					}
				}
				Tensor<Float> input = Tensor.create(featureData, Float.class);
				runner.feed(tensorNames[i], input);
			}

		}
		if (nFeaturesInt > 0) {
			int nBatchInt = rawDataInt.get(0).length;
			if (nBatchInt != nBatch) {
				System.out.println("Bad integer");
			}
			for (int i = 0; i < nFeaturesInt; i++) {
				nBatchInt = rawDataInt.get(i).length;
				if (nBatchInt != nBatch) {
					System.out.println("Bad integer");
				}
				;
				int featureLenInt = rawDataInt.get(i)[0].length;
				long[][] featureDataInt = new long[nBatch][featureLenInt];
				for (int j = 0; j < featureLenInt; j++) {
					for (int k = 0; k < nBatchInt; k++) {
						featureDataInt[k][j] = (long) rawDataInt.get(i)[k][j];
					}
				}
				Tensor<Long> inputInt = Tensor.create(featureDataInt, Long.class);
				runner.feed(tensorNamesInt[i], inputInt);
			}

		}

		Tensor result = runner.fetch(outName).run().get(0);
		float[][] out = new float[nBatch][1];

		result.copyTo(out);
		return out;
	}

	public String getName() {
		return tensorNames[0];
	}

	@Override
	public DailyToSurrogate getDailyToSurrogate() {
		return dayToANN;
	}

}
