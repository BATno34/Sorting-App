package june_11;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InsertionSort implements Runnable{
	public static int [] barsHeight;
	
	public InsertionSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	@Override
	public void run() {
 	   	insertionSort();
	}

	private void insertionSort() {
		 int temp;
		 int[]barsHeightCopy = barsHeight;
		 ArrayList<Integer> sortedIndexes = new ArrayList<Integer>();
		 sortedIndexes.add(0);
		 
		 for (int i = 1; i < barsHeight.length; i++) {			 
			 temp = barsHeight[i];
			 int j = i - 1;
			 
			 //Display initial comparing
			 ArrayList<Integer> importantIndexes = new ArrayList<Integer>(2);
			 importantIndexes.add(i);
			 importantIndexes.add(j);
			 SortingAlgorithmsFunction.drawSortedBars(importantIndexes,sortedIndexes);
			 try {
				 Thread.sleep(100);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
		   	 }
			 
			 //Find place to insert and update with colouring
			 while ((j > -1) && ((barsHeight[j] - temp) > 0)) {
				  int copy = barsHeightCopy[j+1];
				  barsHeightCopy[j+1] = barsHeightCopy[j];
				  barsHeightCopy[j] = copy;
				  importantIndexes.set(1,j);
				  SortingAlgorithmsFunction.drawSortedBars(importantIndexes,sortedIndexes);           	   
				  j--;
				  try { 
					Thread.sleep(100);
				  } catch (InterruptedException e) {
					e.printStackTrace();
				  }
			 }
			 
			 //Update the section that is considered "sorted part"
	         barsHeight = barsHeightCopy;
	         barsHeight[j+1] = temp;
	  		 sortedIndexes.add(i);
		 }
		 SortingAlgorithmsFunction.endOfSort();
		 JOptionPane.showMessageDialog(null, "Sorting complete!");
	}
	

}
