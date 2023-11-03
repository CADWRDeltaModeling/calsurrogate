package calsim.surrogate.examples;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import calsim.surrogate.*;

class SurrogateIdentifier {
	Integer location;
	Integer aveType;

	public SurrogateIdentifier(int location, int aveType) {
		location = location;
		aveType = aveType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aveType, location);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurrogateIdentifier other = (SurrogateIdentifier) obj;
		return Objects.equals(aveType, other.aveType) && Objects.equals(location, other.location);
	}

}

/**
 * The SalinitySurrogateManager is a singleton meant to be subclassed and used
 * as a delegate for calls coming from CalSIM The functions that it performs
 * include: 1) tracking which surrogate is called for which location and 2)
 */
public enum SalinitySurrogateManager { // ENUM is used to ensure singleton
	INSTANCE;

	/*
	 * 1 = Jersey Point 2 = Contra Costa - Rock Slough 3 = Emmaton 4 = Antioch 5 =
	 * Collinsville 6 = Chipps Island 7 = Los Vaqueros Intake 8 = Middle River 9 =
	 * Victoria Intake 10 = CVP Intake 11 = CCFB 12 = CCFB Intake 20 = Beldan 21 =
	 * Martinez }
	 */
	// Only Emmaton in prototype
	public final int JER_CALSIM = 1; // Jersey Point
	public final int RSL_CALSIM = 2; // Rock Slough
	public final int EMM_CALSIM = 3; // Emmaton
	public final int ANH_CALSIM = 4; // Antioch
	public final int CLL_CALSIM = 5; // Collinsville
	public final int MAL_CALSIM = 6; // Mallard is Chipps
	public final int BDL_CALSIM = 20; // Beldons Landing

	// Only Monthly mean
	public final int MEAN = 0; // TODO this might not be right. Check WRESL

	final HashMap<SurrogateIdentifier, SurrogateMonth> surrogateForLoc = new HashMap<SurrogateIdentifier, SurrogateMonth>();
	final HashMap<Integer, Integer> calsimToSurrogateNdx = new HashMap<Integer, Integer>();
	final HashMap<RunRecord, double[][]> cachedGradient = new HashMap<RunRecord, double[][]>();
	final HashMap<RunRecord, double[][]> cachedSurrogate = new HashMap<RunRecord, double[][]>();

	SalinitySurrogateManager() {
		init();
	}

	public void init() {
		//SurrogateMonth emmMonth = EmmatonExampleTensorFlowANN.emmatonSurrogateMonth();
		//setSurrogateForSite(EMM_CALSIM, MEAN, emmMonth);
		//calsimToSurrogateNdx.put(EMM_CALSIM, 0);

	}

	public void setSurrogateForSite(int location, int aveType, SurrogateMonth surrogateMonth) {
		SurrogateIdentifier hasher = new SurrogateIdentifier(location, aveType);
		surrogateForLoc.put(hasher, surrogateMonth);
	}

	public SurrogateMonth getSurrogateForSite(int location, int aveType) {
		SurrogateIdentifier hasher = new SurrogateIdentifier(location, aveType);
		return surrogateForLoc.get(hasher);
	}

	public double lineGenImpl(ArrayList<double[][]> monthlyInput, int location, int variable, int ave_type, int month,
			int year) {

		double sac0 = 0.; // TODO
		double exp0 = 0.; // TODO
		double targetWQ = 0.0; // TODO
		double[][] grad = null;
		int siteNDX = 0; // TODO

		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		LinearConstraint linear = new LinearConstraint(sm); // TODO Alternatively a lot of LInearConstraint could be
															// static

		int cyclePlaceholder = 0;
		// TODO what are the 0,0 on second line?
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), sac0, exp0, 0, 0, year, month, cyclePlaceholder,
				ave_type);

		if (cachedGradient.containsKey(rec)) {
			int index = 0; // TODO indexForLocation
			grad = cachedGradient.get(rec);

		} else {
			// Calculate and cache
			grad = linear.gradient(monthlyInput, year, month);
			cachedGradient.put(rec, grad); // Caches for next time

		}
		double[] constraint = linear.formulateConstraint(grad[siteNDX], targetWQ, sac0, exp0);

		return constraint[variable];

	}

	public float annEC(ArrayList<double[][]> monthly, int location, int variable, int ave_type, int month, int year) {
		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		int cyclePlaceholder = 0;
		double sac0 = 0.;
		double exp0 = 0.;
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), sac0, exp0, 0, 0, year, month, cyclePlaceholder,
				ave_type);

		if (cachedSurrogate.containsKey(rec)) {
			double[][] cached = cachedSurrogate.get(rec);
			int locIndex = 2; // TODO array index from calsim location
			return (float) cached[0][0]; // cachedSurrogate(rec,location); // TODO which variable??
		} else if (cachedGradient.containsKey(rec)) {
			double[][] cached = cachedGradient.get(rec);
			cachedSurrogate.put(rec, cached);
			return (float) cached[0][0];
		} else {
			double[][] eval = sm.annMonth(monthly, year, month);
			((HashMap<RunRecord, double[][]>) cachedSurrogate).put(rec, eval);
			return (float) eval[0][0];
		}
	}

}
