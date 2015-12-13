package gr.uoa.di.NmapProject.SA;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;

import org.json.simple.JSONObject;

public class Registration {
	
	String deviceName = null;
	String ip = null;
	String mac = null;
	String osVersion = null;
	String nMapVersion = null;
	String unionHash = null;
	
	public Registration() throws UnknownHostException, SocketException , Exception {
		
		InetAddress interfaceIP = InetAddress.getLocalHost();
		ip = interfaceIP.getHostAddress().toString();
		deviceName = interfaceIP.getHostName();
		
//		NetworkInterface network = NetworkInterface.getByInetAddress(interfaceIP);
//		byte[] mac = network.getHardwareAddress();
//		System.out.print("Current MAC address : ");
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < mac.length; i++) {
//			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
//		}
//		System.out.println(sb.toString());
		
		mac = "macAddress";
		
		osVersion = System.getProperty("os.version");
		
		NmapJob myVersion = new NmapJob(0, "-V", false, 0);
		nMapVersion = myVersion.runJob().substring(13, 17);
		
		String all = deviceName+ip+mac+osVersion+nMapVersion;
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(all.getBytes());
		unionHash = new String(messageDigest.digest());
		
	}
	
	public void PrintRegistrationForm(String myDevice, InetAddress myInterfaceIP, String myMac, 
			String myOS, String myNmap, String myHash) {
		System.out.println(myDevice);
		System.out.println(myInterfaceIP);
		System.out.println(myMac);
		System.out.println(myOS);
		System.out.println(myNmap);
		System.out.println(myHash);
	}
	
	public JSONObject toJson(){
		JSONObject json=new JSONObject();
		json.put("device_name",deviceName);
		json.put("ip", ip);
    	json.put("mac", mac);
    	json.put("os_version", osVersion);
    	json.put("nmap_version", nMapVersion);
    	json.put("hash", unionHash);
    	return json;
	}
}
