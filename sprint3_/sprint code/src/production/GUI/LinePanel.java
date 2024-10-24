package production.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import sprint3_0.production.GameLogic.LogicalCell;
import production.GameLogic.SOS_Game_Main.GameTurn;


/**
 * Draws the SOS sequence lines
 */
public class LinePanel extends JPanel {

	Graphics2D g2d;

	ArrayList<ArrayList<Integer>> coordinates;
//	Gameboard gameboard;
//	ArrayList<CellPanel> cells;

	public LinePanel() {
		
		coordinates = new ArrayList<ArrayList<Integer>>();
		
		setPreferredSize(new Dimension(0, 0));

		setOpaque(false);
		setVisible(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		// draw for each set of coordinates
		for(ArrayList<Integer> coords : coordinates) {
			// of form x1,y1,x2,y2,(int)(who!=RedPlayer)
			drawLine(coords.get(0), coords.get(1), coords.get(2), coords.get(3), coords.get(4));
		}

	}
	
	// of form x1,y1,x2,y2,(int)(who!=RedPlayer)
	private void drawLine(int x1, int y1, int x2, int y2, int color) {
		
		if(color == 0) {
			g2d.setColor(Color.RED);
		}
		else {
			g2d.setColor(Color.BLUE);
		}
		
		g2d.drawLine(x1,y1,x2,y2);
	}

	public void clearCoords() {
		coordinates = new ArrayList<ArrayList<Integer>>();
	}
	public void addCoords(ArrayList<Integer> coords) {
		coordinates.add(coords);
	}
	
	public void setBrushSize(int size) {
		//g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // set brush options
	}
}
