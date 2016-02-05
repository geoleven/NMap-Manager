package gr.uoa.di.NmapProject.AM.DB;
/**
 * 
 * SAs information (again :P)
 * 
 * @author George
 *
 */
public class SAInfoStatus {
	/**
	 * id
	 */
	public int id = -1;
	/**
	 * device name
	 */
	public  String deviceName = null;
	/**
	 * ip
	 */
	public String interfaceIP = null;
	/**
	 * mac address
	 */
	public String interfaceMacAddr = null;
	/**
	 * os version 
	 */
	public  String osVersion = null;
	/**
	 * nmap version
	 */
	public String nMapVersion = null;
	/**
	 * true if sa is registered false if not
	 */
	public boolean isAccepted = false;
	/**
	 * a hash of the above
	 */
	public String unionHash = null;
	/**
	 * status of the SA
	 */
	public  boolean status = false;
    
	/**
	 * 
	 * Constructor
	 * 
	 * @param cid
	 * @param d
	 * @param ip
	 * @param mac
	 * @param os
	 * @param nmap
	 * @param hash
	 * @param ac
	 * @param s
	 */
    public SAInfoStatus(int cid, String d, String ip, String mac, String os, String nmap, String hash, boolean ac, boolean s) {
    	id = cid;
    	deviceName = d;
    	interfaceIP = ip;
    	interfaceMacAddr = mac;
    	osVersion = os;
    	nMapVersion = nmap;
    	isAccepted = ac;
    	unionHash = hash;
    	status = s;
    }
    
    public void print(){
    	System.out.println("["+id+" , "+deviceName+" , "+interfaceIP+" , "+interfaceMacAddr+" , "+osVersion+" , "+nMapVersion+" , "+isAccepted+" , "+unionHash+" , "+status+"]");
    }
	
}
