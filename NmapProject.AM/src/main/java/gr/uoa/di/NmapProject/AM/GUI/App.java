package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gr.uoa.di.NmapProject.AM.DB.AdminDAO;
import gr.uoa.di.NmapProject.AM.Server.Server;

/**
 * The main application for the GUI of the A.M..
 * 
 * @author George
 *
 */
public class App {
	
	private Server server;
	private Login loginFrame;
	private AdminPanel adminPanel;
	private Scanner in = new Scanner(System.in);
	
	/**
	 * Runs the main GUI application.
	 */
	public void run(){
		startServer();
		startOrExit();
	}
	
	/**
	 * Display continue dialog
	 */
	public void startOrExit(){
		System.out.println("Type 'start' to open  Admin Panel or anything else to exit");
		String input = in.nextLine();
		if(input.equals("start")){
			startGUI();
		}else{
			exit();
		}
	}
	
	/**
	 * Press Enter to close Server
	 */
	public void enterToExit(){
		
		System.out.println("Press Enter to close the Server");
//		String input = in.nextLine();
		exit();
	}
	
	/**
	 * Start GUI
	 */
	public void startGUI(){
		try{
			startLoginFrame();
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Starts the server
	 */
	private void startServer(){
		server = new Server("http://0.0.0.0:8080/am/");
		server.start();
	}
	/**
	 * Creates the login frame
	 */
	private void startLoginFrame() throws Exception{
		loginFrame = new Login();
		
//		Client client = ClientBuilder.newClient();
		
		loginFrame.loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				
				String username = loginFrame.userText.getText();
				String password = new String(loginFrame.passwordText.getPassword());
				
				if(AdminDAO.authenticate(username, password)){
					loginFrame.dispose();
					loginFrame = null;
					startAdminPanel();
					
				}
				else{
					JOptionPane.showMessageDialog(source , "Authentication Failed");
					loginFrame.userText.setText("");
					loginFrame.passwordText.setText("");
				}
			}
		});
		
		loginFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(loginFrame, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	loginFrame.dispose();
		        	loginFrame = null;
		        	enterToExit();
		        }
		    }
		});
		
		loginFrame.getRootPane().setDefaultButton(loginFrame.loginButton);

	}
	
	/**
	 * Creates the Admin Panel after loggin in
	 */
	private void startAdminPanel() {
		adminPanel = new AdminPanel();
		adminPanel.setVisible(true);
		
		adminPanel.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	//closethreadstatus
		    	adminPanel.dispose();
		    	adminPanel = null;
		    	enterToExit();
		    }
		});
	}
	
	/**
	 * Stop the server and quits
	 */
	private void exit(){
		server.stop();
		System.exit(0);
	}
}
