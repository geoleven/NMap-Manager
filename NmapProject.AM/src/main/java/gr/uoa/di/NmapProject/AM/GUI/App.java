package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	/**
	 * Runs the main GUI application.
	 */
	public void run(){
		startServer();
		startLoginFrame();
	}
	
	private void startServer(){
		server = new Server("http://localhost:8080/am/");
		server.start();
	}
	
	private void startLoginFrame(){
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
		            exit();
		        }
		    }
		});
		
		loginFrame.getRootPane().setDefaultButton(loginFrame.loginButton);

	}
	
	
	private void startAdminPanel() {
	adminPanel = new AdminPanel();
	adminPanel.setVisible(true);
	
	adminPanel.addWindowListener(new java.awt.event.WindowAdapter() {
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	    	//closethreadstatus
	        exit();
	    }
	});
}
	

	private void exit(){
		server.stop();
		System.exit(0);
	}
}
