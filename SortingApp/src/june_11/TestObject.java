package june_11;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class TestObject {
	
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
					System.out.println("array");
					object.drawing();
					swaps++;     //counting swaps
				}
			}
			turn++;
		} while (swaps != 0);     //when there was no swap, it means that it's sorted
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Test Object");
		frame.setVisible(true);
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
