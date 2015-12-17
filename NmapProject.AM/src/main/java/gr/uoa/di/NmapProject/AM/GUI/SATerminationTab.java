package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;

import javax.swing.JComboBox;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

public class SATerminationTab {
	public static void addItemsToComboBox(JComboBox<String> cb) {
		LinkedList<SAInfoStatus>  list = AdminPanelDAO.getOnlineSAInfo();
		cb.removeAllItems();
		if (list == null)
			return;
		for (SAInfoStatus s : list) {
			cb.addItem(s.unionHash);
		}
	}
	
	public static void stopSA(String saHash) {
		// TODO write code to stop the selected sa
		System.out.println(saHash);
	}
}
