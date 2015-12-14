package gr.uoa.di.NmapProject.AM.GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7888681097028785305L;

	public MainFrame(){
		setTitle("Admin Panel");

		setSize(1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		add(panel);
		
		setVisible(true);
	}
	
}
