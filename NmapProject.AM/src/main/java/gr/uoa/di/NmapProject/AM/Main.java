package gr.uoa.di.NmapProject.AM;

import gr.uoa.di.NmapProject.AM.GUI.App;
import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

/**
 * Main class.
 *
 */
public class Main {	
	public static void main(String[] args) throws Exception{
		Globals globals = new Globals();
		App app = new App();
		app.run();
		
		//TODO REMOVE THIS 
//		while(true){
//			Thread.sleep(1000);
//			OnlineStatus.getInstance().printStatuses();
//		}
	}
}