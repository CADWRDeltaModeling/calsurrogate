package calsim.surrogate;

public class DisaggregateMonthsDaysToOps extends DisaggregateMonths {
	
	
	public DisaggregateMonthsDaysToOps(int nMon) {
		super(nMon);
	}

	/** Disaggregate monthly data looking back in time
	 *  If that is a bad direction we can fix it.
	 */
	public double[] apply(int year, int month, double[] dataRev) {
		double[][] ts = asIrregArray(year,month,dataRev);
		double xNewMax = ts[0][this.getNMonth()];
        double xNewMin = ts[0][0];

        
		

		
		
		
		/*
		  do i=tim0,(tim1-1)              ! last portion of 4th previous month
				    if ( i < (dxc_prv0-(mon0-tim1))) then
				      dxc(i) = 1.0 !shengjun changed to make 1 as DXC open 8/2/2004
				    else
				      dxc(i) = 0.0 !shengjun changed to make 0 as DXC close 8/2/2004
				    end if
				  end do
				  DO i=tim1,(tim2-1)              ! 3rd previous month
				    if ( i-tim1 < dxc_prv1) then
				      dxc(i) = 1.0 !shengjun changed to make 1 as DXC open 8/2/2004
				    else
				      dxc(i) = 0.0 !shengjun changed to make 0 as DXC close 8/2/2004
				    end if
				  END DO
				  DO i=tim2,(tim3-1)              ! 2nd previous month
				    if ( i-tim2 < dxc_prv2) then
				      dxc(i) = 1.0 !shengjun changed to make 1 as DXC open 8/2/2004
				    else
				      dxc(i) = 0.0 !shengjun changed to make 0 as DXC close 8/2/2004
				    end if
				  END DO
				  DO i=tim3,(tim4-1)              ! previous month
				    if ( i-tim3 < dxc_prv3) then
				      dxc(i) = 1.0 !shengjun changed to make 1 as DXC open 8/2/2004
				    else
				      dxc(i) = 0.0 !shengjun changed to make 0 as DXC close 8/2/2004
				    end if
				  END DO
				  DO i=tim4,tim5                  ! current month
				    if ( i-tim4 < dxc_prv4) then
				      dxc(i) = 1.0 !shengjun changed to make 1 as DXC open 8/2/2004
				    else
				      dxc(i) = 0.0 !shengjun changed to make 0 as DXC close 8/2/2004
				    end if
				  END DO*/
		double[] out = new double[1]; 
		return out;
	}

}
