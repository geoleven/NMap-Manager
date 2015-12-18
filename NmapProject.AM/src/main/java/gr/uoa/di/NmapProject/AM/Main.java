package gr.uoa.di.NmapProject.AM;

import gr.uoa.di.NmapProject.AM.GUI.App;

/**
 * Main class.
 *
 */
public class Main {	
	public static void main(String[] args){
		Globals globals = new Globals();
		App app = new App();
		app.run();
	}
}