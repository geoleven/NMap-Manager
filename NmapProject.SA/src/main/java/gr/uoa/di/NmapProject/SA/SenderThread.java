package gr.uoa.di.NmapProject.SA;

public class SenderThread implements Runnable {

	ResultQueue resultQueue;

	public SenderThread(ResultQueue q) {
		resultQueue = q;
	}

	public void run() {
		try {
			while (true) {
				printResult();
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
			//ServerRequest sRSendRes = new ServerRequest("http://localhost:8080/am/");
			//sRSendRes.sendResults(res.result);
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
