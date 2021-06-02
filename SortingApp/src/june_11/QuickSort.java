package june_11;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class QuickSort implements Runnable{
	public static int [] barsHeight;
	ArrayList<Integer> pivotIndex = new ArrayList<Integer>();
	
	public QuickSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	@Override
	public void run() {
 	   	quickSort(0, barsHeight.length - 1);
 	   	SortingAlgorithmsFunction.endOfSort();
		JOptionPane.showMessageDialog(null, "Sorting complete!");
	}

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
	
	public static void pause() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
