package gr.uoa.di.NmapProject.AM.DB;

public class Result {
	public int id = 0;
	public String xml = "";
	public int jobs_id = 0;
	public int software_agents_id = 0;
	public String time_created = null;
	
	public Result(int jobs_id, int software_agents_id , String xml ) {
		this.xml = xml;
		this.jobs_id = jobs_id ;
		this.software_agents_id = software_agents_id;
	}
	
}
