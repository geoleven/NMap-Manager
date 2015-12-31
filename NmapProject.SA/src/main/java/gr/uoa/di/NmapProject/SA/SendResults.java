package gr.uoa.di.NmapProject.SA;
/**
 * 
 * Handles Sender Thread
 * 
 * @author George
 *
 */
public class SendResults {
	
	ResultQueue results;
	SenderThread sender;
	Thread senderThread;
	/**
	 * Constructor
	 * @param rq
	 */
	public SendResults(ResultQueue rq) {
		results = rq;
		sender = new SenderThread(rq);
		senderThread = new Thread(sender);
		
	}
	/**
	 * Start thread
	 */
	public void start() {
		senderThread.start();
	}
	/**
	 * 
	 * Stop Thread
	 * 
	 * @throws InterruptedException
	 */
	public void stopSendingResults() throws InterruptedException {
		senderThread.interrupt();
		senderThread.join();
	}
	
}
