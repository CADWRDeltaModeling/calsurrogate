package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.InputMismatchException;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class InputSizeInfoTest {	
	

	
	@Test
	void testGood() {
		ArrayList<double[][]> inputs=new ArrayList<double[][]>();
		
		double[][] arr0 = {{1.,2.,3.}};
		inputs.add(arr0);
		double[][] arr1 = {{4.,5.}};		
		inputs.add(arr1);
		InputSizeInfo info = new InputSizeInfo(inputs);
		assertEquals(info.batchLen,1);
		assertEquals(info.maxSize,3);
		assertEquals(info.minSize,2);


		double[][] arr2 = {{4.,5.,6.,7.}};		
		inputs.set(1, arr2);
		InputSizeInfo info2 = new InputSizeInfo(inputs);
		assertEquals(info2.batchLen,1);
		assertEquals(info2.maxSize,4);
		assertEquals(info2.minSize,3);
			
		
	}

	
	@Test 
	void testDifferentBatchSize() {

		ArrayList<double[][]> inputs=new ArrayList<double[][]>();
		double[][] arr0 = {{1.,2.,3.}};
		inputs.add(arr0);
		double[][] arr1 = {{4.,5.},{7.,5}};		
		inputs.add(arr1);
		assertThrows(InputMismatchException.class,()->{
		   InputSizeInfo info = new InputSizeInfo(inputs);
	    });
	}
}
