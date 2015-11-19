package sa;

public class SenderThread implements Runnable {

	ResultQueue queue;

	public SenderThread(ResultQueue q) {
		queue = q;
	}

	@Override
	public void run() {
		try {
			while (true) {
				printResult();
				synchronized (queue) {
					queue.wait();
				}
			}
		} catch (InterruptedException e) {
			System.err.println("SenderThread interrupted. Exiting.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @SenderThread.run");
		}
	}

	private boolean printResult() throws Exception {
		Result res = queue.getResult();
		if (res != null) {
			res.print();
			return true;
		}
		return false;
	}

}
