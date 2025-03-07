package calsim.surrogate;

import java.util.ArrayList;

public class LinearConstraint {

	public static final int VAL_NDX = 0;
	public static final int DSAC_NDX = 1;
	public static final int DEXP_NDX = 2;

	public LinearConstraint(SurrogateMonth annMonth) {
		this.annMonth = annMonth;
	}

	SurrogateMonth annMonth;

	/**
	 * Computes the gradient (finite difference approximation) of the surrogate output with respect to
	 * Sacramento and Export flows.
	 *
	 * <p>The function builds five perturbed cases per input location (each corresponding to a
	 * particular perturbation scenario):
	 * <ul>
	 *   <li>Index 0: Unperturbed (nominal) value.
	 *   <li>Index 1: Sacramento flow perturbed by -1.
	 *   <li>Index 2: Sacramento flow perturbed by +1.
	 *   <li>Index 3: Export flow perturbed by -1.
	 *   <li>Index 4: Export flow perturbed by +1.
	 * </ul>
	 * The gradient is then computed using central differences.
	 *
	 * @param monthlyInputs List of input arrays (one per location) where each array has dimensions
	 *                      [batch size][time lag]. The current time period is at index 0.
	 * @param year  The calendar year being analyzed.
	 * @param month The calendar month being analyzed.
	 * @return A 2D array where each row corresponds to an output location. The columns are:
	 *         <ul>
	 *           <li>Index 0: Nominal surrogate value (S₀).
	 *           <li>Index 1: Finite difference derivative with respect to Sac flow.
	 *           <li>Index 2: Finite difference derivative with respect to Export flow.
	 *         </ul>
	 */
	public double[][] gradient(ArrayList<double[][]> monthlyInputs, int year, int month) {
	    final int NPERTCASE = 5;  // Total perturbed cases: [0]=nominal, [1-2]=Sac, [3-4]=Exports
	    final double[] perturb = { -1.0, 1.0 };  // Offsets for finite difference estimation
	    int nLoc = monthlyInputs.size();
	    int nTime = monthlyInputs.get(0)[0].length;

	    // Build a list of perturbed inputs (one per location)
	    ArrayList<double[][]> perturbedInputs = new ArrayList<>();
	    for (int iLoc = 0; iLoc < nLoc; iLoc++) {
	        double[][] perturbedData = new double[NPERTCASE][nTime];
	        double[][] src = monthlyInputs.get(iLoc);
	        // Copy the nominal (unperturbed) values into all cases
	        for (int iPert = 0; iPert < NPERTCASE; iPert++) {
	            System.arraycopy(src[0], 0, perturbedData[iPert], 0, nTime);
	        }
	        perturbedInputs.add(perturbedData);
	    }

	    // Apply finite difference perturbations for Sacramento and Export flows.
	    // Note: Hardwired that iLoc==0 is Sac and iLoc==1 is Exports.
	    int iPert = 0;  // Start with unperturbed case at index 0.
	    for (int iLoc = 0; iLoc < 2; iLoc++) {
	        for (int ip = 0; ip < perturb.length; ip++) {
	            iPert += 1;
	            // For current location (Sac or Exports), modify the current time period value.
	            perturbedInputs.get(iLoc)[iPert][0] += perturb[ip];
	        }
	    }

	    // Evaluate the surrogate for all perturbed cases.
	    double[][] monthOut = annMonth.annMonth(perturbedInputs, year, month);

	    // Compute the gradients using central differences.
	    int nOut = monthOut[0].length;
	    double[][] ddQAll = new double[nOut][3];
	    double dQ = perturb[1] - perturb[0];  // Expected to be 2.0

	    for (int iOut = 0; iOut < nOut; iOut++) {
	        // Nominal output value
	        ddQAll[iOut][0] = monthOut[0][iOut];
	        // Derivative with respect to Sac: difference between positive and negative perturbation results
	        double dEC = monthOut[2][iOut] - monthOut[1][iOut];
	        ddQAll[iOut][1] = dEC / dQ;
	        // Derivative with respect to Exports
	        dEC = monthOut[4][iOut] - monthOut[3][iOut];
	        ddQAll[iOut][2] = dEC / dQ;
	    }
	    return ddQAll;
	}

	/**
	 * Generate a linear constraint for QSac and Qexp that serves to enforce a
	 * single water quality objective.
	 * 
	 * Note that unlike a lot of the other methods in this library that will treat
	 * all of the locations covered by a multivariate surrogate at once, this is
	 * just a utility for a single location and water quality target. The reason for
	 * that is that the targets vary and are not given for all the locations at once
	 * in a single call from WRESL. So what ends up happening is that the client
	 * code calls gradient() and caches that result. Then when individual calls come
	 * in for a constraint at various stations it will use this method as a utility.
	 * 
	 * @param monthly             Inputs Monthly flow histories from CalSIM. Values
	 *                            for the present month represent the linearization
	 *                            point
	 * @param year                Year in which constraint is being generated
	 * @param month               Month in which constraint is being generated
	 * @param cycle               CalSIM cycle for which constraint is being
	 *                            generated, needed for cacheing.
	 * @param constraintComponent constraints have several parameters. This
	 *                            describes which is being queries
	 * @param aveType             Basis of summarizing water quality compliance.
	 * @return void
	 */
	public double[] formulateConstraint(double[] valGradient, double sac0, double exp0, double targetWQ) {

		// reorganize this:
		// S0 + dS/dSac * (Sac - Sac0) + dS/dExp * (Exp - Exp0) < target
		// Where the incoming values of monthlyInputs.get(0)[][0] (first time slot) is
		// Sac0
		// and the incoming monthlyInputs.get(1)[][0] (most recent time slot) is Exp0
		// and S0 is the corresponding salinity for those nominal values
		// Reorganizing the above equation gives the actual constraints on Sac and Exp
		// In terms of known quantities:
		// dS/dSac* Sac + dS/dExp*Exp < (target - S0 + dS/dSac*Sac0 + dS/dExp*Exp0)
		// The variable rhs represents the total on the right
		// for (int iLoc = 0)
		double rhs = targetWQ - valGradient[LinearConstraint.VAL_NDX] + valGradient[LinearConstraint.DSAC_NDX] * sac0
				+ valGradient[LinearConstraint.DEXP_NDX] * exp0;
		double sacCoef = valGradient[LinearConstraint.DSAC_NDX];
		double expCoef = valGradient[LinearConstraint.DEXP_NDX];
		double[] constraintCoefs = { rhs, sacCoef, expCoef };
		boolean verbose = true;
		if(verbose) {
			System.out.println("targetWQ");
			System.out.println(targetWQ);
			System.out.println("sac0");
			System.out.println(sac0);
			System.out.println("exp0");
			System.out.println(exp0);
			System.out.println("Coefs: "+ rhs +" "+sacCoef+ " "+ expCoef );
		}
		return constraintCoefs;
	}

}
