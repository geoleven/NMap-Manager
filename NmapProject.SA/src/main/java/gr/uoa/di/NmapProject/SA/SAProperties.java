package gr.uoa.di.NmapProject.SA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * Responsible for the configuration of the SA 
 * 
 * @author George
 *
 */
public class SAProperties {
	/**
	 * Read the one time job thread number from specified path
	 */
	public void readThreadNum(){
		readThreadNum(Globals.pathName + "properties");
	}
	/**
	 * read thread number
	 * @param filename
	 */
	public void readThreadNum(String filename) {
		
		File myPath = new File(filename);
		if (!myPath.exists()) {
			System.err.println("No file \"properties\" in \"~/.myNmap/\"  , assuming 5 threads");
			Globals.oneTimeJobThreadsNumber = 5;
		} else {
			BufferedReader br = null;
			try {
				String threadNumberString;
				br = new BufferedReader(new FileReader(filename));
				if((threadNumberString = br.readLine()) != null) {
					if (threadNumberString.contains("Number of threads="))
						Globals.oneTimeJobThreadsNumber = Integer.parseInt(threadNumberString.substring(threadNumberString.lastIndexOf('=')+1));
					if (Globals.verbose)
						System.err.println("Using " + Globals.oneTimeJobThreadsNumber + " threads.");
				} else {
					System.err.println("Number of threads is not specified in property file , assuming 5 threads");
					Globals.oneTimeJobThreadsNumber = 5;
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
