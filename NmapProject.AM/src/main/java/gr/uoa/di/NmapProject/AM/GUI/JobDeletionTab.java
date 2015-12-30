package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;

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
	 * Does the actually job stopping of a job.
	 * 
	 * @param jp
	 *            The job to stop.
	 */
	public static void stopJobOfSA(JobPrev jp) {
		// TODO Write the code to enqueue the stop job jp
		System.out.println(jp.toString());
	}
}
