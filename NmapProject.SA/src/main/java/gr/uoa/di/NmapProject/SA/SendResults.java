package gr.uoa.di.NmapProject.SA;

public class SendResults {
	
	ResultQueue results;
	SenderThread sender;
	Thread senderThread;

	public SendResults(ResultQueue rq) {
		results = rq;
		sender = new SenderThread(rq);
		senderThread = new Thread(sender);
		
	}
	
	public void start() {
		senderThread.start();
	}
	
	public void stopSendingResults() throws InterruptedException {
		senderThread.interrupt();
		senderThread.join();
	}
	
}
