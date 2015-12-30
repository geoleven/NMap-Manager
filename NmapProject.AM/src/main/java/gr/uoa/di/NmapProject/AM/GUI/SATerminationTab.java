package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;

import javax.swing.JComboBox;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;
import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

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
		
		OnlineStatus.getInstance().setForExit(saHash);
		
		System.out.println(saHash);
	}
}
