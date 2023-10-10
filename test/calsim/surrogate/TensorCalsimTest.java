package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class TensorCalsimTest {

    @Test	
	void testEmmatonANN() {
		String fname = "./ann_calsim-main/emmaton";
		String[] tensorNames =  {"serving_default_sac_input:0",
				"serving_default_exports_input:0","serving_default_dcc_input:0",
				"serving_default_net_dcd_input:0","serving_default_sjr_input:0",
				"serving_default_tide_input:0","serving_default_smsg_input:0",
				};
		
		String[] tensorNamesInt = new String[0];
		String outName = "StatefulPartitionedCall:0";

		Surrogate wrap = new TensorWrapper(fname,tensorNames,tensorNamesInt,outName,null);
		System.out.println("Success creating wrapper");
		long[][] inpInt = {{}};

		double[][] sac = {{7724.8,7763.5,7785.7,7799.5,7808.8,7815.2,7819.8,7823.2,7831.7,
			          7816.5,7737.3,7746.7,8539.2,9661.6,9685.6,10222.1,10596.9,10586.9}};
		double[][] exp =  {{2972.1,2972.1,2972.1,2972.1,2972.1,2972.1,2972.1,2972.1,2972.1,
			                   2788.0,1959.6,1959.6,2086.5,2308.6,2308.6,1816.7,1406.7,1406.7}};

		double[][]	dcc = {{30.0,30.0,30.0,30.0,30.0,30.0,30.0,30.0,30.0,30.2,
			                31.0,31.0,31.0,31.0,31.0,28.3,26.0,26.0}};
		double[][] net_dcd = {{1680.3,1680.3,1680.3,1680.3,1680.3,1680.3,1680.3,1680.3,1680.3,
			                   1835.9,2536.0,2536.0,2645.9,2838.4,2838.4,2750.8,2677.8,2677.8}};
		double[][] sjr = {{770.7,761.6,756.3,753.0,750.8,749.3,748.1,747.3,744.8,694.5,
				            538.5,534.8,503.5,459.9,464.2,805.4,1043.6,1053.8}};
		double[][] tide =	{{6.2,6.1,6.2,6.1,5.7,5.2,4.8,4.6,4.6,5.7,5.2,5.7,6.5,4.9,6.3,6.2,6.0,6.1}};
		double[][] smscg =	{{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.2,1.0,1.0,1.0,1.0,1.0,1.0,1.0,1.0}};		
			
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(dcc);
		floatInput.add(net_dcd);
		floatInput.add(sjr);
		floatInput.add(tide);
		floatInput.add(smscg);

		float[][] est = wrap.estimate(floatInput,null);
		for (int ibatch = 0; ibatch<1 ; ibatch++) {
			for (int jtime = 0; jtime<4; jtime++) {
				System.out.println("Out " + ibatch + "," + jtime + "  out: " + est[ibatch][jtime]);
			}
		}
  }
}
