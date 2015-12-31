package gr.uoa.di.NmapProject.SA;

import sun.misc.Signal;
/**
 * 
 * Stopper class for exiting normally
 * 
 * @author George
 *
 */
public class Stopper {

	GetPendingJobs getPendingJobs;
	OneTimeJobs oneTimeJobs;
	PeriodicJobs periodicJobs;
	SendResults sendResults;
	
	/**
	 * Constructor
	 * @param gpj
	 * @param oj
	 * @param pj
	 * @param sr
	 */
	public Stopper(GetPendingJobs gpj, OneTimeJobs oj, PeriodicJobs pj, SendResults sr) {
		getPendingJobs = gpj;
		oneTimeJobs = oj; 
		periodicJobs = pj;
		sendResults = sr;
	}
	/**
	 * 
	 * Add a shutdown hook
	 * 
	 * if enabled cntrl+C stop execution normally rather than force quit
	 * 
	 * @throws InterruptedException
	 */
	public void attachShutDownHook() throws InterruptedException{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
					if (Globals.verbose)
						System.err.println("Trying to do a clean exit.");
					try {
						oneTimeJobs.stopThreads();
						periodicJobs.stopThreads();
						getPendingJobs.stopGettingNewJobs();
						sendResults.stopSendingResults();
						System.err.println("Threads terminated normally. Shutting down.");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	
	/**
	 * Stop execution
	 */
	public void closeNow() {
		sun.misc.Signal.raise(new Signal("INT"));
	}
	
}
