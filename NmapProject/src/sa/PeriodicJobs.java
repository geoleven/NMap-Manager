package sa;

import java.util.LinkedList;

public class PeriodicJobs {

	private LinkedList<Thread> PeriodicJobsThreadList;
	private ResultQueue results;
	
	public PeriodicJobs(ResultQueue results) {
		PeriodicJobsThreadList = new LinkedList<Thread>();
		if (results == null)
			results = new ResultQueue();
		else
			this.results = results;			
	}
	
	public void stopThreads() throws InterruptedException {
		for(int c = 0; c < PeriodicJobsThreadList.size(); c++) {
			PeriodicJobsThreadList.getLast().interrupt();
			PeriodicJobsThreadList.removeLast().join();
		}
	}
	
	public void addToPeriodicJobs(NmapJob myJob) {
		if (Globals.verbose)
			System.err.println("Starting a new periodic job.");
		PeriodicJobsThreadList.add(new Thread(new PeriodicJobThread(myJob, results)));
		PeriodicJobsThreadList.getLast().start();

	}
	
}
