package sa;

import java.util.LinkedList;

public class OneTimeJobs {
	
	private LinkedList<Thread> OneTimeJobsThreadList = null;
	
	public void stopThreads() {
		return;
	}
	
	public void createThreads(JobQueue pendingJobsQueue, ResultQueue results){
		if (Globals.verbose)
			System.err.println("Starting " + Globals.oneTimeJobThreadsNumber + " one time job threads.");
		for(int c = 0; c < Globals.oneTimeJobThreadsNumber; c++) {
			OneTimeJobsThreadList.add(new Thread(new OneTimeJobThread(pendingJobsQueue, results)));
			OneTimeJobsThreadList.getLast().start();
		}
	}
}
