package sa;

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
	
	public void stopSendingResults() {
		senderThread.interrupt();
	}
	
}
