package calsim.surrogate;

import java.util.ArrayList;

public class MockSurrogate implements Surrogate {

	DailyToSurrogate dayToSurrogate;
	double SACMIN = 5000;
	double SACMAX = 25000;
	double EXPMAX = 14000;
	double EXPMIN = 500;

	public MockSurrogate(int lenHist) {
		dayToSurrogate = new DailyToSurrogateDefault(lenHist, true);
	}

	/**
	 * Rescales values between
	 */
	double rescale(double val, double vmin, double vmax, double scaledmin, double scaledmax) {
		double valspan = vmax - vmin;
		double scaledspan = scaledmax - scaledmin;
		double valnew = scaledmin + scaledspan * (val - vmin) / valspan;
		return valnew;
	}

	double kernel(double sac, double exp, double[] mix) {
		double std = mix[0];
		double convex = mix[1];
		double inv = mix[2];
		double Zs = 0, Zi = 0., Zc = 0.;

		if (std > 0.) {
			double X = rescale(sac, SACMIN, SACMAX, -1.5, 2.);
			double Y = rescale(exp, EXPMIN, EXPMAX, -2., 2.);

			double Zs1 = Math.exp(-X * X - Y * Y);
			double Zs2 = X - Y + Math.exp(-(X - 1.) * (X - 1.) - (Y - 1.) * (Y - 1.));
			Zs = -(Zs1 + Zs2) * 2.;
			Zs = rescale(Zs, -8., 7., 150., 2000.);
		}
		if (convex > 0.) {
			double X = rescale(sac, SACMIN, SACMAX, -1., 0.);
			double Y = rescale(exp, EXPMIN, EXPMAX, -1, 1.7);

			double Zc1 = Math.exp(-X * X - Y * Y);
			double Zc2 = (X - Y) * (0.5 - .25 * X - 0.08 * X * Y)
					+ 5 * Math.exp(-(X - 1.) * (X - 1.) / 5 - (Y - 1.) * (Y - 1.) / 8);
			Zc = -(Zc1 + Zc2) * 2.;
			Zc = rescale(Zc, -9.28, 0.52, 150., 2000.);
		}
		if (inv > 0.) {
			double X = rescale(sac, SACMIN, SACMAX, -3., 3.);
			double Y = rescale(exp, EXPMIN, EXPMAX, -2., 2.);
			double Zi1 = Math.exp(-X * X - Y * Y);
			double Zi2 = X + Y + 0.3 * Math.exp(-(X - 1.) * (X - 1.) - (Y - 1.) * (Y - 1.));
			Zi = (Zi1 + Zi2) * 2.;
			Zi = rescale(Zi, -10., 10., 150., 2000.);
		}
		double out = std * Zs + convex * Zc + inv * Zi;
		return out;
	}

	@Override
	public float[][] estimate(ArrayList<double[][]> rawData, ArrayList<long[][]> rawDataInt) {
		double[][] sac = rawData.get(0);
		double[][] exp = rawData.get(1);
		double[][] mix0 = rawData.get(2);
		double[][] mix1 = rawData.get(3);
		double[][] mix2 = rawData.get(4);
		int nbatch = sac.length;
		int ntime = sac[0].length;
		float[][] out = new float[nbatch][ntime];
		for (int ibatch = 0; ibatch < nbatch; ibatch++) {
			for (int jtime = 0; jtime < ntime; jtime++) {
				double[] mix = { mix0[ibatch][jtime], mix1[ibatch][jtime], mix2[ibatch][jtime] };
				out[ibatch][jtime] = (float) kernel(sac[ibatch][jtime], exp[ibatch][jtime], mix);
			}
		}
		return out;
	}

	@Override
	public DailyToSurrogate getDailyToSurrogate() {
		return this.dayToSurrogate;
	}

    /**
     * Returns the expected number of features (i.e. the length of the float ArrayList)
     * that this surrogate requires.
     */
    public int getNFeatures() {
    	return 5;
    }
	
}
