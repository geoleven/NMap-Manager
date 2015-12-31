package gr.uoa.di.NmapProject.SA;
/**
 * 
 * Thread for sending results over to AM
 * 
 * @author George
 *
 */
public class SenderThread implements Runnable {

	ResultQueue resultQueue;
	ServerRequest serverSide;
	/**
	 * Constructor
	 * @param q
	 */
	public SenderThread(ResultQueue q) {
		resultQueue = q;
		serverSide = new ServerRequest(Globals.baseURL);
	}
	/**
	 * Main loop
	 */
	public void run() {
		try {
			while (true) {
				sendToServer();
				synchronized (resultQueue) {
					resultQueue.wait();
				}
			}
		} catch (InterruptedException e) {
			System.err.println("SenderThread interrupted. Exiting.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @SenderThread.run");
		}
	}
	/**
	 * Searches for results in queue
	 * if it finds one it sends them to AM
	 * @return
	 * @throws Exception
	 */
	private boolean sendToServer() throws Exception {
		Result res = resultQueue.getResult();
		if (res != null) {
			
			serverSide.sendResult(res);
			
			return true;
		}
		return false;
	}
	/**
	 * Print results ( Dont use this )
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private boolean printResult() throws Exception {
		Result res = resultQueue.getResult();
		if (res != null) {
			res.print();
			return true;
		}
		return false;
	}

}
