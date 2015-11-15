package sa;

public class Main {
	
	public static void main(String[] args) {
		if(!MySystemFiles.checkProgramPathExists()) {
			return;
		}
		SAProperties myp = new SAProperties();
		myp.readOneTimeThreadNumberFromFile();
		System.out.println(myp.oneTimeJobThreadsNumber);
	}

}
