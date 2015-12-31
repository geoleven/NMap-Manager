package gr.uoa.di.NmapProject.SA;

import java.util.ArrayList;
/**
 * 
 * Shared Queue For storing results
 * 
 * @author George
 *
 */
public class ResultQueue {
	ArrayList<Result> queue;
	
	/**
	 * Constructor
	 */
	public ResultQueue(){
		queue = new ArrayList<Result>();
	}
	
	/**
	 * Add a result
	 * @param res
	 */
	synchronized public void addResult(Result res){
		queue.add(res);
//		notify();
	}
	/**
	 * Add the result of a job
	 * @param job
	 * @param res
	 */
	public void addResult(NmapJob job , String res){
		addResult(new Result(job , res));
	}
	/**
	 * Get result
	 * @return
	 * @throws Exception
	 */
	synchronized public Result getResult() throws Exception {
		if(queue.size() > 0){
			Result j = queue.get(0);
			queue.remove(0);
			return j;
		}
		else{
//			wait();
			return null;
		}
	}
	/**
	 * Print queue
	 */
	public void print(){
		for(int i = 0 ; i < queue.size() ; i++){
			queue.get(i).print();
		}
	}
}