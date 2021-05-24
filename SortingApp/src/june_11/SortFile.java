package june_11;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SortFile {

	private JFrame frame;
	private JFileChooser chooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortFile window = new SortFile();
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
	public SortFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton openFileButton = new JButton("Open File");
		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = chooser.getSelectedFile();
				String filename = f.getAbsolutePath();
				//JTextField1.setText(filename);
			}
		});
		openFileButton.setBounds(6, 12, 117, 29);
		frame.getContentPane().add(openFileButton);
		
		chooser = new JFileChooser();
		chooser.setBounds(124, 6, 200, 42);
		frame.getContentPane().add(chooser);
		chooser.setCurrentDirectory(new File("c:\\temp"));
		//chooser.setFileFilter(new FileNameExtensionFilter ("txt"));
		
		
	}
}
