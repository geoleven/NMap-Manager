package main.java.gr.uoa.di.NmapProject.SA;

import java.util.LinkedList;

public class PeriodicJobs {

	private LinkedList<Thread> PeriodicJobsThreadList;
	private LinkedList<Integer> PeriodicJobsThreadListIds;
	private ResultQueue results;
	
	public PeriodicJobs(ResultQueue results) {
		PeriodicJobsThreadList = new LinkedList<Thread>();
		PeriodicJobsThreadListIds = new LinkedList<Integer>();
		if (results == null)
			results = new ResultQueue();
		else
			this.results = results;			
	}
	
	public void stopThreads() throws InterruptedException {
		for(int c = 0; c < PeriodicJobsThreadList.size(); c++) {
			PeriodicJobsThreadList.getLast().interrupt();
			PeriodicJobsThreadList.removeLast().join();
			PeriodicJobsThreadListIds.removeLast();
		}
	}
	
	public void addToPeriodicJobs(NmapJob myJob) {
		if (Globals.verbose)
			System.err.println("Starting a new periodic job.");
		PeriodicJobsThreadList.add(new Thread(new PeriodicJobThread(myJob, results)));
		PeriodicJobsThreadList.getLast().start();
		PeriodicJobsThreadListIds.add(new Integer(myJob.id));

	}
	
	public void removePeriodicJob(int id) {
		for(int c = 0; c < PeriodicJobsThreadList.size(); c++) {
			if (PeriodicJobsThreadListIds.get(c) == id) {
				//System.out.println("Removing job : "+id);
				PeriodicJobsThreadList.get(c).interrupt();
				PeriodicJobsThreadList.remove(c);
				PeriodicJobsThreadListIds.remove(c);
			}
		}
	}
	
}
