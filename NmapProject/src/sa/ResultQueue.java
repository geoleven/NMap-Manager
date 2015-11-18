package sa;

import java.util.ArrayList;

public class ResultQueue {
	ArrayList<Result> queue;
	
	public ResultQueue(){
		queue = new ArrayList<Result>();
	}
	
	synchronized public void addResult(Result res){
		queue.add(res);
//		notify();
	}
	
	public void addResult(NmapJob job , String res){
		addResult(new Result(job , res));
	}
	
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
	
	public void print(){
		for(int i = 0 ; i < queue.size() ; i++){
			queue.get(i).print();
		}
	}
}