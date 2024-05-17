package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MockSurrogateTest {

	void TestDailyToSurrogate() {
		double[] data = new double[200];
		for (int i = 0; i < 118; i++) {
			data[i] = (double) i;
		}
		Surrogate mock = new MockSurrogate(118);

	}

	@Test
	void testValues() {

		Surrogate mock = new MockSurrogate(4);

		double[][] sac = { { 10000., 20000., 10000., 20000 }, { 10000., 20000., 10000., 20000 },
				{ 10000., 20000., 10000., 5000 } };
		double[][] exp = { { 4000., 4000., 12000., 12000. }, { 4000., 4000., 12000., 12000. },
				{ 4000., 4000., 12000., 12000. } };
		double[][] mix0 = { { 1., 1., 0.33, 0.33 }, { 0., 0., 0.3, 0.3 }, { 0., 0., 0., 0.3 } };
		double[][] mix1 = { { 0., 0., 0.33, 0.33 }, { 1., 1., 0.6, 0.6 }, { 0., 0., 0., 0.6 } };
		double[][] mix2 = { { 0., 0., 0., 0. }, { 0., 0., 0.6, 0.6 }, { 1., 1., 1, 0.6 } };
		ArrayList<double[][]> floatInput = new ArrayList<double[][]>();
		floatInput.add(sac);
		floatInput.add(exp);
		floatInput.add(mix0);
		floatInput.add(mix1);
		floatInput.add(mix2);

		float[][] est = mock.estimate(floatInput, null);
		for (int ibatch = 0; ibatch < 2; ibatch++) {
			for (int jtime = 0; jtime < 4; jtime++) {
				System.out.println("Out " + ibatch + "," + jtime + "  out: " + est[ibatch][jtime]);
			}
		}

	}

}
