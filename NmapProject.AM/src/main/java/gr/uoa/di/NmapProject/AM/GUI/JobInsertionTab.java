package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.JComboBox;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

public class JobInsertionTab {

	public static void addItemsToComboBox(JComboBox<String> cb){
		LinkedList<SAInfoStatus>  list = AdminPanelDAO.getAcceptedSAInfo();
		for (SAInfoStatus s : list) {
			cb.addItem(s.unionHash);
		}
	}
}
