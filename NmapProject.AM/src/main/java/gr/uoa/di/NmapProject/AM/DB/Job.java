package gr.uoa.di.NmapProject.AM.DB;

public class Job {
	public int id = 0;
	public int saID = 0;
	public String parameters = "";
	public boolean periodic = false;
	public int period = 0;

	public Job(int id, String parameters, boolean periodic, int period, int saID) {
		this.id = id;
		this.parameters = parameters;
		this.periodic = periodic;
		this.period = period;
		this.saID = saID;
	}
	
	public Job(String parameters, boolean periodic, int period, int saID) {
		this(0, parameters, periodic, period, saID);
	}
}
