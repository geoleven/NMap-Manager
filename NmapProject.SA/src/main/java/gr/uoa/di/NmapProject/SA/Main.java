package gr.uoa.di.NmapProject.SA;

/**
 * 
 * Main class of SA 
 * 
 * After registering successfully
 * 
 * Creates and starts all threads
 * 
 * @author George
 *
 */
public class Main {
	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			ServerRequest sR = new ServerRequest(Globals.baseURL);
			sR.registrationRequest();
			
			Globals.verbose = true;
			if (!MySystemFiles.checkProgramPathExists()) {
				if (Globals.verbose)
					System.err.println("The deamon cannot run without its directory '~/.myNmap'");
				return;
			}
			SAProperties myProperties = new SAProperties();
			myProperties.readThreadNum();

			JobQueue jQ = new JobQueue();
			ResultQueue rQ = new ResultQueue();
			PeriodicJobs pj = new PeriodicJobs(rQ);
			GetPendingJobs jf = new GetPendingJobs(jQ, pj);
			SendResults sT = new SendResults(rQ);
			OneTimeJobs otj = new OneTimeJobs(jQ, rQ);

			Stopper stopper = new Stopper(jf, otj, pj, sT);
			stopper.attachShutDownHook();
			
			jf.setStopper(stopper);
			
			otj.start();
			jf.start();
			sT.start();			
			
//			stopper.closeNow();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
