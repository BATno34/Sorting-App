package june_11;

import javax.swing.JLabel;

public class BubbleSort implements Runnable{
	public static JLabel[] bars;
	public static int [] barsHeight;
	
	public BubbleSort(JLabel[] bars, int[] barsHeight) {
		this.bars = bars;
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
		do {
			swaps = 0;
	    	for (int i = 0; i < (barsHeight.length - turn - 1); i++) {
	    		if (barsHeight[i+1] < barsHeight[i]) {							
					copy = barsHeightCopy[i];
					barsHeight[i] = barsHeight[i+1];
					barsHeight[i+1] = copy;
					barsHeightCopy = barsHeight;					
					SortingAlgorithmsFunction.drawSortedBars(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					swaps++;     //counting swaps
				}
			}
			turn++;
	    } while (swaps!=0);   //when there was no swap, it means that it's sorted

		System.out.println("Sort done");
	}
	

}

