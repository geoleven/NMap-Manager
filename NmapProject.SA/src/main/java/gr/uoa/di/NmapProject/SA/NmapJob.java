package main.java.gr.uoa.di.NmapProject.SA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NmapJob {
	public int id = 0;
	public String parameters = "";
	public boolean periodic = false;
	public int period = 0;

	public NmapJob(int id, String parameters, boolean periodic, int period) {
		this.id = id;
		this.parameters = parameters;
		this.periodic = periodic;
		this.period = period;
	}

	public String runJob() {
		String result = "";
		try {
			if (Globals.verbose)
				System.err.println("sudo nmap -oX - " + this.parameters);
			Process proc = Runtime.getRuntime().exec("sudo nmap -oX - " + this.parameters);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			if (Globals.verbose) {
				BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
				String s = null;
				while ((s = stdError.readLine()) != null) {
					System.out.println(s);
				}
			}

			String temp = null;
			while ((temp = stdInput.readLine()) != null) {
				result = result + temp;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void print() {
		System.out.println("[ " + id + " | " + parameters + " | " + periodic + " | " + period + " ]");
	}
}
