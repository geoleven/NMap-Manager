package gr.uoa.di.NmapProject.AM.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * This class holds the GUI for the first mini-panel for the administrator log in.
 * @author George
 *
 */
@SuppressWarnings("javadoc")
public class Login extends JFrame{
	
	private static final long serialVersionUID = 5759641117023243983L;
	
	public JTextField userText;
	
	public JButton loginButton;
	
	public JPasswordField passwordText;
	
	/**
	 * Creates the login mini-panel.
	 */
	public Login() {
		
		setTitle("Admin Login");

		setSize(300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		placeComponents(panel);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void placeComponents(JPanel panel) {

		panel.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		panel.add(passwordText);

		loginButton = new JButton("Login");
		loginButton.setBounds(100, 76, 80, 25);
		panel.add(loginButton);
		
		
	}

}
