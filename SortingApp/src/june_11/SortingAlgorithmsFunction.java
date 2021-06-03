package june_11;

import java.util.*;
import java.util.List;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Collections;

public class SortingAlgorithmsFunction extends JFrame {
	
	private static JLabel lblBars = new JLabel();
	private static JSlider slider;
	private static JSlider sliderSpeed;
	private static JButton sortButton;
	private static JButton toReturn;
	private static JComboBox comboBox;
	private static JLabel lblChooseSortType;
	private static JLabel lblSpeed;
	private static JLabel lblColours;
	private static JLabel lblColourExplanation;
	private static JPanel contentPane;
	
	public static int numBars = 10;
	public static int width;
	public static JLabel[] bars = new JLabel[28];
	public static int [] barsHeight;
	public static int [] barsHeightCopy;
	
	public static int sortDelay = 500;
	private static JButton sortedListButton;
	private static JButton reverseListButton;
	private static JButton shuffleListButton;
	
	public static JLabel[] drawBars () {
		if(bars[0] != null) {
			Container parent = bars[0].getParent();
			for(int i = 0; i < bars.length; i++) {
				parent = bars[i].getParent();
				parent.remove(bars[i]);
				parent.validate();
			}
			parent.repaint();
		}
		
		bars = new JLabel [numBars];
		barsHeight = new int [numBars];
		barsHeightCopy = new int [numBars];
		for (int m = 0; m < numBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			bars[m].setBackground(Color.ORANGE);
			int height = (int)(Math.random() * 200) + 20;
			barsHeight[m] = height;
			barsHeightCopy[m] = height;
			bars[m].setBounds((m*(2+width)+(800-(numBars*(width+2)))/2), (400 - height), width, height);
			contentPane.add(bars[m]);
		}
		
		return bars;
	}
	
	
	public static void drawSortedBars (ArrayList<Integer> comparingBars, ArrayList<Integer> sortedBars) {
		Container parent = bars[0].getParent();
		for(int i = 0; i < bars.length; i++) {
			parent = bars[i].getParent();
			parent.remove(bars[i]);
			parent.validate();
		}
		
		for (int m = 0; m < numBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			if (sortedBars.contains(m)) {
				bars[m].setBackground(Color.BLUE);
			} else if (comparingBars.indexOf(m) != -1){
				bars[m].setBackground(Color.BLACK);
			} else {
				bars[m].setBackground(Color.ORANGE);
			}
			bars[m].setBounds((m*(2+width)+(800-(numBars*(width+2)))/2), (400 - barsHeight[m]), width, barsHeight[m]);
			contentPane.add(bars[m]);
		}
	
        parent.repaint();
        parent.validate();
	}

