package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;
import gr.uoa.di.NmapProject.AM.DB.JobDAO;

/**
 * The class holding all the needed functions for the job deletion tab.
 * 
 * @author George
 *
 */
public class JobDeletionTab {

	/**
	 * Populates the list to be rendered in the tab.
	 * 
	 * @param list
	 *            The JList to populate.
	 * @param sa
	 *            The S.A. for which the list is populated.
	 */
	public static void populateList(JList<JobPrev> list, String sa) {
		LinkedList<JobPrev> results = AdminPanelDAO.getPeriodicJobsOfSA(sa);
		DefaultListModel<JobPrev> listModel = new DefaultListModel<JobPrev>();
		for (JobPrev jp : results)
			listModel.addElement(jp);
		list.setModel(listModel);
	}

	/**
	 * Changes jobs status too "Delete" in db.
	 * 
	 * @param jp
	 *            The job to stop.
	 */
	public static void stopJobOfSA(JobPrev jp) {
		System.out.println(jp.toString());
		
		JobDAO.setStatusToDelete(jp.id());
		
	}
}
