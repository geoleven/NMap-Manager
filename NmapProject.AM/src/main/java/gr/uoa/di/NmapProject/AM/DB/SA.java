package gr.uoa.di.NmapProject.AM.DB;

import org.json.simple.JSONObject;

public class SA {
	public int id;
	public String device_name;
	public String ip;
	public String mac_address;
	public String nmap_version;
	public String hash;
	
	public SA(int id , String device_name , String ip , String mac_address , String nmap_version , String hash){
		this.id = id;
		this.device_name = device_name;
		this.ip = ip;
		this.mac_address = mac_address;
		this.nmap_version = nmap_version;
		this.hash = hash;
		
	}
	
	public SA(String device_name , String ip , String mac_address , String nmap_version , String hash){
		this.device_name = device_name;
		this.ip = ip;
		this.mac_address = mac_address;
		this.nmap_version = nmap_version;
		this.hash = hash;
		
	}
	
	public SA(JSONObject sa){
		
	}
	
}
