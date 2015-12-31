package gr.uoa.di.NmapProject.SA;

import java.util.ArrayList;

/**
 * 
 * Shared queue for one time jobs
 * 
 * @author George
 *
 */
public class JobQueue {
	
	/**
	 * Job queue
	 */
	ArrayList<NmapJob> queue;
	
	/**
	 * Constructor
	 */
	public JobQueue(){
		queue = new ArrayList<NmapJob>();
	}
	
	/**
	 * Add a job to the queue
	 * @param job
	 */
	synchronized public void addJob(NmapJob job) {
		queue.add(job);
	}
	
	/**
	 * Request a job from queue
	 * @return job
	 * @throws Exception
	 */
	synchronized public NmapJob getJob() throws Exception {
		if(queue.size() > 0){
			NmapJob j = queue.get(0);
			queue.remove(0);
			
			return j;
		}
		else{
			return null;
		}
	}
	
	/**
	 * Print jobs on queue
	 */
	public void print(){
		for(int i = 0 ; i < queue.size() ; i++){
			queue.get(i).print();
		}
	}
}
