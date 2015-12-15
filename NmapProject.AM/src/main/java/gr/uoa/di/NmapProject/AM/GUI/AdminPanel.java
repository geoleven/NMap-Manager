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
import java.awt.Dimension;
import java.awt.Panel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import java.awt.Component;


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
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane adminPanelTabs = new JTabbedPane(JTabbedPane.TOP);
		adminPanelTabs.setBorder(null);
		adminPanelTabs.setSelectedIndex(-1);
		adminPanelTabs.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adminPanelTabs.setBounds(0, 0, 796, 574);
		contentPane.add(adminPanelTabs);
		
		JPanel pendingRegistrationsTab = new JPanel();
		pendingRegistrationsTab.setBorder(null);
		adminPanelTabs.addTab("Pending Registrations", null, pendingRegistrationsTab, "");
		pendingRegistrationsTab.setLayout(null);
		
		JScrollPane PendingRegistrationsList = new JScrollPane();
		PendingRegistrationsList.setBorder(null);
		PendingRegistrationsList.setViewportBorder(null);
		PendingRegistrationsList.setBounds(10, 11, 769, 450);
		pendingRegistrationsTab.add(PendingRegistrationsList);
		
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
		adminPanelTabs.setEnabledAt(0, true);
		
		JPanel saStatusMonitorTab = new JPanel();
		saStatusMonitorTab.setBorder(null);
		adminPanelTabs.addTab("SA Status Monitor", null, saStatusMonitorTab, null);
		saStatusMonitorTab.setLayout(null);
		
		JList saStatusMonitorList = new JList();
		saStatusMonitorList.setBounds(10, 11, 769, 521);
		saStatusMonitorTab.add(saStatusMonitorList);
		
		JPanel jobAssignmentTab = new JPanel();
		jobAssignmentTab.setBorder(null);
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
		
		JCheckBox isPeriodic = new JCheckBox("     isPeriodic");
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
		jobDeletionTab.setBorder(null);
		adminPanelTabs.addTab("Job Deletion", null, jobDeletionTab, null);
		
		JPanel resultsTab = new JPanel();
		resultsTab.setBorder(null);
		adminPanelTabs.addTab("Results", null, resultsTab, null);
		
		JPanel remoteTerminationTab = new JPanel();
		remoteTerminationTab.setBorder(null);
		adminPanelTabs.addTab("Remote Termination", null, remoteTerminationTab, null);
		remoteTerminationTab.setLayout(new BoxLayout(remoteTerminationTab, BoxLayout.X_AXIS));
		
		Panel pl = new Panel();
		FlowLayout flowLayout = (FlowLayout) pl.getLayout();
		flowLayout.setVgap(50);
		remoteTerminationTab.add(pl);
		
		Panel pm = new Panel();
		remoteTerminationTab.add(pm);
		pm.setLayout(null);
		
		JLabel lblPleaseSelectWhich = new JLabel(String.format("<html><dev WIDTH=%d>%s</div></html>", 250, "Please select which software agent you would like to terminate: "));
		lblPleaseSelectWhich.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPleaseSelectWhich.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPleaseSelectWhich.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPleaseSelectWhich.setBounds(83, 11, 257, 115);
		lblPleaseSelectWhich.setHorizontalAlignment(SwingConstants.CENTER);
		pm.add(lblPleaseSelectWhich);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 172, 407, 40);
		pm.add(comboBox);
		
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setBounds(10, 332, 407, 40);
		pm.add(btnTerminate);
		
		JLabel lblTerminationresult = new JLabel("");
		lblTerminationresult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerminationresult.setBounds(0, 429, 427, 115);
		lblTerminationresult.setHorizontalAlignment(SwingConstants.CENTER);
		pm.add(lblTerminationresult);
		
		Panel pr = new Panel();
		remoteTerminationTab.add(pr);
	}
}
