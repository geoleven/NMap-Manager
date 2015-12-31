package gr.uoa.di.NmapProject.SA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * Nmap job Class
 * 
 * @author George
 *
 */
public class NmapJob {
	/**
	 * id of job
	 */
	public int id = 0;
	/**
	 * parameters of job
	 */
	public String parameters = "";
	/**
	 * is the job periodic or not ?
	 */
	public boolean periodic = false;
	/**
	 * period of job ( 0 or -1 if not periodic)
	 */
	public int period = 0;
	/**
	 * status of job (Delete or Pending)
	 */
	public String status = ""; 
	
	/**
	 * Constructor without status
	 * @param id
	 * @param parameters
	 * @param periodic
	 * @param period
	 */
	public NmapJob(int id, String parameters, boolean periodic, int period) {
		this.id = id;
		this.parameters = parameters;
		this.periodic = periodic;
		this.period = period;
	}
	
	/**
	 * Constructor with status
	 * @param id
	 * @param parameters
	 * @param periodic
	 * @param period
	 * @param status
	 */
	public NmapJob(int id, String parameters, boolean periodic, int period , String status) {
		this.id = id;
		this.parameters = parameters;
		this.periodic = periodic;
		this.period = period;
		this.status = status;
	}
	
	/**
	 * Execute a Job 
	 * @return
	 */
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
	
	/**
	 * Results of a job
	 */
	public void print() {
		System.out.println("[ " + id + " | " + parameters + " | " + periodic + " | " + period + " ]");
	}
	
}
