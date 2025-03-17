package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import calsim.surrogate.examples.ExampleExogAssignment;

class PracticalTest {

	@Test
	void testUnivariate() throws URISyntaxException {


		URL resourceUrl = getClass().getClassLoader().getResource("calsim/surrogate/ann/emmaton");

		assertNotNull(resourceUrl, "Resource not found");

		// Convert to Path
		Path resourcePath = Paths.get(resourceUrl.toURI());

		String fname = resourcePath.toString();
		SurrogateMonth annMonth = createSurrogateMonth(fname);

		double[][] sac = { { 14765.289427181004,13478.35135024262,36344.084703109846,49641.401116570276,21677.466640999544 } };
		double[][] exp = { { 11985.391312255339,12301.106028028918,9106.153016670627,7070.969778570741,5572.729092403552} };
		double[][] dcc = { { 31.0,31.0,0.0,0.0,0.0 } };
		double[][] dcd = { { 1869.8758413461412,2605.369642928569,2337.711622751071,1622.9627999066765,1167.545973662801 } };
		double[][] sjr = { { 2442.5400684029796,3998.709350903452,8062.840526488872,6325.312129502397,9512.362226873918 } };
		double[][] tide = { { 6.56,6.184,5.508,5.083,6.913 } };
		double[][] smsg = { { 0.0,0.0,1.0,1.0,1.0 } };
		ArrayList<double[][]> floatInput = new ArrayList<>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(dcc);
		floatInput.add(dcd);
		floatInput.add(sjr);
		floatInput.add(tide);
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
	
		int nx0 = 8;
		int nx1 = 8;
		GridResult grid = annMonth.evaluateOnGrid(floatInput, year, month, 4000.,25000.,nx0, 500, 12000,  nx1);
		
		for (int i = 0; i<nx0; i++) {
			for (int j=0; j<nx1; j++) {
				System.out.println("x0="+grid.getGridInput0()[i]+" x1="+grid.getGridInput1()[j]+" res="+grid.getResult()[i][j][0]);			
			}
		}
		System.out.println("Done");
	}
	
    	public Surrogate getTFModel(String fname) {

			String[] tensorNames = { "serving_default_sac:0", "serving_default_exports:0",
					"serving_default_dcc:0", "serving_default_net_dcd:0", "serving_default_sjr:0",
					"serving_default_tide:0", "serving_default_smscg:0", };

			String[] tensorNamesInt = new String[0];
			String outName = "StatefulPartitionedCall:0";
			DailyToSurrogate dayToANN = new DailyToSurrogateBlocked(8, 10, 11);
            return new TensorWrapper(fname, tensorNames, tensorNamesInt, outName, dayToANN);
		}

		/**
		 * Complete application of the ANN or other surrogate. Disaggregates monthly
		 * inputs to daily, marches through days repackaging the inputs into the time
		 * aggregation/treatment expected by the ANN and applying the ANN output. Then
		 * re-aggregates to an array over the batch size with one monthly statistic per
		 * batch
		 * 
		 * @param fname filename
		 * @return 2D Array of monthly aggregated results with dimensions of batch size
		 *         by number of stations predicted
		 */

		public SurrogateMonth createSurrogateMonth(String fname) {
			Surrogate ann = getTFModel(fname);			
			DisaggregateMonths spline = new DisaggregateMonthsSpline(5);
			DisaggregateMonths repeat = new DisaggregateMonthsRepeat(5);
			DisaggregateMonths daysOps = new DisaggregateMonthsDaysToOps(5, 1., 0.);
			DisaggregateMonths[] disagg = { repeat, repeat, daysOps, spline, spline, spline, repeat };

			AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;
			List<ExogTimeSeriesAssignment> assigns = Collections.singletonList(ExampleExogAssignment.TIDE.getAssignment());
            return new SurrogateMonth(disagg, ann, agg, assigns);
		}


}
