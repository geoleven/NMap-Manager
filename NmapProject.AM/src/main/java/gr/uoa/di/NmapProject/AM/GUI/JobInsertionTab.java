package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.JComboBox;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.JobDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

public class JobInsertionTab {
	
	public static void assignJob(String param, boolean isPeriodic, int period, String saHash) {
		System.out.println("Got this assignment: " + "   " + param +  "   " + period +  "   " + saHash);
		
		if(JobDAO.newJob(param , isPeriodic , period , saHash)){
			System.out.println("Insertion successful ...");
		}else{
			System.err.println("Insertion failed ...");
		}
		
	} 

	public static void addItemsToComboBox(JComboBox<String> cb){
		// FIXME change from db access to list specially for online!!!
		LinkedList<SAInfoStatus>  list = AdminPanelDAO.getAcceptedSAInfo();
		cb.removeAllItems();
		if (list == null)
			return;
		for (SAInfoStatus s : list) {
			cb.addItem(s.unionHash);
		}
	}
}
