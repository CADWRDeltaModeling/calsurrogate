package calsim.surrogate.examples;
import java.util.HashMap;
import java.util.Objects;

import calsim.surrogate.*;


	


/*
1 = Jersey Point
2 = Contra Costa - Rock Slough
3 = Emmaton
4 = Antioch
5 = Collinsville
6 = Chipps Island
7 = Los Vaqueros Intake
8 = Middle River
9 = Victoria Intake
10 = CVP Intake
11 = CCFB
12 = CCFB Intake
20 = Beldan
21 = Martinez	
}*/




public class SalinitySurrogateManager {

	final HashMap<Integer,Surrogate>  surrogateForSite = new HashMap<Integer,Surrogate>();
	final HashMap<RunRecord,double[][]> cachedLinegen = new HashMap<RunRecord,double[][]>();
	final HashMap<RunRecord,double[][]> cachedSurrogate = new HashMap<RunRecord,double[][]>();
	
	public void init() {
		
		String[] tensorNames = {"sac flow"};
		String[] tensorNamesInt = null;		
	    String outName = "outname"; //TODO?
	    BlockDailyToSurrogate blocks = new BlockDailyToSurrogate(8,10,11);
	    DailyToSurrogate[] dataToSurrogates = {blocks,blocks,blocks,blocks,
	    		                               blocks,blocks,blocks};
		
        Surrogate jersey = new TensorWrapper("path/to/jersey",
    			           tensorNames,
    			           tensorNamesInt,
    			          "jersey",
    			          dataToSurrogates[0]);
		
        
	    surrogateForSite.put(Integer.valueOf(1),jersey);

	    
	    /*
		public SurrogateMonth(DisaggregateMonths disagg,Surrogate daily, AggregateMonths agg) {
			this.disagg = disagg;
			this.daily = daily;
			this.agg = agg;
		}*/	    
	    
		
	}
	
	
	public float annlinegen_a(float[] Qsac_prv, float[] Qexp_prv, 
			float[] Qsjr_prv, float Qsjr_fut, 
			float[] DXC_prv,  float DXC_fut, 
			float[] DICU_prv, float DICU_fut, 
			float[] Qsac_oth_prv, float Qsac_oth_fut, 
			float[] Qexp_oth_prv, float Qexp_oth_fut, 
			float[] VernEC_prv, float VernEC_fut, 
			float[] mon, float ECTARGET, 
			float linear1,float linear2, 
			int location, int variable, 
			int ave_type, int currMonth, 
			int currYear, int ForceOption) {

		Surrogate s = surrogateForLoc(location);
		int cyclePlaceholder = 0;
		RunRecord rec = new RunRecord(s,(double) (double) Qsac_prv[0],Qexp_prv[0], 
					0,0,currYear, currMonth, cyclePlaceholder,ave_type);
				
		if (cachedLinegen.containsKey(rec)){
			// getLinearization;
		}else {
			//doLinegen
			//cache
		}
		return (float)-99999;

	}
	

	public float annec_a(float[] Qsac_prv, float[] Qexp_prv, 
			float[] Qsjr_prv, float Qsjr_fut, 
			float[] DXC_prv,  float DXC_fut, 
			float[] DICU_prv, float DICU_fut, 
			float[] Qsac_oth_prv, float Qsac_oth_fut, 
			float[] Qexp_oth_prv, float Qexp_oth_fut, 
			float[] VernEC_prv, float VernEC_fut, 
			float[] mon, float ECTARGET, 
			float linear1,float linear2, 
			int location, int variable, 
			int ave_type, int currMonth, 
			int currYear, int ForceOption) {
		

		Surrogate s = surrogateForLoc(Integer.valueOf(location));
		int cyclePlaceholder = 0;
		RunRecord rec = new RunRecord(s, (double) Qsac_prv[0],(double) Qexp_prv[0], 
				0,0,currYear, currMonth, cyclePlaceholder,ave_type);
			
	    if (cachedSurrogate.containsKey(rec)){
	    	double[][] cached = cachedSurrogate.get(rec);
	    	int locIndex = 2; // TODO array index from calsim location
			return (float) cached[0][0]; //cachedSurrogate(rec,location); // TODO which variable??
		}else {
			AggregateMonths agg = AggregateMonths.AggType.MONTHLY_MEAN.getAggregator(); //TODO change to actual and do lookup
			DisaggregateMonths disagg = new DisaggregateMonthsSpline(5); // TODO different for each
			//TODO erased this to compile
			//Surrogate monthly = new SurrogateMonth(disagg, s, agg); // TODO different from each variable. Coordinate with init()
			//double[][] out = monthly.estimate(null, null); // TODO generate arguments for ANNMonth
			//cachedSurrogate.put(rec, monthly);
			int locIndex = 2; // TODO array index from calsim
			//return (float) monthly[0][locIndex]
		    return 909999997.f;
		}
	}
	
	
	
	
    public float cachedSurrogate(){ return (float) 0.;}
	
	public Surrogate surrogateForLoc(int loc) {
		return surrogateForLoc(Integer.valueOf(loc));
	}

	public Surrogate surrogateForLoc(Integer loc){
		return surrogateForSite.get(loc);
	}
	
	public Object getLinearization(int rec,int location) {
		return null; //TODO
	}
	
	public void doLineGen() {}
	
	
    public void initSurrogates() {}	
	
	
	
	
	
}
