package sprint1_0.production.GameLogic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import sprint1_0.production.GUI.CellPanel;
import sprint1_0.production.GUI.Gameboard;
import sprint1_0.production.GUI.SOS_Window;

public class GameLogicManager {
	int boardsize, remainingCells;
	int red_SOS_Count, blue_SOS_Count;
	
	// create grid that will hold the gameboard logic cells
	ArrayList<ArrayList<Cell>> grid;
	
	GameType gameType; // [ can be 'S' or 'G' ]
	GameTurn gameTurn; // [ can be 'TurnRed' or 'TurnBlue' ]
	
	public enum GameType {
		// Game Type
		Simple,
		General,
	}
	
	public enum GameTurn {
		// Game State
		TurnRed,
		TurnBlue,
		WinRed,
		WinBlue,
	}
	
	public enum Direction {
		North,
		West,
		East,
		South,
		NorthWest,
		SouthWest,
		NorthEast,
		SouthEast
	}
	
	Gameboard gameboard; 
	SOS_Window window;
	
	//public ToggleTurnsEvent toggleTurnsEvent;
	int gameboardDimensions;

	
	public GameLogicManager() {
		gameTurn = GameTurn.TurnRed;
		
		//gameboard = gb;
		//toggleTurnsEvent = new ToggleTurnsEvent();
		
		
	}
	
	public void connectGUIWindow(SOS_Window gw) {
		window = gw;
		//JOptionPane.showMessageDialog(null, "Connected GUI Window");
	}
	//public void test() { JOptionPane.showMessageDialog(null, "TEST CONFIRMED"); }
	
	
	public Gameboard initGame(int boardsize, int gameboardDimensions, GameType gameType) {
		gameboard = new Gameboard(boardsize, gameboardDimensions);
		gameboard.setSize(gameboardDimensions,gameboardDimensions);
		
		//gameboard.setLayout(new GridLayout(10, 10));
		
		gameboard.connectGameLogicManager(this);
		// init game state variables
		this.boardsize = boardsize;
		this.gameboardDimensions = gameboardDimensions;
		remainingCells = boardsize;
		this.gameType = gameType;
		
		
		initGameBoard();
		
		
		//JOptionPane.showMessageDialog(null, gameType);
		return gameboard;
	}
	
	
	public boolean initGameBoard() {
		gameboard.setVisible(false); // #######################
		
		
		grid = new ArrayList<ArrayList<Cell>>(boardsize);
		//gameboard = new Gameboard(boardsize, gameboardDimensions);
		remainingCells = boardsize * boardsize;
		
		
		for(int i = 0; i < boardsize; i++){
			// initialize a new row to the grid
			grid.add(new ArrayList<Cell>(boardsize));
			
			for(int j = 0; j < boardsize; j++) {
				
				//System.out.print(i + " " + j + "\n");
				
				// create, initialize, and add the logical cell to the grid at i,j
				var cell = new Cell();
				cell.setXY(i, j);
				grid.get(i).add(j, cell);
				//System.out.print("]]]] "+i + "  "  +  j + "\n");
				
				// add a visible cell that is represented by the grid's logical cells
				gameboard.addCell(i,j); 
				//gameboard.addCell(); 
				
			} // end j for
		} // end i for
		
		//gameboard.repaint(); // #######################
		gameboard.setVisible(false);
		gameboard.setVisible(true);
		return true;
	}
	
	
	/*
	public Game GameLoop() {
		while(true) {
			//System.out.print("Running\n");
			//toggleTurnsEvent.InvokeEvent();
			try {
				TimeUnit.SECONDS.sleep(2);
				continue;
			}
			catch(Exception e) {
				break;
			}
		} // end while
		return null;
	}
	*/
	
	
	
	
	public boolean isValidMove(int x, int y) {
		//System.out.print("<iv> "+x + "  "  +  y + "\n");
		Cell selectedCell = grid.get(x).get(y);
		
		if(selectedCell.getCellOpt() != CellOpt.NULL) {
			// the cell is occupied; react accordingly
			
			JOptionPane.showMessageDialog(gameboard, "selected cell is not null; " + selectedCell.getCellOpt());
			
			return false;
		}
		else {
			// valid move
			// {following is replaced by prcessMove -> } selectedCell.setCellOpt((gameTurn == GameTurn.TurnRed) ? window.getRed_SO_Option() : window.getBlue_SO_Option());
			// if the player isn't a computer
			if(!window.isComputer((gameTurn == GameTurn.TurnRed) ? true : false)) {
				// get the ui selected element for S O 
				CellOpt option = (gameTurn == GameTurn.TurnRed) ? window.getRed_SO_Option() : window.getBlue_SO_Option();
				// process the move
				processMove(option, x, y, gameTurn);
				return true;
			}
			else {
				
				// HAVE COMPUTER DO ITS THING
				return false;
			}
		}
		
		
	}
	
	// generic-ish because it is designed to be called both by ai and gui
	public void processMove(CellOpt option, int x, int y, GameTurn turn) {
		Cell selectedCell = grid.get(x).get(y);
		
		selectedCell.setCellOpt(option);
		
		// draw letter option that is passed in. 
		//    [ turning the enum to string, then turning string into char ]
		gameboard.drawLetter(option.toString().charAt(0));  // <<<<<<< CALL THIS FROM GAMEBOARD INSTEAD??
		
		
		remainingCells--;
		
		
		if (makesSOS()) {
			// MARK that sos was created USING TURN passed into funtion?
				// ex: gameboard.drawsos(x1, x2, y1, y2, turn);
			// DONT TOGGLE TURN
		}
		else {
			ToggleTurn();
		}
	}
	
	
	public void clearBoard() {
		gameboard.clearBoard();
		gameboard = null;
		
	}
	
	public int getBoardSize() {
		return boardsize;
	}


	
	public void ToggleTurn() {
		if(remainingCells > 0) {
			if(gameTurn == GameTurn.TurnRed) {
				gameTurn = GameTurn.TurnBlue;
			}
			else {
				gameTurn = GameTurn.TurnRed;
			}
		}
		else { // else if there aren't any more cells left
			// end game
			JOptionPane.showMessageDialog(gameboard, "Game Complete.");
		}
		
		window.ToggleTurn();
	}
	
	
	
	
	
	boolean makesSOS() {
		return false;
	}
	
	
	public String getTurn() {
		return gameTurn.toString();
	}
	
	public GameType getGameType() {
		return gameType;
	}
	
	public Gameboard getGameboard() {
		return gameboard;
	}
	
	
}
