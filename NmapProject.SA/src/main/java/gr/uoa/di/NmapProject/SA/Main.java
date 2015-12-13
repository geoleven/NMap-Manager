package gr.uoa.di.NmapProject.SA;

public class Main {

	public static void main(String[] args) {
		try {
			
//			Registration myr = new Registration();
			
			ServerRequest sR = new ServerRequest("http://localhost:8080/am/");
			sR.registrationRequest();
			
//			Globals.verbose = true;
//			if (!MySystemFiles.checkProgramPathExists()) {
//				if (Globals.verbose)
//					System.err.println("The deamon cannot run without its directory '~/.myNmap'");
//				return;
//			}
//			SAProperties myProperties = new SAProperties();
//			myProperties.readThreadNum();
//			// System.out.println(myp.oneTimeJobThreadsNumber);
//
//			JobQueue jQ = new JobQueue();
//			ResultQueue rQ = new ResultQueue();
//			PeriodicJobs pj = new PeriodicJobs(rQ);
//			GetPendingJobs jf = new GetPendingJobs(jQ, pj);
//			SendResults sT = new SendResults(rQ);
//			OneTimeJobs otj = new OneTimeJobs(jQ, rQ);
//
//			Stopper stopper = new Stopper(jf, otj, pj, sT);
//			stopper.attachShutDownHook();
//			
//			otj.start();
//			jf.start();
//			sT.start();			
			
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
