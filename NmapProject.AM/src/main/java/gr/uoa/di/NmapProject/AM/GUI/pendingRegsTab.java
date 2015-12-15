package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JCheckBox;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SA;

public class pendingRegsTab {
	@SuppressWarnings("unchecked")
	public static void populatePendingRegList(CheckBoxList checkList) {
		LinkedList<SA> curList = AdminPanelDAO.getPendReg();
		JCheckBox[] tempArray = null;
		if (curList.size() > 0) {
			tempArray = new JCheckBox[curList.size()];
			int counter = 0;
			for (SA curSA : curList) {
				tempArray[counter] = new JCheckBox(String.format("<html><pre>%s</pre></html>", curSA.id + "\t" + curSA.device_name + "\t\t" + curSA.ip + "\t"
						+ curSA.mac_address + "\t" + curSA.os_version + "\t\t" + "Nmap: " + curSA.nmap_version + "\t" + curSA.hash));
				counter++;
			}
		}
		checkList.setListData(tempArray);
	}
	
	@SuppressWarnings("unchecked")
	public static void acceptSelectedSAs(CheckBoxList checkList) {
		LinkedList<Integer> idsToBeAccepted = new LinkedList<Integer>();
		for (JCheckBox cb : (List<JCheckBox>)(checkList.getSelectedValuesList())) {
			if (cb.isSelected()) {
				System.out.println("Adding " + Integer.parseInt(cb.getText().substring(11, cb.getText().indexOf("\t"))) + ".");
				idsToBeAccepted.add(Integer.parseInt(cb.getText().substring(11, cb.getText().indexOf("\t"))));
			}
		}
		AdminPanelDAO.acceptSAs(idsToBeAccepted);
	}
}
