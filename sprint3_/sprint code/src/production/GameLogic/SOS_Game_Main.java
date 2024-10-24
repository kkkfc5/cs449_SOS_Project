package production.GameLogic;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import production.GUI.CellPanel;
import production.GUI.Gameboard;
import production.GUI.SOS_Window;

public abstract class SOS_Game_Main {

	int boardsize, remainingCells;
	int gameboardDimensions;
	int red_SOS_Count, blue_SOS_Count;

	// create grid that will hold the gameboard logic cells
	ArrayList<ArrayList<CellLogical>> grid;
	ArrayList<CellLogical> occupiedCells;

	GameType gameType; // [ can be 'S' or 'G' ]
	GameTurn gameTurn; // [ can be 'TurnRed' or 'TurnBlue' ]
	GameWinState gameWinState;

	Gameboard gameboard;
	SOS_Window window;

	// ==============================

	public enum GameType {
		// Game Type
		Simple, General
	}

	public enum GameTurn {
		TurnRed, TurnBlue
	}

	public enum GameWinState {
		InProgress, RedWin, BlueWin, Tie
	}

	public enum CellOpt {
		S, O, NULL
	}

	// ==============================

	public SOS_Game_Main() {
		gameTurn = GameTurn.TurnRed;
		occupiedCells = new ArrayList<CellLogical>();
		gameWinState = GameWinState.InProgress;
	}

	public void connectGUIWindow(SOS_Window gw) {
		window = gw;
	}

	// Initializes a game with the specified sizes and type. Returns it to
	// SOS_Window.
	public Gameboard initGame(int boardsize, int gameboardDimensions, GameType gameType) {

		// init gameboard variables
		gameboard = new Gameboard(boardsize, gameboardDimensions);
		gameboard.setSize(gameboardDimensions + 1, gameboardDimensions + 1);
		gameboard.connectSOS_Game(this);

		// init game state variables
		this.boardsize = boardsize;
		this.gameboardDimensions = gameboardDimensions;
		this.gameType = gameType;
		this.gameTurn = GameTurn.TurnRed;

		// properly init gameboard
		initGameBoard();

		return gameboard; // returns so that sos_window can attach the new gameboard
	}

	// creates a new gui gameboard of the specified size
	public boolean initGameBoard() {

		// create new grid for holding logical cells and init remaining cells
		grid = new ArrayList<ArrayList<CellLogical>>(boardsize);
		remainingCells = boardsize * boardsize;

		for (int i = 0; i < boardsize; i++) {

			// initialize a new row to the grid
			grid.add(new ArrayList<CellLogical>(boardsize));

			for (int j = 0; j < boardsize; j++) {

				// create, initialize, and add the logical cell to the grid at i,j
				var cell = new CellLogical();
				cell.setXY(i, j);
				grid.get(i).add(j, cell);

				// add a visible cell that is represented by the grid's logical cells
				gameboard.addCell(i, j);

			} // end j for
		} // end i for

		gameboard.setVisible(false);
		gameboard.setVisible(true);
		return true;
	}

	// processes move at grid at passed in x,y
	public boolean processIfValidMove(int x, int y) {

		CellLogical selectedLogiCell = grid.get(x).get(y);

		if (selectedLogiCell.getCellOpt() != CellOpt.NULL) {
			// the cell is occupied; react accordingly

			return false;
		} else { // if valid move

			// if the player isn't a computer
			if (!window.isComputer((gameTurn == GameTurn.TurnRed) ? true : false)) {
				// get the ui selected element for S O
				CellOpt option = (gameTurn == GameTurn.TurnRed) ? window.getRed_SO_Option()
						: window.getBlue_SO_Option();
				// process the move
				processMove(option, x, y, gameTurn);
				return true;
			} else {

				// HAVE COMPUTER DO ITS THING
				return false;
			}

			/*
			 * REFORMAT ABOVE TO:
			 * 
			 * if not valid: return false else: if !window.iscomputer, then call player
			 * object to invoke move? -- MEANS WE SHOULD MAKE THE LISTENER IN THE
			 * PLAYER_OBJ? else if computer, then call computer to invoke move
			 * 
			 * === REFACTOR ONLY IF WE NEED TO HAVE HEIRARCHY FOR PLAYER
			 */
		}

	}

	// generic-ish because it is designed to be called both by ai and gui
	// Abstract method that defines simple/general game win conditions
	public abstract void processMove(CellOpt option, int x, int y, GameTurn turn);

	public void clearBoard() {
		gameboard.clearBoard();
		gameboard = null;

	}
	
