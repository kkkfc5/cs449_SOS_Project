package sprint1_0.production.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import sprint1_0.PoC_GRAPHICS_TEST.TEST_PANEL;
import sprint1_0.production.GameLogic.*;

public class Gameboard extends JPanel implements MouseListener {
	
	
	int boardsize;
	CellPanel selectedCellPanel;
	ArrayList<CellPanel> cells;
	
	GameLogicManager gameLogicManager;
	
	
	public Gameboard(int boardsize, int gameboardDimensions) {
		this.boardsize = boardsize;
		setLayout(new GridLayout(boardsize, boardsize));
		setPreferredSize(new Dimension(gameboardDimensions, gameboardDimensions));
	    //System.out.print("BoardSize: " + boardsize + "\n");
	    
	} // end constructor

	public void connectGameLogicManager(GameLogicManager gm) {
		gameLogicManager = gm;
		cells = new ArrayList<CellPanel>();
		//JOptionPane.showMessageDialog(null, "CONNECTED GAMELOGIC MANAGER TO : " + gm);
		//gameLogicManager.test();
		//test();
	}
	
	
	
	public void clearBoard() {
		removeAll();
		
	}
	
	
	public Cell addCell(int x, int y) {
	//public Cell addCell() {
		
		// create and add the visual cell to this gameboard
		//System.out.print("}}}} "+x + "  "  +  y+ "\n");
		var cellPanel = new CellPanel(x,y);
		//cellPanel.setBackground(Color.black);
		cellPanel.setSize((int)getSize().getHeight()/boardsize, (int)getSize().getHeight()/boardsize);
		//cellPanel.setPreferredSize(new Dimension(50,50));
		cellPanel.addMouseListener(this); // lets users click on the panel

		add(cellPanel);
		
		setVisible(false);
		setVisible(true);
		
		cells.add(cellPanel);
		
		return null;
	}
	
	
	public void drawLetter(char charToDraw) {
		if( charToDraw == 'S' ) {
			selectedCellPanel.drawS();
		} else if ( charToDraw == 'O' ) {
			selectedCellPanel.drawO();
		} else {
			// error
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// get the clicked on panel and draw
		selectedCellPanel = (CellPanel) e.getSource();
		//JOptionPane.showMessageDialog(null, "selected: " + selectedCellPanel);
		//gameLogicManager.test();
		
		//System.out.print("{ " + selectedCellPanel.getGridX() + ", " + selectedCellPanel.getGridY() + " }\n");
		if(gameLogicManager.isValidMove(selectedCellPanel.getGridX(), selectedCellPanel.getGridY())) {
			// Good move
		}
		else {
			// INVALID MOVE
		}
		//JOptionPane.showMessageDialog(null, "Clicked");
		//selectedCellPanel.setVisible(false);
		//remove(selectedCellPanel);
		//setVisible(false);
		//setVisible(true);
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
		for(CellPanel p : cells) {
			if(p.getGridX() == x && p.getGridY() == y) {
				selectedCellPanel = p;
				System.out.print(selectedCellPanel.getGridX() + " " + selectedCellPanel.getGridY());
			}
		}
		
	}
}
