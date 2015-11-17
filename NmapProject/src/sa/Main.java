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

			Thread jF = new Thread(new JobFinder(jQ));
			Thread sT = new Thread(new SenderThread(rQ));
			Thread oneTime = new Thread(new OneTimeJobThread(jQ, rQ));

			jF.start();
			sT.start();
			oneTime.start();

			Thread.sleep(5 * 1000);
			System.out.println("GTFO");
			jF.interrupt();
			sT.interrupt();
			oneTime.interrupt();
			// Globals.finish.set(true);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
