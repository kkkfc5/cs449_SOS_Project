package production.GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import production.GameLogic.*;
import production.GameLogic.SOS_Game_Main.GameTurn;



/**
 * Visible gameboard. 
 * This is a grid of visible cells (CellPanel), and 
 */

public class Gameboard extends JLayeredPane implements MouseListener {

	int boardsize, gameboardDimensions;
	CellPanel selectedCellPanel;
	ArrayList<CellPanel> cells;

	SOS_PairList pairs;
	LinePanel linePanel;
	CellPanelGrid cellGridPanel;
	Container container;
	
	Graphics2D g2d;

	SOS_Game_Main gameLogicManager;

	public Gameboard(int boardsize, int gameboardDimensions) {
		this.boardsize = boardsize;
		this.gameboardDimensions = gameboardDimensions;
		cellGridPanel = new CellPanelGrid(boardsize, gameboardDimensions);
		
		setLayout(new GridLayout(1, 1));
		setPreferredSize(new Dimension(gameboardDimensions, gameboardDimensions));
		pairs = new SOS_PairList();
		
		linePanel = new LinePanel();
		
		
		// create a layered pane so that the SOS lines can overlap the gameboard object.
		JLayeredPane lp = new JLayeredPane();
		lp.setBounds(0,0,gameboardDimensions, gameboardDimensions);
		
		cellGridPanel.setBounds(0, 0, gameboardDimensions, gameboardDimensions);
		linePanel.setBounds(0, 0, gameboardDimensions, gameboardDimensions);
		
		linePanel.setOpaque(false);
		
		lp.add(cellGridPanel, Integer.valueOf(0));
		lp.add(linePanel, Integer.valueOf(1));
		
		lp.setVisible(true);
		add(lp);
		
		lp.setComponentZOrder(linePanel, 0);
		lp.setComponentZOrder(cellGridPanel, 1);
	} // end constructor

	// connects a new game logic manager to this gameboard object
	public void connectSOS_Game(SOS_Game_Main gm) {
		gameLogicManager = gm;
		cells = new ArrayList<CellPanel>();
	}

	public void clearBoard() {
		removeAll();
	}

	// create a new visible cell at grid location X.Y  
	public void addCell(int x, int y) {

		// create and add the visual cell to this gameboard
		var cellPanel = new CellPanel(x, y);

		cellPanel.setSize((int) getSize().getHeight() / boardsize, (int) getSize().getHeight() / boardsize);
		cellPanel.addMouseListener(this); // lets users click on the panel

		cellGridPanel.add(cellPanel); 
		
		setVisible(false);
		setVisible(true);

		cells.add(cellPanel);

		return;
	}

	// call the selected visible panel to draw a char 
	public void drawLetter(char charToDraw) {
		if (charToDraw == 'S') {
			selectedCellPanel.drawS();
		} else if (charToDraw == 'O') {
			selectedCellPanel.drawO();
		} else {
			// error
		}
	}
	
	
	/*
	 * PAINTING GAMEBOARD SECTION
	 */
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		UpdateSOSLines(g);
	}

	/**
	 * for every SOS_Pair, update the linePanels set of coordinates to draw
	 * @param g
	 */
	public void UpdateSOSLines(Graphics g) {
		linePanel.clearCoords();
		linePanel.setBrushSize(8);
		
		for (SOS_Pair pair : pairs.getPairs()) {
			linePanel.addCoords(getCoords(pair.getS1(), pair.getS2(), pair.getWho()));
		}
		
	}

	
	/**
	 * 
	 * @param c1
	 * @param c2
	 * @param who
	 * @return an array list of form x1,y1,x2,y2,(int)(who!=RedPlayer)
	 */
	public ArrayList<Integer> getCoords(CellLogical c1, CellLogical c2, GameTurn who) {

		// create the panels with a temp location
		CellPanel panelFrom = new CellPanel(-1, -1), panelTo = new CellPanel(-1, -1);

		// convert the logical cells to the visible cells so we will know where to paint
		for (CellPanel panel : cells) {
			if (panel.getGridX() == c1.getX() && panel.getGridY() == c1.getY()) {
				panelFrom = panel;
			} else if (panel.getGridX() == c2.getX() && panel.getGridY() == c2.getY()) {
				panelTo = panel;
			}
		}

		// init the array of form x1,y1,x2,y2,(int)(who==RedPlayer)
		var tempArray = new ArrayList<Integer>();
		tempArray.add(panelFrom.getX() + ((gameboardDimensions / boardsize) / 2));
		tempArray.add(panelFrom.getY() + ((gameboardDimensions / boardsize) / 2));
		tempArray.add(panelTo.getX() + ((gameboardDimensions / boardsize) / 2));
		tempArray.add(panelTo.getY() + ((gameboardDimensions / boardsize) / 2));

		if (who == GameTurn.TurnRed) {
			// paint red
			tempArray.add(0);
		} else if (who == GameTurn.TurnBlue) {
			tempArray.add(1);
		} else {
			JOptionPane.showMessageDialog(this, "How did we get here? Error Func: drawLine() in Gameboard.java\n" + who);
			return null;
		}

		return tempArray;
	}

	/**
	 * Disable the clicking for each visible cell
	 */
	public void toggleCellsEnabled() {
		for(CellPanel p : cells) {
			if(p.isEnabled()) {
				p.setEnabled(!p.isEnabled());
				p.removeMouseListener(this);
			}
			else {
				p.setEnabled(!p.isEnabled());
				p.addMouseListener(this);
			}
		}
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// get the clicked on panel and draw
		selectedCellPanel = (CellPanel) e.getSource();
 
		if (gameLogicManager.processIfValidMove(selectedCellPanel.getGridX(), selectedCellPanel.getGridY())) {
			// Good move
		} else {
			// INVALID MOVE
		}
	}
	
	public CellPanel TESTgetSelectedCell() {
		return selectedCellPanel;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setSelectedCell(int x, int y) {
		// for every cell, if visible cell's X,Y is the X,Y passed in, then setselected cell panel to that
		for (CellPanel p : cells) {
			if (p.getGridX() == x && p.getGridY() == y) {
				selectedCellPanel = p;
			}
		}
	}

	public SOS_PairList getPairList() {
		return pairs;
	}
	
	public void addLinePanel() {
		add(linePanel);
	}
}
