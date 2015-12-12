package gr.uoa.di.NmapProject.SA;

import java.net.InetAddress;

public class RegistrationForm {
	String deviceName = null;
	InetAddress interfaceIP = null;	
	byte[] mac = null;
	String osVersion = null;
	String nMapVersion = null;
	String unionHash = null;
	
	public RegistrationForm(String myDevice, InetAddress myInterfaceIP, byte[] myMac, 
			String myOS, String myNmap, String myHash) {
		deviceName = myDevice;
		interfaceIP = myInterfaceIP;
		mac = myMac;
		osVersion = myOS;
		nMapVersion = myNmap;
		unionHash = myHash;
	}
}
