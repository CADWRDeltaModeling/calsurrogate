package calsim.surrogate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
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

	/*
	 * 1 = Jersey Point 2 = Contra Costa - Rock Slough 3 = Emmaton 4 = Antioch 5 =
	 * Collinsville 6 = Chipps Island 7 = Los Vaqueros Intake 8 = Middle River 9 =
	 * Victoria Intake 10 = CVP Intake 11 = CCFB 12 = CCFB Intake 20 = Beldon 21 =
	 * Martinez }
	 */
	// Only Emmaton in prototype
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
	public static final int BDL_CALSIM = 20; // Beldons Landing
	public static final int MRZ_CALSIM = 21; // Martinez
	public static final int X2_CALSIM =  30; // X2


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
		//return 0;  //TODO previous hardwire
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




	public double lineGenImpl(ArrayList<double[][]> monthlyInput, int location, int variable, 
	        int ave_type, int month, int year, double sac0, double exp0, double targetWQ) {
	    boolean verbose = true;
	    int cyclePlaceholder = 0; // still a placeholder

	    int siteNDX = this.getIndexForSite(location);
	    System.out.println("Linegen Location: "+location+" siteNDX "+siteNDX);
	    SurrogateMonth sm = getSurrogateForSite(location, ave_type);
	    LinearConstraint linear = new LinearConstraint(sm);

	    // For lineGen, we want to cache based on the entire input.
	    // Here we use the convenience constructor: note that we pass the raw monthly inputs.
	    // By convention, we set intInput0 = 0 (not location-specific here),
	    // intInput1 = 0, and intContext = CACHE_REC_LINEGEN.
	    RunRecord rec = new RunRecord(sm.getDailySurrogate(), monthlyInput, 0, 0, CACHE_REC_LINEGEN,
	                                  year, month, cyclePlaceholder, ave_type);

	    GridResult gr;
	    double loBound0 = 4000, hiBound0 = 22000;
	    double loBound1 = 800., hiBound1 = 12800;
	    if (this.cachedGridResult.containsKey(rec)) {
	        System.out.println("&&&&&&&&&&&&& In Grid, Key recognized: " + rec.toString());
	        gr = cachedGridResult.get(rec);
	    } else {
	        System.out.println("************* Key NOT recognized: " + rec.toString());
	        gr = sm.evaluateOnGrid(monthlyInput, year, month, loBound0, hiBound0, 4, loBound1, hiBound1, 3);
	        cachedGridResult.put(rec, gr);
	    }

	    int feasible = assessFeasible(gr, siteNDX, targetWQ);
	    if (verbose) {
	    	System.out.println("assessFeasible for location: "+location);
	        if (feasible > 0)
	            System.out.println("Year "+year+ " Month "+ month + " infeasible");
	        else if (feasible < 0)
	            System.out.println("Year "+year+ " Month "+ month + " always feasible");
	        else
	            System.out.println("Year "+year+ " Month "+ month + " sometimes feasible");
	    }
	    if (feasible < 0) { // always feasible case
	        double[] alwaysFeasibleConstraint = {0., -1., 1.};
	        return alwaysFeasibleConstraint[variable];
	    } else if (feasible > 0) { // infeasible
	        double[] neverFeasibleConstraint = {-(hiBound0 - loBound1), -1., 1.};
	        return neverFeasibleConstraint[variable];
	    }

	    double[][] grad;
	    if (cachedGradient.containsKey(rec)) {
	        grad = cachedGradient.get(rec);
	    	System.out.println("&-&-&-&-&-&-&-&- In Grad, Key recognized: " + rec.toString());
	    	System.out.println("Retrieved gradient with dimensions "+grad.length+ " "+grad[0].length);
	    } else {
	        grad = linear.gradient(monthlyInput, year, month);
	    	System.out.println("Generated gradient with dimensions "+grad.length+ " "+grad[0].length);	        
	        cachedGradient.put(rec, grad);
	    }

	    if (verbose) {
	        System.out.println("lineGenImpl grad");
	        System.out.println(grad[siteNDX][0] + " " + grad[siteNDX][1] + " " + grad[siteNDX][2]);
	        System.out.println("targetWQ: " + targetWQ+" sac0: " + sac0+ " exp0: " + exp0);
	    }
	    double[] constraint = linear.formulateConstraint(grad[siteNDX], sac0, exp0, targetWQ);
	    return constraint[variable];
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
				double sac = gr.getGridInput0()[i];
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
		return annEC(monthly,location,ave_type,0.,month,year);
	}

	public float annEC(ArrayList<double[][]> monthly, int location, int ave_type, double ave_thresh, int month, int year) {
	    SurrogateMonth sm = getSurrogateForSite(location, ave_type);
	    int cyclePlaceholder = 0;
	    // Previously, sac0 and exp0 were extracted manually. Now we use the convenience constructor.
	    // For annEC we set intInput0 = 0 (or use location if that is appropriate) and intInput1 = (int) ave_thresh.
	    int int0 = 0; int int1=0;
	    RunRecord rec = new RunRecord(sm.getDailySurrogate(), monthly, int0, int1, CACHE_REC_ANNEC,
                year, month, cyclePlaceholder, ave_type);  
	    this.logInputs(sm, rec, monthly, false, "annEC", null);
	    
	    int locIndex = this.getIndexForSite(location);
	    System.out.println("annEC Location: "+location+" locIndex "+locIndex);
	    if (cachedSurrogate.containsKey(rec)) {
	        System.out.println("================= Surrogate cache found " + rec.toString());
	        double[][] cached = cachedSurrogate.get(rec);
	        System.out.println("Dimensions of cached " + cached.length + " " + cached[0].length);
	        return (float) cached[0][locIndex];
	    } else {
	        System.out.println("Surrogate cache NOT found " + rec.toString());
	        sm.getAgg().setThreshold(ave_thresh);
	        double[][] eval = sm.annMonth(monthly, year, month);
	        System.out.println("Caching with dimensions of eval " + eval.length + " " + eval[0].length);
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

		return requiredFlow(target, monthlyInputs,
				flowLoBound, flowHiBound, location, ave_type, month, year, 0.);
	}



	public float requiredFlow(double target, ArrayList<double[][]> monthlyInputs, 
	        double flowLoBound, double flowHiBound, int location,
	        int ave_type, int month, int year, double nth) {

	    SurrogateMonth sm = getSurrogateForSite(location, ave_type);

	    int cyclePlaceholder = 0; // placeholder cycle
	    int intInput0 = location;   // use location here
	    int intInput1 = (ave_type == AggregateMonths.NTH_SMALLEST.calsimCode ? (int) nth : 0);
	    // For requiredFlow we need to override the flow being inferred (Sac).
	    // Assume that the Sacramento flow is at feature index 1.
	    double[] features = RunRecord.extractLatestFeatures(monthlyInputs);
	    features[0] = 0.0; // override Sacramento flow with zero

	    // Set the context field to indicate requiredFlow caching.
	    RunRecord rec = new RunRecord(sm.getDailySurrogate(), features, intInput0, intInput1, CACHE_REC_REQFLOW_BASE,
	                                  year, month, cyclePlaceholder, ave_type);

	    if (cachedRequiredFlow.containsKey(rec)) {
	        System.out.println("Required flow cache found: " + rec.toString());
	        float fReq = cachedRequiredFlow.get(rec);
	        System.out.println("Value for location "+location + " is "+fReq);
            return fReq;
	    } else {
	        System.out.println("Required flow cache NOT found: " + rec.toString());
	    }

	    InverseSurrogateMonth ism = new InverseSurrogateMonth(sm);
	    int flowIndex = 0;
	    double req = ism.invert(target, monthlyInputs, flowIndex, flowLoBound, flowHiBound, 
	                              year, month, location);
	    
	    System.out.println("target: " + target + " req="+req);
	    if (req > flowHiBound) req = 999999.;
	    if (req < flowLoBound) req = -999999.;
	    float fReq = (float) req;
	    cachedRequiredFlow.put(rec, fReq);
	    System.out.println("Calcualted as " + fReq);
	    return fReq;
	}



	//TODO: there are a lot of "placeholderCycles that need to be replaced
	// Need to make sure that the output location stuff is OK. surrogateForSite seems fine, but is the indexing used too?
}

