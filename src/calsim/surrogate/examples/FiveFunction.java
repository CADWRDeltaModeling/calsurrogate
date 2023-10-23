package calsim.surrogate.examples;

/**
 * This is a trivial function that returns the number 5 no matter what you input.
 * The class should be compiled along with the rest of the project and output to a 
 * jar file called calsurrogate.jar. 
 * 2. the 
 * 
 * The reason for this code is to test a workflow where the user has a jar file with their
 * own computational code. The  
 * To spice it up the inputs and outputs are doubles. 
 */
public class FiveFunction {

	public double eval(double input) {
		return 5.;
	}
	
}
