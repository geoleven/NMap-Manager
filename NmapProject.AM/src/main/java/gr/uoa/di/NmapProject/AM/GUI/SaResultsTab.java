package gr.uoa.di.NmapProject.AM.GUI;

import java.util.Date;
import java.util.LinkedList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
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
	
	public static void populateSASpecificResultTextArea(JTextArea ta, String sa, Date start, Date end) {
		LinkedList<String> resList = AdminPanelDAO.getResultsOfSABetweenTime(sa, start.getTime(), end.getTime(), false);
		LinkedList<String> parsedList = parseResultList(resList);
		String finalText = "";
		for(String s : parsedList) {
			finalText += s + "\n" + "___________________________________________________________________________________________________________" + "\n";
		}
//		ta.setText(null);
		ta.setText(finalText);
	}
	
	private static LinkedList<String> parseResultList(LinkedList<String> unparsed) {
		LinkedList<String> results = new LinkedList<String>();
		if (unparsed != null) {
		// TODO Things...
			for(String s : unparsed)
				results.add(s);
		}
		return results;
	}
}
