package june_11;

import java.awt.*;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class SortingAlgorithmsFunction extends JFrame {
	
	public static void drawBars (int numberOfBars, int width) {
		JLabel [] bars = new JLabel [numberOfBars];
		for (int m = 0; m < numberOfBars; m++) {
			bars[m] = new JLabel ("");
			bars[m].setOpaque(true);
			bars[m].setBackground(Color.ORANGE);
			int height = (int)(Math.random() * 200);
			bars[m].setBounds((m*(20+width)+100), (400 - height), width, height);
			contentPane.add(bars[m]);
		}
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
		
		drawBars(numBars,(600 - 20 * (numBars - 1))/numBars);
	}
}
