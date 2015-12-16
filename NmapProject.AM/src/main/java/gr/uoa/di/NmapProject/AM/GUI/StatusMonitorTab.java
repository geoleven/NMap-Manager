package gr.uoa.di.NmapProject.AM.GUI;

import javax.swing.JTable;

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
		smt = new StatusMonitorThread(this.table);
		smt.populateData();
		this.table = new JTable(smt.data, columnNames);
		statusMonitorThread = new Thread(smt);
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
