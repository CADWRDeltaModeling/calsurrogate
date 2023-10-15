package calsim.surrogate;

import calsim.surrogate.examples.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calsim.surrogate.examples.SalinitySurrogateManager;

class SalinitySurrogateManagerTest {

	@Test
	void testSalinityManager() {
		SalinitySurrogateManager singleton = SalinitySurrogateManager.INSTANCE;
	}

}
