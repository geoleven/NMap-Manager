package gr.uoa.di.NmapProject.AM.DB;

import org.json.simple.JSONObject;

public class SA {
	public int id;
	public String device_name;
	public String ip;
	public String mac_address;
	public String os_version;
	public String nmap_version;
	public String hash;
	public Boolean is_accepted;
	
	public SA(int id , String device_name , String ip , String mac_address , String nmap_version , String hash , String os_version){
		this.id = id;
		this.device_name = device_name;
		this.ip = ip;
		this.mac_address = mac_address;
		this.nmap_version = nmap_version;
		this.hash = hash;
		this.os_version = os_version;
		
	}
	
	public SA(String device_name , String ip , String mac_address , String nmap_version , String hash , String os_version){
		this.device_name = device_name;
		this.ip = ip;
		this.mac_address = mac_address;
		this.nmap_version = nmap_version;
		this.hash = hash;
		this.os_version = os_version;
	}
	
	public SA(JSONObject sa){
		device_name = (String) sa.get("device_name");
		ip = (String) sa.get("ip");
		mac_address = (String) sa.get("mac");
		nmap_version = (String) sa.get("nmap_version");
		hash = (String) sa.get("hash");
		os_version = (String) sa.get("os_version");
	}
	
	public void print(){
		System.out.println("Device name : "+device_name);
		System.out.println("ip : "+ip);
		System.out.println("OS : "+os_version);
		System.out.println("mac_address : "+mac_address);
		System.out.println("nmap_version : "+nmap_version);
		System.out.println("hash : "+hash);
	}
}
