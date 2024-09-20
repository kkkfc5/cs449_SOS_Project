package app;

import java.awt.*;

import javax.swing.*;

public class JPanelGraphicsTest extends JPanel {

	public JPanelGraphicsTest(){}
	
	// this is called on initialization of the panel
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // lets the superclass paint what we aren't updating ourselves?
								// https://stackoverflow.com/questions/28724609/what-does-super-paintcomponentg-do
		
		Graphics2D g2d = (Graphics2D) g; // cast the graphics to 2d because 2d has more options
		g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // set brush options
		
		// not the best way, but proof of concept of making a line.
		g2d.drawLine(this.getX() + 300, this.getY() - 50, this.getX() + 100, this.getY() - 10);
	}
	
}
