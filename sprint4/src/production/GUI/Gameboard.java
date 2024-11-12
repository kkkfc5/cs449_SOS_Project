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
import production.GameLogic.GameStateManager.*;
import production.GameEvents.CellToMouse_Listener_Event_Manager.CellToMouse_Event;
import production.GameEvents.Draw_Cell_Event_Manager.Draw_Cell_Event;
import production.GameEvents.Game_Over_Event_Manager.Game_Over_Event;
import production.GameEvents.SOS_Created_Event_Manager.SOS_Created_Event;
import production.GameEvents.Update_Utility_Event_Manager.Update_Utility_Event;

/**
 * Visible gameboard. This is a grid of visible cells (CellPanel), and
 */

public class Gameboard extends JLayeredPane implements SOS_Created_Event, Update_Utility_Event, Game_Over_Event, CellToMouse_Event, Draw_Cell_Event {

	int boardsize, gameboardDimensions;
	CellPanel selectedCellPanel;
	ArrayList<CellPanel> cellPanelList;

	//SOS_PairList pairs;
	LinePanel linePanel;
	CellPanelGrid cellGridPanel;
	// Container container;

	Graphics2D g2d;
	
	GameStateManager gameStateManager;
	
	

	//SOS_Game_Main gameLogicManager;

	public Gameboard() {
		this.boardsize = 5;
		this.gameboardDimensions = 300;
		cellGridPanel = new CellPanelGrid(boardsize, gameboardDimensions);

		setLayout(new GridLayout(1, 1));
		setPreferredSize(new Dimension(gameboardDimensions, gameboardDimensions));
		//pairs = new SOS_PairList();

		linePanel = new LinePanel();
		
		initGameboard(boardsize);

	} // end constructor

	public void connectGameStateManager(GameStateManager g) {
		gameStateManager = g;
	}
	
	
	void setLayeredPane() {
		// create a layered pane so that the SOS lines can overlap the gameboard object.
		JLayeredPane lp = new JLayeredPane();
		lp.setBounds(0, 0, gameboardDimensions, gameboardDimensions);

		cellGridPanel.setBounds(0, 0, gameboardDimensions, gameboardDimensions);
		linePanel.setBounds(0, 0, gameboardDimensions, gameboardDimensions);

		linePanel.setOpaque(false);

		lp.add(cellGridPanel, Integer.valueOf(0));
		lp.add(linePanel, Integer.valueOf(1));

		lp.setComponentZOrder(linePanel, 0);
		lp.setComponentZOrder(cellGridPanel, 1);
		
		lp.setVisible(true);
		add(lp);
	}

	// connects a new game logic manager to this gameboard object
	//public void connectSOS_Game(SOS_Game_Main gm) {
		//gameLogicManager = gm;
		//cells = new ArrayList<CellPanel>();
	//}

	public void clearBoard() {
		selectedCellPanel = null;
		cellPanelList = new ArrayList<CellPanel>();
		//cellGridPanel = new CellPanelGrid(boardsize, gameboardDimensions);
		//pairs = new SOS_PairList();
		linePanel = new LinePanel();
		setLayeredPane();
		removeAll();
	}

	
	
	
	// create a new visible cell at grid location X.Y
	void addCell(int x, int y) {

		// create and add the visual cell to this gameboard
		var cellPanel = new CellPanel(x, y);

		cellPanel.setSize((int) getSize().getHeight() / boardsize, (int) getSize().getHeight() / boardsize);
		// cellPanel.addMouseListener(this); // lets users click on the panel
		// cellPanel.addMouseListener(gameLogicManager.getPlayer_Human());

		cellPanelList.add(cellPanel);
		cellGridPanel.add(cellPanel);

		return;
	}

	
	public void initGameboard(int boardsize) {
		// remove previous data
		clearBoard();
		
		this.boardsize = boardsize;
		cellGridPanel = new CellPanelGrid(boardsize, gameboardDimensions);
		
		for(int x = 0; x < boardsize; x++) {
			for(int y = 0; y < boardsize; y++) {
				addCell(x, y);
			}
		}
		
		
		// now that all visible cells are initialized,
		// set and display the grid and lines
		setLayeredPane();
		
		return;
	}
	
