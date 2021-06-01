package june_11;

import java.util.ArrayList;

import javax.swing.JLabel;

public class QuickSort implements Runnable{
	public static int [] barsHeight;
	
	public QuickSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}
	
	@Override
	public void run() {
 	   	quickSort(0, barsHeight.length - 1);
	}

	private void quickSort(int start, int end) {
		int temp;
		ArrayList<Integer> pivotIndex = new ArrayList<Integer>();
		ArrayList<Integer> startEndIndexes = new ArrayList<Integer>(2);
		//Take the middle element as pivot
		int middle = start + (end - start) / 2;
		int pivot = barsHeight[middle];
		pivotIndex.add(middle);
		startEndIndexes.add(start);
		startEndIndexes.add(end);
		//Make the left side all numbers smaller than the pivot
		//and the right side all numbers larger than the pivot
		int i = start;
		int j = end;
		while (i <= j) {
			while (barsHeight[i] < pivot) {
				i++;
			}
			while (barsHeight[j] > pivot) {
				j--;
			}
			
			SortingAlgorithmsFunction.drawSortedBars(startEndIndexes,pivotIndex);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//If an element is positioned at the right side of the pivot
			//but is smaller than the pivot is swapped with the element 
			//one the left but larger than the pivot
			if (i <= j) {
				if (i == middle) {
					pivotIndex.set(0, j);
				} else if (j == middle) {
					pivotIndex.set(0, i);
				}
				temp = barsHeight[i];
				barsHeight[i] = barsHeight[j];
				barsHeight[j] = temp;
				i++;
				j--;
			}
			
			SortingAlgorithmsFunction.drawSortedBars(startEndIndexes,pivotIndex);
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
	
	private void quickSort2(int start, int end) {
		int temp;
		ArrayList<Integer> pivotIndex = new ArrayList<Integer>();
		ArrayList<Integer> swappingIndexes = new ArrayList<Integer>(2);
		//Take the middle element as pivot
		int middle = start + (end - start) / 2;
		int pivot = barsHeight[middle];
		pivotIndex.add(middle);
		//Make the left side all numbers smaller than the pivot
		//and the right side all numbers larger than the pivot
		int i = start;
		int j = end;
		while (i <= j) {
			while (barsHeight[i] < pivot) {
				i++;
			}
			while (barsHeight[j] > pivot) {
				j--;
			}
			
			swappingIndexes.add(i);
			swappingIndexes.add(j);
			SortingAlgorithmsFunction.drawSortedBars(swappingIndexes,pivotIndex);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//If an element is positioned at the right side of the pivot
			//but is smaller than the pivot is swapped with the element 
			//one the left but larger than the pivot
			if (i <= j) {
				if (i == middle) {
					pivotIndex.set(0, j);
				} else if (j == middle) {
					pivotIndex.set(0, i);
				}
				temp = barsHeight[i];
				barsHeight[i] = barsHeight[j];
				barsHeight[j] = temp;
				//SortingAlgorithmsFunction.drawSortedBars(importantIndexes,sortedIndexes);
				i++;
				j--;
			}
			
			swappingIndexes.clear();
			swappingIndexes.add(i-1);
			swappingIndexes.add(j+1);
			SortingAlgorithmsFunction.drawSortedBars(swappingIndexes,pivotIndex);
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
