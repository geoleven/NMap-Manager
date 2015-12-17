package gr.uoa.di.NmapProject.AM.GUI;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;

// Root of all evil :D
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

//import java.awt.Component;


public class AdminPanel extends JFrame {

	private static final int numOfAssigns = 6;

	private static final long serialVersionUID = -6682482690528271851L;
	private JPanel contentPane;
	LinkedList<JTextField> periodEntry = new LinkedList<JTextField>();
	LinkedList<JTextField> givenCmd = new LinkedList<JTextField>();
	LinkedList<JCheckBox> isPeriodic = new LinkedList<JCheckBox>();
	LinkedList<JPanel> miniPanel = new LinkedList<JPanel>();
	private CheckBoxList pendingRegistrationsList;
	public StatusMonitorTab smt = null;
	private String lastSASelectedToAssignJob = null;
	private JComboBox<String> saDropDownList;
	private int lastSetPeriod = -1;
	private JComboBox<String> jdCB;
	private String lastSASelectedToDeleteJob = null;
	JList<JobPrev> delList = null;
	private JComboBox<String> runningSADropDownlist;
	private JButton btnSumbit;
	private JPanel expandableAssigns;
	private String lastSASelectedToShowRes = null;
	private JComboBox<String> saSpecCB;
	private JTextArea txtSaResults;

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

		JPanel pnl1 = new JPanel();
		pnl1.setBounds(184, 11, 420, 34);
		jobAssignmentTab.add(pnl1);

		JLabel giveJobLbl = new JLabel("Please enter job/-s to be assigned:");
		pnl1.add(giveJobLbl);
		giveJobLbl.setHorizontalAlignment(SwingConstants.CENTER);
		giveJobLbl.setFont(new Font("Tahoma", Font.BOLD, 12));

		JPanel jobAssignPnl = new JPanel();
		jobAssignPnl.setBounds(10, 85, 771, 376);
		jobAssignmentTab.add(jobAssignPnl);
		jobAssignPnl.setLayout(null);

		JScrollPane jobAssignScrollPane = new JScrollPane();
		jobAssignScrollPane.setBounds(0, 0, 771, 376);
		jobAssignPnl.add(jobAssignScrollPane);

		expandableAssigns = new JPanel();
		jobAssignScrollPane.setViewportView(expandableAssigns);
		expandableAssigns.setLayout(new BoxLayout(expandableAssigns, BoxLayout.Y_AXIS));

		for (int jobAsgnC = 0; jobAsgnC < numOfAssigns; jobAsgnC++) {
			miniPanel.add(new JPanel());
			miniPanel.getLast().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			givenCmd.add(new JTextField());
			miniPanel.getLast().add(givenCmd.getLast());
			givenCmd.getLast().setColumns(10);

			isPeriodic.add(new JCheckBox("isPeriodic   "));
			miniPanel.getLast().add(isPeriodic.getLast());
			isPeriodic.getLast().setHorizontalAlignment(SwingConstants.CENTER);
			isPeriodic.getLast().setActionCommand("isPeriodic");
			isPeriodic.getLast().setHorizontalTextPosition(SwingConstants.LEADING);
			isPeriodic.getLast().setFont(new Font("Tahoma", Font.PLAIN, 14));

			periodEntry.add(new JTextField());
			miniPanel.getLast().add(periodEntry.getLast());
			periodEntry.getLast().setColumns(10);
			expandableAssigns.add(miniPanel.getLast());
		}

		btnSumbit = new JButton("Sumbit");
		btnSumbit.setBounds(581, 472, 200, 32);
		jobAssignmentTab.add(btnSumbit);

		saDropDownList = new JComboBox<String>();
		saDropDownList.setBounds(10, 472, 355, 32);
		jobAssignmentTab.add(saDropDownList);
		saDropDownList.setMaximumRowCount(10);
		
