package calsim.surrogate;

/**
 * Represents a function that may be line searched from a start point in
 * direction searchDir In the context of CalSIM this is used to hold most of the
 * inputs in the ANN steady while characterizing or optimizing over current
 * month Sac and current Month export flows. Contours of that constraint are
 * assumed to be of the form f(Qsac,Qexp,otherVars) - EC = 0 where otherVars
 * includes other variables in calsim including lags and EC is the contour being
 * searched for, typically a regulatory value.
 */
public interface LineSearchable {

	public double eval(double[] x);

}
