package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;

import javax.swing.JComboBox;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;
import gr.uoa.di.NmapProject.AM.Server.OnlineStatus;

/**
 * Class to handle the functions in S.A. termination tab.
 * 
 * @author George
 *
 */
public class SATerminationTab {
	/**
	 * The function to populate the drop down list the active S.A.s which can be terminated.
	 * @param cb The combo box element to take the info from.
	 */
	public static void addItemsToComboBox(JComboBox<String> cb) {
		LinkedList<SAInfoStatus>  list = AdminPanelDAO.getOnlineSAInfo();
		cb.removeAllItems();
		if (list == null)
			return;
		for (SAInfoStatus s : list) {
			cb.addItem(s.unionHash);
		}
	}
	
	/**
	 * The function that pushes the request for an S.A. termination.
	 * 
	 * @param saHash The hash of the S.A. to terminate.
	 */
	public static void stopSA(String saHash) {
		
		OnlineStatus.getInstance().setForExit(saHash);
		
		System.out.println(saHash);
	}
}
