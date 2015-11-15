package sa;

public class Main {
	
	public static void main(String[] args) {
		Globals.verbose = true;
		if(!MySystemFiles.checkProgramPathExists()) {
			return;
		}
		SAProperties myp = new SAProperties();
		myp.readOneTimeThreadNumberFromFile();
		//System.out.println(myp.oneTimeJobThreadsNumber);
		
		NmapJob myjob = new NmapJob(0, "-O 192.168.1.254", false, 0);
		
		System.out.println(myjob.runJob());
	}

}
