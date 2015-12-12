package gr.uoa.di.NmapProject.SA;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Registration {
	RegistrationForm registrationForm = null;
	
	public Registration() throws UnknownHostException, SocketException {
		InetAddress interfaceIP = InetAddress.getLocalHost();
		System.out.println(interfaceIP.getHostAddress());
		String deviceName = interfaceIP.getHostName();
		System.out.println(deviceName);
		NetworkInterface network = NetworkInterface.getByInetAddress(interfaceIP);
//		byte[] mac = network.getHardwareAddress();
//		System.out.print("Current MAC address : ");
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < mac.length; i++) {
//			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
//		}
//		System.out.println(sb.toString());
		byte[] mac = null;
		String osVersion = System.getProperty("os.version");
		NmapJob myVersion = new NmapJob(0, "-V", false, 0);
		String nMapVersion = myVersion.runJob().substring(13, 17);
		String unionHash = null;
		PrintRegistrationForm(deviceName, interfaceIP, mac, osVersion, nMapVersion, unionHash);
		registrationForm = new RegistrationForm(deviceName, interfaceIP, mac, osVersion, nMapVersion, unionHash);
	}
	
	public void PrintRegistrationForm(String myDevice, InetAddress myInterfaceIP, byte[] myMac, 
			String myOS, String myNmap, String myHash) {
		System.out.println(myDevice);
		System.out.println(myInterfaceIP);
		System.out.println(myMac);
		System.out.println(myOS);
		System.out.println(myNmap);
		System.out.println(myHash);
	}
}
