package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.SwingConstants;

import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SA;

public class pendingRegsTab {
	public static void populatePendingRegList(JList<JCheckBox> checkList) {
		LinkedList<SA> curList = AdminPanelDAO.getPendReg();
		System.out.println(curList.size());
		//currentHeight
		for (SA curSA : curList) {
			JCheckBox tempcb = new JCheckBox(curSA.id + "\t" + curSA.device_name + "\t" + curSA.ip + "\t" + curSA.mac_address + "\t" + curSA.os_version + "\t" + curSA.nmap_version + "\t" + curSA.hash);
			tempcb.setHorizontalAlignment(SwingConstants.CENTER);
			//tempcb.setActionCommand("setToReg");
			tempcb.setHorizontalTextPosition(SwingConstants.LEADING);
			tempcb.setFont(new Font("Tahoma", Font.PLAIN, 12));
			//tempcb.setBounds(0, 0, 750, 50);
			checkList.add(tempcb);
			
		    int index = checkList.getMaxSelectionIndex(); //get selected index
		    if (index == -1) { //no selection, so insert at beginning
		        index = 0;
		    } else {           //add after the selected item
		        index++;
		    }
			checkList.setSelectedIndex(index);
			checkList.ensureIndexIsVisible(index);
			checkList.updateUI();
			
			System.out.println(curSA.id + "\t" + curSA.device_name + "\t" + curSA.ip + "\t" + curSA.mac_address + "\t" + curSA.os_version + "\t" + curSA.nmap_version + "\t" + curSA.hash);
		}
	}
}
