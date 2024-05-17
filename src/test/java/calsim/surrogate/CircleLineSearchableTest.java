package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a concrete example of a LineSearchable. It is an implicit
 * representation of a circle, where f(x,y) - rad is zero on the circle,
 * positive outside and negative inside. Probably best to search it in only one
 * quadrant of coure.
 */
class CircleLineSearchable extends BrentSolver {
	public double eval(double[] x) {
		double rad = 50.;
		return (rad * rad - x[0] * x[0] - x[1] * x[1]);
	}
}

class CircleLineSearchableTest {
	@Test
	void testComputeObjectiveValue() {
		CircleLineSearchable circle = new CircleLineSearchable();
		circle.setMin(0);
		circle.setMax(220.);
		double[] start = { 0., 0. };
		circle.setStart(start);
		double[] searchDir = { 1., 1. };
		circle.setSearchDir(searchDir);
		double zopt = circle.doSolve();
		System.out.println("circle " + zopt);
	}

	@Test
	void testEval() {
		CircleLineSearchable circle = new CircleLineSearchable();
		// double[] x = {30.,40.};
		double[] x = { 35.35534479, 35.35534479 };
		double out = circle.eval(x);
		assertTrue(Math.abs(out) < 1e-1);
	}
}
