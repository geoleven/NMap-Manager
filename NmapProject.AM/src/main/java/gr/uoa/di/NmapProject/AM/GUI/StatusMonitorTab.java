package gr.uoa.di.NmapProject.AM.GUI;

import javax.swing.JTable;

/**
 * The class which handles the status monitor tab.
 * 
 * @author George
 *
 */
public class StatusMonitorTab {
	
	/**
	 * A boolean showing if the status monitor live feedback thread is running.
	 */
	public boolean isRunning = false;
	private Object mutex = new Object();
	StatusMonitorThread smt = null;
	Thread statusMonitorThread = null;
	
	/**
	 * It initializes the background process for the status monitor tab to be properly populated.
	 * @param table
	 */
	public StatusMonitorTab(JTable table) {
		smt = new StatusMonitorThread();
//		smt.populateData();
		smt.drawTable();
	}
	
	/**
	 * Getter of the JTable pointer.
	 * @return The JTable pointer for this tab.
	 */
	public JTable getTable() {
		return smt.table;
	}
	
	/**
	 * It starts the thread that takes care of the live feedback for the status of S.A.s.
	 */
	public void start() {
		synchronized(mutex) {
			if(!isRunning){
				statusMonitorThread = new Thread(smt);
				statusMonitorThread.start();
			}
			isRunning = true;
		}
	}
	
	/**
	 * It stops the thread that takes care of the live feedback for the status of S.A.s.
	 */
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
