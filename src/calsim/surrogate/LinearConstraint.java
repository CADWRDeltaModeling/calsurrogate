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
	public double[][] linearize( ArrayList<double[][]> monthlyInputs,  
			int year, int month, int cycle, 
			int constraintComponent, int location, int aveType, double targetWQ) {

		double[][] valGradient = gradient(monthlyInputs,year,month);
		return valGradient;		
	}

	/**
	 * 
	 * @param monthlyInputs Monthly values, with locations as the ArrayList members and the other dims
	 * are batch and time lags in that order
	 * @param year analyzed
	 * @param month analyzed
	 * @return 2D array with the first dimension being output locations and second dimension being the 
	 * value returned whcih is 0 for value, 1 as derivative w.r.t. Sac (or first input) and 2 for 
	 * derivative w.r.t. exports (or second input)
	 */
	public double[][] gradient( ArrayList<double[][]> monthlyInputs, int year, int month) {

		// centered estimate first then sac and exports negative and positive in that order
		final int NPERTCASE = 5;
		final double[] perturb = {-1.0,1.0};
		int nLoc = monthlyInputs.size();
		//nBatch = monthlyInputs.get(0).length; //TODO this should be checked to be one until handled 
		int nTime = monthlyInputs.get(0)[0].length;


		ArrayList<double[][]> perturbedInputs = new ArrayList<double[][]>();

		// Initialize everything to be the same as input monthly values
		for (int iLoc = 0; iLoc < nLoc; iLoc++) {
			double[][] perturbedData = new double[NPERTCASE][nTime];
			double[][] src = monthlyInputs.get(iLoc);
			for (int iPert=0; iPert < NPERTCASE; iPert++) {
				System.arraycopy(src[0], 0, perturbedData[iPert],0,nTime);
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
		double[][] monthOut = annMonth.annMonth(perturbedInputs, year, month);


		// Derivative of each output location with respect to Sac and Exp
		// TODO it would be nice to query this
		int nOut = monthOut[0].length;
		double[][] ddQAll = new double[nOut][3];  
		double dQ = perturb[1]-perturb[0];

		for (int iOut=0; iOut<nOut; iOut++) {
			ddQAll[iOut][0]= monthOut[0][iOut];
			double dEC = monthOut[2][iOut]-monthOut[1][iOut]; 
			ddQAll[iOut][1] = dEC/(dQ);   // derivative of location iLoc EC w.r.t. Sac flow
			dEC = monthOut[4][iOut]-monthOut[3][iOut]; 
			ddQAll[iOut][2] = dEC/(dQ);    // derivative of location iloc EC w.r.t. Exports
		}
		return ddQAll;
	}	

}
