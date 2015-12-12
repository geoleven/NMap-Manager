package main.java.gr.uoa.di.NmapProject.SA;

public class GetPendingJobs {
	
	private Thread jobFinderThread;
	private JobFinder jobFinder;
	
	public GetPendingJobs(JobQueue jq, PeriodicJobs pj) {
		jobFinder = new JobFinder(jq, pj);
		jobFinderThread = new Thread(jobFinder);
	}
	
	public void start() {
		jobFinderThread.start();
	}
	
	public void stopGettingNewJobs() throws InterruptedException {
		jobFinderThread.interrupt();
		jobFinderThread.join();
	}
}
