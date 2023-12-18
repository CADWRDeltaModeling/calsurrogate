package calsim.surrogate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
	

	// Only Monthly mean and maximum of 14-day running ave are implemented
	// and for 14-day running ave the actual implementation is truncated as explained
	// in the aggregateMonth code.
	
	public final int MONTHLY_AVE = 1; 
	public final int MAX_14 = 6;

	final HashMap<SurrogateIdentifier, SurrogateMonth> surrogateForLoc = new HashMap<SurrogateIdentifier, SurrogateMonth>();
	final HashMap<Integer, Integer> surrogateNdx = new HashMap<Integer, Integer>();
	final HashMap<RunRecord, double[][]> cachedGradient = new HashMap<RunRecord, double[][]>();
	final HashMap<RunRecord, double[][]> cachedSurrogate = new HashMap<RunRecord, double[][]>();
	final HashMap<RunRecord, GridResult> cachedGridResult = new HashMap<RunRecord, GridResult>();

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
	    //return this.surrogateNdx.get(Integer.valueOf(calsimLocation)).intValue();
	    return 0;  //TODO hardwire
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
		return surrogateForLoc.get(hasher);
	}
	
	

	
    /**
     * 
     * @param monthlyInput
     * @param location
     * @param variable parameter of constraint being requested. See documentation for LinearConstraint
     * @param ave_type Calsim code corresponding to aggregateMonth
     * @param month Calendar month of call
     * @param year  Calendar year of call
     * @param sac0  Nominal Sacramento R. Flow around which linearization is structured 
     * @param exp0  Nominal Export around which linearization is structured
     * @param targetWQ EC objective that informs constraint
     * @return
     */
	public double lineGenImpl(ArrayList<double[][]> monthlyInput, int location, int variable, 
			int ave_type, int month,int year, double sac0, double exp0, double targetWQ) {
		boolean verbose = false;
		int cyclePlaceholder = 0; // TODO hardwired, need to use the actual cycle
		
		double[][] grad = null;
		int siteNDX = this.getIndexForSite(location);

		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		LinearConstraint linear = new LinearConstraint(sm); 


		// TODO what are the 0,0 on second line?
		int int0 = 5;  // batch size
		int int1 = 0;
		//System.out.println("Generating Run Record in linegen yr/mon "+year+" "+month );
		//System.out.println( monthlyInput.get(0)[0][0]   +  " " + monthlyInput.get(1)[0][0] +  " "
		//                    + monthlyInput.get(2)[0][0] +  " " + monthlyInput.get(3)[0][0] +  " " 
		//		            + monthlyInput.get(3)[0][0] +  " " + monthlyInput.get(4)[0][0] +  " " 
		//                    + monthlyInput.get(5)[0][0]);
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), sac0, exp0, int0, int1, year, month, cyclePlaceholder,
				ave_type);
		
		GridResult gr;
		double loBound0=4000; double hiBound0=22000;
		double loBound1=800.; double hiBound1=12800;
		if (this.cachedGridResult.containsKey(rec)) {
			gr = cachedGridResult.get(rec);
		}else {
			gr = sm.evaluateOnGrid(monthlyInput, year, month, loBound0, hiBound0, 4, loBound1, hiBound1, 3);
			cachedGridResult.put(rec,gr);
		}
		
		int feasible = assessFeasible(gr, siteNDX, targetWQ);  //TODO in early prototypes siteNDX should be 0
		if (verbose) {
			if (feasible > 0) {
				System.out.println("Year "+year+ " Month "+ month + " infeasible ++++++++++++++++++");
	
			}
			if (feasible < 0) {
				System.out.println("Year "+year+ " Month "+ month + " always  feasible ---------------");
	
			}
			if (feasible == 0) {
				System.out.println("Year "+year+ " Month "+ month + " sometimes feasible ==============");
			}
		}
		if (feasible < 0) { // return trivial values to indicate sac - exp > 0.
			double[] alwaysFeasibleConstraint = {1.,-1., 0.};
			return alwaysFeasibleConstraint[variable];
		} else if(feasible > 0) { // return reasonable behavior for infeasible case
			double[] neverFeasibleConstraint = {1.,-1.,hiBound0-loBound1};
			return neverFeasibleConstraint[variable]; // TODO Move this to magic? 
		}

		if (cachedGradient.containsKey(rec)) {
			int index = 0; // TODO indexForLocation
			grad = cachedGradient.get(rec);

		} else {
			// Calculate and cache
			grad = linear.gradient(monthlyInput, year, month);
			cachedGradient.put(rec, grad); // Caches for next time

		}

		if(verbose) {
			System.out.println("lineGenImpl grad");
			System.out.println(grad[siteNDX][0]+" "+grad[siteNDX][1]+" "+grad[siteNDX][2]);
			System.out.println("targetWQ");
			System.out.println(targetWQ);
			System.out.println("sac0");
			System.out.println(sac0);
			System.out.println("exp0");
			System.out.println(exp0);
		}
		double[] constraint = linear.formulateConstraint(grad[siteNDX], sac0, exp0, targetWQ);

		return constraint[variable];

	}

	/** Return > 0 if infeasible, < 0 if always feasible and 0 if varies over range
	 * 
	 * @param gr
	 * @param siteNdx
	 * @param targetWQ
	 * @return
	 */
	private int assessFeasible(GridResult gr, int siteNdx, double targetWQ) {
		int nx0 = gr.getGridInput0().length;
		int nx1 = gr.getGridInput1().length;
		boolean allNeg = true;
		boolean allPos = true;
		for (int i=0; i< nx0; i++) {
			for (int j=0; j<nx1; j++) {
				// System.out.println("assessFeasible WQ target="+targetWQ+ " " + gr.getResult()[i][j][siteNdx]);
				
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
				+ SEP + rec.year + SEP + rec.month + SEP + rec.cycle + SEP + aveType + SEP + failure + SEP + comment;
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


	public float annEC(ArrayList<double[][]> monthly, int location, int variable, int ave_type, int month, int year) {
		// TODO: Why is there a variable argument. Isn't this just for linegen?
		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		// TODO use actual cycle unless that is a bad idea
		int cyclePlaceholder = 0; 
		double sac0 = monthly.get(1)[0][0];         
		double exp0 = monthly.get(2)[0][0];
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), sac0, exp0, 0, 0, year, month, cyclePlaceholder,
				ave_type);
		this.logInputs(sm, rec, monthly, false, "annEC",null);
		// index within the surrogate output of the site of interest
        int locIndex = this.getIndexForSite(location); //TODO this got hardwired to zero early on, is it fixed?
		
		if (cachedSurrogate.containsKey(rec)) {
			double[][] cached = cachedSurrogate.get(rec);
			return (float) cached[0][locIndex]; // cachedSurrogate(rec,location); // TODO add index for variable
		} else if (cachedGradient.containsKey(rec)) {
			double[][] cached = cachedGradient.get(rec);
			cachedSurrogate.put(rec, cached);
			return (float) cached[0][locIndex];    // TODO: lacks index for location
		} else {
			double[][] eval = sm.annMonth(monthly, year, month);
			((HashMap<RunRecord, double[][]>) cachedSurrogate).put(rec, eval);
			return (float) eval[0][locIndex];     // TODO lacks index for variable
		}
	}

	/**
	 * Given historical input, location find the required Sacramento flow to meet 
	 * a salinity or EC Target if such a value lies between sacLoBound and sacHiBound.
	 * Currently there is no attempt at caching here. 
	 * @param target EC Target
	 * @param monthlyInputs Monthly inputs. Current period Sac can be junk -- it is not used
	 * @param sacLoBound Lower bound for search
	 * @param sacHiBound Upper bound for search
	 * @param location Location where target is to be met
	 * @param ave_type averaging used
	 * @param month calendar month
	 * @param year calendar year
	 * @returns Required flow. If required flow is above sacHiBound, returns a number above sacHiBound. 
	 * If required flow is below sacLoBound, returns a number below sacLoBound. Currently +/- 999999.
	 */
	public float requiredSac(double target, ArrayList<double[][]> monthlyInputs, 
			double sacLoBound, double sacHiBound, int location,
			int ave_type, int month, int year) {
		SurrogateMonth sm = getSurrogateForSite(location,ave_type);
		InverseSurrogateMonth ism = new InverseSurrogateMonth(sm);
		int hardwiredLoc = 0;
		int sacIndex=0;
		double req = ism.invert(target, monthlyInputs, sacIndex, sacLoBound, sacHiBound, 
				          year, month, hardwiredLoc);

		if (req > sacHiBound) {
			// Salinity target was not feasible
			req =  999999.;
		}
		if (req< sacLoBound) {
			req = -999999.;
		}
		return (float) req;
	}
	
	
//TODO: there are a lot of "placeholderCycles that need to be replaced
// Need to make sure that the output location stuff is OK. surrogateForSite seems fine, but is the indexing used too?
	
	
}