	public static void endOfSort(){
		for(int i = 0; i<bars.length; i++) {
			Container parent = bars[i].getParent();
			bars[i].setBackground(Color.BLUE);
			parent.repaint();
			parent.validate();
		}
		
		sortButton.setVisible(true);
		slider.setBounds(10, 11, 521, 26);
		comboBox.setEnabled(true);
		lblChooseSortType.setVisible(true);
		lblBars.setVisible(true);
		lblColours.setVisible(false);
		lblColourExplanation.setVisible(false);
		toReturn.setVisible(true);
		sortedListButton.setVisible(true);
		reverseListButton.setVisible(true);
		shuffleListButton.setVisible(true);
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortingAlgorithmsFunction frame = new SortingAlgorithmsFunction();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SortingAlgorithmsFunction() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				numBars = slider.getValue();
				lblBars.setText("Bars: "+numBars);
				width = 600 / numBars;
				bars = drawBars();
			}
		});
		slider.setValue(28);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(50);
		slider.setMinimum(5);
		slider.setBounds(10, 11, 507, 26);
		contentPane.add(slider);
		
		lblBars.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBars.setBounds(541, 6, 200, 31);
		contentPane.add(lblBars);
		
		sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortButton.setVisible(false);
				slider.setBounds(10, 11, 0, 26);
				comboBox.setEnabled(false);
				lblChooseSortType.setVisible(false);
				lblBars.setVisible(false);
				lblColours.setVisible(true);
				lblColourExplanation.setVisible(true);
				toReturn.setVisible(false);
				sortedListButton.setVisible(false);
				reverseListButton.setVisible(false);
				shuffleListButton.setVisible(false);
								
				Thread sortingThread = new Thread();
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("BUBBLE SORT")) {
					sortingThread = new Thread(new BubbleSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("INSERTION SORT")) {
					sortingThread = new Thread(new InsertionSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("MERGE SORT")) {
					sortingThread = new Thread(new MergeSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("SELECTION SORT")) {
					sortingThread = new Thread(new SelectionSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("QUICK SORT")) {
					sortingThread = new Thread(new QuickSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Pivot and already sorted.");
				}
				sortingThread.start();
			}
		});
		sortButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		sortButton.setBounds(73, 450, 102, 33);
		contentPane.add(sortButton);
		
		toReturn = new JButton("Return");
		toReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mainMenu = new MainMenu();
				dispose();
				mainMenu.frame.setVisible(true);
			}
		});
		toReturn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		toReturn.setBounds(73, 495, 102, 33);
		contentPane.add(toReturn);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bars = drawBars();
			}
		});
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"}));
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(541, 88, 200, 26);
		contentPane.add(comboBox);
		
		lblChooseSortType = new JLabel();
		lblChooseSortType.setText("Choose Sort Type:");
		lblChooseSortType.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblChooseSortType.setBounds(541, 46, 200, 31);
		contentPane.add(lblChooseSortType);
		
		lblSpeed = new JLabel();
		lblSpeed.setText("Sorting Delay (ms): 500");
		lblSpeed.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblSpeed.setBounds(317, 84, 200, 31);
		contentPane.add(lblSpeed);
		
		sliderSpeed = new JSlider();
		sliderSpeed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sortDelay = sliderSpeed.getValue();
				lblSpeed.setText("Sorting Delay (ms): "+sortDelay);
			}
		});
		sliderSpeed.setMinimum(10);
		sliderSpeed.setValue(500);
		sliderSpeed.setMaximum(1000);
		sliderSpeed.setMajorTickSpacing(10);
		sliderSpeed.setBounds(10, 88, 297, 26);
		contentPane.add(sliderSpeed);
		
		lblColours = new JLabel();
		lblColours.setText("Colours:");
		lblColours.setVerticalAlignment(SwingConstants.TOP);
		lblColours.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblColours.setBounds(107, 433, 588, 26);
		contentPane.add(lblColours);
		lblColours.setVisible(false);
		
		lblColourExplanation = new JLabel("");
		lblColourExplanation.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblColourExplanation.setBounds(107, 450, 594, 26);
		contentPane.add(lblColourExplanation);
		
		sortedListButton = new JButton("Show Sorted List");
		sortedListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(barsHeight);
				ArrayList<Integer> emptyList = new ArrayList<Integer>();
				drawSortedBars(emptyList, emptyList);
			}
		});
		sortedListButton.setBounds(469, 433, 210, 29);
		contentPane.add(sortedListButton);
		
		reverseListButton = new JButton("Show Reversed List");
		reverseListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arrays.sort(barsHeight);
				int[] copyBarsHeight = barsHeight.clone();
				for (int i = copyBarsHeight.length - 1; i >= 0; i--) {
					barsHeight[copyBarsHeight.length - 1 - i] = copyBarsHeight[i];
				}
				ArrayList<Integer> emptyList = new ArrayList<Integer>();
				drawSortedBars(emptyList, emptyList);
								
			}
		});
		reverseListButton.setBounds(469, 471, 210, 29);
		contentPane.add(reverseListButton);
		
		shuffleListButton = new JButton("Show Shuffled List");
		shuffleListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Integer> intList = new ArrayList<Integer>(barsHeight.length);
				for (int i : barsHeight) {
				    intList.add(i);
				}
				Collections.shuffle(intList);
				for (int i = 0; i < intList.size() - 1; i++) {
					barsHeight[i] = intList.get(i);
				}
				ArrayList<Integer> emptyList = new ArrayList<Integer>();
				drawSortedBars(emptyList, emptyList);
			}
		});
		shuffleListButton.setBounds(469, 512, 210, 29);
		contentPane.add(shuffleListButton);
		lblColourExplanation.setVisible(false);
	}
}
