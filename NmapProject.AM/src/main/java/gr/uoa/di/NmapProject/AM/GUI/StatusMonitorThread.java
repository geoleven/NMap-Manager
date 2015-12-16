package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.JTable;
import gr.uoa.di.NmapProject.AM.Globals;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

public class StatusMonitorThread implements Runnable {
	
	JTable table = null;
		
	public Object[][] data = null;
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void run() {
		try {
			while(true) {
				//TODO
				populateData();
				Thread.sleep(1000 * Globals.refreshRate);
			}
		} catch (InterruptedException e) {
			if (Globals.verbose)
				System.err.println("Status monitor thread interrupted.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @StatusMonitorThread.run");
			e.printStackTrace();
		}	
	}
	
	public void populateData() {
		LinkedList<SAInfoStatus> list = AdminPanelDAO.getActiveSAInfo();
		data = new Object[list.size()][];
		for (int c1 = 0; c1 < list.size(); c1++) {
			Object[] temp = new Object[] {list.get(c1).status ? "Online" : "Offline",
						list.get(c1).id,
						list.get(c1).deviceName,
						list.get(c1).interfaceIP,
						list.get(c1).interfaceMacAddr,
						list.get(c1).osVersion,
						list.get(c1).nMapVersion,
						list.get(c1).isAccepted ? "Yes" : "No",
						list.get(c1).unionHash};
			data[c1] = temp;
		}
	}
}
