package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;

import javax.swing.JComboBox;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;

public class SaResultsTab {
	public static void populateSAWithResultsList(JComboBox<String> cb) {
		LinkedList<String> curList = AdminPanelDAO.geSAWithResults();
		cb.removeAllItems();
		if (curList == null)
			return;
		for (String s : curList) {
			cb.addItem(s);
		}
	}
}
