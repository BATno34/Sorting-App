package june_11;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel welcomeLabel = new JLabel("Welcome to the sorting app!");
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		welcomeLabel.setBounds(225, 100, 350, 50);
		frame.getContentPane().add(welcomeLabel);
		
		JButton functionOneButton = new JButton("Sort a text file");
		functionOneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortFile sortFile = new SortFile ();
				sortFile.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		functionOneButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		functionOneButton.setBounds(300, 200, 200, 50);
		frame.getContentPane().add(functionOneButton);
		
		JButton functionTwoButton = new JButton("Sorting Algorithms");
		functionTwoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortingAlgorithmsFunction functionTwo = new SortingAlgorithmsFunction ();
				functionTwo.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		functionTwoButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		functionTwoButton.setBounds(300, 300, 200, 50);
		frame.getContentPane().add(functionTwoButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Thank you for using this app!");
				frame.dispose();     //closes the frame
			}
		});
		quitButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		quitButton.setBounds(300, 400, 200, 50);
		frame.getContentPane().add(quitButton);
	}
}
