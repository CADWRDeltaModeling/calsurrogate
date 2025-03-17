package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class TensorWrapperTest {

    @Test
    public void testBatchDifferences() throws IOException {
        // --- Setup model and input names ---
    	
    	File dataFolder = ResourceUtils.extractResourceFolder("/calsim/surrogate/data");    	
    	String dataPath = dataFolder.getAbsolutePath();
    	
    	
    	File modelFolder = ResourceUtils.extractResourceFolder(
    	"/calsim/surrogate/ann/schism_base.suisun_gru2_tf2");
    	String modelPath = dataFolder.getAbsolutePath();
    	
    	
    	// Produced without unscaling by h5_tf
    	//File modelFolder = ResourceUtils.extractResourceFolder(
    	//		"F:/ann_workspace/casanntra/example/base.suisun_debug");
    	//String modelPath = "F:/ann_workspace/casanntra/example/base.suisun_debug";

    	System.out.println(modelPath);
        //String modelPath = "F:/ann_workspace/calsurrogate/src/main/resources/calsim/surrogate/ann/schism_base.suisun_gru2_tf";    
        String[] tensorNames = { "serving_default_northern_flow:0", 
        		                 "serving_default_exports:0", 
        		                 "serving_default_sjr_flow:0",
        		                 "serving_default_cu_flow:0",
        		                 "serving_default_sf_tidal_energy:0",
        		                 "serving_default_sf_tidal_filter:0",
        		                 "serving_default_dcc:0",
        		                 "serving_default_smscg:0"};
        
        String[] tensorNamesInt = {};  // Assume no integer inputs for this test.
        String outName = "StatefulPartitionedCall:2";
        DailyToSurrogate dummyDaily = new DailyToSurrogateDefault(90,false);

        // Instantiate TensorWrapper.
        TensorWrapper wrapper = new TensorWrapper(modelPath, 
        		tensorNames, tensorNamesInt, outName, dummyDaily);

        // --- Load cached CSV inputs ---
        ArrayList<double[][]> rawData = DebugUtils.readInputFeatures(
        		Arrays.asList(tensorNames),dataPath);
        System.out.println("Read "+rawData.size()
                           + " features. nbatch="
        		           + rawData.get(0).length
        		           + " ntime=" 
        		           + rawData.get(0)[0].length);
        
        // Optionally limit the number of rows if there are many.
        int maxRows = 12;
        ArrayList<double[][]> limitedRawData = new ArrayList<>();
        for (double[][] feature : rawData) {
            int rows = Math.min(feature.length, maxRows);
            double[][] limited = new double[rows][feature[0].length];
            for (int i = 0; i < rows; i++) {
                System.arraycopy(feature[i], 0, limited[i], 0, feature[i].length);
            }
            limitedRawData.add(limited);
        }
        
        // --- Run model with a multi-batch call ---
        float[][] multiOutput = wrapper.estimate(limitedRawData, null);
        int nBatch = limitedRawData.get(0).length;
        
        // Check differences between multi-batch row 0 and row 1.
        boolean diffBetweenBatch0and1 = false;
        if(nBatch >= 2) {
            for (int j = 0; j < multiOutput[0].length; j++) {
                if (Math.abs(multiOutput[0][j] - multiOutput[1][j]) > 1e-6) {
                    diffBetweenBatch0and1 = true;
                    break;
                }
            }
        }
        assertTrue(diffBetweenBatch0and1, "Multi-batch output should differ between batch 0 and batch 1");

        // --- Run model with a single-batch call using only the first row (as done earlier) ---
        ArrayList<double[][]> singleBatchData = new ArrayList<>();
        for (double[][] feature : limitedRawData) {
            double[][] single = new double[1][feature[0].length];
            System.arraycopy(feature[0], 0, single[0], 0, feature[0].length);
            singleBatchData.add(single);
        }
        float[][] singleOutput = wrapper.estimate(singleBatchData, null);
        // Compare multi-batch row 0 to the single-batch call (which uses row 0)
        boolean diffBetweenSingleAndMulti = false;
        for (int j = 0; j < multiOutput[0].length; j++) {
            if (Math.abs(multiOutput[0][j] - singleOutput[0][j]) > 1e-6) {
                diffBetweenSingleAndMulti = true;
                break;
            }
        }
        //assertTrue(diffBetweenSingleAndMulti, 
         //       "Output for batch 0 should differ between single-batch call and multi-batch call");

        // --- Now run two separate single-row calls: one for row 0 and one for row 1 ---
        ArrayList<double[][]> singleRowData0 = new ArrayList<>();
        ArrayList<double[][]> singleRowData1 = new ArrayList<>();
        for (double[][] feature : limitedRawData) {
            // For row 0:
            double[][] row0 = new double[1][feature[0].length];
            System.arraycopy(feature[0], 0, row0[0], 0, feature[0].length);
            singleRowData0.add(row0);
            
            // For row 1:
            double[][] row1 = new double[1][feature[0].length];
            if (feature.length > 1) {
                System.arraycopy(feature[9], 0, row1[0], 0, feature[0].length);
            } else {
                // Fallback if there is no second row: copy row 0.
                System.arraycopy(feature[0], 0, row1[0], 0, feature[0].length);
            }
            singleRowData1.add(row1);
        }
        
        float[][] singleRow0Output = wrapper.estimate(singleRowData0, null);
        float[][] singleRow1Output = wrapper.estimate(singleRowData1, null);
        
        // --- Compare these single row calls to multi-batch outputs ---
        boolean row0Match = true;
        for (int j = 0; j < multiOutput[0].length; j++) {
            if (Math.abs(multiOutput[0][j] - singleRow0Output[0][j]) > 1e-6) {
                row0Match = false;
                break;
            }
        }
        //assertTrue(row0Match, "Multi-batch row 0 should match single row call for row 0");

        boolean row1Match = true;
        if (nBatch >= 2) {
            for (int j = 0; j < multiOutput[0].length; j++) {
                if (Math.abs(multiOutput[1][j] - singleRow1Output[0][j]) > 1e-6) {
                    row1Match = false;
                    break;
                }
            }
            //assertTrue(row1Match, "Multi-batch row 1 should match single row call for row 1");
        }
        
        // Optionally print outputs for manual inspection.
        System.out.println("Multi-batch output (batch 0): " + Arrays.toString(multiOutput[0]));
        System.out.println("Multi-batch output (batch 1): " + Arrays.toString(multiOutput[1]));
        System.out.println("Single row output for row 0: " + Arrays.toString(singleRow0Output[0]));
        System.out.println("Single row output for row 1: " + Arrays.toString(singleRow1Output[0]));
        
        wrapper.close();
    }
    

}