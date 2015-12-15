package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;


public class AdminPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6682482690528271851L;
	private JPanel contentPane;
	private JTextField periodEntry;
	private JTextField givenCmd;

	/**
	 * Launch the application.
	 */
	public void runAdminPanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel frame = new AdminPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminPanel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Admin Panel");
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane adminPanelTabs = new JTabbedPane(JTabbedPane.TOP);
		adminPanelTabs.setSelectedIndex(-1);
		adminPanelTabs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adminPanelTabs.setBounds(0, 0, 794, 572);
		contentPane.add(adminPanelTabs);
		
		JPanel pendingRegistrationsTab = new JPanel();
		adminPanelTabs.addTab("Pending Registrations", null, pendingRegistrationsTab, "");
		pendingRegistrationsTab.setLayout(null);
		
		JPanel pendingRegistrationActions = new JPanel();
		pendingRegistrationActions.setBounds(10, 472, 769, 50);
		pendingRegistrationsTab.add(pendingRegistrationActions);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		pendingRegistrationActions.setLayout(new GridLayout(0, 2, 50, 50));
		pendingRegistrationActions.add(refreshButton);
		
		JButton acceptButton = new JButton("Accept Selected");
		pendingRegistrationActions.add(acceptButton);
		
		JScrollPane PendingRegistrationsList = new JScrollPane();
		PendingRegistrationsList.setBounds(10, 11, 769, 450);
		pendingRegistrationsTab.add(PendingRegistrationsList);
		adminPanelTabs.setEnabledAt(0, true);
		
		JPanel saStatusMonitorTab = new JPanel();
		adminPanelTabs.addTab("SA Status Monitor", null, saStatusMonitorTab, null);
		saStatusMonitorTab.setLayout(null);
		
		JList saStatusMonitorList = new JList();
		saStatusMonitorList.setBounds(10, 11, 769, 521);
		saStatusMonitorTab.add(saStatusMonitorList);
		
		JPanel jobAssignmentTab = new JPanel();
		adminPanelTabs.addTab("Job Assignment", null, jobAssignmentTab, null);
		jobAssignmentTab.setLayout(null);
		
		JPanel leftBlank = new JPanel();
		leftBlank.setBounds(0, 0, 184, 543);
		jobAssignmentTab.add(leftBlank);
		
		JPanel middlePanel = new JPanel();
		middlePanel.setBounds(184, 99, 420, 346);
		jobAssignmentTab.add(middlePanel);
		middlePanel.setLayout(new GridLayout(6, 1, 5, 5));
		
		JPanel pnl1 = new JPanel();
		middlePanel.add(pnl1);
		
		JLabel giveJobLbl = new JLabel("Please enter a new job to be assigned:");
		pnl1.add(giveJobLbl);
		giveJobLbl.setHorizontalAlignment(SwingConstants.CENTER);
		giveJobLbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel pnl2 = new JPanel();
		middlePanel.add(pnl2);
		pnl2.setLayout(null);
		
		givenCmd = new JTextField();
		givenCmd.setBounds(67, 5, 286, 32);
		pnl2.add(givenCmd);
		givenCmd.setColumns(10);
		
		JPanel pnl3 = new JPanel();
		middlePanel.add(pnl3);
		pnl3.setLayout(null);
		
		JCheckBox isPeriodic = new JCheckBox("         isPeriodic");
		isPeriodic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		isPeriodic.setBounds(149, 5, 121, 36);
		pnl3.add(isPeriodic);
		
		JPanel pnl4 = new JPanel();
		middlePanel.add(pnl4);
		pnl4.setLayout(null);
		
		periodEntry = new JTextField();
		periodEntry.setBounds(127, 11, 166, 26);
		pnl4.add(periodEntry);
		periodEntry.setColumns(10);
		
		JPanel pnl5 = new JPanel();
		middlePanel.add(pnl5);
		pnl5.setLayout(null);
		
		JComboBox saDropDownList = new JComboBox();
		saDropDownList.setBounds(143, 5, 128, 32);
		saDropDownList.setMaximumRowCount(0);
		pnl5.add(saDropDownList);
		
		JPanel pnl6 = new JPanel();
		middlePanel.add(pnl6);
		pnl6.setLayout(null);
		
		JButton btnSumbit = new JButton("Sumbit");
		btnSumbit.setBounds(157, 5, 105, 41);
		pnl6.add(btnSumbit);
		
		JPanel rightBlank = new JPanel();
		rightBlank.setBounds(604, 0, 184, 543);
		jobAssignmentTab.add(rightBlank);
		
		JPanel jobDeletionTab = new JPanel();
		adminPanelTabs.addTab("Job Deletion", null, jobDeletionTab, null);
		
		JPanel resultsTab = new JPanel();
		adminPanelTabs.addTab("Results", null, resultsTab, null);
		
		JPanel remoteTerminationTab = new JPanel();
		adminPanelTabs.addTab("Remote Termination", null, remoteTerminationTab, null);
	}
}
