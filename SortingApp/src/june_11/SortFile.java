package june_11;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Color;

public class SortFile {

	public static JFrame frame;
	private ArrayList<String> originalList = new ArrayList<>();
	private ArrayList<String> outputList;
	private JLabel fileLabel;
	private JLabel saveLabel;
	private JRadioButton orderRdBtn;
	private JRadioButton reverseRdBtn;
	private JRadioButton shuffleRdBtn;
	private JTextArea inputTextArea;
	private JTextArea outputTextArea;
	

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
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton openFileButton = new JButton("Open File");
		openFileButton.setBackground(Color.WHITE);
		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLabel.setText("File Not Saved");
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(filePath);
					try {
			            FileReader fr = new FileReader(file);
			            BufferedReader br = new BufferedReader(fr);
			            String line = br.readLine(); 
			            while (line != null) { 
			            	originalList.add(line); 
			            	line = br.readLine(); 
			            } 
			            br.close();
			            fileLabel.setText("File Selected: " + filePath);
			            for (String s : originalList) {
			            	inputTextArea.append(s.toString() + "\n");
			            }
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
		openFileButton.setBounds(26, 23, 89, 23);
		frame.getContentPane().add(openFileButton);
		
		JButton saveFileButton = new JButton("Save File");
		saveFileButton.setBackground(Color.WHITE);
		saveFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (originalList.size()<1) {
					JOptionPane.showMessageDialog(null, "No Content to Sort");
				} else if (orderRdBtn.isSelected()==false && reverseRdBtn.isSelected()==false 
						   && shuffleRdBtn.isSelected()==false){
					JOptionPane.showMessageDialog(null, "Please select an option to sort");
				} else {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("."));
					int response = chooser.showSaveDialog(null);
					if (response == JFileChooser.APPROVE_OPTION) {
						String filePath = chooser.getSelectedFile().getAbsolutePath();
						File file = new File(filePath);
						try {
							FileWriter fw = new FileWriter(file);
					        BufferedWriter bw = new BufferedWriter(fw);
					        for (int i=0; i<outputList.size(); i++) {
					        	bw.write(outputList.get(i));
					        	bw.newLine();
					        }
				            bw.close();
				            saveLabel.setText("File saved: " + filePath);
				            clearTA(inputTextArea);
				            clearTA(outputTextArea);
				            originalList.clear();
				            outputList.clear();
				            fileLabel.setText("No File Selected");
						} catch (FileNotFoundException ex) {
				        	JOptionPane.showMessageDialog(null, "File Not Found");
				        } catch (IOException e1) {
				        	JOptionPane.showMessageDialog(null, "Error");
						}
					} else {
						JOptionPane.showMessageDialog(null, "No File Selected");
					}
				}
				
			}
		});
		saveFileButton.setBounds(26, 517, 89, 23);
		frame.getContentPane().add(saveFileButton);
		
		fileLabel = new JLabel("No File Selected");
		fileLabel.setBounds(149, 27, 609, 14);
		frame.getContentPane().add(fileLabel);
		
		saveLabel = new JLabel("File Not Saved");
		saveLabel.setBounds(149, 521, 609, 14);
		frame.getContentPane().add(saveLabel);
		
		inputTextArea = new JTextArea();
		inputTextArea.setLineWrap(true);
		inputTextArea.setEditable(false);
		JScrollPane inputScroll = new JScrollPane (inputTextArea, 
												   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
												   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScroll.setSize(350, 383);
		inputScroll.setLocation(24, 100);
		frame.getContentPane().add(inputScroll);
		
		outputTextArea = new JTextArea();
		outputTextArea.setLineWrap(true);
		outputTextArea.setEditable(false);
		JScrollPane outputScroll = new JScrollPane (outputTextArea, 
												   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
												   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputScroll.setSize(350, 383);
		outputScroll.setLocation(408, 100);
		frame.getContentPane().add(outputScroll);
		
		orderRdBtn = new JRadioButton("Sort in ascending order");
		orderRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orderRdBtn.isSelected()) {
					if (originalList.size()<1) {
						JOptionPane.showMessageDialog(null, "No Content to Sort");
					} else {
		            	outputList = new ArrayList <String>();
		            	outputList.addAll(originalList);
		            	reverseRdBtn.setSelected(false);
		            	shuffleRdBtn.setSelected(false);
		            	Collections.sort(outputList);
						printOutputTA(outputList, outputTextArea);
					}
	            } else {
	            	clearTA(outputTextArea);
	            }
			}
		});
		orderRdBtn.setBounds(26, 60, 234, 23);
		frame.getContentPane().add(orderRdBtn);
		
		reverseRdBtn = new JRadioButton("Sort in descending order");
		reverseRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reverseRdBtn.isSelected()) {
					if (originalList.size()<1) {
						JOptionPane.showMessageDialog(null, "No Content to Sort");
					} else {
		            	outputList = new ArrayList <String>();
		            	outputList.addAll(originalList);
		            	orderRdBtn.setSelected(false);
		            	shuffleRdBtn.setSelected(false);
						Collections.sort(outputList, Collections.reverseOrder());
						printOutputTA(outputList, outputTextArea);
					}
	            } else {
	            	clearTA(outputTextArea);
	            }
			}
		});
		reverseRdBtn.setBounds(325, 60, 242, 23);
		frame.getContentPane().add(reverseRdBtn);
		
		shuffleRdBtn = new JRadioButton("Shuffle");
		shuffleRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shuffleRdBtn.isSelected()) {
					if (originalList.size()<1) {
						JOptionPane.showMessageDialog(null, "No Content to Sort");
					} else {
		            	outputList = new ArrayList <String>();
		            	outputList.addAll(originalList);
		            	orderRdBtn.setSelected(false);
		            	reverseRdBtn.setSelected(false);
						Collections.shuffle(outputList);
						printOutputTA(outputList, outputTextArea);
					}
	            } else {
	            	clearTA(outputTextArea);
	            }
			}
		});
		shuffleRdBtn.setBounds(630, 60, 114, 23);
		frame.getContentPane().add(shuffleRdBtn);
		
		
		
	}
	
	public static void printOutputTA(ArrayList<String> list, JTextArea textArea) {
		clearTA(textArea);
    	for (String s : list) {
    		textArea.append(s.toString() + "\n");
        }
	}
	
	public static void clearTA (JTextArea textArea) {
		textArea.selectAll();
		textArea.replaceSelection("");
	}
}
