package gr.uoa.di.NmapProject.SA;

public class SenderThread implements Runnable {

	ResultQueue resultQueue;
	ServerRequest serverSide;

	public SenderThread(ResultQueue q) {
		resultQueue = q;
		serverSide = new ServerRequest(Globals.baseURL);
	}

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
	
	private boolean sendToServer() throws Exception {
		Result res = resultQueue.getResult();
		if (res != null) {
			
			serverSide.sendResult(res);
			
			return true;
		}
		return false;
	}

	private boolean printResult() throws Exception {
		Result res = resultQueue.getResult();
		if (res != null) {
			res.print();
			return true;
		}
		return false;
	}

}
