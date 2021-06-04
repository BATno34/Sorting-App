package june_11;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * The animation of using quick sort method to sort bars
 * @author Ardavan, Shirley, Shreyas
 * @version June 11 2021
 */
public class QuickSort implements Runnable{
	public static int [] barsHeight;
	ArrayList<Integer> pivotIndex = new ArrayList<Integer>();
	
	/** Contructor
	 * @param barsHeight		The int array that contains the bars' height to sort 
	 */
	public QuickSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	/**
	 * When a thread starts, this method will be called so that the animation could run.
	 * Override from implementing Runnable
	 */
	@Override
	public void run() {
 	   	quickSort(0, barsHeight.length - 1);
 	   	SortingAlgorithmsFunction.endOfSort();
		JOptionPane.showMessageDialog(null, "Sorting complete!");
	}

	/** Uses quick sort algorithm to sort a list in ascending order
	 * @param start				The start index of the list to sort (inclusive)
	 * @param end				The end index of the list to sort (inclusive)
	 */
	private void quickSort(int start, int end) {
		int temp;
		ArrayList<Integer> startEndIndexes = new ArrayList<Integer>(2);
		ArrayList<Integer> swappingIndexes = new ArrayList<Integer>(2);
		//Take the middle element as pivot
		int middle = start + (end - start) / 2;
		int pivot = barsHeight[middle];
		pivotIndex.add(middle);
		
		//Make the left side all numbers smaller than the pivot
		//and the right side all numbers larger than the pivot
		int i = start;
		int j = end;
		//Display comparison
		swappingIndexes.add(i);
		swappingIndexes.add(j);
		SortingAlgorithmsFunction.drawSortedBars(swappingIndexes, pivotIndex);
		pause();
		
		while (i <= j) {
			while (barsHeight[i] < pivot) {
				i++;
				swappingIndexes.set(0, i);
				SortingAlgorithmsFunction.drawSortedBars(swappingIndexes, pivotIndex);
				pause();
			}
			while (barsHeight[j] > pivot) {
				j--;
				swappingIndexes.set(1, j);
				SortingAlgorithmsFunction.drawSortedBars(swappingIndexes, pivotIndex);
				pause();
			}
			
			//If an element is positioned at the right side of the pivot
			//but is smaller than the pivot is swapped with the element 
			//one the left but larger than the pivot
			if (i <= j) {
				if (i == pivotIndex.get(pivotIndex.size()-1)) {
					pivotIndex.set(pivotIndex.size()-1, j);
				} else if (j == pivotIndex.get(0)) {
					pivotIndex.set(pivotIndex.size()-1, i);
				}
				
				//Display the swapping
				temp = barsHeight[i];
				barsHeight[i] = barsHeight[j];
				barsHeight[j] = temp;
				swappingIndexes.set(0, i);
				swappingIndexes.set(1, j);
				i++;
				j--;
			}
			SortingAlgorithmsFunction.drawSortedBars(swappingIndexes, pivotIndex);
			pause();
		}
 
		//recursively sort two sub parts
		if (start < j) {
			quickSort(start, j);
		}
		if (end > i) {
			quickSort(i, end);
		}
		
		
	}
	
	/**
	 * Add a delay when running the animation
	 */
	public static void pause() {
		try {
			Thread.sleep(SortingAlgorithmsFunction.sortDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