	// call the selected visible panel to draw a char
	public void drawLetter(CellOpt opt) {
		if (opt == CellOpt.S) {
			selectedCellPanel.drawS();
		} else if (opt == CellOpt.O) {
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
	 * 
	 * @param g
	 */
	public void UpdateSOSLines(Graphics g) {
		linePanel.clearCoords();
		linePanel.setBrushSize(8);

		for (SOS_Pair pair : gameStateManager.getPairList().getPairs()) {
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
		for (CellPanel panel : cellPanelList) {
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
			JOptionPane.showMessageDialog(this,
					"How did we get here? Error Func: drawLine() in Gameboard.java\n" + who);
			return null;
		}

		return tempArray;
	}

	/**
	 * Disable the clicking for each visible cell
	 */
	public void toggleCellsEnabled() {
		for (CellPanel p : cellPanelList) {
			if (p.isEnabled()) {
				p.setEnabled(!p.isEnabled());
				// p.removeMouseListener(this);
			} else {
				p.setEnabled(!p.isEnabled());
				// p.addMouseListener(this);
			}
		}
	}

	public void setSelectedCell(int x, int y) {
		// for every cell, if visible cell's X,Y is the X,Y passed in, then setselected
		// cell panel to that
		for (CellPanel p : cellPanelList) {
			if (p.getGridX() == x && p.getGridY() == y) {
				selectedCellPanel = p;
			}
		}
	}

	//public SOS_PairList getPairList() {
	//	return pairs;
	//}

	public void addLinePanel() {
		add(linePanel);
	}

	public CellPanel TESTgetSelectedCell() {
		return selectedCellPanel;
	}

	/*public void TESTupdateUtility(int x, int y, int i, int j) {
		for (CellPanel p : cellPanelList) {
			if (p.getGridX() == x && p.getGridY() == y) {
				p.drawUtil(i, j);
				;
			}
		}
	}*/


	@Override
	public void onSOSCreation(SOS_Pair pair) {
		// TODO Auto-generated method stub
		gameStateManager.addPair(pair);
		
		repaint();
	}

	@Override
	public void onUpdateUtility(int x, int y, int s, int o) {
		for (CellPanel p : cellPanelList) {
			if (p.getGridX() == x && p.getGridY() == y) {
				p.drawUtil(s, o);
			}
		}
		
	}

	@Override
	public void onGameOver() {
		//toggleCellsEnabled(); // %%%%%%%%%%%%% <------ NEED TO REMOVE MOUSE LISTENERS FOR PANELS
		
	}

	@Override
	public void onCellToMouseConnect(SOS_Player_Human human) {
		
		//JOptionPane.showMessageDialog(null, "GAMEBOARD onCellToMouseConnect");
		
		for(CellPanel p : cellPanelList) {
			p.addMouseListener(human);
		}
		
	}
	
	@Override
	public void onCellToMouseDisconnect(SOS_Player_Human human) {
		
		for(CellPanel p : cellPanelList) {
			p.removeMouseListener(human);
		}
		
	}

	@Override
	public void onDrawCell(CellLogical dataCell) {
		// TODO Auto-generated method stub
		
		System.out.print("Drawing cell\n");
		
		for(CellPanel p : cellPanelList) {
			if(p.getGridX() == dataCell.getX() && p.getGridY() == dataCell.getY()) {
				selectedCellPanel = p;
				break;
			}
		}
		
		//JOptionPane.showMessageDialog(cellGridPanel, "onDrawCell in Gameboard:  cellopt: "+dataCell.getCellOpt());
		drawLetter(dataCell.getCellOpt());
		
	}

}
