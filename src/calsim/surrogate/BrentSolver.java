
package calsim.surrogate;

/**
 * Implements the <a href="http://mathworld.wolfram.com/BrentsMethod.html">
 * Brent algorithm</a> for finding zeros of real univariate functions. The
 * function should be continuous but not necessarily smooth. The {@code solve}
 * method returns a zero {@code x} of the function {@code f} in the given
 * interval {@code [a, b]} to within a tolerance {@code 2 eps abs(x) + t} where
 * {@code eps} is the relative accuracy and {@code t} is the absolute accuracy.
 * <p>
 * The given interval must bracket the root.
 * </p>
 * <p>
 * The reference implementation is given in chapter 4 of <blockquote>
 * <b>Algorithms for Minimization Without Derivatives</b>, <em>Richard P.
 * Brent</em>, Dover, 2002 </blockquote>
 *
 */
public abstract class BrentSolver implements LineSearchable {

	private double[] start;
	private double[] searchDir;

	private double min = 0.;
	private double max = 1.;

	private double absoluteAccuracy = 1e-6;
	private double relativeAccuracy = 1e-5;
	private double functionAccuracy = 1.e-4;

	public double getAbsoluteAccuracy() {
		return absoluteAccuracy;
	}

	public double getRelativeAccuracy() {
		return relativeAccuracy;
	}

	public double getFunctionAccuracy() {
		return functionAccuracy;
	}

	public boolean almostEqual(double x, double y) {
		return Math.abs(x - y) < getAbsoluteAccuracy(); // TODO should this be one of the above precisions?
	}

	public double computeObjectiveValue(double z) {
		double[] x = getStart();
		System.out.println("x=" + x[0] + "," + x[1]);
		double[] xrev = new double[2];
		for (int i = 0; i < x.length; i++) {
			xrev[i] = x[i] + z * this.getSearchDir()[i];

		}
		System.out.println("xrev " + xrev[0] + " " + xrev[1]);
		double xout = this.eval(xrev);
		System.out.println(
				"evaluated scalar loc: " + z + " at x[0]" + xrev[0] + " x[1] " + xrev[1] + " gives xout: " + xout);
		return this.eval(xrev);
	}

	public double[] getStart() {
		return this.start;
	}

	public void setStart(double[] start) {
		this.start = start;
	}

	// This is for Brent method, not the
	public double getStartValue() {
		return (this.min + this.max) / 2.;
	}

	public double doSolve() {
		double min = getMin();
		double max = getMax();
		final double initial = getStartValue();
		final double functionValueAccuracy = getFunctionAccuracy();

		// verifySequence(min, initial, max);

		// Return the initial guess if it is good enough.
		double yInitial = computeObjectiveValue(initial);
		if (Math.abs(yInitial) <= functionAccuracy) {
			return initial;
		}

		// Return the first endpoint if it is good enough.
		double yMin = computeObjectiveValue(min);
		if (Math.abs(yMin) <= functionAccuracy) {
			return min;
		}

		System.out.println("ok initial " + initial + " min " + min + " yInitial " + yInitial + " ymin " + yMin);

		// Reduce interval if min and initial bracket the root.
		if (yInitial * yMin < 0) {
			System.out.println("A initial " + initial + " min " + min + "yInit" + yInitial + " " + yMin);
			return brent(min, initial, yMin, yInitial);
		}

		// Return the second endpoint if it is good enough.
		double yMax = computeObjectiveValue(max);
		if (Math.abs(yMax) <= functionAccuracy) {
			return max;
		}

		// Reduce interval if initial and max bracket the root.
		if (yInitial * yMax < 0) {
			System.out.println("B initial " + initial + " max " + max + "yInit" + yInitial + " " + yMax);
			return brent(initial, max, yInitial, yMax);
		}

		throw new IndexOutOfBoundsException("No bracketing values");
	}

	/**
	 * Search for a zero inside the provided interval. This implementation is based
	 * on the algorithm described at page 58 of the book <blockquote> <b>Algorithms
	 * for Minimization Without Derivatives</b>, <it>Richard P. Brent</it>, Dover
	 * 0-486-41998-3 </blockquote>
	 *
	 * @param lo  Lower bound of the search interval.
	 * @param hi  Higher bound of the search interval.
	 * @param fLo Function value at the lower bound of the search interval.
	 * @param fHi Function value at the higher bound of the search interval.
	 * @return the value where the function is zero.
	 */
	private double brent(double lo, double hi, double fLo, double fHi) {
		double a = lo;
		double fa = fLo;
		double b = hi;
		double fb = fHi;
		double c = a;
		double fc = fa;
		double d = b - a;
		double e = d;

		final double t = getAbsoluteAccuracy();
		final double eps = getRelativeAccuracy();

		while (true) {
			if (Math.abs(fc) < Math.abs(fb)) {
				a = b;
				b = c;
				c = a;
				fa = fb;
				fb = fc;
				fc = fa;
			}

			final double tol = 2 * eps * Math.abs(b) + t;
			final double m = 0.5 * (c - b);

			if (Math.abs(m) <= tol || almostEqual(fb, 0)) {
				return b;
			}
			if (Math.abs(e) < tol || Math.abs(fa) <= Math.abs(fb)) {
				// Force bisection.
				d = m;
				e = d;
			} else {
				double s = fb / fa;
				double p;
				double q;
				// The equality test (a == c) is intentional,
				// it is part of the original Brent's method and
				// it should NOT be replaced by proximity test.
				if (a == c) {
					// Linear interpolation.
					p = 2 * m * s;
					q = 1 - s;
				} else {
					// Inverse quadratic interpolation.
					q = fa / fc;
					final double r = fb / fc;
					p = s * (2 * m * q * (q - r) - (b - a) * (r - 1));
					q = (q - 1) * (r - 1) * (s - 1);
				}
				if (p > 0) {
					q = -q;
				} else {
					p = -p;
				}
				s = e;
				e = d;
				if (p >= 1.5 * m * q - Math.abs(tol * q) || p >= Math.abs(0.5 * s * q)) {
					// Inverse quadratic interpolation gives a value
					// in the wrong direction, or progress is slow.
					// Fall back to bisection.
					d = m;
					e = d;
				} else {
					d = p / q;
				}
			}
			a = b;
			fa = fb;

			if (Math.abs(d) > tol) {
				b += d;
			} else if (m > 0) {
				b += tol;
			} else {
				b -= tol;
			}
			fb = this.computeObjectiveValue(b); // TODO was 'eval'
			if ((fb > 0 && fc > 0) || (fb <= 0 && fc <= 0)) {
				c = a;
				fc = fa;
				d = b - a;
				e = d;
			}
		}
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double[] getSearchDir() {
		return searchDir;
	}

	public void setSearchDir(double[] searchDir) {
		this.searchDir = searchDir;
	}
}
