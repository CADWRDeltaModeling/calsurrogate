package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import calsim.surrogate.examples.*;
import calsim.surrogate.AggregateMonths;

class LinearConstraintTest {

	@Test
	void testLinearConstraint0() {
		SurrogateMonth ann = EmmatonExampleTensorFlowANN.emmatonSurrogateMonth();
		LinearConstraint linear = new LinearConstraint(ann);

		double[][] sac = { { 7771., 6990., 10500., 9500., 18000. } };
		double[][] exp = { { 3500., 2750., 6300., 4200., 11700. } };

		double[][] dcc = { { 0.0, 0.0, 0.0, 30., 31. } };
		double[][] net_dcd = { { 1632., 1360., 895., 2029., 2565. } };
		double[][] sjr = { { 1277., 1347., 1205., 1120., 920. } };
		double[][] tide = { { 6.560, 6.184, 5.508, 5.083, 6.913 } };
		double[][] smscg = { { 0.000, 0.000, 0.000, 1.000, 1.000 } };

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
		int aveType = AggregateMonths.MONTHLY_MEAN.calsimCode;
		double targetWQ = 2500;

		double[][] grad = linear.gradient(floatInput, year, month);
		// out[0][0] is the value at location 0
		// out[0][1] is the derivative at location 0 w.r.t. Sac
		// out[0][2] is the derivative at location 0 w.r.t. exports

		
		floatInput.get(0)[0][0] += 10.; // 7781.0;
		floatInput.get(1)[0][0] -= 10.;
		double[][] out2 = ann.annMonth(floatInput, year, month);
		double externalDiff = out2[0][0] - grad[0][LinearConstraint.VAL_NDX];
		assertEquals(externalDiff, grad[0][LinearConstraint.DSAC_NDX] * 10. - grad[0][LinearConstraint.DEXP_NDX] * 10.,
				0.1);

	}
	
	@Test
	void testLinearConstraint1() {
		SurrogateMonth ann = EmmatonExampleTensorFlowANN.emmatonSurrogateMonth();
		LinearConstraint linear = new LinearConstraint(ann);

		double[][] sac = { { 9092.7563,5000.859,652.6672,7880.0294,9416.97163} };
		double[][] exp = { { 3500., 2750., 6300., 4200., 11700. } };
		double[][] dcc = { { 0.0, 0.0, 0.0, 0.0, 0.0 } };
		double[][] net_dcd = { { 2337.711622751071,1622.9627999066765,1167.5459736628015,207.51803969841788,-2112.678173853766 } };
		double[][] sjr = { { 8036.92894210754,6311.5376031729775,9512.701883713473,7674.3730284983,7316.436115552524 } };
		double[][] tide = { { 5,6.56,6.184,5.508,5.083,6.913 } };
		double[][] smscg = { { 0.000, 0.000, 0.000, 1.000, 1.000 } };

		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(dcc);
		floatInput.add(net_dcd);
		floatInput.add(sjr);
		floatInput.add(tide);
		floatInput.add(smscg);

		int year = 1922;
		int month = 7;
		int cycle = 0;
		int constraintComponent = 0;
		int location = 0;
		int aveType = AggregateMonths.MONTHLY_MEAN.calsimCode;

		double[][] grad = linear.gradient(floatInput, year, month);
		// out[0][0] is the value at location 0
		// out[0][1] is the derivative at location 0 w.r.t. Sac
		// out[0][2] is the derivative at location 0 w.r.t. exports
		System.out.println("grad");
        System.out.println(grad[0][0]);
        System.out.println(grad[0][1]);
        System.out.println(grad[0][2]);
        
        double targetWQ = 1740.;
        double sac0 = sac[0][0];
        double exp0 = exp[0][0];
    	double[] constraint = linear.formulateConstraint(grad[0], sac0, exp0, targetWQ);
        
        
		floatInput.get(0)[0][0] += 10.;
		floatInput.get(1)[0][0] -= 10.;
		double[][] out2 = ann.annMonth(floatInput, year, month);
		double externalDiff = out2[0][0] - grad[0][LinearConstraint.VAL_NDX];
		assertEquals(externalDiff, grad[0][LinearConstraint.DSAC_NDX] * 10. - grad[0][LinearConstraint.DEXP_NDX] * 10.,
				0.1);

	}	

	
	@Test
	void testLinearConstraint2() {
		SurrogateMonth ann = EmmatonExampleTensorFlowANN.emmatonSurrogateMonth();
		LinearConstraint linear = new LinearConstraint(ann);

		double[][] sac = { { 9766.175691133974,14662.82642771133,12998.43326914722,16668.514572476895,24216.215179751343} };
		double[][] exp = { { 4502.2922802128,8125.171961652489,5060.69426035361,3712.8918427175477,4636.414446643629 } };
		//double[][] dcc = { { 31.0,31.0,25.999999999999993,0.0,0.0 } };
		double[][] dcc = { { 0.0,0.0,0.,0.0,0.0 } };
		double[][] net_dcd = { { 1899.3208915963032,2557.725946504804,2349.7695397664606,988.5063872623928,90.02670608797422 } };
		double[][] sjr = { { 1260.3361144318524,1331.3163357750168,1943.3619053233094,3112.8842851819595,3734.592943520188 } };
		double[][] tide = { { 5,6.56,6.184,5.508,5.083,6.913 } };
		double[][] smscg = { { 1.0,1.0,1.0,1.0,1.0 } };

		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(dcc);
		floatInput.add(net_dcd);
		floatInput.add(sjr);
		floatInput.add(tide);
		floatInput.add(smscg);

		int year = 1925;
		int month = 8;
		int location = 0;
		int aveType = AggregateMonths.MONTHLY_MEAN.calsimCode;

		double[][] grad = linear.gradient(floatInput, year, month);
		// out[0][0] is the value at location 0
		// out[0][1] is the derivative at location 0 w.r.t. Sac
		// out[0][2] is the derivative at location 0 w.r.t. exports
		System.out.println("test 2 grad");
        System.out.println(grad[0][0]);
        System.out.println(grad[0][1]);
        System.out.println(grad[0][2]);
        
        double targetWQ = 2240.;
        double sac0 = sac[0][0];
        double exp0 = exp[0][0];
    	double[] constraint = linear.formulateConstraint(grad[0], sac0, exp0, targetWQ);
        
        
		floatInput.get(0)[0][0] += 60.;
		floatInput.get(1)[0][0] -= 60.;
		double[][] out2 = ann.annMonth(floatInput, year, month);
		double externalDiff = out2[0][0] - grad[0][LinearConstraint.VAL_NDX];
		//assertEquals(externalDiff, grad[0][LinearConstraint.DSAC_NDX] *60. 
		//		- grad[0][LinearConstraint.DEXP_NDX] * 60.,
		//		0.3);

	}		
	
}
