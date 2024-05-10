package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DailyToSurrogateTest {

	double[] data;

	@BeforeEach
	void setUp() throws Exception {
		data = new double[124];
		for (int i = 0; i < 124; i++) {
			data[i] = (double) i;
		}
	}

	@Test
	void testDefault() {
		DailyToSurrogateDefault dd2s = new DailyToSurrogateDefault(118, false);
		double[] out = dd2s.dailyToSurrogateInput(data, 119);
		assertTrue(out[0] == 2.0);
		assertTrue(out.length == 118);
		assertTrue(out[out.length - 1] == 119.);

		DailyToSurrogateDefault dd2n = new DailyToSurrogateDefault(118, true);
		double[] out1 = dd2n.dailyToSurrogateInput(data, 119);
		assertTrue(out1[0] == 119.0);
		assertTrue(out1.length == 118);
		assertTrue(out1[out.length - 1] == 2.0);

	}

	@Test
	void testBlock() {
		DailyToSurrogateBlocked blocker = new DailyToSurrogateBlocked(8, 10, 11);
		double[] out = blocker.dailyToSurrogateInput(data, 119);
		assertTrue(out.length == 18);
		assertTrue(out[0] == 119.);
		assertTrue(out[7] == 112.);
		assertTrue(out[17] == 7.);

	}
}
