package gr.uoa.di.NmapProject.AM.Server;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/**
 * 
 * Singleton Class foor tracking online SAs
 * and SAs that need to exit
 * 
 * @author George
 *
 */
public class OnlineStatus {
	
	private static OnlineStatus instance = new OnlineStatus();
	LinkedList<Map<String, Object>> saLastRequest = new LinkedList<Map<String, Object>>();
	LinkedList<String> forExit = new LinkedList<String>();
	
	private OnlineStatus(){}
	
	/**
	 * Returns the only instance of OnlineStatus
	 */
	public static OnlineStatus getInstance(){
		return instance;
	}

	
	/**
	 * 
	 * Updates the time of the last request of an SA
	 * 
	 * @param saHash
	 * 		SAs Hash
	 * 	
	 */
	public void update( String saHash ){
		 java.util.Date date= new java.util.Date();
		 Timestamp curTime = new Timestamp(date.getTime());
		 
		 boolean inList = false;
		 for( Map<String, Object> s : saLastRequest){
			 String hash = (String) s.get("hash");
			 if(hash.equals(saHash)){
				 s.put("time" , curTime);
				 inList = true;
			 }
		 }
		 
		 if(inList == false){
			 Map<String, Object> s = new HashMap<String, Object>();
			 s.put("hash" , saHash);
			 s.put("time" , curTime);
			 saLastRequest.add(s);
		 }
	}
	/**
	 * 
	 * Online Status of SA
	 * 
	 * @param saHash
	 * 		hash of the SA
	 * @return
	 * 		true if SA is online
	 * 		false otherwise
	 */
	public boolean isOnline( String saHash ){
		
		for(Map<String, Object> s : saLastRequest){
			if(s.get("hash").equals(saHash)){
				
				Long lastReq = ((Timestamp) s.get("time")).getTime();
				
				java.util.Date date= new java.util.Date();
				Long curTime = (new Timestamp(date.getTime())).getTime();
				 
				Long secSinceLastReq = (curTime - lastReq) / 1000;
				
				if(secSinceLastReq <= 4){
					return true;
				} else {
					return false;
				}
			}
		}
		
		return false;
	}
	/**
	 * print statuses for all SAs
	 */
	public void printStatuses(){
		
		System.out.println("SA statuses : ");
		
		for(Map<String, Object> s : saLastRequest){
			String hash = (String) s.get("hash");
			System.out.println(" SA : "+hash+" , "+isOnline(hash));
		}
		
		System.out.println("");
		
	}
	
	/**
	 * 
	 * Set an SA as ready for exit
	 * 
	 * @param hash
	 * 		hash of the SA
	 */
	public void setForExit( String hash ){
		boolean contains = false;
		for( String h : forExit){
			if(h.equals(hash)){
				contains = true;
			}
		}
		if(contains == false){
			forExit.add(hash);
		}
	}
	/**
	 * Return SA exit status
	 * @param hash
	 * 		hash of the SA
	 * @return
	 * 		true if ready else false
	 */
	public boolean isForExit( String hash ){
		for( String h : forExit){
			if(h.equals(hash)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * After exiting remove SA from the list
	 * @param hash
	 * 		hash of the SA
	 */
	public void exited( String hash ){
		forExit.remove(hash);
	}
	/**
	 * Print all SAs that are ready for exiting
	 */
	public void printExit(){
		System.out.println("Set for Exit :");
		
		for(String h : forExit){
			System.out.println(h);
		}
		System.out.println();
		
	}
	/**
	 * 
	 * Returns a list of all the online SAs
	 * 
	 */
	public LinkedList<String> getOnline(){
		
		LinkedList<String> online = new LinkedList<String>();
		
		for(Map s : saLastRequest){
			Long lastReq = ((Timestamp) s.get("time")).getTime();
			
			java.util.Date date= new java.util.Date();
			Long curTime = (new Timestamp(date.getTime())).getTime();
			 
			Long secSinceLastReq = (curTime - lastReq) / 1000;
			
			if(secSinceLastReq <= 4){
				online.push((String) s.get("hash"));
			}
		}
		
		return online;
	}
	
}
