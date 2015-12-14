package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gr.uoa.di.NmapProject.AM.DB.AdminDAO;
import gr.uoa.di.NmapProject.AM.Server.Server;

public class App {
	
	private Server server;
	private Login loginFrame;
	private MainFrame mainFrame;
	
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
		
		loginFrame.loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton source = (JButton) e.getSource();
				
				String username = loginFrame.userText.getText();
				String password = new String(loginFrame.passwordText.getPassword());
				
				if(AdminDAO.authenticate(username, password)){
					loginFrame.dispose();
					loginFrame = null;
					startMainFrame();
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
	}
	
	private void startMainFrame(){
		mainFrame = new MainFrame();
		
		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        exit();
		    }
		});
	}

	private void exit(){
		server.stop();
		System.exit(0);
	}
}
