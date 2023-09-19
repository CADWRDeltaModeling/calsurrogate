package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TensorCalsimTest {


	@Test
	void testRSAC092() {
	    String fname = "./ann_calsim-main/RSAC092";
	    String[] tensorNames =  {"serving_default_input_preprocessed_input:0"};
	    String[] tensorNamesInt = new String[0];
	    String outName = "StatefulPartitionedCall:0";

	    
	    
	    System.out.println("Success creating wrapper");
	    long[][] inpInt = {{}};

	    double[][] inpFloat = new double[][] {
	       { 0.6, 0.72, 0.9, 0.75237382, 0.9,
	        -0.21546655, -0.17141414, -0.38003647, -0.65384933, -0.77411415,
	        -0.89288707, -0.69964324, -0.77795826, -0.7921897, -0.81271535,
	         0., 0., 0., 1., 0., 0., 0., 0., 0., 1.,
	         0., 0., 0., 0., 0., 0., 0. } };
	    ArrayList<double[][]> features = new ArrayList<>();
	    features.add(inpFloat);
	    ArrayList<long[][]> featuresInt = new ArrayList<>();

	    int feature0=0;
	    int feature1=0;
	    int ndx0=1;
	    int ndx1=0;
	    Surrogate wrap = new TensorWrapper(fname,tensorNames,tensorNamesInt,outName,null);	
		
	}

    @Test	
	void testNDOEstimator() {
		String fname = "./ann_calsim-main/calsim_delta_outflow_estimator";
		String[] tensorNames =  {"serving_default_input_preprocessed_input:0"};
		String[] tensorNamesInt = new String[0];
		String outName = "StatefulPartitionedCall:0";

		Surrogate wrap = new TensorWrapper(fname,tensorNames,tensorNamesInt,outName,null);
		System.out.println("Success creating wrapper");
		long[][] inpInt = {{}};

		double[][] inpFloat = new double[][] { 
			{ 0.7228964, 1.05657969, 1.32106489, 0.75237382, 0.86339624,
				-0.21546655, -0.17141414, -0.38003647, -0.65384933, -0.77411415,
				-0.89288707, -0.69964324, -0.77795826, -0.7921897, -0.81271535,
				0., 0., 0., 1., 0., 0., 0., 0., 0., 1.,
				0., 0., 0., 0., 0., 0., 0. },
			{ 0.9228964, 1.05657969, 1.32106489, 0.75237382, 0.86339624,
					-0.21546655, -0.17141414, -0.38003647, -0.65384933, -0.77411415,
					-0.89288707, -0.69964324, -0.77795826, -0.7921897, -0.81271535,
					0., 0., 0., 1., 0., 0., 0., 0., 0., 1.,
					0., 0., 0., 0., 0., 0., 0. } };    
					ArrayList<double[][]> features = new ArrayList<double[][]>();
					features.add(inpFloat);
					ArrayList<long[][]> featuresInt = new ArrayList<long[][]>();
					float[][] out = wrap.estimate(features, featuresInt);
                    assertEquals(out[0][0],5216.211,0.001);
					float[][] out2 = wrap.estimate(features, featuresInt);
					assertEquals(out[0][0],out2[0][0]); //consistency

	}
	
	
}
