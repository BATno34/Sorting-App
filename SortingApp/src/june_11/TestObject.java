package june_11;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class TestObject {
	
	public static int numBars = 10;
	public static int [] barsHeight = new int [numBars];
	
	public static void main(String[] args) {
		JFrame frame = new JFrame ("Test Object");
		frame.setVisible(true);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		for (int m = 0; m < numBars; m++) {
			barsHeight [m] = (int)(Math.random() * 200);
		}
		
		DrawShape object = new DrawShape(numBars, barsHeight);
		frame.add(object);
		object.drawing();
	}

}
