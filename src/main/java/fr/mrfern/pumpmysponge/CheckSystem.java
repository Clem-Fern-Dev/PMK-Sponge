package fr.mrfern.pumpmysponge;

public abstract class CheckSystem {

	public static float checkCalculTime(int i) {
	
		long u[] = {0};
		u[0] = 1;
		u[1] = 1;	
		
		long time_list[] = {0};
		
		for(int a=0;a<i;i++) {
			
			long startTime = System.currentTimeMillis();
		    CheckSystem ext=new CheckSystem() {};
		    u[a+2] = ext.fibonacie(u[a+1],u[a]);		    
		    long endTime = System.currentTimeMillis();
		    
		    time_list[a] = endTime - startTime;		    
		    
		}
		
		long total = 0;
		for (long f : time_list) {
			total = total + f;
		}
		
		total = total / time_list.length;
		
		return total;		
	}
	
	private long fibonacie(long u,long u1) {
		return u + u1;
	}
	
}
