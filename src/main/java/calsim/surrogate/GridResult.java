package calsim.surrogate;

import java.util.ArrayList;

public class GridResult {

	
	
	public GridResult() {
		super();
		this.gridInput0 = null;
		this.gridInput1 = null;
		this.inputs = null;
		this.result = null;
		this.month = -1;
		this.year = -1;
	}	
	
	public GridResult(double[] gridInput0, double[] gridInput1, ArrayList<double[][]> inputs, double[][][] result,
			int month, int year) {
		super();
		this.gridInput0 = gridInput0;
		this.gridInput1 = gridInput1;
		this.inputs = inputs;
		this.result = result;
		this.month = month;
		this.year = year;
	}
	private double[] gridInput0;
	private double[] gridInput1;
	private ArrayList<double[][]> inputs;
    private double[][][] result;
	

	private int month;
    private int year;	

    
    
    
    
    
    
    public double[][][] getResult() {
		return result;
	}
	public void setResult(double[][][] result) {
		this.result = result;
	}    
    
	public double[] getGridInput0() {
		return gridInput0;
	}
	public void setGridInput0(double[] gridInput0) {
		this.gridInput0 = gridInput0;
	}
	public double[] getGridInput1() {
		return gridInput1;
	}
	public void setGridInput1(double[] gridInput1) {
		this.gridInput1 = gridInput1;
	}
	public ArrayList<double[][]> getInputs() {
		return inputs;
	}
	public void setInputs(ArrayList<double[][]> inputs) {
		this.inputs = inputs;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

    
    
    
	
	

}
