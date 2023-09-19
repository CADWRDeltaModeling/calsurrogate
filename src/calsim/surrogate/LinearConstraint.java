package calsim.surrogate;

import java.util.ArrayList;

public class LinearConstraint {

	public LinearConstraint(SurrogateMonth annMonth) {
		this.annMonth = annMonth;
	}

	SurrogateMonth annMonth;

	/**
	 * Generate a linear constraint for QSac and Qexp that serves to enforce water quality objectives
	 * @param monthly Inputs Monthly flow histories from CalSIM. Values for the present month represent the linearization point
	 * @param year Year in which constraint is being generated	
	 * @param month Month in which constraint is being generated
	 * @param cycle CalSIM cycle for which constraint is being generated, needed for cacheing.
	 * @param constraintComponent constraints have several parameters. This describes which is being queries
	 * @param aveType Basis of summarizing water quality compliance.
	 * @return void
	 */
	public double[][] linearize( ArrayList<double[][]> monthlyInputs, int ndxSac, int ndxExp, 
			int year, int month, int cycle, 
			int constraintComponent, int location, int aveType, double targetWQ) {

		// centered estimate first then sac and exports negative and positive in that order
		final int NPERTCASE = 5;
		final double[] perturb = {-1.,1.};
		//final double NONSENSE = -9999999.;
		//final int SACNDX=0;  // TODO HARDWIRED
		//final int EXPNDX=1;  // TODO HARDWIRED
		//double[] ddQ = {NONSENSE,NONSENSE};
		//double[] pOut = {NONSENSE,NONSENSE};
		//[] qIn = {NONSENSE,NONSENSE};
		int nLoc = monthlyInputs.size();
		//nBatch = monthlyInputs.get(0).length; //TODO this should be checked to be one until handled 
		int nTime = monthlyInputs.get(0)[0].length;


		ArrayList<double[][]> perturbedInputs = new ArrayList<double[][]>();
		
		// Initialize everything to be the same as input monthly values
		for (int iLoc = 0; iLoc < nLoc; iLoc++) {
			double[][] perturbedData = new double[NPERTCASE][nTime];
			for (int iPert=0; iPert < NPERTCASE; iPert++) {
				System.arraycopy(monthlyInputs.get(iLoc), 0, perturbedData[iPert],0,nTime);
			}
			perturbedInputs.add(perturbedData);
		}

		// Now perturb Sac and Exports
		int iPert = 0;  
		for (int iLoc=0; iLoc<2; iLoc++) {   //TODO HARDWIRED sac and exp location in array
			for (int ip=0; ip< perturb.length; ip ++) {
				iPert += 1;
				// iLoc = 0 is Sac, iLoc = 1 is Exports
				// iPert is the perturbation case and the final 0 is the current time period
				perturbedInputs.get(iLoc)[iPert][0] =  perturbedInputs.get(iLoc)[iPert][0] + perturb[ip];
			}
		}

		// monthOut has dimensions of batch size (5) by number of stations predicted by ANN
		double[][] monthOut = annMonth.annMonth(perturbedInputs, location, year, month, cycle);


		// Derivative of each location with respect to Sac and Exp
		double[][] ddQAll = new double[nLoc][2];  
		double dQ = perturb[1]-perturb[0];
		for (int iLoc=0; iLoc<nLoc; iLoc++) {
			double dEC = monthOut[2][iLoc]-monthOut[1][iLoc]; 
			ddQAll[iLoc][0] = dEC/(2.*dQ);   // derivative of location iLoc EC w.r.t. Sac flow
			dEC = monthOut[4][iLoc]-monthOut[3][iLoc]; 
			ddQAll[iLoc][1] = dEC/(2.*dQ);    // derivative of location iloc EC w.r.t. Exports
		}
        return ddQAll;
		//pOut[ip] = -9999.; annMonth.annMonth(monthlyInputs, location, year, month, cycle); //TODO
		// Now we have the derivatives of the objective (including all the averaging of output
		// with respect to Sac (index 0 of ddQ) and Exports (index 1)
		// What remains is to convert this to a constraint.
		// We will write this around Qsac0 ad Qexp0 
		// (Qsac - Qsac0)*ddQ[0] + (Qexp - Qexp0)*ddQ[1] < ECTARGET
		// Rearranging this gives Qsac*ddQ[0] + Qexp*ddQ[1] < ECTARGET + Qsac0*ddQ[0] + Qexp0*ddQ[1]
		//if(constraintComponent ==) /// return the corresponding component	
	}
}
