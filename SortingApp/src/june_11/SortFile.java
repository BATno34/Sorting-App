package june_11;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class SortFile {

	private JFrame frame;
	private ArrayList<String> listOfLines;
	private JLabel fileLabel;

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
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				//chooser.setFileFilter(new FileNameExtensionFilter("txt"));
				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(filePath);
					try {
			            FileReader fr = new FileReader(file);
			            BufferedReader br = new BufferedReader(fr);
			            listOfLines = new ArrayList<>(); 
			            String line = br.readLine(); 
			            while (line != null) { 
			            	listOfLines.add(line); 
			            	line = br.readLine(); 
			            } 
			            br.close();
			            fileLabel.setText("File Selected: " + filePath);
					} catch (FileNotFoundException ex) {
			        	JOptionPane.showMessageDialog(null, "File Not Found");
			        } catch (IOException e1) {
			        	JOptionPane.showMessageDialog(null, "Error");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No File Selected");
				}
			}
		});
		openFileButton.setBounds(6, 12, 89, 23);
		frame.getContentPane().add(openFileButton);
		
		JButton printFileButton = new JButton("Print File");
		printFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i=0; i<listOfLines.size(); i++) {
					System.out.println(listOfLines.get(i));
				}
			}
		});
		printFileButton.setBounds(6, 80, 89, 23);
		frame.getContentPane().add(printFileButton);
		
		JButton saveFileButton = new JButton("Save File");
		saveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int response = chooser.showSaveDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File file = new File(chooser.getSelectedFile().getAbsolutePath());
					try {
						FileWriter fw = new FileWriter(file);
				        BufferedWriter bw = new BufferedWriter(fw);
				        for (int i=0; i<listOfLines.size(); i++) {
				        	bw.write(listOfLines.get(i));
				        	bw.newLine();
				        }
			            bw.close();
					} catch (FileNotFoundException ex) {
			        	JOptionPane.showMessageDialog(null, "File Not Found");
			        } catch (IOException e1) {
			        	JOptionPane.showMessageDialog(null, "Error");
					}
				} else {
					JOptionPane.showMessageDialog(null, "No File Selected");
				}
			}
		});
		saveFileButton.setBounds(6, 153, 89, 23);
		frame.getContentPane().add(saveFileButton);
		
		fileLabel = new JLabel("No File Selected");
		fileLabel.setBounds(128, 16, 239, 14);
		frame.getContentPane().add(fileLabel);
		
		
		
	}
}
