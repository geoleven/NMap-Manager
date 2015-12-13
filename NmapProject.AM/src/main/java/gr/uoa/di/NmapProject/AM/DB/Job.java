package gr.uoa.di.NmapProject.AM.DB;

public class Job {
	public int id = 0;
	public int saID = 0;
	public String parameters = "";
	public boolean periodic = false;
	public int period = 0;
	public String status = null;

	public Job(int id, String parameters, boolean periodic, int period, int saID, String status) {
		this.id = id;
		this.parameters = parameters;
		this.periodic = periodic;
		this.period = period;
		this.saID = saID;
		this.status = status;
	}
	
	public Job(String parameters, boolean periodic, int period, int saID, String status) {
		this(0, parameters, periodic, period, saID, status);
	}
	
	public void print() {
		System.out.println("[ " + id + " | " + parameters + " | " + periodic + " | " + period + " ]");
	}
}
