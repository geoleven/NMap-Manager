package gr.uoa.di.NmapProject.AM.DB;

import org.json.simple.JSONObject;
/**
 * 
 * SAs information
 * 
 * @author George
 *
 */
public class SA {
	/**
	 * id of the SA
	 */
	public int id;
	/**
	 * device name
	 */
	public String device_name;
	/**
	 * ip 
	 */
	public String ip;
	/**
	 * mac address
	 */
	public String mac_address;
	/**
	 * version of the os
	 */
	public String os_version;
	/**
	 * nmap version installed
	 */
	public String nmap_version;
	/**
	 * a hash of all of the above
	 */
	public String hash;
	/**
	 * true if SA is registered false otherwise
	 */
	public Boolean is_accepted;
	/**
	 * Constructor with id
	 * @param id
	 * @param device_name
	 * @param ip
	 * @param mac_address
	 * @param nmap_version
	 * @param hash
	 * @param os_version
	 */
	public SA(int id , String device_name , String ip , String mac_address , String nmap_version , String hash , String os_version){
		this.id = id;
		this.device_name = device_name;
		this.ip = ip;
		this.mac_address = mac_address;
		this.nmap_version = nmap_version;
		this.hash = hash;
		this.os_version = os_version;
		
	}
	/**
	 * Constructor without id
	 * @param device_name
	 * @param ip
	 * @param mac_address
	 * @param nmap_version
	 * @param hash
	 * @param os_version
	 */
	public SA(String device_name , String ip , String mac_address , String nmap_version , String hash , String os_version){
		this.device_name = device_name;
		this.ip = ip;
		this.mac_address = mac_address;
		this.nmap_version = nmap_version;
		this.hash = hash;
		this.os_version = os_version;
	}
	/**
	 * Constructor using json object
	 * @param sa
	 */
	public SA(JSONObject sa){
		device_name = (String) sa.get("device_name");
		ip = (String) sa.get("ip");
		mac_address = (String) sa.get("mac");
		nmap_version = (String) sa.get("nmap_version");
		hash = (String) sa.get("hash");
		os_version = (String) sa.get("os_version");
	}
	/**
	 * Print SAs registration form
	 */
	public void print(){
		System.out.println("Device name : "+device_name);
		System.out.println("ip : "+ip);
		System.out.println("OS : "+os_version);
		System.out.println("mac_address : "+mac_address);
		System.out.println("nmap_version : "+nmap_version);
		System.out.println("hash : "+hash);
	}
}
