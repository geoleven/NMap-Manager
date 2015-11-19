package sa;

public class Stopper {

	GetPendingJobs getPendingJobs;
	OneTimeJobs oneTimeJobs;
	PeriodicJobs periodicJobs;
	SendResults sendResults;
	
	public Stopper(GetPendingJobs gpj, OneTimeJobs oj, PeriodicJobs pj, SendResults sr) {
		getPendingJobs = gpj;
		oneTimeJobs = oj; 
		periodicJobs = pj;
		sendResults = sr;
	}
	
	public void attachShutDownHook() throws InterruptedException{
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
					if (Globals.verbose)
						System.out.println("Trying to do a clean exit.");
					try {
						oneTimeJobs.stopThreads();
						periodicJobs.stopThreads();
						getPendingJobs.stopGettingNewJobs();
						sendResults.stopSendingResults();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}
	
}
