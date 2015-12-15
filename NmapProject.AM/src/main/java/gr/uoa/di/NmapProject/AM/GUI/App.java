package gr.uoa.di.NmapProject.AM.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;

import gr.uoa.di.NmapProject.AM.DB.AdminDAO;
import gr.uoa.di.NmapProject.AM.Server.Server;

public class App {
	
	private Server server;
	private Login loginFrame;
//	private MainFrame mainFrame;
	private AdminPanel adminPanel;
	
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
					//startMainFrame();
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
		
		loginFrame.passwordText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("lalala");
					loginFrame.loginButton.doClick();
				}
			}
		});
		
		loginFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("lalaloooo");
					loginFrame.loginButton.doClick();
				}
			}
		});
		

	}
	
//	private void startMainFrame(){
//		mainFrame = new MainFrame();
//		
//		mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
//		    @Override
//		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
//		        exit();
//		    }
//		});
//	}
	
	private void startAdminPanel() {
	adminPanel = new AdminPanel();
	adminPanel.setVisible(true);
	
	adminPanel.addWindowListener(new java.awt.event.WindowAdapter() {
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
