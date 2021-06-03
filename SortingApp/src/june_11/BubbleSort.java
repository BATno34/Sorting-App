package june_11;

import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BubbleSort implements Runnable{
	public static int [] barsHeight;
	
	public BubbleSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		bubbleSort();
	}
	
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
