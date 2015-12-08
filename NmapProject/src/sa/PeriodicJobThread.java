package sa;

public class PeriodicJobThread implements Runnable {
	
	ResultQueue results;
	NmapJob myJob;
	String singleJobResults; 
	
	public PeriodicJobThread(NmapJob nmj, ResultQueue rq) {
		results = rq;
		myJob = nmj;
		singleJobResults = null;
	}
	
	public void run() {
		if(myJob.periodic && myJob.period > 0) {
			try {
				while(true) {
					singleJobResults = myJob.runJob();
					results.addResult(myJob , singleJobResults);
					synchronized (results) {
						results.notify();
					}
					Thread.sleep(1000*myJob.period);
				}
			} catch (InterruptedException e) {
				if (Globals.verbose)
					System.err.println("PeriodicJobThread interrupted. Exiting " + myJob.id + ".");
			} catch (Exception e) {
				System.err.println("Unexpected exception " + e.getMessage() + " @PeriodicJobThread.run");
				e.printStackTrace();
			}	
		}
	}
}
