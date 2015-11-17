package sa;

import java.util.ArrayList;

public class JobQueue {
	
	ArrayList<NmapJob> queue;
	
	public JobQueue(){
		queue = new ArrayList<NmapJob>();
	}
	
	synchronized public void addJob(NmapJob job) {
		queue.add(job);
		notify();
	}
	
	synchronized public NmapJob getJob() {
		if(queue.size() > 0){
			NmapJob j = queue.get(0);
			queue.remove(0);
			
			return j;
		}
		else{
			return null;
		}
	}
	
	public void print(){
		for(int i = 0 ; i < queue.size() ; i++){
			queue.get(i).print();
		}
	}
}
