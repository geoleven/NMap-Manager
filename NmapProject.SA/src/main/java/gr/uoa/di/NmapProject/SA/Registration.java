package gr.uoa.di.NmapProject.SA;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.Enumeration;

import org.json.simple.JSONObject;

public class Registration {
	
	String deviceName = null;
	String ip = null;
	String mac = null;
	String osVersion = null;
	String nMapVersion = null;
	String unionHash = null;
	
	public Registration() throws UnknownHostException, SocketException , Exception {
		
		InetAddress netAddrIp = null;
		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
		for (NetworkInterface netIf : Collections.list(nets)) {
			if (netIf.isUp() || !netIf.isLoopback()) {
				Enumeration<InetAddress> addresses = netIf.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress addr = addresses.nextElement();
					if (addr instanceof Inet4Address) {
						netAddrIp= addr;
						ip = addr.getHostAddress();
					}
				}
			}
			
		}
		
		NetworkInterface network = NetworkInterface.getByInetAddress(netAddrIp);
		byte[] macAddress = network.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < macAddress.length; i++) {
			sb.append(String.format("%02X%s", macAddress[i], (i < macAddress.length - 1) ? "-" : ""));
		}
		mac = sb.toString();
		
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
