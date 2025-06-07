package calsim.surrogate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
//import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

class SurrogateIdentifier {
	Integer location;
	Integer aveType;     //	See AggregateMonths.calsimCode for integer keys and values
	/*
	   AVE_TYPE KEY:
		      1 = monthly average
		      2 = first day of month value
		      3 = last day of month value
		      4 = maximum daily value
		      5 = minimum daily vlaue
		      6 = maximum 14-day value
		      7 = average for first 15 days
		      8 = average for last 15 days
		      37 = average for last 7 day
	 */

	public SurrogateIdentifier(int location, int aveType) {
		this.location = location;
		this.aveType = aveType;
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
		return location.equals(other.location) && aveType.equals(other.aveType);
	}

}





/**
 * The SalinitySurrogateManager is a singleton meant to be subclassed and used
 * as a delegate for calls coming from CalSIM The functions that it performs
 * include: 1) tracking which surrogate is called for which location and 2)
 */
public enum SalinitySurrogateManager { // ENUM is used to ensure singleton
	INSTANCE;

	/* CalSim Location Codes. Must be coordinated with reverse map below
	 */
	public static final int JER_CALSIM = 1; // Jersey Point
	public static final int RSL_CALSIM = 2; // Rock Slough
	public static final int EMM_CALSIM = 3; // Emmaton
	public static final int ANH_CALSIM = 4; // Antioch
	public static final int CLL_CALSIM = 5; // Collinsville
	public static final int MAL_CALSIM = 6; // Mallard is Chipps
	public static final int LVR_CALSIM = 7; // Los Vaqueros Intake
	public static final int MDR_CALSIM = 8; // Middle River Intake
	public static final int VIC_CALSIM = 9; // Victoria River Intake 
	public static final int TRP_CALSIM = 10; // CVP Tracy Intake 
	public static final int CCF_CALSIM = 11; // Clifton Court
	public static final int CCI_CALSIM = 12; // Clifton Court Intake near CC Ferry
	public static final int BAC_CALSIM = 15; // Old R. at Bacon Island (Rock Slough)		
	public static final int BDL_CALSIM = 20; // Beldons Landing
	public static final int MRZ_CALSIM = 21; // Martinez
	public static final int X2_CALSIM =  30; // X2

	// Must be coordinated with the above constants
	public static final Map<Integer, String> LOCATION_MAP = new HashMap<>();

	static {
		LOCATION_MAP.put(JER_CALSIM, "JER");
		LOCATION_MAP.put(RSL_CALSIM, "RSL");
		LOCATION_MAP.put(EMM_CALSIM, "EMM");
		LOCATION_MAP.put(ANH_CALSIM, "ANH");
		LOCATION_MAP.put(CLL_CALSIM, "CLL");
		LOCATION_MAP.put(MAL_CALSIM, "MAL");
		LOCATION_MAP.put(LVR_CALSIM, "LVR");
		LOCATION_MAP.put(MDR_CALSIM, "MDR");
		LOCATION_MAP.put(VIC_CALSIM, "VIC");
		LOCATION_MAP.put(TRP_CALSIM, "TRP");
		LOCATION_MAP.put(CCF_CALSIM, "CCF");
		LOCATION_MAP.put(CCI_CALSIM, "CCI");
		LOCATION_MAP.put(BAC_CALSIM, "BAC");		
		LOCATION_MAP.put(BDL_CALSIM, "BDL");
		LOCATION_MAP.put(MRZ_CALSIM, "MRZ");
		LOCATION_MAP.put(X2_CALSIM, "X2");
	}

	public static String getLocationCode(int locationId) {
		return LOCATION_MAP.getOrDefault(locationId, "UNKNOWN");
	}	

	// Constants associated with calling context
	public static final int CACHE_REC_CONSTRAINT = 2192;
	public static final int CACHE_REC_LINEGEN = 1392;
	public static final int CACHE_REC_GRID  = 1592;
	public static final int CACHE_REC_GRADIENT = 1792;
	public static final int CACHE_REC_ANNEC = 1993;
	public static final int CACHE_REC_REQFLOW_BASE = 2093;
	public static final int CACHE_REC_TEST = 3093;	