		JButton btnAddMore = new JButton("Add another job . . .");
		btnAddMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miniPanel.add(new JPanel());
				miniPanel.getLast().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				givenCmd.add(new JTextField());
				miniPanel.getLast().add(givenCmd.getLast());
				givenCmd.getLast().setColumns(10);
				isPeriodic.add(new JCheckBox("isPeriodic   "));
				miniPanel.getLast().add(isPeriodic.getLast());
				isPeriodic.getLast().setHorizontalAlignment(SwingConstants.CENTER);
				isPeriodic.getLast().setActionCommand("isPeriodic");
				isPeriodic.getLast().setHorizontalTextPosition(SwingConstants.LEADING);
				isPeriodic.getLast().setFont(new Font("Tahoma", Font.PLAIN, 14));
				periodEntry.add(new JTextField());
				miniPanel.getLast().add(periodEntry.getLast());
				periodEntry.getLast().setColumns(10);
				expandableAssigns.add(miniPanel.getLast());	
				expandableAssigns.revalidate();
			}
		});
		btnAddMore.setBounds(400, 472, 137, 32);
		jobAssignmentTab.add(btnAddMore);
		
		JLabel lblPleaseEnteThe = new JLabel("NMap parameters:");
		lblPleaseEnteThe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPleaseEnteThe.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPleaseEnteThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseEnteThe.setBounds(10, 60, 384, 26);
		jobAssignmentTab.add(lblPleaseEnteThe);
		
		JLabel lblNewLabel = new JLabel("Period of job (if periodic):");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBounds(404, 60, 377, 26);
		jobAssignmentTab.add(lblNewLabel);
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
				lastSASelectedToAssignJob = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();
				// System.out.println(lastSASelectedToAssignJob);
			}
		});
		btnSumbit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int tempi = 0;
				for (int assignsCounter = 0; assignsCounter < miniPanel.size(); assignsCounter++) {
					if (!givenCmd.get(assignsCounter).getText().equals("")) {
						if (isPeriodic.get(assignsCounter).isSelected()) {
							try {
								tempi = Integer.parseUnsignedInt(periodEntry.get(assignsCounter).getText());
							} catch (NumberFormatException nfe1) {
								// TODO msg insert proper period time
								System.out.println("Weird period");
								return;
							}
							lastSetPeriod = tempi;
						} else {
							lastSetPeriod = -1;
						}
						if (lastSASelectedToAssignJob == null) {
							// TODO msg no sa selected
							System.out.println("No SA selected.");
							return;
						}
						JobInsertionTab.assignJob(givenCmd.get(assignsCounter).getText(),
								isPeriodic.get(assignsCounter).isSelected(), lastSetPeriod, lastSASelectedToAssignJob);
					}
				}
			}
		});

		JPanel jobDeletionTab = new JPanel();
		jobDeletionTab.setBorder(null);
		adminPanelTabs.addTab("Job Deletion", null, jobDeletionTab, null);
		jobDeletionTab.setLayout(null);

		JLabel lblPleaseSelectThe = new JLabel(
				"Please select the Software Agent from which you wish to stop a periodic job:");
		lblPleaseSelectThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectThe.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPleaseSelectThe.setBounds(10, 20, 771, 26);
		jobDeletionTab.add(lblPleaseSelectThe);

		jdCB = new JComboBox<String>();
		jdCB.setBounds(134, 57, 525, 20);
		jdCB.setMaximumRowCount(10);
		delList = new JList<JobPrev>();
		jdCB.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				JobInsertionTab.addItemsToComboBox(jdCB);
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (lastSASelectedToDeleteJob != null) {
					delList.removeAll();
					JobDeletionTab.pupulateList(delList, lastSASelectedToDeleteJob);
				}
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Delete?
			}
		});
		jdCB.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				lastSASelectedToDeleteJob = (String) ((JComboBox<String>) e.getSource()).getSelectedItem();
				// System.out.println("Hmm " + lastSASelectedToDeleteJob);
			}
		});
		jobDeletionTab.add(jdCB);

		JScrollPane jDelScrollPane = new JScrollPane();
		jDelScrollPane.setBounds(10, 103, 771, 350);
		jobDeletionTab.add(jDelScrollPane);

		jDelScrollPane.setViewportView(delList);

		JButton btnClrSel = new JButton("Clear Selection/-s");
		btnClrSel.setBounds(124, 480, 170, 40);
		btnClrSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delList.clearSelection();
			}
		});
		jobDeletionTab.add(btnClrSel);

		JButton btnStopSelected = new JButton("Stop Selected");
		btnStopSelected.setBounds(499, 480, 170, 40);
		btnStopSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JobPrev curJP : delList.getSelectedValuesList()) {
					JobDeletionTab.stopJobOfSA(curJP);
				}
			}
		});
		jobDeletionTab.add(btnStopSelected);

		JPanel saSpecificResults = new JPanel();
		adminPanelTabs.addTab("SA Specific Results", null, saSpecificResults, null);
		saSpecificResults.setLayout(null);
		saSpecificResults.setBorder(null);
		
		JScrollPane scrollSASpecTextArea = new JScrollPane();
		scrollSASpecTextArea.setBounds(10, 134, 771, 400);
		saSpecificResults.add(scrollSASpecTextArea);

		txtSaResults = new JTextArea();
		scrollSASpecTextArea.setViewportView(txtSaResults);
		txtSaResults.setLineWrap(true);
		txtSaResults.setEditable(false);
		txtSaResults.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtSaResults.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtSaResults.setBackground(SystemColor.menu);

		JLabel label = new JLabel("Please select starting time:");
		label.setBounds(246, 28, 160, 39);
		saSpecificResults.add(label);

		JLabel label_1 = new JLabel("Please select ending time:");
		label_1.setBounds(444, 28, 160, 39);
		saSpecificResults.add(label_1);

		saSpecCB = new JComboBox<String>();
		saSpecCB.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SaResultsTab.populateSAWithResultsList(saSpecCB);
			}
		});
		saSpecCB.setBounds(10, 74, 200, 32);
		saSpecCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastSASelectedToShowRes = (String) saSpecCB.getSelectedItem();
			}
		});
		saSpecCB.setMaximumRowCount(10);
		
		saSpecificResults.add(saSpecCB);

		JLabel lblPleaseSelectA = new JLabel("Please select a Software Agent:");
		lblPleaseSelectA.setBounds(10, 40, 200, 14);
		saSpecificResults.add(lblPleaseSelectA);
				
		Date saspecSTDate = new Date();
		SpinnerDateModel saspecSTSDM = new SpinnerDateModel(saspecSTDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner saspecST = new JSpinner(saspecSTSDM);
		saspecST.setBounds(246, 74, 160, 32);
		DateEditor de_saspecST = new JSpinner.DateEditor(saspecST, "dd/MM/yyyy HH:mm:ss");
		saspecST.setEditor(de_saspecST);
		saSpecificResults.add(saspecST);
		
		Date saspecETDate = new Date();
		SpinnerDateModel saspecETSDM = new SpinnerDateModel(saspecETDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner saspecET = new JSpinner(saspecETSDM);
		saspecET.setBounds(444, 74, 160, 32);
		DateEditor de_saspecET = new JSpinner.DateEditor(saspecET, "dd/MM/yyyy HH:mm:ss");
		saspecET.setEditor(de_saspecET);
		saSpecificResults.add(saspecET);
		
		JButton btnShowResults = new JButton("Show Results");
		btnShowResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			SaResultsTab.populateSASpecificResultTextArea(txtSaResults ,lastSASelectedToShowRes);
			}
		});
		btnShowResults.setBounds(627, 40, 154, 66);
		saSpecificResults.add(btnShowResults);

		JPanel resultsTab = new JPanel();
		resultsTab.setBorder(null);
		adminPanelTabs.addTab("Results", null, resultsTab, null);
		resultsTab.setLayout(null);

		JTextArea txtResultsArea = new JTextArea();
		txtResultsArea.setBackground(new Color(240, 240, 240));
		txtResultsArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtResultsArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		txtResultsArea.setBounds(10, 134, 771, 400);
		resultsTab.add(txtResultsArea);

		JLabel lblPleaseSelectSTime = new JLabel("Please select starting time:");
		lblPleaseSelectSTime.setBounds(12, 28, 196, 39);
		resultsTab.add(lblPleaseSelectSTime);

		JLabel lblPleaseSelectETime = new JLabel("Please select ending time:");
		lblPleaseSelectETime.setBounds(284, 28, 196, 39);
		resultsTab.add(lblPleaseSelectETime);
		
		Date resSTDate = new Date();
		SpinnerDateModel resSTSDM = new SpinnerDateModel(resSTDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner resST = new JSpinner(resSTSDM);
		resST.setBounds(10, 78, 155, 32);
		DateEditor de_resST = new JSpinner.DateEditor(resST, "dd/MM/yyyy HH:mm:ss");
		resST.setEditor(de_resST);
		resultsTab.add(resST);
		
		Date resETDate = new Date();
		SpinnerDateModel resETSDM = new SpinnerDateModel(resETDate, null, null, Calendar.HOUR_OF_DAY);
		JSpinner resET = new JSpinner(resETSDM);
		resET.setBounds(284, 78, 155, 32);
		DateEditor de_resET = new JSpinner.DateEditor(resET, "dd/MM/yyyy HH:mm:ss");
		resET.setEditor(de_resET);
		resultsTab.add(resET);
		
		JButton btnNewButton = new JButton("Show Results");
		btnNewButton.setBounds(519, 78, 200, 32);
		resultsTab.add(btnNewButton);

		JPanel remoteTerminationTab = new JPanel();
		remoteTerminationTab.setBorder(null);
		adminPanelTabs.addTab("Remote Termination", null, remoteTerminationTab, null);
		remoteTerminationTab.setLayout(null);

		JPanel pm = new JPanel();
		pm.setBounds(184, 50, 420, 450);
		remoteTerminationTab.add(pm);
		pm.setLayout(null);

		JLabel lblPleaseSelectWhich = new JLabel(String.format("<html><dev WIDTH=%d>%s</div></html>", 250,
				"Please select which software agent you would like to terminate: "));
		lblPleaseSelectWhich.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPleaseSelectWhich.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPleaseSelectWhich.setBounds(83, 11, 257, 115);
		lblPleaseSelectWhich.setHorizontalAlignment(SwingConstants.CENTER);
		pm.add(lblPleaseSelectWhich);

		runningSADropDownlist = new JComboBox<String>();
		runningSADropDownlist.setBounds(0, 172, 417, 40);
		runningSADropDownlist.setMaximumRowCount(10);
		runningSADropDownlist.addPopupMenuListener(new PopupMenuListener() {
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				SATerminationTab.addItemsToComboBox(runningSADropDownlist);
			}

			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				if (lastSASelectedToDeleteJob != null) {
					delList.removeAll();
					JobDeletionTab.pupulateList(delList, lastSASelectedToDeleteJob);
				}
			}

			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Delete?
			}
		});
		pm.add(runningSADropDownlist);

		JButton btnTerminate = new JButton("Terminate");
		btnTerminate.setBounds(0, 332, 417, 40);
		btnTerminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SATerminationTab.stopSA((String) runningSADropDownlist.getSelectedItem());
			}
		});
		pm.add(btnTerminate);

		JLabel lblTerminationresult = new JLabel("");
		lblTerminationresult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTerminationresult.setBounds(0, 429, 427, 115);
		lblTerminationresult.setHorizontalAlignment(SwingConstants.CENTER);
		pm.add(lblTerminationresult);

		adminPanelTabs.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int currentIndex = ((JTabbedPane) e.getSource()).getSelectedIndex();
				switch (currentIndex) {
				case 0:
					smt.stop();
					break;
				case 1:
					smt.start();
					;
					break;
				case 2:
					smt.stop();
					;
					break;
				case 3:
					smt.stop();
					;
					break;
				case 4:
					smt.stop();
					;
					break;
				case 5:
					smt.stop();
					;
					break;
				case 6:
					smt.stop();
					;
					break;
				}
			}
		});

	}

	public CheckBoxList getPendingRegistrationsList() {
		return pendingRegistrationsList;
	}
}
