package june_11;

import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SortingAlgorithmsFunction extends JFrame {
	private JLabel lblBars = new JLabel();
	private JSlider slider;
	
	public JLabel[] bars = new JLabel[28];
	
	public static JLabel[] drawBars (JLabel[] bars, int numberOfBars, int width) {
		if(bars[0] != null) {
			Container parent = bars[0].getParent();
			for(int i = 0; i < bars.length; i++) {
				parent = bars[i].getParent();
				parent.remove(bars[i]);
				parent.validate();
			}
			parent.repaint();
		}
		
		bars = new JLabel [numberOfBars];
		for (int m = 0; m < numberOfBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			bars[m].setBackground(Color.ORANGE);
			int height = (int)(Math.random() * 200);
			bars[m].setBounds((m*(2+width)+(800-(numberOfBars*(width+2)))/2), (400 - height), width, height);
			contentPane.add(bars[m]);
		}
		
		return bars;
	}
	
	private static JPanel contentPane;
	public static ArrayList<Integer> barsHeight = new ArrayList<Integer>();
	public static int numBars = 10;

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
				int numBars = slider.getValue();
				lblBars.setText("Bars: "+numBars);
				
				bars = drawBars(bars, numBars, (600/numBars));
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
	}
}
