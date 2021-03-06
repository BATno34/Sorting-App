package june_11;

/**
 * The animation of using merge sort method to sort bars
 * @author Ardavan, Shirley, Shreyas
 * @version June 11 2021
 */

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MergeSort implements Runnable {
	public static int [] barsHeight;
	
	/**
	 * constructor for a MergeSort object/thread
	 * @param barsHeight, the array of heights of the bars
	 */
	public MergeSort(int[] barsHeight) {
		this.barsHeight = barsHeight;
	}

	/**
	 * override from implementing Runnable
	 */
	public void run() {
		mergeSort(0, barsHeight.length-1);
	}

	/**
	 * merges two subarrays of an array (for merge sort)
	 * @param lo, the lowest index of the part of the array to be sorted
	 * @param mid, the mid point of the part of the array to be sorted
	 * @param hi, the highest index of the part of the array to be sorted
	 */
	private void merge(int lo, int mid, int hi) {
		ArrayList<Integer> mergedSorted = new ArrayList<Integer>();
		ArrayList<Integer> important = new ArrayList<Integer>();
		ArrayList<Integer> sorted = new ArrayList<Integer>();
		
		int i = lo;
		int j = mid + 1;
		
		while(i <= mid && j <= hi) {
			if(barsHeight[i] < barsHeight[j]) { //if the current item in the lower half is smaller than the current item in the upper half
				mergedSorted.add(barsHeight[i]);
				i++;
			} else {//if the current item in the upper half is smaller than the current item in the lower half
				mergedSorted.add(barsHeight[j]);
				j++;
			}
		}
		
		//copy any other elements remaining in the lower half
		while(i<=mid) {
			mergedSorted.add(barsHeight[i]);
			i++;
		}
		
		//copy any other elements remaining in the upper half
		while(j<=hi) {
			mergedSorted.add(barsHeight[j]);
			j++;
		}
		
		//copy mergedSorted into main list
		i = 0;
		j = lo;

		while(i < mergedSorted.size()) {
			int temp = mergedSorted.get(i);
			int switchingIndex = -1;
			for(int k = lo; k <= hi; k++) {
				if(temp == barsHeight[k]) {
					switchingIndex = k;
				}
			}

			important.add(switchingIndex);
			important.add(j);
			
			barsHeight[switchingIndex] = barsHeight[j];
			barsHeight[j] = mergedSorted.get(i);
			SortingAlgorithmsFunction.drawSortedBars(important,sorted);
			 try {
				 Thread.sleep(SortingAlgorithmsFunction.sortDelay);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
		   	 }
			important.clear();
			sorted.add(j);
			
			i++;
			j++;
		}
		
		if(lo == 0 && hi == barsHeight.length-1) {
			SortingAlgorithmsFunction.endOfSort();
			JOptionPane.showMessageDialog(null, "Sorting complete!");
		}
	}
	
	/**
	 * Sorts an List in ascending order (lowest to highest) using the merge sort
	 * algorithm.
	 * @param lo, the lowest index of the part of the array to be sorted
	 * @param hi, the highest index of the part of the array to be sorted
	 */
	private void mergeSort(int lo, int hi)
	{
		if((lo < hi) && (hi - lo)>=1) {
			ArrayList<Integer> important = new ArrayList<Integer>();
			ArrayList<Integer> sorted = new ArrayList<Integer>();
			//midpoint
			int mid = (lo+hi)/2;
			
			//sort first and second halves
			mergeSort(lo, mid);
			 
			mergeSort(mid+1, hi);
			 
			//merge the sorted halves
			merge(lo, mid, hi);
		}
	}
}
