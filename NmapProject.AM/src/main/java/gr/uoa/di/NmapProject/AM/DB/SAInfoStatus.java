package gr.uoa.di.NmapProject.AM.DB;

public class SAInfoStatus {
	
	public int id = -1;
	public  String deviceName = null;
	public String interfaceIP = null;
	public String interfaceMacAddr = null;
	public  String osVersion = null;
	public String nMapVersion = null;
	public boolean isAccepted = false;
	public String unionHash = null;
	public  boolean status = false;
    
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
	
}
