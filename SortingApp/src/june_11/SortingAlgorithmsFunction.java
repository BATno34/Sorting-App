package june_11;

/**
 * Function 2 of the app, allows the user to visualize various sorting algorithms
 * @author Ardavan, Shirley, Shreyas
 * @version June 11 2021
 */

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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SortingAlgorithmsFunction extends JFrame {
	//GUI components
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
	private static JFrame frame;
	private static JButton sortedListButton;
	private static JButton reverseListButton;
	private static JButton shuffleListButton;
	
	//bars for visualizing
	public static int numBars = 10;
	public static int width;
	public static JLabel[] bars = new JLabel[28];
	public static int [] barsHeight;
	public static int [] barsHeightCopy;
	
	public static int sortDelay = 500;
	
	public static AutoResize resize;
	
	/**
	 * generates a new array of bars and draws it on the screen
	 * @return the new array of JLabels (bars)
	 */
	public static JLabel[] drawBars () {
		if(bars[0] != null) { //if there are bars in the array
			for(int i = 0; i < bars.length; i++)
				contentPane.remove(bars[i]);
			
			contentPane.repaint(); //removes bars from the frame before redrawing
		}
		
		bars = new JLabel [numBars];
		barsHeight = new int [numBars];
		barsHeightCopy = new int [numBars];
		
		//generate new bar array
		for (int m = 0; m < numBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			bars[m].setBackground(Color.ORANGE);
			
			int height = (int)(Math.random() * 200) + 20; //randomize height of each bar between 20 and 220
			barsHeight[m] = height;
			barsHeightCopy[m] = height;
			resize = new AutoResize((m*(2+width)+(800-(numBars*(width+2)))/2), (400 - height), width, height, 0, 800, 600, contentPane.getWidth(), contentPane.getHeight());
			
			bars[m].setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
			contentPane.add(bars[m]);
		}
		
		return bars;
	}
	
	/**
	 * when a sorting method is underway, redraw the array at each step of sorting
	 * @param comparingBars, an ArrayList of the indexes of bars that are currently being compared
	 * @param sortedBars, an ArrayList of the indexes of bars that have already been sorted
	 * @param pivotBar, an ArrayList of the pivot index (only for quicksort)
	 */
	public static void drawSortedBars (ArrayList<Integer> comparingBars, ArrayList<Integer> sortedBars) {

		ArrayList<Integer> emptyList = new ArrayList<Integer>();
		drawSortedBars(comparingBars, sortedBars, emptyList);
		
	}
	
	/**
	 * when a sorting method is underway, redraw the array at each step of sorting
	 * @param comparingBars, an ArrayList of the indexes of bars that are currently being compared
	 * @param sortedBars, an ArrayList of the indexes of bars that have already been sorted
	 * @param pivotBar, an ArrayList of the pivot index (only for quicksort)
	 */
	public static void drawSortedBars (ArrayList<Integer> comparingBars, ArrayList<Integer> sortedBars, ArrayList<Integer> pivotBar) {

		for(int i = 0; i < bars.length; i++)
			contentPane.remove(bars[i]); //remove bars from frame so that they can be redrawn
		
		for (int m = 0; m < numBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			
			if (pivotBar.contains(m)) { //already sorted
				bars[m].setBackground(Color.GREEN);
			} else if (comparingBars.indexOf(m) != -1){ //being compared (for insertion)
				bars[m].setBackground(Color.BLACK); 
			} else if (sortedBars.contains(m)) { //already sorted
				bars[m].setBackground(Color.BLUE);
			} else if (comparingBars.indexOf(m) != -1){ //being compared
				bars[m].setBackground(Color.BLACK);
			} else {
				bars[m].setBackground(Color.ORANGE); //all other bars
			}
			resize = new AutoResize((m*(2+width)+(800-(numBars*(width+2)))/2), (400 - barsHeight[m]), width, barsHeight[m], 0, 800, 600, contentPane.getWidth(), contentPane.getHeight());
			bars[m].setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
			contentPane.add(bars[m]);
		}
		
		contentPane.repaint(); //repaint frame
	}

	/**
	 * when the sort is complete, set components as visible and repaint all bars as blue
	 */
	public static void endOfSort(){
		for(int i = 0; i<bars.length; i++) {//recolour bars as blue
			Container parent = bars[i].getParent();
			bars[i].setBackground(Color.BLUE);
			parent.repaint();
			parent.validate();
		}
		
		//set components on frame as interactable/visible
		frame.setResizable(true);
		sortButton.setVisible(true);
		resize = new AutoResize(10, 11, 507, 26, 0, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		slider.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
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
					frame = new SortingAlgorithmsFunction();
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
		//main frame setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				contentPane.removeAll();
				contentPane.repaint();
				addComponent();
			}
		});
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	/**
	 * add components to the frame
	 */
	public static void addComponent() {
		//bars slider
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				numBars = slider.getValue();
				lblBars.setText("Bars: "+numBars);
				width = (600 / numBars);
				bars = drawBars();
			}
		});
		slider.setValue(28);
		slider.setMajorTickSpacing(1);
		slider.setMaximum(50);
		slider.setMinimum(5);
		resize = new AutoResize(10, 11, 507, 26, 0, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		slider.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(slider);
		
		//label for number of bars
		resize = new AutoResize (541, 6, 200, 31, 14, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		lblBars.setFont(new Font("Times New Roman", Font.PLAIN, resize.newFontSize()));
		lblBars.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(lblBars);
		
		sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//set frame components as not visible or not interactable
				frame.setResizable(false);
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
				
				//determine type of sort to be performed
				Thread sortingThread = new Thread();
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("BUBBLE SORT")) { //bubble sort
					sortingThread = new Thread(new BubbleSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				else if (comboBox.getSelectedItem().toString().toUpperCase().equals("INSERTION SORT")) { //insertion sort
					sortingThread = new Thread(new InsertionSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				else if (comboBox.getSelectedItem().toString().toUpperCase().equals("MERGE SORT")) { //merge sort
					sortingThread = new Thread(new MergeSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				else if (comboBox.getSelectedItem().toString().toUpperCase().equals("SELECTION SORT")) { //selection sort
					sortingThread = new Thread(new SelectionSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted.");
				}
				else if (comboBox.getSelectedItem().toString().toUpperCase().equals("QUICK SORT")) { //quick sort
					sortingThread = new Thread(new QuickSort(barsHeight));
					lblColourExplanation.setText("Yellow: Not sorted. Black: Being compared. Blue: Already sorted. Green: Current pivot.");
				}
				sortingThread.start();
			}
		});
		resize = new AutoResize (73, 450, 102, 33, 15, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		sortButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, resize.newFontSize()));
		sortButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(sortButton);
		
		//return to previous frame 
		toReturn = new JButton("Return");
		toReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu mainMenu = new MainMenu();
				frame.setVisible(false);
				mainMenu.frame.setVisible(true);
			}
		});
		resize = new AutoResize (73, 495, 102, 33, 15, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		toReturn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, resize.newFontSize()));
		toReturn.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(toReturn);
		
		//combo box for choosing type of sort
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bars = drawBars();
			}
		});
		resize = new AutoResize (541, 88, 200, 26, 12, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, resize.newFontSize()));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"}));
		comboBox.setMaximumRowCount(5);
		comboBox.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(comboBox);
		
		// some labels
		lblChooseSortType = new JLabel();
		lblChooseSortType.setText("Choose Sort Type:");
		resize = new AutoResize (541, 46, 200, 31, 14, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		lblChooseSortType.setFont(new Font("Times New Roman", Font.PLAIN, resize.newFontSize()));
		lblChooseSortType.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(lblChooseSortType);
		
		lblSpeed = new JLabel();
		lblSpeed.setText("Sorting Delay (ms): 500");
		resize = new AutoResize (317, 84, 200, 31, 14, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		lblSpeed.setFont(new Font("Times New Roman", Font.PLAIN, resize.newFontSize()));
		lblSpeed.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(lblSpeed);
		
		//slider for choosing sort delay
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
		resize = new AutoResize (10, 88, 297, 26, 0, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		sliderSpeed.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(sliderSpeed);
		
		//labels for colour explanations
		lblColours = new JLabel();
		lblColours.setText("Colours:");
		lblColours.setVerticalAlignment(SwingConstants.TOP);
		resize = new AutoResize (107, 433, 588, 26, 14, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		lblColours.setFont(new Font("Times New Roman", Font.PLAIN, resize.newFontSize()));
		lblColours.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(lblColours);
		lblColours.setVisible(false);
		
		lblColourExplanation = new JLabel("");
		resize = new AutoResize (107, 450, 594, 26, 14, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		lblColourExplanation.setFont(new Font("Times New Roman", Font.PLAIN, resize.newFontSize()));
		lblColourExplanation.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(lblColourExplanation);
		
		//buttons for changing order of array
		sortedListButton = new JButton("Show Sorted List");
		sortedListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//sort array
				Arrays.sort(barsHeight);
				ArrayList<Integer> emptyList = new ArrayList<Integer>();
				drawSortedBars(emptyList, emptyList);
			}
		});
		resize = new AutoResize (469, 433, 210, 29, 11, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		sortedListButton.setFont(new Font("Tahoma", Font.BOLD, resize.newFontSize()));
		sortedListButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(sortedListButton);
		
		reverseListButton = new JButton("Show Reversed List");
		reverseListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {//reverse array
				Arrays.sort(barsHeight);
				int[] copyBarsHeight = barsHeight.clone();
				for (int i = copyBarsHeight.length - 1; i >= 0; i--) {
					barsHeight[copyBarsHeight.length - 1 - i] = copyBarsHeight[i];
				}
				ArrayList<Integer> emptyList = new ArrayList<Integer>();
				drawSortedBars(emptyList, emptyList);
								
			}
		});
		resize = new AutoResize (469, 471, 210, 29, 11, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		reverseListButton.setFont(new Font("Tahoma", Font.BOLD, resize.newFontSize()));
		reverseListButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(reverseListButton);
		
		shuffleListButton = new JButton("Show Shuffled List");
		shuffleListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //shuffle array
				List<Integer> intList = new ArrayList<Integer>(barsHeight.length);
				for (int i : barsHeight) {
				    intList.add(i);
				}
				Collections.shuffle(intList);
				for (int i = 0; i < intList.size(); i++) {
					barsHeight[i] = intList.get(i);
				}
				ArrayList<Integer> emptyList = new ArrayList<Integer>();
				drawSortedBars(emptyList, emptyList);
			}
		});
		resize = new AutoResize (469, 512, 210, 29, 11, 800, 600, contentPane.getWidth(), contentPane.getHeight());
		shuffleListButton.setFont(new Font("Tahoma", Font.BOLD, resize.newFontSize()));
		shuffleListButton.setBounds(resize.newX(), resize.newY(), resize.newWidth(), resize.newHeight());
		contentPane.add(shuffleListButton);
		lblColourExplanation.setVisible(false);
	}
}
