package sa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SAProperties {
	public int oneTimeJobThreadsNumber = 0;
	public void readOneTimeThreadNumberFromFile(String filename) {
		
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
