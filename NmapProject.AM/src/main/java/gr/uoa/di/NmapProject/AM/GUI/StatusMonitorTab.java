package gr.uoa.di.NmapProject.AM.GUI;

import javax.swing.JTable;

public class StatusMonitorTab {
	
	public boolean isRunning = false;
	private Object mutex = new Object();
	StatusMonitorThread smt = null;
	Thread statusMonitorThread = null;
	
	public StatusMonitorTab(JTable table) {
		smt = new StatusMonitorThread();
//		smt.populateData();
		smt.drawTable();
	}
	
	public JTable getTable() {
		return smt.table;
	}
	
	public void start() {
		synchronized(mutex) {
			if(!isRunning){
				statusMonitorThread = new Thread(smt);
				statusMonitorThread.start();
			}
			isRunning = true;
		}
	}
	
	public void stop() {
		synchronized(mutex) {
			if(isRunning) {
				statusMonitorThread.interrupt();
				try {
					statusMonitorThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			statusMonitorThread = null;
			isRunning = false;
		}
	}
	
	
}
