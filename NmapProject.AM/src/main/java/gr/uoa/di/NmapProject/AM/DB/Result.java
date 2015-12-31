package gr.uoa.di.NmapProject.AM.DB;
/**
 * 
 * Results of a job 
 * 
 * @author George
 *
 */
public class Result {
	/**
	 * id of the result
	 */
	public int id = 0;
	/**
	 * xml text of the result
	 */
	public String xml = "";
	/**
	 * id of the job
	 */
	public int jobs_id = 0;
	/**
	 * id of the sa the job belongs to
	 */
	public int software_agents_id = 0;
	/**
	 * time that the result was created
	 */
	public String time_created = null;
	/**
	 * Construct
	 * @param jobs_id
	 * @param software_agents_id
	 * @param xml
	 */
	public Result(int jobs_id, int software_agents_id , String xml ) {
		this.xml = xml;
		this.jobs_id = jobs_id ;
		this.software_agents_id = software_agents_id;
	}
	
}
