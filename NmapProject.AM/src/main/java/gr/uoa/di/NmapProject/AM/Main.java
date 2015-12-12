package main.java.gr.uoa.di.NmapProject.AM;

import main.java.gr.uoa.di.NmapProject.AM.DB.Admin;
import main.java.gr.uoa.di.NmapProject.AM.DB.AdminDAO;
import main.java.gr.uoa.di.NmapProject.AM.Server.Server;

/**
 * Main class.
 *
 */
public class Main {

	public static void main(String[] args){

		Server server = new Server("http://localhost:8080/am/");
		server.start();
		
		AdminDAO.create(new Admin("test2" , "pass2" , true));
		
		server.stop();
		System.exit(1);	
	}
}