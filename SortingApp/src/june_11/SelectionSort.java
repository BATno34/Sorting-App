package june_11;

import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SelectionSort implements Runnable{
	
	public static int [] barsHeight;
	
	public SelectionSort(int[] barsHeight) {
		
		this.barsHeight = barsHeight;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		selectionSort();
	}
	
	public void selectionSort() {
		int n = barsHeight.length;
		ArrayList<Integer> comparingBars = new ArrayList<Integer>();
		ArrayList<Integer> sortedList = new ArrayList<Integer>();
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++) {
                if (barsHeight[j] < barsHeight[min_idx]) {
                	try { 
                		Thread.sleep(100);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
                	//comparingBars.add(j);
                	//comparingBars.add(min_idx);
                	SortingAlgorithmsFunction.drawSortedBars(comparingBars, sortedList);
                    min_idx = j;
                    //comparingBars.remove(comparingBars.indexOf(j));
                    //comparingBars.remove(comparingBars.indexOf(min_idx));
                }
                
            }
            //comparingBars.remove(comparingBars.indexOf(min_idx));
  
            // Swap the found minimum element with the first
            // element
            int temp = barsHeight[min_idx];
            barsHeight[min_idx] = barsHeight[i];
            barsHeight[i] = temp;
            sortedList.add(i);
            try { 
        		Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            SortingAlgorithmsFunction.drawSortedBars(comparingBars, sortedList);
            
        }
        
		SortingAlgorithmsFunction.endOfSort();
		JOptionPane.showMessageDialog(null, "Sorting complete!");
	}

}
