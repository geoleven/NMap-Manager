package sa;

public class Main {

	public static void main(String[] args) {
		try {
			Globals.verbose = true;
			if (!MySystemFiles.checkProgramPathExists()) {
				return;
			}
			SAProperties myp = new SAProperties();
			myp.readThreadNum();
			// System.out.println(myp.oneTimeJobThreadsNumber);

			JobQueue jQ = new JobQueue();
			ResultQueue rQ = new ResultQueue();
			PeriodicJobs pj = new PeriodicJobs(rQ);
			GetPendingJobs jf = new GetPendingJobs(jQ, pj);
			SendResults sT = new SendResults(rQ);
			OneTimeJobs otj = new OneTimeJobs(jQ, rQ);
			
			
			otj.start();
			jf.start();
			sT.start();

			Thread.sleep(20 * 1000);
			
//			System.out.println("Exiting now.");
//			
//			jf.stopGettingNewJobs();
//			sT.stopSendingResults();
//			otj.stopThreads();
//			pj.stopThreads();
			Stopper stopper = new Stopper(jf, otj, pj, sT);
			stopper.attachShutDownHook();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
