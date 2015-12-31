package gr.uoa.di.NmapProject.AM.GUI;

/**
 * A class holding minimal job preview information.
 * 
 * @author George
 *
 */
public class JobPrev {
	private String params = null;
//	private String periodic = null;
	private int period = -1;
	private int id = -1;

	/**
	 * Creates a new job preview.
	 * @param i The job's id.
	 * @param p The job's parameters.
	 * @param per The job's period if periodic.
//	 * @param ispr The job's periodic status.
	 */
	public JobPrev(int i, String p, int per, String ispr) {
		params = p;
//		periodic = ispr;
		period = per;
		id = i;
	}

	@Override
	public String toString() {
		return String.format("<html><pre>%s</pre></html>",
				"ID: " + id + "\t Parameters: " + params + "\t Period: " + period);
	}
	
	/**
	 * Getter of the JobPrev id.
	 * @return The ID of the job.
	 */
	public int id(){
		return id;
	}
}
