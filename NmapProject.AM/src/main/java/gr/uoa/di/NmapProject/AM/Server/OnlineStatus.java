package gr.uoa.di.NmapProject.AM.Server;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class OnlineStatus {
	
	private static OnlineStatus instance = new OnlineStatus();
	
	private OnlineStatus(){}
	
	public static OnlineStatus getInstance(){
		return instance;
	}
	
	LinkedList<Map> saLastRequest = new LinkedList<Map>();  
	
	public void update( String saHash ){
		 java.util.Date date= new java.util.Date();
		 Timestamp curTime = new Timestamp(date.getTime());
		 
		 boolean inList = false;
		 for( Map s : saLastRequest){
			 String hash = (String) s.get("hash");
			 if(hash.equals(saHash)){
				 s.put("time" , curTime);
				 inList = true;
			 }
		 }
		 
		 if(inList == false){
			 Map s = new HashMap();
			 s.put("hash" , saHash);
			 s.put("time" , curTime);
			 saLastRequest.add(s);
		 }
	}
	
	public boolean isOnline( String saHash ){
		
		for(Map s : saLastRequest){
			if(s.get("hash") == saHash){
				
				Long lastReq = ((Timestamp) s.get("time")).getTime();
				
				java.util.Date date= new java.util.Date();
				Long curTime = (new Timestamp(date.getTime())).getTime();
				 
				Long secSinceLastReq = (curTime - lastReq) / 1000;
				
				System.out.println(secSinceLastReq);
				
				if(secSinceLastReq <= 4){
					return true;
				} else {
					return false;
				}
			}
		}
		
		return false;
	}
	
	public void printStatuses(){
		
		System.out.println("SA statuses : ");
		
		for(Map s : saLastRequest){
			String hash = (String) s.get("hash");
			System.out.println(" SA : "+hash+" , "+isOnline(hash));
		}
		
		System.out.println("");
		
	}
	
}