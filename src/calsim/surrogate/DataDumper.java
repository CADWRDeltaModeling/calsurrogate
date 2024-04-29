package calsim.surrogate;

import java.util.ArrayList;

public class DataDumper {
    String filename;
    
	DataDumper(String fname){
		this.filename = fname;
	}
	
	DataDumper(){
		this.filename = null;
	}
	
	void dumpInputs(ArrayList<double[][]> monthlyInputs) {
		int nInput = monthlyInputs.size();
		for (int i = 0; i<nInput; i++) {
			System.out.println("Input "+ i);
			int lenArr = monthlyInputs.get(i)[0].length;
			for (int j=0; j<lenArr; j++) {
				System.out.println("j="+j+": "+ monthlyInputs.get(i)[0][j]);
			}
		}
	}
	
}
