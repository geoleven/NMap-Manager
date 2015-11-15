package sa;

import java.io.File;

public class MySystemFiles {

	public static boolean checkProgramPathExists(){
		File path = new File(Globals.pathName);
		if (path.exists() && path.isDirectory()) {
			return true;
		}
		else if(path.exists() && !path.isDirectory()){
			//System.err.println("mple");
			return false;
		} 
		else {
			if (path.mkdir()) {
				return true;
			} else {
				//System.err.println("kokkino");
				return false;
			}
		}
	}
}

