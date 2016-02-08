package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import java.util.Map;

import javax.swing.JCheckBox;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;

/**
 * Class for the admin registration tab functions.
 * 
 * @author George
 *
 */
public class AdminRegsTab {

	/**
	 * Fills the list of the pending admin registrations.
	 * 
	 * @param checkList The list element to populate.
	 */
	@SuppressWarnings("unchecked")
	public static void populateAdminRegList(CheckBoxList checkList) {
		Map<Integer, String> curMap = AdminPanelDAO.getAdminReg();
		JCheckBox[] tempArray = new JCheckBox[curMap.size()];
		if (curMap.size() > 0) {
			int counter = 0;
			for (Map.Entry<Integer, String> curAdmin : curMap.entrySet()) {
				tempArray[counter] = new JCheckBox(String.format("<html><pre>%s</pre></html>", "ID: " + curAdmin.getKey() + "\tE-mail: " + curAdmin.getValue()));
				counter++;
			}
		}
		checkList.setListData(tempArray);
	}
	
	/**
	 * After user input it validates and accepts the selected admins.
	 * 
	 * @param checkList
	 */
	public static void acceptSelectedAdmins(CheckBoxList checkList) {
		LinkedList<Integer> adminsToBeAccepted = new LinkedList<Integer>();
		JCheckBox cb = null;
		for (int index = 0; index < checkList.getModel().getSize(); index++) {
			cb = (JCheckBox) checkList.getModel().getElementAt(index);
			if (cb.isSelected()) {
				System.out.println("Adding " + Integer.parseInt(cb.getText().substring(15, cb.getText().indexOf("\tE-mail"))) + ".");
				adminsToBeAccepted.add(Integer.parseInt(cb.getText().substring(15, cb.getText().indexOf("\tE-mail"))));
			}
		}
		AdminPanelDAO.acceptAdmins(adminsToBeAccepted);
	}
	
	/**
	 * After user input it validates and rejects the selected admins.
	 * 
	 * @param checkList
	 */
	public static void rejectSelectedAdmins(CheckBoxList checkList) {
		LinkedList<Integer> adminsToBeRejected = new LinkedList<Integer>();
		JCheckBox cb = null;
		for (int index = 0; index < checkList.getModel().getSize(); index++) {
			cb = (JCheckBox) checkList.getModel().getElementAt(index);
			if (cb.isSelected()) {
				System.out.println("Rejecting " + Integer.parseInt(cb.getText().substring(15, cb.getText().indexOf("\tE-mail"))) + ".");
				adminsToBeRejected.add(Integer.parseInt(cb.getText().substring(15, cb.getText().indexOf("\tE-mail"))));
			}
		}
		AdminPanelDAO.rejectAdmins(adminsToBeRejected);
	}
}
