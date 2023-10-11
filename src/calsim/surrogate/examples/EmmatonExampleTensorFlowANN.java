package calsim.surrogate.examples;

import java.util.ArrayList;

import calsim.surrogate.*;
public class EmmatonExampleTensorFlowANN {


	public static Surrogate emmatonANN() {
		String fname = "./ann_calsim-main/emmaton";
		String[] tensorNames =  {"serving_default_sac_input:0",
				"serving_default_exports_input:0","serving_default_dcc_input:0",
				"serving_default_net_dcd_input:0","serving_default_sjr_input:0",
				"serving_default_tide_input:0","serving_default_smscg_input:0",
		};

		String[] tensorNamesInt = new String[0];
		String outName = "StatefulPartitionedCall:0";
        DailyToSurrogate dayToANN = DefaultDailyToSurrogate();
		Surrogate wrap = new TensorWrapper(fname,tensorNames,tensorNamesInt,outName,null);	
		return wrap;
	}


	
	
	/**
	 * Complete application of the ANN or other surrogate. 
	 * Disaggregates monthly inputs to daily, marches through days repackaging the inputs
	 * into the time aggregation/treatment expected by the ANN and applying the ANN output. Then
	 * re-aggregates to an array over the batch size with one monthly statistic per batch
	 * @param monthlyInputs
	 * @param year
	 * @param month
	 * @param cycle
	 * @return 2D Array of monthly aggregated results with dimensions of batch size by number of stations predicted 
	 */
	//public double[][] annMonth(ArrayList<double[][]> monthlyInputs, int location, int year, int month, int cycle) {
		
 

    public static SurrogateMonth emmatonSurrogateMonth() {
	 
        DisaggregateMonths disag = new DisaggregateMonthsRepeat(5);
        Surrogate emm = emmatonANN();
        AggregateMonths agg = AggregateMonths.AggType.MONTHLY_MEAN.getAggregator();
    	SurrogateMonth month = new SurrogateMonth(disag, emmatonANN(), agg);
    	return month;

    }
 
    
    
    
}