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
		int nInputEst = monthlyInputs.size();
		int nbatchEst=monthlyInputs.get(0).length;
		int nTimeEst=monthlyInputs.get(0)[0].length;
		
		
		for (int ifeature = 0; ifeature < 2; ifeature++) {
			System.out.println("Input feature"+ ifeature);
			int nbatch = monthlyInputs.get(ifeature).length;
			if (nbatch>3) {nbatch=3;}
			for (int ibatch=0; ibatch <nbatch; ibatch++) {
				int lenArr = monthlyInputs.get(ifeature)[ibatch].length;
				for (int j=0; j<lenArr; j++) {
					System.out.println("ifeature = "+ifeature+ " ibatch="+ibatch+" j="+j+": "+ monthlyInputs.get(ifeature)[ibatch][j]);
				}
			}
		}
		System.out.println("Num features="+nInputEst+" Est batch size="+nbatchEst+" Est time dimension="+nTimeEst);
	}

}