	/**
	 * Checks win conditions and swaps current game turn if applicable
	 */
	public void ToggleTurn() {
		if (remainingCells > 0) {
			if (gameTurn == GameTurn.TurnRed) {
				gameTurn = GameTurn.TurnBlue;
			} else {
				gameTurn = GameTurn.TurnRed;
			}
		} else { // else if there aren't any more cells left
					// end game

			if (red_SOS_Count > blue_SOS_Count) {
				window.setWinnerText(GameTurn.TurnRed);
				gameWinState = GameWinState.RedWin;
			} else if (red_SOS_Count < blue_SOS_Count) {
				window.setWinnerText(GameTurn.TurnBlue);
				gameWinState = GameWinState.BlueWin;
			} else {
				window.setWinnerText(null);
				gameWinState = GameWinState.Tie;
			}

			return;
		}

		window.ToggleTurn();
	}

	/**
	 * If this function detects selectedCell made an SOS, it calls gameboard to draw it
	 * @param selectedCell is logical cell user or computer selected 
	 * @return true if SOS is made, false otherwise
	 */
	protected boolean checkMadeSOS(CellLogical selectedCell) {

		int origRedSOSCount = red_SOS_Count, origBlueSOSCount = blue_SOS_Count;

		int searchX = selectedCell.getX();
		int searchY = selectedCell.getY();

		// for all surrounding cells
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {

				// don't check the center (i.e. the cell we clicked on)
				if (i == 0 && j == 0) {
					continue;
				}

				// if in bounds of the gameboard
				if (((searchX + i) >= 0 && (searchX + i) < boardsize) && (searchY + j) >= 0
						&& (searchY + j) < boardsize) {

					// if placed cell is S
					if (selectedCell.getCellOpt() == CellOpt.S) {

						CellLogical potentialOCell = grid.get(searchX + i).get(searchY + j);

						// check if cells surrounding the selected one has an O
						if (potentialOCell.getCellOpt() == CellOpt.O) {

							// if the other side of the O is in bounds
							if (((searchX + (2 * i)) >= 0 && (searchX + (2 * i)) < boardsize)
									&& (searchY + (2 * j)) >= 0 && (searchY + (2 * j)) < boardsize) {

								// reference to the other side of the O
								CellLogical potentialSCell = grid.get(searchX + (2 * i)).get(searchY + (2 * j));

								// check if cell on other side of O is an S
								if (potentialSCell.getCellOpt() == CellOpt.S && !gameboard.getPairList()
										.hasPair(selectedCell, grid.get(searchX + (2 * i)).get(searchY + (2 * j)))) {

									// creates a new SOS pair and hands it to gameboard to manage
									var newPair = new SOS_Pair(selectedCell, potentialSCell, gameTurn);
									gameboard.getPairList().addPair(newPair);
									gameboard.repaint();

									// increment sos count
									if (gameTurn == GameTurn.TurnRed) 
									{	red_SOS_Count++;	}
									else 
									{	blue_SOS_Count++;	}
									

									continue;
								}

							}

						}

						continue;
					} else if (selectedCell.getCellOpt() == CellOpt.O) {

						CellLogical potentialSCell = grid.get(searchX + i).get(searchY + j);

						// check if cells surrounding the selected one has an O
						if (potentialSCell.getCellOpt() == CellOpt.S) {

							// if In bounds
							if (((searchX + (-1 * i)) >= 0 && (searchX + (-1 * i)) < boardsize)
									&& (searchY + (-1 * j)) >= 0 && (searchY + (-1 * j)) < boardsize) {

								CellLogical inverseDirSCell = grid.get(searchX + (-1 * i)).get(searchY + (-1 * j));

								// check if the inverse direction also has an S
								if (inverseDirSCell.getCellOpt() == CellOpt.S
										&& !gameboard.getPairList().hasPair(potentialSCell, inverseDirSCell)) {

									// creates a new SOS pair and hands it to gameboard to manage
									var newPair = new SOS_Pair(potentialSCell, inverseDirSCell, gameTurn);
									gameboard.getPairList().addPair(newPair);
									gameboard.repaint();

									
									// increment sos count
									if (gameTurn == GameTurn.TurnRed) 
									{	red_SOS_Count++;	}
									else 
									{	blue_SOS_Count++;	}

									continue;
								}
							}
						}
					} // end if-else selected == S or O
				} // end if surrounding is in gameboard
			} // end for j
		} // end for i

		// if an SOS was made, return true
		if (origRedSOSCount != red_SOS_Count || origBlueSOSCount != blue_SOS_Count) {
			return true;
		}
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

	public int getBoardSize() {
		return boardsize;
	}

	public int getRedSOSCount() {
		return red_SOS_Count;
	}

	public int getBlueSOSCount() {
		return blue_SOS_Count;
	}

	public int getRemainingCells() {
		return remainingCells;
	}

	public GameWinState getGameWinState() {
		return gameWinState;
	}

	public void setGameboard(Gameboard g) {
		gameboard = g;
	}
}
