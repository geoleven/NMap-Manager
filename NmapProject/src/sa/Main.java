package sa;

public class Main {
	
	public static void main(String[] args) {
		try{
			Globals.verbose = true;
			if(!MySystemFiles.checkProgramPathExists()) {
				return;
			}
			SAProperties myp = new SAProperties();
			myp.readOneTimeThreadNumberFromFile();
			//System.out.println(myp.oneTimeJobThreadsNumber);
			
			JobQueue jQ = new JobQueue();
			
			Thread jF = new Thread(new JobFinder(jQ));
			
			jF.start();
		}
		catch (Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	

}
