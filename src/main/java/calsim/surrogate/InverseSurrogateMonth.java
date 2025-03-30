package calsim.surrogate;

import java.util.ArrayList;

class SurrogateLineSearcher extends BrentSolver{
   
	SurrogateMonth surrogate;
	double target;
	ArrayList<double[][]> inputs;
    int year;
    int month;
    int iLoc; 

	public SurrogateLineSearcher(SurrogateMonth surrogate) {
		super();
		this.surrogate = surrogate;
	}    
    
    
	public void setTarget(double target) {
		this.target = target;
	}
	
	public void setInputs(ArrayList<double[][]> inputs, int year, int month, int iLoc) {
		this.inputs = inputs;
		this.year = year;
		this.month = month;
		this.iLoc = iLoc;
	}
	

    
	//TODO This invokes as search for each location, which will not be efficient if the surrogate
	// is multivariate. A more efficient way to do that case would
	// be to set all/many targets and have the value returned by eval be the worst exceedance.
	// That way you are basically only running one line search, not one per station/location
	public void setLoc(int iLoc) {
		this.iLoc = iLoc;
	}
	

	@Override
	public double eval(double[] x) {
		this.inputs.get(0)[0][0]=x[0];
		this.inputs.get(1)[0][0]=x[1];
        double[][] output = surrogate.annMonth(this.inputs, this.year, this.month);
        return output[0][iLoc] - target;
	}	
}



///////////////////////////////////////////////////////////////////////

public class InverseSurrogateMonth {
	public InverseSurrogateMonth(SurrogateMonth surrogate) {
		super();
		this.surrogate = surrogate;
	}
	
	SurrogateMonth surrogate;
	SurrogateLineSearcher searcher;

	/**
	 * Given target surrogate output and the value of one input (fixedIndex = 0 or 1), determine
	 * the target. Search bounds must be given.
	 * @param target
	 * @param inputs
	 * @param searchIndex  Should be 0 (Sac) or 1 (Exports)
	 * @param loBound Lower bound on search index
	 * @param hiBound Upper bound on search index
	 * @param iLoc index in the output tensor for component being inverted
	 * @return value of non-fixed variable that meets target. Returns < loBound if answers 
	 *         are all < target (all feasible and > hiBound if > target (exceedance)
	 */
	public double invert(double target, ArrayList<double[][]> inputs, int searchIndex, 
			double loBound, double hiBound, int year, int month, int iLoc) {
        int fixedIndex = (searchIndex+1)%2;
        double[] searchDir = {0., 0.}; 
        searchDir[searchIndex] = 1.;        
		searcher = new SurrogateLineSearcher(surrogate);
     	searcher.setSearchDir(searchDir);
     	searcher.setTarget(target);
     	searcher.setInputs(inputs, year, month, iLoc);
     	
		double[] start = { 0.0, 0.0 };
		start[fixedIndex] = inputs.get(fixedIndex)[0][0];
		searcher.setMin(loBound);
     	searcher.setMax(hiBound);
		
		//start[searchIndex] = (loBound + hiBound)/2.;
		searcher.setStart(start);
		return searcher.doSolve();
	}
	
	
	
	
	
	
}