	// Only Monthly mean and maximum of 14-day running ave are implemented
	// and for 14-day running ave the actual implementation is truncated as explained
	// in the aggregateMonth code.

	public static final int MONTHLY_AVE = 1; 
	public static final int MAX_14 = 6;

	final HashMap<SurrogateIdentifier, SurrogateMonth> surrogateForLoc = new HashMap<SurrogateIdentifier, SurrogateMonth>();
	final HashMap<Integer, Integer> surrogateNdx = new HashMap<Integer, Integer>();

	public final int CACHELEN = 1000; 
	final Map<RunRecord, double[]> cachedConstraints = new LRUCache<>(1000); //{rhs, sacCoef, expCoef}.
	final Map<RunRecord, double[][]> cachedGradient = new LRUCache<>(1000);
	final Map<RunRecord, double[][]> cachedSurrogate = new LRUCache<>(1000);
	final Map<RunRecord, GridResult> cachedGridResult = new LRUCache<>(1000);
	final Map<RunRecord, Float> cachedRequiredFlow = new LRUCache<>(1000);
	private Logger LOGGER = null;
	private boolean header=false;
	boolean isLogging = false;

	SalinitySurrogateManager() {
		init();
	}


	private void init() {}

	public void enableLogging(String logDir) {
		LOGGER = Logger.getLogger("calsim.surrogate");
		FileHandler handler;
		try {
			handler = new FileHandler(logDir);
			handler.setFormatter(new SimpleFormatter() {
				@Override
				public synchronized String format(LogRecord lr) {
					return lr.getMessage();
				}
			});
			handler.setLevel(Level.INFO);
			LOGGER.setUseParentHandlers(false);
			LOGGER.addHandler(handler);
			this.isLogging = true;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	/**
	 * Set the output index that applies for an encoded location  
	 * @param location the CalSim location that will be sent in
	 * @param index within surrogate output
	 */
	public void setIndexForSite(int location, int index) {
		this.surrogateNdx.put(Integer.valueOf(location), Integer.valueOf(index));
	}

	/**
	 * Get the index in the surrogate output that corresponds to a CalSim location code sent in from WRESL
	 * @param calsimLocation
	 * @return
	 */
	public int getIndexForSite(int calsimLocation) {
		return this.surrogateNdx.get(Integer.valueOf(calsimLocation)).intValue();
	}

	/**
	 * Set Surrogate for location
	 * @param location (is this the CalSim code?) //TODO document this
	 * @param aveType  average type (is this the calsim Code?) //TODO document this 
	 * @param surrogateMonth
	 */
	public void setSurrogateForSite(int location, int aveType, SurrogateMonth surrogateMonth) {
		SurrogateIdentifier hasher = new SurrogateIdentifier(location, aveType);
		surrogateForLoc.put(hasher, surrogateMonth);
	}

	/**
	 * Get surrogate for location
	 * @param location (is this the CalSim code??)
	 * @param aveType average type (is this the calsim code?) //TODO document this 
	 * @return
	 */
	public SurrogateMonth getSurrogateForSite(int location, int aveType) {
		SurrogateIdentifier hasher = new SurrogateIdentifier(location, aveType);
		if (! this.surrogateForLoc.containsKey(hasher)) {
			throw new IllegalArgumentException("No surrogate registered for location code "+location+" ave type "+aveType);
		}
		return surrogateForLoc.get(hasher);
	}

	/**
	 * Get surrogate for location
	 * @param location (is this the CalSim code??)
	 * @param aveType average type (is this the calsim code?) //TODO document this 
	 * @return
	 */
	public boolean hasSurrogateForSite(int location, int aveType) {
		SurrogateIdentifier hasher = new SurrogateIdentifier(location, aveType);
		return surrogateForLoc.containsKey(hasher);
	}


	/**
	 * Generates a linear constraint for Sacramento (`Qsac`) and Export (`Qexp`) flows 
	 * to enforce a salinity water quality objective at a given location. The method 
	 * first determines whether the salinity constraint is always feasible, never feasible, 
	 * or sometimes feasible, and constructs a linear constraint accordingly.
	 * 
	 * <p>The procedure follows these steps:
	 * <ul>
	 *   <li>Uses a cached or newly computed grid evaluation to assess feasibility over a range of `Qsac` and `Qexp` values.</li>
	 *   <li>If the constraint is always feasible, a default constraint of `{0, -1, 1}` is returned.</li>
	 *   <li>If the constraint is never feasible, a default constraint of `{-(hiBound0 - loBound1), -1, 1}` is returned.</li>
	 *   <li>If feasibility is mixed, a gradient-based linear constraint is computed.</li>
	 *   <li>The computed constraint is returned as an array: `{rhs, sacCoef, expCoef}`.</li>
	 * </ul>
	 * 
	 * <p>The function leverages caching where possible to avoid redundant surrogate model evaluations.
	 * 
	 * @param monthlyInput  A list of input matrices for the surrogate model, indexed by features.
	 *                      Each entry is a `double[][]` where the first dimension is the batch index
	 *                      and the second dimension is the time lag.
	 * @param location      The location index where the constraint is being applied.
	 * @param variable      The index of the coefficient to return (0 for `rhs`, 1 for `sacCoef`, 2 for `expCoef`).
	 * @param ave_type      The averaging method used for salinity compliance.
	 * @param month         The month in which the constraint is being generated.
	 * @param year          The year in which the constraint is being generated.
	 * @param sac0          The nominal Sacramento flow (`Qsac_0`) at which the constraint is linearized.
	 * @param exp0          The nominal Export flow (`Qexp_0`) at which the constraint is linearized.
	 * @param targetWQ      The salinity water quality target that the constraint must enforce.
	 * @return The requested coefficient of the linear constraint (`rhs`, `sacCoef`, or `expCoef`).
	 */
	public double lineGenImpl(ArrayList<double[][]> monthlyInput, int location, int variable, 
			int ave_type, int month, int year, double sac0, double exp0, double targetWQ) {
		boolean verbose = false;
		int cyclePlaceholder = 0;

		int siteNDX = this.getIndexForSite(location);
		System.out.println("Linegen Location: " + getLocationCode(location) 
		+ "("+ location + ")" + " siteNDX " + siteNDX);
		System.out.println("sac0="+sac0+" exp0="+exp0);
		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		LinearConstraint linear = new LinearConstraint(sm);

		// This run record is location specific, for storing constraint coefs
		RunRecord recConstraint = new RunRecord(sm.getDailySurrogate(), monthlyInput, 
				location, 0, CACHE_REC_CONSTRAINT ,
				year, month, cyclePlaceholder, ave_type);        
		if (cachedConstraints.containsKey(recConstraint)) {
			if (verbose) {
			  System.out.println("Using cached constraint for location: " + location + " siteNDX: " + siteNDX);
			  System.out.println(recConstraint.toString());
			  System.out.println(Arrays.toString(cachedConstraints.get(recConstraint)));
			}
			return cachedConstraints.get(recConstraint)[variable];
		}
		if (verbose) {System.out.println("Cached constraint not found");}
	    //public RunRecord(Surrogate surrogate, double[] floatInputs, 
		//        int intInput0, int intInput1, int intContext,
        //        int year, int month, int cycle, int aveType, double aveParam)

		// This run record is generic, for interfaces that operate on the whole dataset
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), monthlyInput, RunRecord.LOC_UNSPEC, 
				0, CACHE_REC_LINEGEN,
				year, month, cyclePlaceholder, ave_type);

		GridResult gr;
		double loBound0 = 4000, hiBound0 = 22000;
		double loBound1 = 800., hiBound1 = 12800;


		if (this.cachedGridResult.containsKey(rec)) {
			if (verbose){System.out.println("&&&&&&&&&&&&& In Grid, Key recognized: " + rec.toString());}
			gr = cachedGridResult.get(rec);
		} else {
			if (verbose){System.out.println("************* In Grid, Key NOT recognized: " + rec.toString());}
		
			gr = sm.evaluateOnGrid(monthlyInput, year, month, loBound0, hiBound0, 4, loBound1, hiBound1, 3);
			if (verbose) {
			  System.out.println("Gridded result for location: "+ getLocationCode(location) + " siteNDX "+siteNDX);
			  System.out.println(gr.toString(siteNDX));
			}
			cachedGridResult.put(rec, gr);
		}

		int feasible = assessFeasible(gr, siteNDX, targetWQ);


		double[] resultConstraint;   // Data structure for result
		if (feasible > 0) {
			if (verbose){System.out.println("Year " + year + " Month " + month + " infeasible");}
			resultConstraint = new double[]{-(hiBound0 - loBound1), -1., 1.};
			cachedConstraints.put(recConstraint, resultConstraint);  // Cache before returning
			return resultConstraint[variable];
		} else if (feasible < 0) {
			if (verbose){System.out.println("Year " + year + " Month " + month + " always feasible");}
			resultConstraint = new double[]{0., -1., 1.};
			cachedConstraints.put(recConstraint, resultConstraint);  // Cache before returning
			return resultConstraint[variable];
		} else {
			if (verbose){System.out.println("Year " + year + " Month " + month + " sometimes feasible");}
		}
		double[][] grad = cachedGradient.containsKey(rec) ? cachedGradient.get(rec) : linear.gradient(monthlyInput, year, month);
		if (!cachedGradient.containsKey(rec)) {
			if (verbose){System.out.println("Generated gradient with dimensions " + grad.length + " " + grad[0].length);}
			cachedGradient.put(rec, grad);
		}

		resultConstraint = linear.formulateConstraint(grad[siteNDX], sac0, exp0, targetWQ);
		if (verbose) {
		  System.out.println("lineGenImpl grad");
		  System.out.println(grad[siteNDX][0] + " " + grad[siteNDX][1] + " " + grad[siteNDX][2]);
		  System.out.println("targetWQ: " + targetWQ + " sac0: " + sac0 + " exp0: " + exp0);
		}
		if (isConstraintFeasible(resultConstraint, hiBound0, loBound1, sac0, exp0,targetWQ)) {
			cachedConstraints.put(recConstraint, resultConstraint);  // Cache before returning
			return resultConstraint[variable];
		}

		if(verbose) { 
			System.out.println("Initial linearized constraint infeasible, searching alternative feasible point.");
		}
		double[] newPoint = findAlternativeFeasiblePoint(gr, siteNDX, targetWQ, sac0, exp0);
		if (newPoint == null) {
			if (verbose) {
				System.out.println("No valid feasible point found, returning always infeasible.");
			}
			resultConstraint =  new double[]{-(hiBound0 - loBound1), -1., 1.};
			cachedConstraints.put(recConstraint, resultConstraint);  // Cache before returning
			return resultConstraint[variable];
		}

		if (verbose){
			System.out.println("*&*&*&*&*&*&*&*&*&*\n");
			System.out.println("Alternative feasible point found: sac=" 
					+ newPoint[0] + " exp=" + newPoint[1]);
			System.out.println("EMM grid");
			System.out.println(gr.toString(this.getIndexForSite(EMM_CALSIM)));
			System.out.println("RSL grid");
			System.out.println(gr.toString(this.getIndexForSite(RSL_CALSIM)));
		}

		// Create a deep copy of monthlyInput to safely modify values
		ArrayList<double[][]> modifiedInput = new ArrayList<>();
		for (double[][] arr : monthlyInput) {
			double[][] copiedArr = new double[arr.length][];
			for (int i = 0; i < arr.length; i++) {
				copiedArr[i] = arr[i].clone();
			}
			modifiedInput.add(copiedArr);
		}

		// Update the new point values in the copied input
		modifiedInput.get(0)[0][0] = newPoint[0];  // Sac flow
		modifiedInput.get(1)[0][0] = newPoint[1];  // Exp flow       

		double[][] newGrad = linear.gradient(modifiedInput, year, month);
		double dS_dSac = newGrad[siteNDX][1];
		double dS_dExp = newGrad[siteNDX][2];

		if (dS_dSac < 0 && dS_dExp > 0) {
			resultConstraint = linear.formulateConstraint(newGrad[siteNDX], newPoint[0], newPoint[1], targetWQ);
			cachedConstraints.put(recConstraint, resultConstraint);  // Cache before returning
			return resultConstraint[variable];
		} else {
			System.out.println("Gradient at new point does not meet expected (-/+) criteria. Returning infeasible.");
			resultConstraint = new double[]{-(hiBound0 - loBound1), -1., 1.};
			cachedConstraints.put(recConstraint, resultConstraint);  // Cache before returning
			return resultConstraint[variable];
		}
	}

