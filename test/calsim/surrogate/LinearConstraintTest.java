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
		/*
		double[][] out = linear.linearize( ArrayList<double[][]> monthlyInputs, int ndxSac, int ndxExp, 
				int year, int month, int cycle, 
				int constraintComponent, int location, int aveType, double targetWQ) {		
		
		   fail("Not yet implemented"); */
	} 

}
