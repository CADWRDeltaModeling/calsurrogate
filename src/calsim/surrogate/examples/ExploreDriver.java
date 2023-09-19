package calsim.surrogate.examples;

import java.util.ArrayList;

import calsim.surrogate.Surrogate;
import calsim.surrogate.TensorContourExplore;
import calsim.surrogate.TensorWrapper;

public class ExploreDriver {


    public static void main(String[] argv) {
    String fname = "F:/projects/ann_workspace/ann/ann_calsim-main/ANN_Models1/calsim_delta_outflow_estimator";
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
    TensorContourExplore contourer = new TensorContourExplore(wrap,
    		features, featuresInt,
    		feature0, ndx0,
    		feature1, ndx1);  
    contourer.setMin(0.);
    contourer.setMax(1.1);
    
    //contourer.setSearchContour(6000.);
    double[] bnd0 = {0.05,.99};
    double[] bnd1 = {0.05,0.99};
    //contourer.setBnd0(bnd0);
    //contourer.setBnd1(bnd1);
    double searchContour = 5600.;
    int nVal = 20;
    contourer.probeContour(searchContour, bnd0, bnd1, nVal);  //searchContour, bnd0, bnd1, nvVal);
        
    }
}