	private boolean isConstraintFeasible(double[] constraint, 
			double hiBound0, double loBound1, double sac0, double exp0, double targetWQ) {
		boolean feasible = (constraint[1] * hiBound0 + constraint[2] *loBound1 <= constraint[0]);
		return feasible;
	}

	private double[] findAlternativeFeasiblePoint(GridResult gr, int siteNDX, double targetWQ, double sac0, double exp0) {
		for (int i = 0; i < gr.getGridInput0().length; i++) {  // Start from lowest Sac values
			for (int j = 0; j < gr.getGridInput1().length; j++) {
				double sac = gr.getGridInput0()[i];
				double exp = gr.getGridInput1()[j];
				double salinity = gr.getResult()[i][j][siteNDX];
				if (salinity <= targetWQ && sac >= sac0 && exp <= exp0) {
					return new double[]{sac, exp};
				}
			}
		}
		return null;
	}


	/**
	 * Evaluates the grid of surrogate outputs to assess overall feasibility relative to the target.
	 *
	 * @param gr       The grid result containing surrogate predictions.
	 * @param siteNdx  The output index corresponding to the location of interest.
	 * @param targetWQ The water quality target.
	 * @return -1 if all grid values are below the target (always feasible),
	 *          1 if all grid values are above the target (infeasible),
	 *          0 if the grid contains a mix of values.
	 */
	private int assessFeasible(GridResult gr, int siteNdx, double targetWQ) {
		int nx0 = gr.getGridInput0().length;
		int nx1 = gr.getGridInput1().length;
		boolean allNeg = true;
		boolean allPos = true;
		for (int i=0; i< nx0; i++) {
			for (int j=0; j<nx1; j++) {
				//double sac = gr.getGridInput0()[i];
				double est = gr.getResult()[i][j][siteNdx];
				if (est > targetWQ) { allNeg=false;}
				else if (est < targetWQ) { allPos=false; }
				if (! (allNeg || allPos)) return 0;  // truncate early if we already know the case is mixed
			}
		}
		// TODO check sure not allPos and allNeg
		int ret = allPos ? 1 : 0;  // positive is "higher than objective"
		ret = allNeg ? -1 : ret;
		return ret;
	}


