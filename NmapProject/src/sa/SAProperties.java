package sa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SAProperties {
	public int oneTimeJobThreadsNumber = 0;
	
	public void readOneTimeThreadNumberFromFile(){
		readOneTimeThreadNumberFromFile(Globals.pathName + "threadNum");
	}
	public void readOneTimeThreadNumberFromFile(String filename) {
		
		File myPath = new File(filename);
		if (!myPath.exists()) {
			System.err.println("No file \"threadNum\" in \"~/.myNmap/\"  , assuming 5 threads");
			oneTimeJobThreadsNumber = 5;
		} else {
			BufferedReader br = null;
			try {
				String threadNumberString;
				br = new BufferedReader(new FileReader(filename));
				if((threadNumberString = br.readLine()) != null) {
					oneTimeJobThreadsNumber = Integer.parseInt(threadNumberString);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}
