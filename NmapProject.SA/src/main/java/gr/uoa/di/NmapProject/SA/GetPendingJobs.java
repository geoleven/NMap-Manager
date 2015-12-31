package gr.uoa.di.NmapProject.SA;


/**
 * 
 * Creates and Manages jobFinderThread
 * 
 * @author George
 *
 */
public class GetPendingJobs {
	/**
	 * Job Finder Thread
	 */
	private Thread jobFinderThread;
	/**
	 * Job Finder Object
	 */
	private JobFinder jobFinder;
	
	/**
	 * Constructor
	 * 
	 * @param jq
	 * @param pj
	 */
	public GetPendingJobs(JobQueue jq, PeriodicJobs pj) {
		jobFinder = new JobFinder(jq, pj);
		jobFinderThread = new Thread(jobFinder);
	}
		
	/**
	 * Starts Thread
	 */
	public void start() {
		jobFinderThread.start();
	}
	/**
	 * Stops job finder
	 * @throws InterruptedException
	 */
	public void stopGettingNewJobs() throws InterruptedException {
		jobFinderThread.interrupt();
		jobFinderThread.join();
	}
	/**
	 * 
	 * Passes Stopper Object to jobFinder 
	 * 
	 * @param s
	 */
	public void setStopper(Stopper s){
		jobFinder.setStopper(s);
	}
}