	public void logInputs(SurrogateMonth sm, RunRecord rec, ArrayList<double[][]> inputs, 
			boolean fail, String context, String comment) {
		if (! isLogging) return;
		InputSizeInfo sizeInfo = new InputSizeInfo(inputs);
		logInputs(sm,rec,inputs,sizeInfo,fail,context,comment);
	}	

	public void logInputs(SurrogateMonth sm, RunRecord rec, ArrayList<double[][]> inputs,InputSizeInfo sizeInfo,
			boolean fail, String context, String comment) {
		if (! this.isLogging) return;
		if (! this.header) {
			LOGGER.info(inputLogHeader(sizeInfo));
			header = true;
		}
		LOGGER.info(inputLogEntry(sm, rec, inputs, sizeInfo,fail,context,comment));
	}

	public String inputLogEntry(SurrogateMonth sm, RunRecord rec,ArrayList<double[][]> inputs, InputSizeInfo sizeInfo, 
			boolean failure, String context, String comment) {
		String SEP = ",";
		if (context == null) context = "";
		if (comment == null) comment = "";
		int maxLag = sizeInfo.maxSize;
		int aveType = sm.getAgg().calsimCode;
		String base = sm.getDailySurrogate().identifier() + SEP + context + SEP 
				+ SEP + rec.getYear() + SEP + rec.getMonth() + SEP + aveType + SEP + failure + SEP + comment;
		int nbatch = sizeInfo.batchLen;
		int nvar = sizeInfo.nVar;
		int[] nLag = sizeInfo.innerDim;

		StringBuilder batchsb = new StringBuilder(base.length()+16*maxLag);
		for (int ivar=0; ivar < nvar; ivar++) {
			for (int ibatch=0; ibatch < nbatch; ibatch++) {

				batchsb.append(base);
				batchsb.append(SEP+ibatch+SEP+ivar);		    	
				for (int ndx = 0; ndx < nLag[ivar]; ndx++) {
					batchsb.append(SEP+inputs.get(ivar)[ibatch][ndx]);
				}
				batchsb.append("\n");
			}
		}
		return batchsb.toString();

	}

