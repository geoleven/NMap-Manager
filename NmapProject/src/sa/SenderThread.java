package sa;


public class SenderThread implements Runnable {

	Thread runner;
	public SenderThread() {
		
	}
	
	@Override
	public void run() {
		System.out.println("I am now sending the harvested XMLs!");
		
	}

}
