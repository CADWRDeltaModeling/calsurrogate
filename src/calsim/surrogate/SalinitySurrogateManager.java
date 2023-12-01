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

    private Logger LOGGER;
    private boolean header=false;
	
	SalinitySurrogateManager() {
		init();
	}

	
    private void init() {
        LOGGER = Logger.getLogger("calsim.surrogate");
        FileHandler handler;
		try {
			handler = new FileHandler("surrrogate_input.log");
	        handler.setFormatter(new SimpleFormatter() {
	            @Override
	            public synchronized String format(LogRecord lr) {
	                return lr.getMessage();
	            }
	        });
	        handler.setLevel(Level.INFO);
	        LOGGER.setUseParentHandlers(false);
	        LOGGER.addHandler(handler);

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	public void logInputs(SurrogateMonth sm, RunRecord rec, ArrayList<double[][]> inputs, 
			               boolean fail, String context, String comment) {
	    InputSizeInfo sizeInfo = new InputSizeInfo(inputs);
        logInputs(sm,rec,inputs,sizeInfo,fail,context,comment);
	}	

	public void logInputs(SurrogateMonth sm, RunRecord rec, ArrayList<double[][]> inputs,InputSizeInfo sizeInfo,
			              boolean fail, String context, String comment) {
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
		int aveType = 0; //TODO
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
		SurrogateMonth sm = getSurrogateForSite(location, ave_type);
		int cyclePlaceholder = 0;
		double sac0 = 0.;
		double exp0 = 0.;
		RunRecord rec = new RunRecord(sm.getDailySurrogate(), sac0, exp0, 0, 0, year, month, cyclePlaceholder,
				ave_type);
        this.logInputs(sm, rec, monthly, false, "annEC",null);
		
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