	/**
	 * Generates Input log header given information about the size of the input tensor
	 * @param sizeInfo
	 * @return
	 */
	public String inputLogHeader(InputSizeInfo sizeInfo) {
		int maxLag = sizeInfo.maxSize;
		String base="surrogate_id,context,year,month,cycle,avetype,failed,comment,batch,variable";
		StringBuilder sb = new StringBuilder(maxLag*3);
		sb.append(base);
		for (int ilag=0; ilag< maxLag; ilag++) {
			sb.append(","+"lag"+String.valueOf(ilag));
		}
		return sb.toString();
	}	

	public float annEC(ArrayList<double[][]> monthly, int location, int ave_type, int month, int year) {	
		//for average types that don't have an additional parameter
		return annEC(monthly,location,ave_type,month,year,RunRecord.NO_AVG_PARAM);
	}

	public float annEC(ArrayList<double[][]> monthly, int location, int ave_type, int month, int year, double ave_thresh) {
		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		int cyclePlaceholder = 0;
		// Previously, sac0 and exp0 were extracted manually. Now we use the convenience constructor.
		// For annEC we set intInput0 = 0 (or use location if that is appropriate) and intInput1 = (int) ave_thresh.
		int int0 = 0; int int1=0;
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), monthly, int0, int1, CACHE_REC_ANNEC,
				year, month, cyclePlaceholder, ave_type,ave_thresh);  
		this.logInputs(sm, rec, monthly, false, "annEC", null);

		int locIndex = this.getIndexForSite(location);
		//System.out.println("annEC Location: "+location+" locIndex "+locIndex);
		if (cachedSurrogate.containsKey(rec)) {
			//System.out.println("================= Surrogate cache found " + rec.toString());
			double[][] cached = cachedSurrogate.get(rec);
			//System.out.println("Dimensions of cached " + cached.length + " " + cached[0].length);
			return (float) cached[0][locIndex];
		} else {
			//System.out.println("Surrogate cache NOT found " + rec.toString());
			sm.getAgg().setThreshold(ave_thresh);
			double[][] eval = sm.annMonth(monthly, year, month);
			//System.out.println("Caching with dimensions of eval " + eval.length + " " + eval[0].length);
			cachedSurrogate.put(rec, eval);
			return (float) eval[0][locIndex];
		}
	}


	/**
	 * Given historical input, location find the required Sacramento flow to meet 
	 * a salinity or EC Target if such a value lies between sacLoBound and sacHiBound.
	 * Currently there is no attempt at caching here. 
	 * @param target EC Target
	 * @param monthlyInputs Monthly inputs. Current period Sac can be junk -- it is not used
	 * @param flowLoBound Lower bound for search
	 * @param flowHiBound Upper bound for search
	 * @param location Location where target is to be met
	 * @param ave_type averaging used
	 * @param month calendar month
	 * @param year calendar year
	 * @returns Required flow. If required flow is above sacHiBound, returns a number above sacHiBound. 
	 * If required flow is below sacLoBound, returns a number below sacLoBound. Currently +/- 999999.
	 */
	public float requiredFlow(double target, ArrayList<double[][]> monthlyInputs, 
			double flowLoBound, double flowHiBound, int location,
			int ave_type, int month, int year) {
		
        double aggParam = RunRecord.NO_AVG_PARAM;
		float req = requiredFlow(target, monthlyInputs,
				flowLoBound, flowHiBound, location, ave_type, month, year, aggParam);
		//System.out.println("Required flow for station: "
		//		+ getLocationCode(location)+"("+location+")");
		return req; 
	}



	public float requiredFlow(double target, ArrayList<double[][]> monthlyInputs, 
			double flowLoBound, double flowHiBound, int location,
			int ave_type, int month, int year, double nth) {

		
		
		SurrogateMonth sm = getSurrogateForSite(location, ave_type);

		// Values for caching
		int cyclePlaceholder = 0; // placeholder cycle
		int intInput0 = location;   // use location here
		int intInput1;
		if (isNearlyInteger(nth, 1e-4)) {
		    intInput1 = (int) Math.round(nth);
		} else {
		    throw new IllegalArgumentException(
		    		"Expected an aggregation parameter (probably # days for X2) that is ≈ integer, but got: " + nth);
		}
		double aggParam = target; 
		intInput1 = (ave_type == AggregateMonths.NTH_SMALLEST.calsimCode) ? intInput1 : 0;
		 
		    
		// For requiredFlow we need to override the flow being inferred (Sac).
		// Assume that the Sacramento flow is at feature index 0.
		double[] features = RunRecord.extractLatestFeatures(monthlyInputs);
		features[0] = 0.0; // override Sacramento/NDOI flow with zero for consistent hash

		// We are using an integer slot for caching avgParam and thus requiring it
		// to be an integer ... we could relax this if we created another floating point
		// slot in RunRecord. Let's see if anyone ever notices
		sm.getAgg().setThreshold(target); // this does no harm even if unused 
		sm.getAgg().setN(intInput1);      // 
		
		
		// Set the context field to indicate requiredFlow caching.
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), features, 
				intInput0, intInput1, CACHE_REC_REQFLOW_BASE,
				year, month, cyclePlaceholder, ave_type, aggParam);

		if (cachedRequiredFlow.containsKey(rec)) {
			//System.out.println("Required flow cache found: " + rec.toString());
			float fReq = cachedRequiredFlow.get(rec);
			//System.out.println("Value for location "+ getLocationCode(location) + " is "+fReq);
			return fReq;
		} else {
			//System.out.println("Required flow cache NOT found: " + rec.toString());
			//System.out.println("Using surrogate: "+sm.getDailySurrogate().getName());
		}

		InverseSurrogateMonth ism = new InverseSurrogateMonth(sm);
		int flowIndex = 0;
		int locIndex = this.getIndexForSite(location);
		//System.out.println("Using location "+location+" output index: "+locIndex);
		double req = ism.invert(target, monthlyInputs, flowIndex, flowLoBound, flowHiBound, 
				year, month, locIndex);

		//System.out.println("target: " + target + " req="+req);
		if (req > flowHiBound) req = 999999.;
		if (req < flowLoBound) req = -999999.;
		float fReq = (float) req;
		cachedRequiredFlow.put(rec, fReq);
		return fReq;
	}


	public static boolean isNearlyInteger(double value, double epsilon) {
	    return Math.abs(value - Math.round(value)) < epsilon;
	}
	//TODO: there are a lot of "placeholderCycles that need to be replaced
	// Need to make sure that the output location stuff is OK. surrogateForSite seems fine, but is the indexing used too?
}

