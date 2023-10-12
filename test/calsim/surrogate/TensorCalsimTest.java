package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import calsim.surrogate.examples.EmmatonExampleTensorFlowANN;
import java.util.ArrayList;


class TensorCalsimTest {

	
    @Test	
	void testEmmatonANN() {
		Surrogate wrap = EmmatonExampleTensorFlowANN.emmatonANN();
        
		long[][] inpInt = {{}};

		double[][] sac = {{7717.142,7716.127,7715.153,7714.168,7713.113,7711.911,7710.454,7708.580,7413.865,6964.834,6990.893,9085.646,10543.928,10538.482,9573.437,9400.652,10259.991,19202.160}};
		double[][] exp =  {{3505.366,3505.366,3505.366,3505.366,3505.366,3505.366,3505.366,3505.366,3228.763,2744.708,2744.708,4691.516,6313.855,6313.855,4611.389,4233.064,4233.064,11767.550}};

		double[][]	dcc = {{0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,16.364,30.000,30.000,30.818,31.000,31.000,31.000}};
		double[][] net_dcd = {{1632.914,1632.914,1632.914,1632.914,1632.914,1632.914,1632.914,1632.914,1364.777,895.536,895.536,1093.966,1259.325,1259.325,2029.012,2200.054,2200.054,2565.653}};
		double[][] sjr = {{1277.638,1277.781,1277.942,1278.132,1278.363,1278.656,1279.040,1279.564,1347.192,1445.815,1443.771,1205.677,1039.761,1039.770,1108.564,1121.629,1104.088,920.058}};
		double[][] tide =	{{6.560,6.184,5.508,5.083,4.913,5.024,5.233,5.991,5.958,5.254,5.719,4.515,5.757,5.255,4.907,6.271,5.489,6.276}};
		double[][] smscg =	{{0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.000,0.818,1.000,1.000,1.000}};		
			
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
			for (int jtime = 0; jtime<1; jtime++) {
				System.out.println("Out " + ibatch + "," + jtime + "  out: " + est[ibatch][jtime]);
			}
		}
  }
    
    @Test
    void testEmmatonSurrogateMonth() {
    	SurrogateMonth surrogateMonth=EmmatonExampleTensorFlowANN.emmatonSurrogateMonth();

    	double[][] sac = {{7771,6990.,10500.,9500.,18000.}};
		double[][] exp =  {{3500.,2750.,6300.,4200.,11700.}};

		double[][] dcc = {{0.0,0.0,0.0,30.,31.}};
		double[][] net_dcd = {{1632.,1360.,895.,2029.,2565.}};
		double[][] sjr =     {{1277.,1347.,1205.,1120.,920.}};
		double[][] tide =	{{6.560,6.184,5.508,5.083,6.913}};
		double[][] smscg =	{{0.000,0.000,0.000,1.000,1.000}};
		
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(dcc);
		floatInput.add(net_dcd);
		floatInput.add(sjr);
		floatInput.add(tide);
		floatInput.add(smscg);

		int year = 1994;  // Ficticious
    	int month = 5; // Ficticious. When?

    	double[][] out = surrogateMonth.annMonth(floatInput,year,month);
    	System.out.println("ANN Month: "+ out[0][0]);

    }  
    
}
