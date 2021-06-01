package june_11;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SortingAlgorithmsFunction extends JFrame {
	
	private static JLabel lblBars = new JLabel();
	private static JSlider slider;
	private static JButton sortButton;
	private static JButton toReturn;
	private static JComboBox comboBox;
	private static JLabel lblChooseSortType;
	private static JPanel contentPane;
	public static int numBars = 10;
	public static int width;
	public static JLabel[] bars = new JLabel[28];
	public static int [] barsHeight;
	public static int [] barsHeightCopy;
	
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
		comboBox.setVisible(true);
		lblChooseSortType.setVisible(true);
		lblBars.setVisible(true);
		toReturn.setVisible(true);
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
		slider.setBounds(10, 11, 521, 26);
		contentPane.add(slider);
		
		lblBars.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBars.setBounds(541, 6, 200, 31);
		contentPane.add(lblBars);
		
		sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortButton.setVisible(false);
				slider.setBounds(10, 11, 0, 26);
				comboBox.setVisible(false);
				lblChooseSortType.setVisible(false);
				lblBars.setVisible(false);
				toReturn.setVisible(false);
				Thread sortingThread = new Thread();
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("BUBBLE SORT"))
					sortingThread = new Thread(new BubbleSort(barsHeight));
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("INSERTION SORT"))
					sortingThread = new Thread(new InsertionSort(barsHeight));
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("MERGE SORT"))
					sortingThread = new Thread(new MergeSort(barsHeight));
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("SELECTION SORT"))
					sortingThread = new Thread(new SelectionSort(barsHeight));
				if (comboBox.getSelectedItem().toString().toUpperCase().equals("QUICK SORT"))
					sortingThread = new Thread(new QuickSort(barsHeight));
				sortingThread.start();
			}
		});
		sortButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		sortButton.setBounds(250, 450, 100, 50);
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
		toReturn.setBounds(450, 450, 100, 50);
		contentPane.add(toReturn);
		
		comboBox = new JComboBox();
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
	}
}
