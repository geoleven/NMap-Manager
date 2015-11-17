package sa;

public class OneTimeJobThread implements Runnable {
	
	JobQueue inQueue;
	ResultQueue outQueue;
	NmapJob job;

	public OneTimeJobThread(JobQueue in, ResultQueue out) {
		inQueue = in;
		outQueue = out;
		job = null;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (getJob())
					executeJob();
				else {
					synchronized(Globals.oneTimeJoblock){
						Globals.oneTimeJoblock.wait();
					}
				}
			}

		} catch (InterruptedException e) {
			System.out.println("OneTimeJobThread interrupted. Exiting.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @OneTimeJobThread.run");
			e.printStackTrace();
		}
	}
	
	public boolean getJob(){
		if((job = inQueue.getJob())!= null){
			return true;
		}
		return false;
	}
	
	public void executeJob(){
		String out = job.runJob();
		outQueue.addResult(job , out);
	}
	
}
