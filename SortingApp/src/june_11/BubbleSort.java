package june_11;

import java.util.*;
import javax.swing.JOptionPane;

/**
 * The animation of using bubble sort method to sort bars
 * @author Ardavan, Shirley, Shreyas
 * @version June 11 2021
 */
public class BubbleSort implements Runnable{
	public static int [] barsHeight;
	
	/** Contructor
	 * @param barsHeight		The int array that contains the bars' height to sort 
	 */
	public BubbleSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	/**
	 * When a thread starts, this method will be called so that the animation could run
	 * Override from implementing Runnable
	 */
	@Override
	public void run() {
		bubbleSort();
	}
	
	/** 
	 * Uses bubble sort algorithm to sort a list in ascending order
	 */
	public void bubbleSort() {
		int swaps;
		int copy;
		int turn = 0;
		int[]barsHeightCopy = barsHeight;
		ArrayList<Integer> sortedIndexes = new ArrayList<Integer>();
		do {
			swaps = 0;
			
	    	for (int i = 0; i < (barsHeight.length - turn - 1); i++) {
	    		ArrayList<Integer> importantIndexes = new ArrayList<Integer>(2);
    			importantIndexes.add(i);
    			importantIndexes.add(i+1);
    			SortingAlgorithmsFunction.drawSortedBars(importantIndexes,sortedIndexes);
    			try {
    				Thread.sleep(SortingAlgorithmsFunction.sortDelay);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			
    			//Swap if current bar height is larger than the next one
	    		if (barsHeight[i+1] < barsHeight[i]) {	    			
					copy = barsHeightCopy[i];
					barsHeight[i] = barsHeight[i+1];
					barsHeight[i+1] = copy;
					barsHeightCopy = barsHeight;
					importantIndexes.set(0, i);
					importantIndexes.set(1, i+1);
					SortingAlgorithmsFunction.drawSortedBars(importantIndexes,sortedIndexes);
					try {
						Thread.sleep(SortingAlgorithmsFunction.sortDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					swaps++;     //counting swaps
				}
			}
	    	sortedIndexes.add(barsHeight.length - turn - 1);
			turn++;
	    } while (swaps!=0);   //when there was no swap, it means that it's sorted
		SortingAlgorithmsFunction.endOfSort();
		
		JOptionPane.showMessageDialog(null, "Sorting complete!");
	}

}
