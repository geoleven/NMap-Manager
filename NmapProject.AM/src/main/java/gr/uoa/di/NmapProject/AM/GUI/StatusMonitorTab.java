package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class StatusMonitorTab {
	
	public boolean isRunning = false;
	public JTable table = null;
	StatusMonitorThread smt = null;
	Thread statusMonitorThread = null;
	public static String[] columnNames = {"Status",
			"ID",
			"Device Name",
			"Interface IP",
			"Interface MacAddr",
			"OS Version",
			"NMap Version",
			"Is accepted",
			"Union Hash"};
	
	public StatusMonitorTab(JTable table) {
		this.table = table;
		smt = new StatusMonitorThread();
		smt.populateData();
		this.table = new JTable(smt.data, columnNames);
//		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		final TableColumnModel columnModel = this.table.getColumnModel();
//	    for (int column = 0; column < this.table.getColumnCount(); column++) {
//	        int width = 50; // Min width
//	        for (int row = 0; row < this.table.getRowCount(); row++) {
//	            TableCellRenderer renderer = this.table.getCellRenderer(row, column);
//	            Component comp = this.table.prepareRenderer(renderer, row, column);
//	            width = Math.max(comp.getPreferredSize().width +1 , width);
//	        }
//	        columnModel.getColumn(column).setPreferredWidth(width);
//	    }
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(60);
		columnModel.getColumn(2).setPreferredWidth(200);
		columnModel.getColumn(3).setPreferredWidth(250);
		columnModel.getColumn(4).setPreferredWidth(300);
		columnModel.getColumn(5).setPreferredWidth(150);
		columnModel.getColumn(6).setPreferredWidth(100);
		columnModel.getColumn(7).setPreferredWidth(100);
		columnModel.getColumn(8).setPreferredWidth(500);
		
		
		smt.setTable(this.table);
		statusMonitorThread = new Thread(smt);
	}
	
	public JTable getTable() {
		return table;
	}
	
	public void start() {
		statusMonitorThread.start();
		setRunning(true);
	}
	
	public void stop() {
		if(isRunning) {
			statusMonitorThread.interrupt();
			try {
				statusMonitorThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setRunning(false);
		}
	}
	
	synchronized public void setRunning(boolean r){
		isRunning = r;
	}
	
	
}
