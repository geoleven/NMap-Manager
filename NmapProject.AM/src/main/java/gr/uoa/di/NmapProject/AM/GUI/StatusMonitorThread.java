package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import gr.uoa.di.NmapProject.AM.Globals;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

/**
 * This class implements the thread which supports the live feedback of the online status of the S.A.s.
 * 
 * @author George
 *
 */
public class StatusMonitorThread implements Runnable {
	
	JTable table = null;
	/**
	 * The table collumNames content.
	 */
	public static String[] columnNames = {"Status",
			"ID",
			"Device Name",
			"Interface IP",
			"Interface MacAddr",
			"OS Version",
			"NMap Version",
			"Is accepted",
			"Union Hash"};
	private Object[][] data = null;
	MyTableModel tmdl = new MyTableModel(AdminPanelDAO.getAcceptedSAInfo());;
	
	
	/**
	 * It changes the contents of a JTable of its kind to the table given.
	 * 
	 * @param table The new table that should be put in the JTable.
	 */
	public void setTable(JTable table) {
		this.table = table;
	}
	
	public void run() {
		try {
			while(true) {
				tmdl.changeList(AdminPanelDAO.getAcceptedSAInfo());
				Thread.sleep(1000 * Globals.refreshRate);
			}
		} catch (InterruptedException e) {
			if (Globals.verbose)
				System.err.println("Status monitor thread interrupted. Exiting.");
		} catch (Exception e) {
			System.err.println("Unexpected exception " + e.getMessage() + " @StatusMonitorThread.run");
			e.printStackTrace();
		}	
	}
	
	/**
	 * It populates the table in the class with the new data to be shown.
	 */
	public void populateData() {
		LinkedList<SAInfoStatus> list = AdminPanelDAO.getAcceptedSAInfo();
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
	
	/**
	 * The function that draws the table and sets its columns to their right width e.t.c..
	 */
	public void drawTable() {
		populateData();
		JTable tempTable = new JTable(data, columnNames);
		table = new JTable(tmdl, tempTable.getColumnModel());
		final TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(60);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(300);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(100);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(500);
	}
}
