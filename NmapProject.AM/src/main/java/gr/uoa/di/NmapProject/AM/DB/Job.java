package gr.uoa.di.NmapProject.AM.DB;

/**
 * A class holding a job's info.
 * 
 * @author George
 *
 */
public class Job {
	
	/**
	 * The job's id.
	 */
	public int id = 0;
	
	/**
	 * The S.A. to which the job is assigned.
	 */
	public int saID = 0;
	
	/**
	 * The job's parameter's string.
	 */
	public String parameters = "";
	
	/**
	 * A boolean showing if the job is periodic.
	 */
	public boolean periodic = false;
	
	/**
	 * If the job is periodic the time of the period.
	 */
	public int period = 0;
	
	/**
	 * The status of the job.
	 */
	public String status = null;

	/**
	 * Creates a new NMap job.
	 * 
	 * @param id
	 *            The job's id.
	 * @param parameters
	 *            The job's parameter's string.
	 * @param periodic
	 *            A boolean showing if the job is periodic.
	 * @param period
	 *            If the job is periodic the time of the period.
	 * @param saID
	 *            The S.A. to which the job is assigned.
	 * @param status
	 *            The status of the job.
	 */
	public Job(int id, String parameters, boolean periodic, int period, int saID, String status) {
		this.id = id;
		this.parameters = parameters;
		this.periodic = periodic;
		this.period = period;
		this.saID = saID;
		this.status = status;
	}
	
	/**
	 * Creates a new NMap job.
	 * 
	 * @param parameters
	 *            The job's parameter's string.
	 * @param periodic
	 *            A boolean showing if the job is periodic.
	 * @param period
	 *            If the job is periodic the time of the period.
	 * @param saID
	 *            The S.A. to which the job is assigned.
	 * @param status
	 *            The status of the job.
	 */
	public Job(String parameters, boolean periodic, int period, int saID, String status) {
		this(0, parameters, periodic, period, saID, status);
	}
	
	
	/**
	 * Nicely prints a job.
	 */
	public void print() {
		System.out.println("[ " + id + " | " + parameters + " | " + periodic + " | " + period + " ]");
	}
}
