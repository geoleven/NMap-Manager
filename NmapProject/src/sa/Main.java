package sa;

public class Main {
	
	public static void main(String[] args) {
		try{
			Globals.verbose = false;
			if(!MySystemFiles.checkProgramPathExists()) {
				return;
			}
			SAProperties myp = new SAProperties();
			myp.readOneTimeThreadNumberFromFile();
			//System.out.println(myp.oneTimeJobThreadsNumber);
			
			JobQueue jQ = new JobQueue();
			ResultQueue rQ = new ResultQueue();
			
			Thread jF = new Thread(new JobFinder(jQ));
			Thread sT = new Thread(new SenderThread(rQ));
			Thread oneTime = new Thread(new OneTimeJobThread(jQ , rQ));
			
			jF.start();
			sT.start();
			oneTime.start();
			
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	

}
