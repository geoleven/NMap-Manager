package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import gr.uoa.di.NmapProject.AM.DB.AdminPanelDAO;

public class JobDeletionTab {

	public static void pupulateList(JList<JobPrev> list, String sa) {
		LinkedList<JobPrev> results = AdminPanelDAO.getPeriodicJobsOfSA(sa);
		DefaultListModel<JobPrev> listModel = new DefaultListModel<JobPrev>();
		for (JobPrev jp : results)
			listModel.addElement(jp);
		list.setModel(listModel);
	}

	public static void stopJobOfSA(JobPrev jp) {
		// TODO Write the code to enqueue the stop job jp
		System.out.println(jp.toString());
	}
}
