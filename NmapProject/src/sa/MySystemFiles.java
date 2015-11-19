package sa;

import java.io.File;

public class MySystemFiles {

	public static boolean checkProgramPathExists(){
		File path = new File(Globals.pathName);
		if (path.exists() && path.isDirectory()) {
			return true;
		}
		else if(path.exists() && !path.isDirectory()){
			//System.err.println("The path exists but is not a directory.");
			return false;
		} 
		else {
			if (path.mkdir()) {
				return true;
			} else {
				//System.err.println("Although the path didn't exist, it could not be created");
				return false;
			}
		}
	}
}

