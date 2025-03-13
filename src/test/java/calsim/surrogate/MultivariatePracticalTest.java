package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import calsim.surrogate.examples.ExampleExogAssignment;

class MultivariatePracticalTest {

	private static final Map<Integer, Integer> outputIndexMap = new HashMap<>();	
	static {
		outputIndexMap.put(SalinitySurrogateManager.JER_CALSIM, 11);
		outputIndexMap.put(SalinitySurrogateManager.RSL_CALSIM, 16); 
		outputIndexMap.put(SalinitySurrogateManager.EMM_CALSIM, 8);
		outputIndexMap.put(SalinitySurrogateManager.ANH_CALSIM, 10);	  
		outputIndexMap.put(SalinitySurrogateManager.CLL_CALSIM, 7);
		outputIndexMap.put(SalinitySurrogateManager.BDL_CALSIM, 5);
		outputIndexMap.put(SalinitySurrogateManager.X2_CALSIM,  0);
	}
	
	@Test
	void test() {
    	File modelFolder;
    	String modelPath;
		try {
			modelFolder = ResourceUtils.extractResourceFolder("/calsim/surrogate/ann/schism_base.suisun_gru2_tf");
			modelPath = modelFolder.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
			modelFolder = null;
			modelPath = null;
		}
    	
    	
		SurrogateMonth annMonth = createSurrogateMonth(modelPath); 

		double[][] sac = { { 14765.289427181004,13478.35135024262,22344.084703109846,49641.401116570276,21677.466640999544 } };
		double[][] exp = { { 3985.391312255339,4301.106028028918,9106.153016670627,7070.969778570741,5572.729092403552} };
		double[][] sjr = { { 2442.5400684029796,3998.709350903452,3062.840526488872,1325.312129502397,2512.362226873918 } };
		double[][] dcd = { { 1869.8758413461412,2605.369642928569,2337.711622751071,1622.9627999066765,1167.545973662801 } };

		double[][] tidal_energy = { { 6.56,6.184,5.508,5.083,6.913 } };
		double[][] tidal_filter = { { 6.56,6.184,5.508,5.083,6.913 } };		
		double[][] smsg = { { 0.0,0.0,1.0,1.0,1.0 } };
		double[][] dcc = { { 31.0,27.0,0.0,0.0,0.0 } };		
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(sjr);
		floatInput.add(dcd);
		floatInput.add(tidal_energy);
		floatInput.add(tidal_filter);
		floatInput.add(dcc);		
		floatInput.add(smsg);		

		double[][] est = annMonth.annMonth(floatInput, 1922, 8);
		for (int ibatch = 0; ibatch < 1; ibatch++) {
			for (int jtime = 0; jtime < 1; jtime++) {
				System.out.println(est[ibatch].length);
				System.out.println("Out " + ibatch + "," + jtime + "  out: " + est[ibatch][jtime]);
			}
		}

		int year = 1924;
		int month = 8;

		int nx0 = 5;
		int nx1 = 6;
		GridResult grid = annMonth.evaluateOnGrid(floatInput, year, month, 5000.,25000.,nx0, 1000, 11000,  nx1);

		int emmIndex = 8;
		for (int i = 0; i<nx0; i++) {
			for (int j=0; j<nx1; j++) {
				System.out.println("x0="+grid.getGridInput0()[i]+" x1="+grid.getGridInput1()[j]+" res="+grid.getResult()[i][j][emmIndex]);			
			}
		}
	}

	public Surrogate getTFModel(String fname) {
		String[] tensorNames = { "serving_default_northern_flow:0", 
				"serving_default_exports:0",
				"serving_default_sjr_flow:0", 
				"serving_default_cu_flow:0", 
				"serving_default_sf_tidal_energy:0",
				"serving_default_sf_tidal_filter:0", 
				"serving_default_dcc:0", 
				"serving_default_smscg:0", };

		String[] tensorNamesInt = new String[0];
		String outName = "StatefulPartitionedCall:1";
		boolean reverse = false;
		DailyToSurrogate dayToANN = new DailyToSurrogateDefault(90,reverse);
		Surrogate wrap = new TensorWrapper(fname, tensorNames, tensorNamesInt, outName, dayToANN);
		return wrap;
	}

	/**
	 * Complete application of the ANN or other surrogate. Disaggregates monthly
	 * inputs to daily, marches through days repackaging the inputs into the time
	 * aggregation/treatment expected by the ANN and applying the ANN output. Then
	 * re-aggregates to an array over the batch size with one monthly statistic per
	 * batch
	 * 
	 * @param monthlyInputs
	 * @param year
	 * @param month
	 * @param cycle
	 * @return 2D Array of monthly aggregated results with dimensions of batch size
	 *         by number of stations predicted
	 */

	public SurrogateMonth createSurrogateMonth(String fname) {
		SalinitySurrogateManager ssm = SalinitySurrogateManager.INSTANCE;
		Surrogate ann = getTFModel(fname);			
		DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
		DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
		DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);
		DisaggregateMonths[] disagg = { spline, spline, spline, spline, repeat, repeat, daysOps, repeat };

		AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
		List<ExogTimeSeriesAssignment> assignments = new ArrayList<>();
		assignments.add(new ExogTimeSeriesAssignment("calsim/surrogate/sf_tide.csv", "sf_tidal_energy", "serving_default_sf_tidal_energy:0"));
		assignments.add(new ExogTimeSeriesAssignment("calsim/surrogate/sf_tide.csv", "sf_tidal_filter", "serving_default_sf_tidal_filter:0"));
		SurrogateMonth annMonth = new SurrogateMonth(disagg, ann, agg, assignments);
		int aggCode = agg.calsimCode;
		for (Integer location : outputIndexMap.keySet()){
			int outIndex = outputIndexMap.get(location);
			if (!ssm.hasSurrogateForSite(location, aggCode)) {
				 ssm.setSurrogateForSite(location, aggCode, annMonth);
				 ssm.setIndexForSite(location, outIndex);   // e.g. 0 if univariate has only one index
			}
		}		
		return annMonth;
	}


}
