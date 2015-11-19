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

			Thread jF = new Thread(new JobFinder(jQ, pj));
			Thread sT = new Thread(new SenderThread(rQ));
			//Thread oneTime = new Thread(new OneTimeJobThread(jQ, rQ));
			OneTimeJobs otj = new OneTimeJobs(jQ, rQ);
			otj.createThreads();
			

			jF.start();
			sT.start();
			//oneTime.start();

			Thread.sleep(20 * 1000);
			System.out.println("GTFO");
			jF.interrupt();
			sT.interrupt();
			//oneTime.interrupt();
			otj.stopThreads();
			pj.stopThreads();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
