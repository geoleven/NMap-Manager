package sa;

import java.util.LinkedList;

public class OneTimeJobs {
	
	private LinkedList<Thread> OneTimeJobsThreadList;
	private JobQueue pendingJobsQueue;
	private ResultQueue results;
	
	public OneTimeJobs(JobQueue pendingJobsQueue, ResultQueue results) {
		OneTimeJobsThreadList = new LinkedList<Thread>();
		if (pendingJobsQueue == null)
			pendingJobsQueue = new JobQueue();
		else
			this.pendingJobsQueue = pendingJobsQueue;
		if (results == null)
			results = new ResultQueue();
		else
			this.results = results;			
	}
	
	public void stopThreads() throws InterruptedException {
		for(int c = 0; c < Globals.oneTimeJobThreadsNumber; c++) {
			OneTimeJobsThreadList.getLast().interrupt();
			OneTimeJobsThreadList.removeLast().join();
		}
	}
	
	public void start(){
		if (Globals.verbose)
			System.err.println("Starting " + Globals.oneTimeJobThreadsNumber + " one time job threads.");
		for(int c = 0; c < Globals.oneTimeJobThreadsNumber; c++) {
			OneTimeJobsThreadList.add(new Thread(new OneTimeJobThread(pendingJobsQueue, results)));
			OneTimeJobsThreadList.getLast().start();
		}
	}
}
