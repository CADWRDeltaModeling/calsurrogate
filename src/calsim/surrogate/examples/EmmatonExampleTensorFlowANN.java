package calsim.surrogate.examples;

import calsim.surrogate.*;
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
        DailyToSurrogate dayToANN = new DailyToSurrogateBlocked(8,10,11);
		Surrogate wrap = new TensorWrapper(fname,tensorNames,tensorNamesInt,outName,dayToANN);	
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

    public static SurrogateMonth emmatonSurrogateMonth() {
		DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
		DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5,1.,0.);
		DisaggregateMonths[] disagg = {spline,spline,daysOps,spline,spline,spline,repeat};
        Surrogate emm = emmatonANN();
        AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
    	SurrogateMonth month = new SurrogateMonth(disagg, emmatonANN(), agg);
    	return month;

    }
  
    
}