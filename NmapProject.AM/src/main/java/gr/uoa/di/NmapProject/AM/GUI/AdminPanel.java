package gr.uoa.di.NmapProject.AM.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;


public class AdminPanel extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6682482690528271851L;
	private JPanel contentPane;
	private JTextField periodEntry;
	private JTextField givenCmd;
	private CheckBoxList pendingRegistrationsList;
	public StatusMonitorTab smt = null;
	private String lastSASelectedToAssignJob = null;
	private JComboBox<String> saDropDownList;
	private JCheckBox isPeriodic;
	private int lastSetPeriod = -1;

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
		
		JPanel pendRegPnl = new JPanel();
		pendRegPnl.setBounds(10, 11, 769, 450);
		pendingRegistrationsTab.add(pendRegPnl);
		pendRegPnl.setLayout(new BorderLayout(0, 0));
		
		JScrollPane pendRegScrollPane = new JScrollPane();
		pendRegPnl.add(pendRegScrollPane);
		
		pendingRegistrationsList = new CheckBoxList();
		pendingRegistrationsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pendRegScrollPane.setViewportView(pendingRegistrationsList);
		pendingRegistrationsList.setVisibleRowCount(20);
		pendingRegistrationsList.setFixedCellHeight(30);
		
		JPanel pendingRegistrationActions = new JPanel();
		pendingRegistrationActions.setBounds(10, 472, 769, 50);
		pendingRegistrationsTab.add(pendingRegistrationActions);
		PendingRegsTab.populatePendingRegList(getPendingRegistrationsList());
		
		JButton refreshButton = new JButton("Refresh");
		pendingRegistrationActions.setLayout(new GridLayout(0, 2, 50, 50));
		pendingRegistrationActions.add(refreshButton);
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PendingRegsTab.populatePendingRegList(getPendingRegistrationsList());
			}
		});
		
		JButton acceptButton = new JButton("Accept Selected");
		pendingRegistrationActions.add(acceptButton);
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PendingRegsTab.acceptSelectedSAs(getPendingRegistrationsList());
				PendingRegsTab.populatePendingRegList(getPendingRegistrationsList());
			}
		});
		adminPanelTabs.setEnabledAt(0, true);
		
		JPanel saStatusMonitorTab = new JPanel();
		saStatusMonitorTab.setBorder(null);
		adminPanelTabs.addTab("SA Status Monitor", null, saStatusMonitorTab, null);
		saStatusMonitorTab.setLayout(null);
		
		JLabel lblSM = new JLabel("Live Status of Software Agents");
		lblSM.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSM.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSM.setHorizontalAlignment(SwingConstants.CENTER);
		lblSM.setBounds(12, 11, 767, 26);
		saStatusMonitorTab.add(lblSM);
		
		JScrollPane scrlStatusMntr = new JScrollPane();
		scrlStatusMntr.setBounds(10, 53, 769, 465);
		saStatusMonitorTab.add(scrlStatusMntr);
		
		
		JTable saStatusMonitorList = null;
		smt = new StatusMonitorTab(saStatusMonitorList);
		saStatusMonitorList = smt.getTable();
		scrlStatusMntr.add(saStatusMonitorList);
		scrlStatusMntr.setViewportView(saStatusMonitorList);
		
		
		saStatusMonitorList.setFillsViewportHeight(true);
		saStatusMonitorList.setCellSelectionEnabled(true);
		
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
		givenCmd.setBounds(67, 5, 287, 32);
		pnl2.add(givenCmd);
		givenCmd.setColumns(10);
		
		JPanel pnl3 = new JPanel();
		middlePanel.add(pnl3);
		pnl3.setLayout(null);
		
		isPeriodic = new JCheckBox("isPeriodic   ");
		isPeriodic.setHorizontalAlignment(SwingConstants.CENTER);
		isPeriodic.setActionCommand("isPeriodic");
		isPeriodic.setHorizontalTextPosition(SwingConstants.LEADING);
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
		
		saDropDownList = new JComboBox<String>();
		saDropDownList.setBounds(71, 5, 270, 32);
		saDropDownList.setMaximumRowCount(10);
		saDropDownList.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				JobInsertionTab.addItemsToComboBox(saDropDownList);
			}
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Delete?
			}
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Delete?	
			}
		});
		saDropDownList.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				lastSASelectedToAssignJob = (String) ((JComboBox<String>)e.getSource()).getSelectedItem();
				//System.out.println(lastSASelectedToAssignJob);
		}	
		});
		
		pnl5.add(saDropDownList);
		
		JPanel pnl6 = new JPanel();
		middlePanel.add(pnl6);
		pnl6.setLayout(null);
		
		JButton btnSumbit = new JButton("Sumbit");
		btnSumbit.setBounds(157, 5, 105, 41);
		pnl6.add(btnSumbit);
		btnSumbit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int tempi = 0;
				if (isPeriodic.isSelected()) {
					try {
						tempi = Integer.parseUnsignedInt(periodEntry.getText());
					} catch (NumberFormatException nfe1) {
						// TODO msg insert proper period time
						System.out.println("Weird period");
						return;
					}
					lastSetPeriod = tempi;
				}
				else {
					lastSetPeriod = -1;
				}
				if (lastSASelectedToAssignJob == null) {
					// TODO msg no sa selected
					System.out.println("No SA selected.");
					return;
				}
				JobInsertionTab.assignJob(givenCmd.getText(), isPeriodic.isSelected(), lastSetPeriod, lastSASelectedToAssignJob);
			}
		});
		
		JPanel rightBlank = new JPanel();
		rightBlank.setBounds(604, 0, 184, 543);
		jobAssignmentTab.add(rightBlank);
		
		JPanel jobDeletionTab = new JPanel();
		jobDeletionTab.setBorder(null);
		adminPanelTabs.addTab("Job Deletion", null, jobDeletionTab, null);
		jobDeletionTab.setLayout(null);
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the Software Agent from which you wish to stop a periodic job:");
		lblPleaseSelectThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectThe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPleaseSelectThe.setBounds(10, 20, 771, 26);
		jobDeletionTab.add(lblPleaseSelectThe);
		
		JComboBox<String> jdCB = new JComboBox<String>();
		jdCB.setBounds(134, 57, 525, 20);
		jdCB.setMaximumRowCount(10);
		jobDeletionTab.add(jdCB);
		
		JPanel saSpecificResults = new JPanel();
		adminPanelTabs.addTab("SA Specific Results", null, saSpecificResults, null);
		saSpecificResults.setLayout(null);
		saSpecificResults.setBorder(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(10, 134, 771, 400);
		saSpecificResults.add(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(296, 74, 200, 20);
		comboBox.setMaximumRowCount(10);
		saSpecificResults.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(581, 74, 200, 20);
		comboBox_1.setMaximumRowCount(10);
		saSpecificResults.add(comboBox_1);
		
		JLabel label = new JLabel("Please select starting time:");
		label.setBounds(298, 28, 196, 39);
		saSpecificResults.add(label);
		
		JLabel label_1 = new JLabel("Please select ending time:");
		label_1.setBounds(584, 28, 196, 39);
		saSpecificResults.add(label_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(10, 74, 200, 20);
		comboBox_2.setMaximumRowCount(10);
		saSpecificResults.add(comboBox_2);
		
		JLabel lblPleaseSelectA = new JLabel("Please select a Software Agent:");
		lblPleaseSelectA.setBounds(10, 40, 200, 14);
		saSpecificResults.add(lblPleaseSelectA);
		
		JPanel resultsTab = new JPanel();
		resultsTab.setBorder(null);
		adminPanelTabs.addTab("Results", null, resultsTab, null);
		resultsTab.setLayout(null);
		
		JTextArea resultsArea = new JTextArea();
		//resultsArea.setBackground(UIManager.getColor("Button.background"));
		resultsArea.setBackground(new Color(240, 240, 240));
		resultsArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		resultsArea.setBounds(10, 134, 771, 400);
		resultsTab.add(resultsArea);
		
		JComboBox saWithResultsDropDownList = new JComboBox();
		saWithResultsDropDownList.setBounds(90, 75, 200, 20);
		saWithResultsDropDownList.setMaximumRowCount(10);
		resultsTab.add(saWithResultsDropDownList);
		
		JComboBox sasResultList = new JComboBox();
		sasResultList.setBounds(488, 75, 200, 20);
		sasResultList.setMaximumRowCount(10);
		resultsTab.add(sasResultList);
		
		JLabel lblPleaseSelectSTime = new JLabel("Please select starting time:");
		lblPleaseSelectSTime.setBounds(92, 29, 196, 39);
		resultsTab.add(lblPleaseSelectSTime);
		
		JLabel lblPleaseSelectETime = new JLabel("Please select ending time:");
		lblPleaseSelectETime.setBounds(491, 29, 196, 39);
		resultsTab.add(lblPleaseSelectETime);
		
		JPanel remoteTerminationTab = new JPanel();
		remoteTerminationTab.setBorder(null);
		adminPanelTabs.addTab("Remote Termination", null, remoteTerminationTab, null);
//		remoteTerminationTab.setLayout(new BoxLayout(remoteTerminationTab, BoxLayout.X_AXIS));
		remoteTerminationTab.setLayout(null);
		
//		Panel pl = new Panel();
//		FlowLayout flowLayout = (FlowLayout) pl.getLayout();
//		flowLayout.setVgap(50);
//		remoteTerminationTab.add(pl);
		
		Panel pm = new Panel();
		pm.setBounds(184, 50, 420, 450);
		remoteTerminationTab.add(pm);
		pm.setLayout(null);
		
		JLabel lblPleaseSelectWhich = new JLabel(String.format("<html><dev WIDTH=%d>%s</div></html>", 250, "Please select which software agent you would like to terminate: "));
		lblPleaseSelectWhich.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPleaseSelectWhich.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPleaseSelectWhich.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPleaseSelectWhich.setBounds(83, 11, 257, 115);
		lblPleaseSelectWhich.setHorizontalAlignment(SwingConstants.CENTER);
		pm.add(lblPleaseSelectWhich);
		
		JComboBox runningSADropDownlist = new JComboBox();
		runningSADropDownlist.setBounds(0, 172, 417, 40);
		runningSADropDownlist.setMaximumRowCount(10);
		pm.add(runningSADropDownlist);
		
		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setBounds(0, 332, 417, 40);
		pm.add(btnTerminate);
		
		JLabel lblTerminationresult = new JLabel("");
		lblTerminationresult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerminationresult.setBounds(0, 429, 427, 115);
		lblTerminationresult.setHorizontalAlignment(SwingConstants.CENTER);
		pm.add(lblTerminationresult);
		
		adminPanelTabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentIndex = ((JTabbedPane)e.getSource()).getSelectedIndex();
				switch (currentIndex) {
				case 0: smt.stop();
				break;
				case 1: smt.start();;
				break;
				case 2: smt.stop();;
				break;
				case 3: smt.stop();;
				break;
				case 4: smt.stop();;
				break;
				case 5: smt.stop();;
				break;
				case 6: smt.stop();;
				break;
				}
			}
		});
		
//		Panel pr = new Panel();
//		remoteTerminationTab.add(pr);
	}
	public CheckBoxList getPendingRegistrationsList() {
		return pendingRegistrationsList;
	}
}
