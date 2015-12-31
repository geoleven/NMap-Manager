package gr.uoa.di.NmapProject.SA;
/**
 * Thread for one time jobs
 * @author George
 *
 */
public class OneTimeJobThread implements Runnable {
	
	JobQueue inQueue;
	ResultQueue outQueue;
	NmapJob job;
	
	/**
	 * Constructor
	 * @param in
	 * @param out
	 */
	public OneTimeJobThread(JobQueue in, ResultQueue out) {
		inQueue = in;
		outQueue = out;
		job = null;
	}
	/**
	 * Main loop
	 * Get a job if there are any in queue
	 * Execute it
	 */
	public void run() {
		try {
			while (true) {
				if (getJob())
					executeJob();
				else {
					synchronized (inQueue) {
						inQueue.wait();
					}
				}
			}
		} catch (InterruptedException e) {
			if (Globals.verbose)
				System.err.println("OneTimeJobThread interrupted. Exiting.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @OneTimeJobThread.run");
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * (try to) get a job
	 * @return
	 * @throws Exception
	 */
	public boolean getJob() throws Exception {
		if((job = inQueue.getJob())!= null){
			return true;
		}
		return false;
	}
	/**
	 * Execute job
	 */
	public void executeJob(){
		String out = job.runJob();
		outQueue.addResult(job , out);
		synchronized (outQueue) {
			outQueue.notify();
		}
	}
	
}
