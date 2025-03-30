package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class MultivariatePracticalTest2 {

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
			modelFolder = ResourceUtils.extractResourceFolder(
					"/calsim/surrogate/ann/schism_base.suisun_gru2_tf");
			modelPath = modelFolder.getAbsolutePath();
			
		} catch (IOException e) {
			e.printStackTrace();
			modelFolder = null;
			modelPath = null;
		}
    	modelPath = "F:/ann_workspace/calsim_examples/calsurrogate-test/Run/External/ann/schism_base.suisun_gru2_tf";
    	System.out.println(modelPath);
		SurrogateMonth annMonth = createSurrogateMonth(modelPath); 

		double[][] sac = { { 4000.0, 40394.8495327244, 16904.736782425287, 18387.243181273156, 11176.84428360607 } };
		double[][] exp = { { 800.0, 9268.207178936515, 7433.307327963787, 8700.852304620485, 8813.182222637366} };
		double[][] sjr = { {8047.182345502621, 6938.933071864789, 2569.522783672705, 3883.330625419198, 1611.7923252073938 } };
		double[][] dcd = { { 252.81939240422022, -2083.7049238257737, 213.23777265386423, -617.069992293113, 1574.412246034835 } };

		double[][] tidal_energy = { { 6.56,6.184,5.508,5.083,6.913 } };
		double[][] tidal_filter = { { 6.56,6.184,5.508,5.083,6.913 } };		
		double[][] smsg = { { 1.0,01.0,1.0,0.0,0.0 } };
		double[][] dcc = { { 0.0,0.0,0.0,10.0,20.0 } };		
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
		for (int ibatch = 0; ibatch < est.length; ibatch++) {
			for (int jloc = 0; jloc < est[0].length; jloc++) {
				System.out.println(est[ibatch].length);
				System.out.println("Out " + ibatch + "," + jloc 
						+ "  out: " + est[ibatch][jloc]);
			}
		}

		int year = 1922;
		int month = 3;

		int nx0 = 3;
		int nx1 = 4;
		double loBound0 = 4000, hiBound0 = 22000;
		double loBound1 = 800., hiBound1 = 12800;
		GridResult grid = annMonth.evaluateOnGrid(floatInput, year, month, 
				            loBound0,hiBound0,nx0, loBound1, hiBound1,  nx1);

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
