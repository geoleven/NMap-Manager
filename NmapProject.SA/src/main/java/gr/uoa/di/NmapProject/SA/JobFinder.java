package gr.uoa.di.NmapProject.SA;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.Random;


/**
 * 
 * Job Finder 
 * 
 * @author George
 *
 */
public class JobFinder implements Runnable {
	
	private JobQueue OneTimeJobsQueue;
	private PeriodicJobs myPeriodicJobs;
//	private BufferedReader openedFile;
//	private Random rand;
//	private ArrayList<String> fileHistory;
//	private String openedFileName;
	private ServerRequest serverSide;
	private Stopper stopper;
	
	/**
	 * Constructor for job Finder
	 * @param jq
	 * @param pj
	 */
	public JobFinder(JobQueue jq, PeriodicJobs pj) {
		OneTimeJobsQueue = jq;
		myPeriodicJobs = pj;
//		openedFile = null;
//		openedFileName = null;
//		rand = new Random();
//		fileHistory = new ArrayList<String>();
		serverSide = new ServerRequest(Globals.baseURL);
		stopper = null;
	}
	
	/**
	 * Set stopper
	 * @param s
	 */
	public void setStopper(Stopper s){
		stopper = s;
	}
	
	/**
	 * Main loop of job finder
	 * Makes a request for jobs 
	 * Then wait for an amount of time
	 */
	public void run() {
		try {
			// while(Globals.finish.get() == false){
			while (true) {
				requestJobs();
				Thread.sleep(3000);
			}
		} catch (InterruptedException e) {
			System.err.println("JobFinder interrupted. Exiting.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @Jobfinder.run");
		}
	}
	
	/**
	 * Request for jobs 
	 * for status delete stops the periodicjobthread
	 * for status pending it inserts the job to the queue
	 * 
	 * @throws Exception
	 */
	private void requestJobs() throws Exception {
		
		LinkedList<NmapJob> jobs = serverSide.requestJobs(stopper);
		if(jobs != null){
			for(NmapJob j : jobs){
				if(j.status.equals("Delete")){
					stopJob(j);
				}else{
					newJob(j);
				}
				
				
			}
		}
	}
	/**
	 * Stops periodic job Thread
	 * @param j
	 */
	private void stopJob(NmapJob j){
		System.out.println("Stopping job");
		j.print();
		myPeriodicJobs.removePeriodicJob(j.id);
	}
	/**
	 * Inserts new job to queue
	 * or starts a periodic job Thread
	 * @param j
	 */
	private void newJob(NmapJob j){
		System.out.println("New Job :");
		j.print();
		if (j.periodic) {
			myPeriodicJobs.addToPeriodicJobs(j);
		} else {
			OneTimeJobsQueue.addJob(j);
			synchronized (OneTimeJobsQueue) {
				OneTimeJobsQueue.notify();
			}
		}
	}
	
}
