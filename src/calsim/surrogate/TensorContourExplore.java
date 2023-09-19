package calsim.surrogate;

import java.util.ArrayList;

/*
 * Utility to explore a contour of a multi-input ANN to probe a set of contours
 * TODO: 
 */
public class TensorContourExplore extends BrentSolver{

	Surrogate tWrap;

	int[] featureNdx= {-1,-1};
	int[] changeNdx= {-1,-1};
	double[] bnd0 = {0.,0.};  // Lower and upper bound on axis 0
	double[] bnd1 = {0.,0.};  // Lower and upper bound on axis 1
	//double xLoc = 0.;         // Sets xLoc, which is the position of the variable being explored at fixed intervals
	ArrayList<double[][]> paramsFloat;
	ArrayList<long[][]> paramsInt;
	int feature0;
	int feature1;
	int ndx0;
	int ndx1;
	double searchContour = -99999.;


	public double getSearchContour() {
		return searchContour;
	}

	public void setSearchContour(double searchContour) {
		this.searchContour = searchContour;
	}

	public TensorContourExplore(Surrogate tWrap,
			ArrayList<double[][]> paramsFloat,
			ArrayList<long[][]> paramsInt,
			int feature0, int ndx0,
			int feature1, int ndx1) {
		this.tWrap = tWrap;  //super();
		this.paramsFloat=paramsFloat;
		this.paramsInt = paramsInt;
		this.feature0 = feature0;
		this.feature1 = feature1;
		this.ndx0 = ndx0;
		this.ndx1 = ndx1;

	}

	public ArrayList<double[][]> getParamsFloat() {
		return paramsFloat;
	}

	public void setParamsFloat(ArrayList<double[][]> paramsFloat) {
		this.paramsFloat = paramsFloat;
	}

	public ArrayList<long[][]> getParamsInt() {
		return paramsInt;
	}

	public void setParamsInt(ArrayList<long[][]> paramsInt) {
		this.paramsInt = paramsInt;
	}


	public double[] getBnd0() {
		return bnd0;
	}

	public void setBnd0(double[] bnd0) {
		this.bnd0 = bnd0;
	}

	public void setBnd0(int ndx, double bnd) {
		this.bnd0[ndx] = bnd;
	}

	public double[] getBnd1() {
		return bnd1;
	}

	public void setBnd1(double[] bnd1) {
		this.bnd1 = bnd1;
	}
	
	public void setBnd1(int ndx, double bnd) {
		this.bnd1[ndx] = bnd;
	}


	// TODO this wont handle perturbations to integers
	public double eval(double[] x) {
		paramsFloat.get(feature0)[0][ndx0]=x[0];
		paramsFloat.get(feature1)[0][ndx1]=x[1];
		float[][] z = this.tWrap.estimate(paramsFloat, paramsInt);
		return (double) z[0][0] - this.searchContour;
	}

	public void probeContour(double contour,double[] bnd0,double[] bnd1,int nVal) {

		ArrayList<double[]> results = new ArrayList<double[]>();
		for (int i = 0; i<nVal; i++) {
			double frac = ((double)i)/((double) (nVal-1));
			double xLoc = bnd0[0] + (bnd0[1]-bnd0[0])*frac;   // location along axis 0
			double[] searchDir = {0.,1.};
			this.setSearchDir(searchDir);     // probe for contour on axis 1 with axis 0 set at xLoc           
			this.setMin(bnd1[0]);
			this.setMax(bnd1[1]);
		    double[] start = {xLoc,0.01};
		    this.setStart(start);
		    
			this.setSearchContour(contour);
			System.out.println("xloc "+xLoc);

            try {
  			    double y = this.doSolve();
			    double[] res = {xLoc,y};
			    results.add(res);
            }catch(Exception e){
            	double[] res = {xLoc,-9999};
            	results.add(res);
            }
		}
		for (int i = 0; i<results.size() ; i++) {
			System.out.println( results.get(i)[0]
					+ ","
					+ results.get(i)[1]);
		}
	}



}
