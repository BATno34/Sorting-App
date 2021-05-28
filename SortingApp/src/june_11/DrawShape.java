package june_11;

import java.awt.Graphics;
import java.awt.*;

import javax.swing.JPanel;

public class DrawShape extends JPanel {
	
	public static int numberOfBars;
	public static int [] heightOfBars;
	
	public DrawShape (int numberOfBars, int [] barsHeight) {
		this.numberOfBars = numberOfBars;
		this.heightOfBars = barsHeight;
		
	}
	
	public void drawing() {
		repaint();
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponents(g);
		
		g.setColor(Color.RED);
		int emptySpace = 600 / (4 * numberOfBars - 1);
		int width = 3 * emptySpace;
		
		for (int m = 0; m < numberOfBars; m++) {
			g.fillRect((m*(emptySpace+width)+100), (400 - heightOfBars[m]), width, heightOfBars[m]);
		}
		
		
		
	}

}
