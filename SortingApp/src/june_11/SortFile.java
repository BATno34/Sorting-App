package june_11;

import java.awt.EventQueue;
import java.awt.Font;
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
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.text.Segment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SortFile {

	public static JFrame frame;
	private static ArrayList<String> originalList = new ArrayList<>();
	private static ArrayList<String> outputList = new ArrayList<>();
	private static ArrayList<Double> doubleOutputList = new ArrayList<>();
	private static ArrayList<Integer> intOutputList = new ArrayList<>();
	private JLabel fileLabel;
	private JLabel saveLabel;
	private static JRadioButton orderRdBtn;
	private static JRadioButton reverseRdBtn;
	private static JRadioButton shuffleRdBtn;
	private static JTextArea inputTextArea;
	private static JTextArea outputTextArea;
	private static JRadioButton doubleRdBtn;
	private static JRadioButton stringRdBtn;
	private JLabel inputLable;
	private JLabel outputLabel;
	private static JRadioButton intRdBtn;
	private JButton clearScreen;
	private static JButton toReturn;

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
		frame.setBounds(100, 100, 1200, 650);
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
					clearScreen();
					String filePath = chooser.getSelectedFile().getAbsolutePath();
					File file = new File(filePath);
					try {
			            FileReader fr = new FileReader(file);
			            BufferedReader br = new BufferedReader(fr);
			            String line2 = br.readLine(); 
			            while (line2 != null) { 
			            	inputTextArea.append(line2 + "\n");
			            	line2 = br.readLine(); 
			            } 
			            br.close();
			            fileLabel.setText("File Selected: " + filePath);
			            originalList = readFromTextArea(inputTextArea, originalList);
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
				        	outputList.clear();
				    		String string[] = outputTextArea.getText().split("\n");
				            Collections.addAll(outputList, string);
					        if (doubleRdBtn.isSelected() && orderRdBtn.isSelected() ||
					        	doubleRdBtn.isSelected() && reverseRdBtn.isSelected()) {
					        	doubleOutputList.clear();
					        	doubleOutputList = toDoubleArrayList(outputList);
					        	for (int i=0; i<doubleOutputList.size(); i++) {
						        	bw.write(doubleOutputList.get(i).toString());
						        	bw.newLine();
						        }
					        } else if (intRdBtn.isSelected() && orderRdBtn.isSelected() ||
					        	   	   intRdBtn.isSelected() && reverseRdBtn.isSelected()) {
					        	intOutputList.clear();
					        	intOutputList = toIntArrayList(outputList);
					        	for (int i=0; i<intOutputList.size(); i++) {
						        	bw.write(intOutputList.get(i).toString());
						        	bw.newLine();
						        }
					        } else {
						        for (int i=0; i<outputList.size(); i++) {
						        	bw.write(outputList.get(i));
						        	bw.newLine();
						        }
					        }
				            bw.close();
				    		saveLabel.setText("File saved: " + filePath);
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
		saveFileButton.setBounds(26, 543, 89, 23);
		frame.getContentPane().add(saveFileButton);
		
		fileLabel = new JLabel("No File Selected");
		fileLabel.setBounds(149, 27, 1025, 14);
		frame.getContentPane().add(fileLabel);
		
		saveLabel = new JLabel("File Not Saved");
		saveLabel.setBounds(149, 546, 1025, 14);
		frame.getContentPane().add(saveLabel);
		
		inputTextArea = new JTextArea();
		inputTextArea.setLineWrap(true);
		inputTextArea.setEditable(true);
		JScrollPane inputScroll = new JScrollPane (inputTextArea, 
												   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
												   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScroll.setSize(500, 292);
		inputScroll.setLocation(50, 162);
		frame.getContentPane().add(inputScroll);
		
		outputTextArea = new JTextArea();
		outputTextArea.setLineWrap(true);
		outputTextArea.setEditable(false);
		JScrollPane outputScroll = new JScrollPane (outputTextArea, 
												   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
												   JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputScroll.setSize(500, 292);
		outputScroll.setLocation(650, 162);
		frame.getContentPane().add(outputScroll);
		
		orderRdBtn = new JRadioButton("Sort in ascending order");
		orderRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (orderRdBtn.isSelected()) {
	            	reverseRdBtn.setSelected(false);
	            	shuffleRdBtn.setSelected(false);
	            } else {
	            	clearTA(outputTextArea);
	            }
			}
		});
		orderRdBtn.setBounds(26, 101, 234, 23);
		frame.getContentPane().add(orderRdBtn);
		
		reverseRdBtn = new JRadioButton("Sort in descending order");
		reverseRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (reverseRdBtn.isSelected()) {
	            	orderRdBtn.setSelected(false);
	            	shuffleRdBtn.setSelected(false);
	            } else {
	            	clearTA(outputTextArea);
	            }
			}
		});
		reverseRdBtn.setBounds(321, 101, 242, 23);
		frame.getContentPane().add(reverseRdBtn);
		
		shuffleRdBtn = new JRadioButton("Shuffle");
		shuffleRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (shuffleRdBtn.isSelected()) {
	            	orderRdBtn.setSelected(false);
	            	reverseRdBtn.setSelected(false);
	            } else {
	            	clearTA(outputTextArea);
	            }
			}
		});
		shuffleRdBtn.setBounds(629, 101, 114, 23);
		frame.getContentPane().add(shuffleRdBtn);
		
		JLabel inputTypeLabel = new JLabel("Choose an input type:");
		inputTypeLabel.setBounds(36, 64, 224, 16);
		frame.getContentPane().add(inputTypeLabel);
		
		doubleRdBtn = new JRadioButton("Double");
		doubleRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (doubleRdBtn.isSelected()) {
					stringRdBtn.setSelected(false);
					intRdBtn.setSelected(false);
				}
			}
		});
		doubleRdBtn.setBounds(220, 64, 114, 23);
		frame.getContentPane().add(doubleRdBtn);
		
		stringRdBtn = new JRadioButton("String (or a mix of String and numbers)");
		stringRdBtn.setBounds(475, 64, 319, 23);
		stringRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (stringRdBtn.isSelected()) {
					doubleRdBtn.setSelected(false);
					intRdBtn.setSelected(false);
				}
			}
		});
		frame.getContentPane().add(stringRdBtn);
		
		intRdBtn = new JRadioButton("Int");
		intRdBtn.setBounds(346, 64, 114, 23);
		intRdBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (intRdBtn.isSelected()) {
					doubleRdBtn.setSelected(false);
					stringRdBtn.setSelected(false);
				}
			}
		});
		frame.getContentPane().add(intRdBtn);
		
		inputLable = new JLabel("File Input:");
		inputLable.setBounds(50, 143, 181, 16);
		frame.getContentPane().add(inputLable);
		
		outputLabel = new JLabel("Output:");
		outputLabel.setBounds(650, 143, 61, 16);
		frame.getContentPane().add(outputLabel);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				originalList = readFromTextArea(inputTextArea, originalList);
				if (orderRdBtn.isSelected()) {
					ascendingSort();
				} else if (reverseRdBtn.isSelected()) {
					descendingSort();
				} else if (shuffleRdBtn.isSelected()) {
					shuffleSort();
				} else {
					JOptionPane.showMessageDialog(null, "Please select a sorting method");
				}
			}
		});
		updateButton.setBackground(Color.WHITE);
		updateButton.setBounds(149, 489, 234, 23);
		frame.getContentPane().add(updateButton);
		
		toReturn = new JButton("Return");
		toReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mainMenu = new MainMenu();
				frame.dispose();
				mainMenu.frame.setVisible(true);
			}
		});
		toReturn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		toReturn.setBounds(550, 550, 100, 50);
		frame.getContentPane().add(toReturn);
		
		clearScreen = new JButton("Clear Screen");
		clearScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearScreen();
			}
		});
		clearScreen.setBackground(Color.WHITE);
		clearScreen.setBounds(800, 489, 234, 23);
		frame.getContentPane().add(clearScreen);
		
		
		
	}
	
	public static ArrayList<Double> toDoubleArrayList (ArrayList<String> list){
		ArrayList<Double> doubleList = new ArrayList<>();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).length()!=0) {
					doubleList.add(Double.parseDouble(list.get(i)));
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Input Type");
			clearSortRdBtns();
			clearTypeRdBtns();
		}
		return doubleList;
	}
	
	public static ArrayList<Integer> toIntArrayList (ArrayList<String> list){
		ArrayList<Integer> intList = new ArrayList<>();
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).length()!=0) {
					intList.add(Integer.parseInt(list.get(i)));
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid Input Type");
			clearSortRdBtns();
			clearTypeRdBtns();
		}
		return intList;
	}
	
	public static void printOutputTA(ArrayList<String> list, JTextArea textArea) {
		clearTA(textArea);
    	for (String s : list) {
    		textArea.append(s + "\n");
        }
	}
	
	
	public static void doublePrintOutputTA(ArrayList<Double> list, JTextArea textArea) {
		clearTA(textArea);
    	for (Double d : list) {
    		textArea.append(d.toString() + "\n");
        }
	}
	
	public static void intPrintOutputTA(ArrayList<Integer> list, JTextArea textArea) {
		clearTA(textArea);
    	for (int i : list) {
    		textArea.append(Integer.toString(i) + "\n");
        }
	}
	
	public static void clearTA(JTextArea textArea) {
		textArea.selectAll();
		textArea.replaceSelection("");
	}
	
	public static void clearSortRdBtns() {
		orderRdBtn.setSelected(false);
    	reverseRdBtn.setSelected(false);
		shuffleRdBtn.setSelected(false);
	}
	
	public static void clearTypeRdBtns() {
		doubleRdBtn.setSelected(false);
		intRdBtn.setSelected(false);
    	stringRdBtn.setSelected(false);
	}
	
	public static void clearScreen() {
        clearTA(inputTextArea);
        clearTA(outputTextArea);
        originalList.clear();
        outputList.clear();
        doubleOutputList.clear();
        intOutputList.clear();
        clearSortRdBtns();
		clearTypeRdBtns();
	}
	
	public static ArrayList<String> readFromTextArea(JTextArea textArea, ArrayList<String> list) {
		list.clear();
		String s[] = textArea.getText().split("\n");
        Collections.addAll(list, s);
        return list;
	}
	
	public static void ascendingSort() {
		if (originalList.size()<1) {
			System.out.println(originalList.size());
			JOptionPane.showMessageDialog(null, "No Content to Sort");
			clearSortRdBtns();
		} else if (doubleRdBtn.isSelected()==false && intRdBtn.isSelected()==false && stringRdBtn.isSelected()==false){
			JOptionPane.showMessageDialog(null, "Please select a file type");
		} else {
        	outputList = new ArrayList <String>();
        	outputList.addAll(originalList);
        	if (doubleRdBtn.isSelected()) {
        		doubleOutputList = toDoubleArrayList(outputList);
        		Collections.sort(doubleOutputList);
				doublePrintOutputTA(doubleOutputList, outputTextArea);
        	} else if (intRdBtn.isSelected()) {
        		intOutputList = toIntArrayList(outputList);
        		Collections.sort(intOutputList);
				intPrintOutputTA(intOutputList, outputTextArea);
        	} else {
        		Collections.sort(outputList);
				printOutputTA(outputList, outputTextArea);
        	}
		}
	}
	
	public static void descendingSort() {
		if (originalList.size()<1) {
			JOptionPane.showMessageDialog(null, "No Content to Sort");
			clearSortRdBtns();
		} else if (doubleRdBtn.isSelected()==false && intRdBtn.isSelected()==false && stringRdBtn.isSelected()==false){
			JOptionPane.showMessageDialog(null, "Please select a file type");
		} else {
        	outputList = new ArrayList <String>();
        	outputList.addAll(originalList);
        	if (doubleRdBtn.isSelected()) {
        		doubleOutputList = toDoubleArrayList(outputList);
        		Collections.sort(doubleOutputList, Collections.reverseOrder());
				doublePrintOutputTA(doubleOutputList, outputTextArea);
        	} else if (intRdBtn.isSelected()) {
        		intOutputList = toIntArrayList(outputList);
        		Collections.sort(intOutputList, Collections.reverseOrder());
				intPrintOutputTA(intOutputList, outputTextArea);
        	} else {
        		Collections.sort(outputList, Collections.reverseOrder());
				printOutputTA(outputList, outputTextArea);
        	}
		}
	}
	
	public static void shuffleSort() {
		if (originalList.size()<1) {
			JOptionPane.showMessageDialog(null, "No Content to Sort");
			clearSortRdBtns();
		} else if (doubleRdBtn.isSelected()==false && intRdBtn.isSelected()==false && stringRdBtn.isSelected()==false){
			JOptionPane.showMessageDialog(null, "Please select a file type");
		} else {
        	outputList = new ArrayList <String>();
        	outputList.addAll(originalList);
			Collections.shuffle(outputList);
			printOutputTA(outputList, outputTextArea);
        }
	}
}
