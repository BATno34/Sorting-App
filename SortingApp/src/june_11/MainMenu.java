package june_11;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainMenu {

	public static JFrame frame;
	public static AutoResize resize;

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
		frame.getContentPane().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().repaint();
				addComponent();
			}
		});
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	public static void addComponent() {
		resize = new AutoResize (225, 100, 350, 50, 30, 800, 600, frame.getWidth(), frame.getHeight());
		JLabel welcomeLabel = new JLabel("Welcome to the sorting app!");
		welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, resize.newFontSize()));
		welcomeLabel.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		frame.getContentPane().add(welcomeLabel);
		
		resize = new AutoResize (300, 200, 200, 50, 20, 800, 600, frame.getWidth(), frame.getHeight());
		JButton functionOneButton = new JButton("Sort a text file");
			functionOneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SortFile sortFile = new SortFile ();
					sortFile.frame.setVisible(true);
					frame.setVisible(false);
				}
			});
		functionOneButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, resize.newFontSize()));
		functionOneButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		frame.getContentPane().add(functionOneButton);
		
		resize = new AutoResize (300, 300, 200, 50, 20, 800, 600, frame.getWidth(), frame.getHeight());
		JButton functionTwoButton = new JButton("Sorting Algorithms");
			functionTwoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SortingAlgorithmsFunction functionTwo = new SortingAlgorithmsFunction ();
					frame.setVisible(false);
					functionTwo.main(null);
				}
			});
		functionTwoButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, resize.newFontSize()));
		functionTwoButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		frame.getContentPane().add(functionTwoButton);
		
		resize = new AutoResize (300, 400, 200, 50, 20, 800, 600, frame.getWidth(), frame.getHeight());
		JButton quitButton = new JButton("Quit");
			quitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Thank you for using this app!");
					frame.dispose();     //closes the frame
				}
			});
		quitButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, resize.newFontSize()));
		quitButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		frame.getContentPane().add(quitButton);
	}
}
