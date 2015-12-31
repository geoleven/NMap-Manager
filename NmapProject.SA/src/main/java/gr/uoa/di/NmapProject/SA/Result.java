package gr.uoa.di.NmapProject.SA;
/**
 * 
 * Class for results of a job
 * 
 * @author George
 *
 */
public class Result {
	/**
	 * job 
	 */
	public NmapJob job;
	/**
	 * result
	 */
	public String result;
	/**
	 * Constructor
	 * @param j
	 * @param r
	 */
	public Result(NmapJob j ,String r){
		job = j;
		result = r;
	}
	/**
	 * Print Results
	 */
	public void print(){
		System.out.println("Result of job :");
		job.print();
		System.out.println(result);
	}
}
