package june_11;

import java.util.ArrayList;

import javax.swing.JLabel;

public class QuickSort implements Runnable{
	public static JLabel[] bars;
	public static int [] barsHeight;
	
	public QuickSort(JLabel[] bars, int[] barsHeight) {
		this.bars = bars;
		this.barsHeight = barsHeight;
	}
	
	@Override
	public void run() {
 	   	quickSort(0, barsHeight.length - 1);
	}

	private void quickSort(int start, int end) {
		int temp;
		ArrayList<Integer> pivotIndex = new ArrayList<Integer>();
		ArrayList<Integer> importantIndexes = new ArrayList<Integer>(2);
		//Take the middle element as pivot
		int middle = start + (end - start) / 2;
		int pivot = barsHeight[middle];
		pivotIndex.add(middle);
		//Make the left side of the pivot all numbers smaller than it
		//and the right side all numbers larger than it
		int i = start;
		int j = end;
		while (i <= j) {
			while (barsHeight[i] < pivot) {
				i++;
			}
			while (barsHeight[j] > pivot) {
				j--;
			}
			
			importantIndexes.add(i);
			importantIndexes.add(j);
			SortingAlgorithmsFunction.drawSortedBars(importantIndexes,pivotIndex);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//An element is positioned at the right side of the pivot
			//but is smaller than the pivot is swapped with the element 
			//one the left but larger than the pivot
			if (i <= j) {
				temp = barsHeight[i];
				barsHeight[i] = barsHeight[j];
				barsHeight[j] = temp;
				//SortingAlgorithmsFunction.drawSortedBars(importantIndexes,sortedIndexes);
				i++;
				j--;
			}
			
			importantIndexes.clear();
			importantIndexes.add(i-1);
			importantIndexes.add(j+1);
			SortingAlgorithmsFunction.drawSortedBars(importantIndexes,pivotIndex);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
 
		//recursively sort two sub parts
		if (start < j) {
			quickSort(start, j);
		}
		if (end > i) {
			quickSort(i, end);
		}
	}
}
