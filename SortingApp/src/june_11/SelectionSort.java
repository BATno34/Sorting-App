package june_11;

import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * The animation of using selection sort method to sort bars
 * @author Ardavan, Shirley, Shreyas
 * @version June 11 2021
 */
public class SelectionSort implements Runnable{
	
	public static int [] barsHeight;
	
	/** Contructor
	 * @param barsHeight		The int array that contains the bars' height to sort 
	 */
	public SelectionSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	/**
	 * When a thread starts, this method will be called so that the animation could run
	 * Override from implementing Runnable
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		selectionSort();
	}
	
	/** 
	 * Uses selection sort algorithm to sort a list in ascending order
	 */
	public void selectionSort() {
		int n = barsHeight.length;
		ArrayList<Integer> comparingBars = new ArrayList<Integer>();
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            comparingBars.add(i);
            for (int j = i+1; j < n; j++) {
                if (barsHeight[j] < barsHeight[min_idx]) {
                	try { 
                		Thread.sleep((SortingAlgorithmsFunction.sortDelay));
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
                    min_idx = j;
                    comparingBars.add(min_idx);
                	SortingAlgorithmsFunction.drawSortedBars(comparingBars, sortedList);
                	comparingBars.remove(comparingBars.indexOf(min_idx));
                }
                
            }
            
  
            // Swap the found minimum element with the first
            // element
            int temp = barsHeight[min_idx];
            barsHeight[min_idx] = barsHeight[i];
            barsHeight[i] = temp;
            sortedList.add(i);
            comparingBars.remove(comparingBars.indexOf(i));
            try { 
        		Thread.sleep(SortingAlgorithmsFunction.sortDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            SortingAlgorithmsFunction.drawSortedBars(comparingBars, sortedList);
            //nothing
        }
        
		SortingAlgorithmsFunction.endOfSort();
		JOptionPane.showMessageDialog(null, "Sorting complete!");
	}

}
