package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calsim.surrogate.examples.*;

class LinearConstraintTest {


	@Test
	void test() {
		SurrogateMonth ann = EmmatonExampleTensorFlowANN.emmatonSurrogateMonth();
	    LinearConstraint linear = new LinearConstraint(ann);

    	double[][] sac = {{7771.,6990.,10500.,9500.,18000.}};
		double[][] exp =  {{3500.,2750.,6300.,4200.,11700.}};

		double[][] dcc = {{0.0,0.0,0.0,30.,31.}};
		double[][] net_dcd = {{1632.,1360.,895.,2029.,2565.}};
		double[][] sjr =   {{1277.,1347.,1205.,1120.,920.}};
		double[][] tide =  {{6.560,6.184,5.508,5.083,6.913}};
		double[][] smscg = {{0.000,0.000,0.000,1.000,1.000}};
		
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(dcc);
		floatInput.add(net_dcd);
		floatInput.add(sjr);
		floatInput.add(tide);
		floatInput.add(smscg);

	    int ndxSac = 0;
	    int ndxExp = 1;
	    int year = 1994;
	    int month = 8;
	    int cycle = 0;
	    int constraintComponent = 0;
	    int location = 0;
	    int aveType = 1;
	    double targetWQ = 2000;
	    		
		double[][] out = linear.gradient( floatInput, year, month);
		//out[0][0] is the value at location 0
		//out[0][1] is the derivative at location 0 w.r.t. Sac
		//out[0][2] is the derivative at location 0 w.r.t. exports
		
		floatInput.get(0)[0][0] += 10.; //7781.0;
		floatInput.get(1)[0][0] -= 10.;
		double[][] out2 = ann.annMonth(floatInput, year, month);
		double externalDiff = out2[0][0] - out[0][0];
		assertEquals(externalDiff,out[0][1]*10.-out[0][2]*10.,0.1);
	}
}
