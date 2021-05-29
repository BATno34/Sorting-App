package june_11;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class TestObjectTwo {

	private JFrame frame;
	public static int numBars = 5;
	public static int [] barsHeight = new int [numBars];
	public static int [] barsHeightCopy = barsHeight;
	
	/**
	 * Sorts a List in ascending order (lowest to highest) using the bubble sort
	 * algorithm
	 * @param list the List to sort
	 */
	private static void bubbleSort(DrawShape object)
	{
		int swaps;
		int copy;
		int turn = 0;
		do {
			swaps = 0;
			for (int i = 0; i < (numBars - turn - 1); i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (barsHeight[i + 1] < barsHeight[i]) {
					copy = barsHeightCopy[i];
					barsHeight[i] = barsHeight[i+1];
					barsHeight[i+1] = copy;
					barsHeightCopy = barsHeight;
					object.heightOfBars = barsHeight;
					object.drawing();
					swaps++;     //counting swaps
				}
			}
			turn++;
		} while (swaps != 0);     //when there was no swap, it means that it's sorted
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				try {
					TestObjectTwo window = new TestObjectTwo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		//});
	}

	/**
	 * Create the application.
	 */
	public TestObjectTwo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Test Object TWo");
		frame.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int m = 0; m < numBars; m++) {
			barsHeight [m] = (int)(Math.random() * 200) + 10;
			System.out.println(barsHeight[m]);
		}
		
		DrawShape object = new DrawShape(numBars, barsHeight);
		frame.add(object);
		object.drawing();
		bubbleSort(object);
	}

}
