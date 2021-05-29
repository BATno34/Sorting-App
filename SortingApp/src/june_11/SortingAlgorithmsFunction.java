package june_11;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SortingAlgorithmsFunction extends JFrame {
	
	private JLabel lblBars = new JLabel();
	private JSlider slider;
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
	
	public static void drawSortedBars () {
		Container parent = bars[0].getParent();
		for(int i = 0; i < bars.length; i++) {
			parent = bars[i].getParent();
			parent.remove(bars[i]);
			parent.validate();
		}
		
		for (int m = 0; m < numBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			bars[m].setBackground(Color.ORANGE);
			bars[m].setBounds((m*(2+width)+(800-(numBars*(width+2)))/2), (400 - barsHeight[m]), width, barsHeight[m]);
			contentPane.add(bars[m]);
		}
	
        parent.repaint();
        parent.validate();
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
		
		JButton sortButton = new JButton("Sort");
		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread sortingThread = new Thread(new BubbleSort(bars, barsHeight));
				sortingThread.start();
			}
		});
		sortButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		sortButton.setBounds(610, 48, 91, 23);
		contentPane.add(sortButton);
		
		
	}
}
