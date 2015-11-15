package sa;

public class OneTimeJobThread implements Runnable {
	
	JobQueue inQueue;
	
	ResultQueue outQueue;
	
	NmapJob job;
	
	public OneTimeJobThread(JobQueue in , ResultQueue out ){
		inQueue = in;
		outQueue = out;
		job = null;
	}
	
	@Override
	public void run() {
		try{
			if(getJob())
				executeJob();
			Thread.sleep(100);
			
		} catch (InterruptedException e) {
			System.out.println("OneTimeJobThread interrupted");
		} catch (Exception e) {
			System.err.println("Unexpected exception "+e.getMessage());
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
