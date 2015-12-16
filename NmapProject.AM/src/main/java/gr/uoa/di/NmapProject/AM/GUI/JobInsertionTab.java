package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.JComboBox;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

public class JobInsertionTab {
	
	public static void assignJob(String param, boolean isPeriodic, int period, String saHash) {
		// TODO write the assignement
		// TODO popup oti kataxwrithike to insertion
		System.out.println("Got this assignment: " + param + period + saHash);
	}

	public static void addItemsToComboBox(JComboBox<String> cb){
		LinkedList<SAInfoStatus>  list = AdminPanelDAO.getAcceptedSAInfo();
		for (SAInfoStatus s : list) {
			cb.addItem(s.unionHash);
		}
	}
}
