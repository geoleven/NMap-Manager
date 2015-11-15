package sa;


public class SenderThread implements Runnable {
	
	ResultQueue queue;
	
	public SenderThread(ResultQueue q) {
		queue = q;
	}
	
	@Override
	public void run() {
		try{
			printResult();
			Thread.sleep(100);
			
		} catch (InterruptedException e) {
			System.out.println("OneTimeJobThread interrupted");
		} catch (Exception e) {
			System.err.println("Unexpected exception "+e.getMessage());
		}		
	}
	
	private void printResult(){
		Result res = queue.getResult();
		if(res != null){
			res.print();
		}
	}
	
}
