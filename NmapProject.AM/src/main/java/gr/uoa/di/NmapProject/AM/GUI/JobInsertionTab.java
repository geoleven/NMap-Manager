package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.JComboBox;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.JobDAO;
import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;

/**
 * The class holding all the needed functions for the job insertion tab.
 * 
 * @author George
 *
 */
public class JobInsertionTab {
	
	/**
	 * Assign a job to an S.A..
	 * 
	 * @param param	The NMap's parameters of this job.
	 * @param isPeriodic A boolean showing if this job will be periodic.
	 * @param period The period of this job, if it is periodic.
	 * @param saHash The hash of the S.A. to which this job will be assigned.
	 */
	public static void assignJob(String param, boolean isPeriodic, int period, String saHash) {
		System.out.println("Got this assignment: " + "   " + param +  "   " + period +  "   " + saHash);
		
		if(JobDAO.newJob(param , isPeriodic , period , saHash)){
			System.out.println("Insertion successful ...");
		}else{
			System.err.println("Insertion failed ...");
		}
		
	} 

	/**
	 * Adds the hashes of the online S.A. to the drop down combo box.
	 * 
	 * @param cb The combo box to populate.
	 */
	public static void addItemsToComboBox(JComboBox<String> cb){
		LinkedList<SAInfoStatus>  list = AdminPanelDAO.getAcceptedSAInfo();
		cb.removeAllItems();
		if (list == null)
			return;
		for (SAInfoStatus s : list) {
			cb.addItem(s.unionHash);
		}
	}
}
