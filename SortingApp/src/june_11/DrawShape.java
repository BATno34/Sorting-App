package june_11;

import java.awt.Graphics;
import java.awt.*;

import javax.swing.JPanel;

public class DrawShape extends JPanel {
	
	public static int numberOfBars;
	public static int [] barsHeight;
	
	public DrawShape (int numberOfBars, int [] barsHeight) {
		this.numberOfBars = numberOfBars;
		this.barsHeight = barsHeight;
		
	}
	
	public void drawing() {
		repaint();
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponents(g);
		
		g.setColor(Color.RED);
		int width = (600 - 20 * (numberOfBars - 1))/numberOfBars;
		
		for (int m = 0; m < numberOfBars; m++) {
			g.fillRect((m*(20+width)+100), (400 - barsHeight[m]), width, barsHeight[m]);
		}
		
		
		
	}

}
