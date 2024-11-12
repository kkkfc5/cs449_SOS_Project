package production.GameLogic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;


import production.GameEvents.*;
import production.GameEvents.Game_Over_Event_Manager.Game_Over_Event;
import production.GameEvents.New_Game_Event_Manager.New_Game_Event;
import production.GameEvents.Player_Clicked_Event_Manager.Player_Clicked_Event;
//import production.GUI.SOS_Window;
import production.GameLogic.GameStateManager.*;

public abstract class SOS_Game_Main implements New_Game_Event, Player_Clicked_Event, production.GameEvents.Toggle_Turns_Event_Manager.Toggle_Turns_Event {

	//int boardsize, remainingCells;
	//int gameboardDimensions;
	//int red_SOS_Count, blue_SOS_Count;

	// create grid that will hold the gameboard logic cells
	ArrayList<ArrayList<CellLogical>> grid;
	ArrayList<CellLogical> occupiedCells;

	//GameType gameType; // [ can be 'S' or 'G' ]
	//GameTurn gameTurn; // [ can be 'TurnRed' or 'TurnBlue' ]
	//GameWinState gameWinState;

	//Gameboard gameboard;
	//SOS_Window window;
	
	//SOS_Player_Main player;
	//SOS_Player_Human player_human;
	//SOS_Player_Computer player_computer;
	
	GameStateManager gameStateManager;
	SOS_Created_Event_Manager SOS_Created_Event;
	Update_Utility_Event_Manager Update_Utility_Event;
	Game_Over_Event_Manager Game_Over_Event;
	Toggle_Turns_Event_Manager Toggle_Turns_Event;
	Get_Selected_Cell_Event_Manager Get_Selected_Cell_Event;
	Draw_Cell_Event_Manager Draw_Cell_Event;
	

	public SOS_Game_Main() {
		//gameTurn = GameTurn.TurnRed;
		occupiedCells = new ArrayList<CellLogical>();
		//gameWinState = GameWinState.InProgress;
		
		// init player
		//player_human = new SOS_Player_Human(this);
		//player_computer = new SOS_Player_Computer(this);
		//player = player_human;
	}

	public void connectGameStateManager(GameStateManager g) {
		gameStateManager = g;
	}
	public void connectSOSCreatedEventManager(SOS_Created_Event_Manager g) {
		SOS_Created_Event = g;
	}
	public void connectUpdateUtilityEventManager(Update_Utility_Event_Manager g) {
		Update_Utility_Event = g;
	}
	public void connectToggleTurnsEventManager(Toggle_Turns_Event_Manager g) {
		Toggle_Turns_Event = g;
	}
	public void connectGetSelectedCellEventManager(Get_Selected_Cell_Event_Manager g) {
		Get_Selected_Cell_Event = g;
	}
	public void connectDrawCellEventManager(Draw_Cell_Event_Manager g) {
		Draw_Cell_Event = g;
	}
	public void connectGameOverEventManager(Game_Over_Event_Manager g) {
		Game_Over_Event = g;
	}
	//public void connectGUIWindow(SOS_Window gw) {
	//	window = gw;
	//}

	// Initializes a game with the specified sizes and type. Returns it to
	// SOS_Window.
	public void initGame() {

		// init gameboard variables
		//gameboard = new Gameboard(boardsize, gameboardDimensions);
		//gameboard.setSize(gameboardDimensions + 1, gameboardDimensions + 1);
		//gameboard.connectSOS_Game(this);

		// init game state variables
		//this.boardsize = boardsize;
		//this.gameboardDimensions = gameboardDimensions;
		//this.gameType = gameType;
		//this.gameTurn = GameTurn.TurnRed;

		// properly init gameboard
		//initGameBoard();

		//return gameboard; // returns so that sos_window can attach the new gameboard
	//}

	// creates a new gui gameboard of the specified size
	//public boolean initGameBoard() {

		int boardsize = gameStateManager.getBoardsize();
		
		// create new grid for holding logical cells and init remaining cells
		grid = new ArrayList<ArrayList<CellLogical>>(boardsize);

		for (int i = 0; i < boardsize; i++) {

			// initialize a new row to the grid
			grid.add(new ArrayList<CellLogical>(boardsize));

			for (int j = 0; j < boardsize; j++) {

				// create, initialize, and add the logical cell to the grid at i,j
				var cell = new CellLogical();
				cell.setXY(i, j);
				grid.get(i).add(j, cell);

			} // end j for
		} // end i for

		
		updateUtility();
		
		return;
	}

	/*
	// processes move at grid at passed in x,y
	public boolean processIfValidMove(int x, int y) {

		CellLogical selectedLogiCell = grid.get(x).get(y);

		if (selectedLogiCell.getCellOpt() != CellOpt.NULL) {
			// the cell is occupied; react accordingly

			return false;
		} else { // if valid move

			// if the player isn't a computer for current player's turn
			if (!window.isComputer(gameTurn == GameTurn.TurnRed)) {
				// get the ui selected element for S O
				CellOpt option = (gameTurn == GameTurn.TurnRed) ? window.getRed_SO_Option()
						: window.getBlue_SO_Option();
				// process the move
				processMove(option, x, y, gameTurn);
				
				// ***************************************************************
				updateUtility();
				
				return true;
			} else {

			// ######## WAIT THIS ONLY GETS CALLED IF PLAYER CLICKS AGAIN TO CALL PROCESS IF VALID
				// so have two booleans for if red/blue is computer or not 
				    // and then in toggle turn check if that is true, 
						// and if so, then directly call conjure move
				// so having the bolean update in toggleturn but also as a listener to that radio button
				
				
				// HAVE COMPUTER DO ITS THING
				
				System.out.print("\n===COMPUTER SELECTED ===\n");
				//conjureMove();
				
				updateUtility();
				return true;
			}
*/
			/*
			 * REFORMAT ABOVE TO:
			 * 
			 * if not valid: return false else: if !window.iscomputer, then call player
			 * object to invoke move? -- MEANS WE SHOULD MAKE THE LISTENER IN THE
			 * PLAYER_OBJ? else if computer, then call computer to invoke move
			 * 
			 * === REFACTOR ONLY IF WE NEED TO HAVE HEIRARCHY FOR PLAYER
			 */
//		}

//	}
	

	public boolean isCellUnoccupied(int x, int y) {
		if(grid.get(x).get(y).getCellOpt() == CellOpt.NULL) {
			return true;
		}
		//JOptionPane.showMessageDialog(null, "OCCUPIED CELL");
		return false;
	}
	
	CellLogical validateCell(CellLogical selectedCell) {
		// note, the human player passes in a new logical cell. 
		// We need to correspond that cell with the one actually on the board. 
		for(int x = 0; x < gameStateManager.getBoardsize(); x++) {
			for(int y = 0; y < gameStateManager.getBoardsize(); y++) {
				if(x == selectedCell.getX() && y == selectedCell.getY()) {
					grid.get(x).get(y).setCellOpt(selectedCell.getCellOpt());
					return grid.get(x).get(y);		
				}
			}
		}
		return null;
	}
	
	/*
	public void processMove() {
		
		//System.out.print("\nSOS_Game_Main: entered processMove\n");
		//gameboard.repaint();
		
		//int time = 0;*/
		/*while(time < window.getSlider().getValue() && window.isComputer(gameTurn==GameTurn.TurnRed)) {
			//System.out.print("")
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			time++;
			System.out.print("\tgameTurn: "+gameTurn + "\n");
		}*/
		/*
		var selectedCell = player.getSelectedCell();
		// if selectedcell.placedbycomputer
		// if this is a human selected cell
		if(selectedCell.getCellOpt() == CellOpt.NULL) {
			// get the ui selected element for S O
			System.out.print("\tCellOpt was null, setting it to what is selected in GUI.\n");
			CellOpt option = (gameTurn == GameTurn.TurnRed) ? window.getRed_SO_Option() : window.getBlue_SO_Option();
			selectedCell.setCellOpt(option);
		}
		
		
		
		
		occupiedCells.add(selectedCell);
		
		gameboard.setSelectedCell(selectedCell.getX(), selectedCell.getY());
		// draw letter option that is passed in. 
		//    [ turning the enum to string, then turning string into char ]
		gameboard.drawLetter(selectedCell.getCellOpt().toString().charAt(0));  // <<<<<<< CALL THIS FROM GAMEBOARD INSTEAD??
		
		remainingCells--;
		
		gameboard.TESTgetSelectedCell().setBackground(Color.BLACK);
		
		gameboard.repaint();
		processMove(selectedCell, gameTurn);
		
		gameboard.repaint();
		updateUtility();
		
		gameboard.repaint();*/
	//}
	
	// generic-ish because it is designed to be called both by ai and gui
	// Abstract method that defines simple/general game win conditions
	public abstract void processMove();

	

	/**
	 * If this function detects selectedCell made an SOS, it calls gameboard to draw it
	 * @param selectedCell is logical cell user or computer selected 
	 * @return true if SOS is made, false otherwise
	 */
	protected boolean checkMadeSOS(CellLogical selectedCell) {

		int boardsize = gameStateManager.getBoardsize();
		
		int origRedSOSCount = gameStateManager.getRed_SOS_Count(), origBlueSOSCount = gameStateManager.getBlue_SOS_Count();

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
								if (potentialSCell.getCellOpt() == CellOpt.S && !gameStateManager.getPairList()
										.hasPair(selectedCell, grid.get(searchX + (2 * i)).get(searchY + (2 * j)))) {

									// creates a new SOS pair and hands it to gameboard to manage
									var newPair = new SOS_Pair(selectedCell, potentialSCell, gameStateManager.getCurrentGameTurn());
									
									SOS_Created_Event.invoke(newPair);

									// increment sos count
									if (gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) 
									{	gameStateManager.increment_Red_SOS_Count();	}
									else 
									{	gameStateManager.increment_Blue_SOS_Count(); }
									

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
										&& !gameStateManager.getPairList().hasPair(potentialSCell, inverseDirSCell)) {

									// creates a new SOS pair and hands it to gameboard to manage
									var newPair = new SOS_Pair(potentialSCell, inverseDirSCell, gameStateManager.getCurrentGameTurn());
									SOS_Created_Event.invoke(newPair);

									
									// increment sos count
									if (gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) 
									{	gameStateManager.increment_Red_SOS_Count();	}
									else 
									{	gameStateManager.increment_Blue_SOS_Count(); }

									continue;
								}
							}
						}
					} // end if-else selected == S or O
				} // end if surrounding is in gameboard
			} // end for j
		} // end for i

		// if an SOS was made, return true
		if (origRedSOSCount != gameStateManager.getRed_SOS_Count() || origBlueSOSCount != gameStateManager.getBlue_SOS_Count()) {
			return true;
		}
		return false;
	}
	
	public void updateUtility() {
		
		System.out.print("\n\n*************************************************** UPDATING UTILITY\n");
		
		
		int boardsize = gameStateManager.getBoardsize();
		
		for(int x = 0; x < boardsize; x++) {
			for(int y = 0; y < boardsize; y++) {
				
				CellLogical cellWork = grid.get(x).get(y);
				
				// if cell is already occupied, 
				// set util to 0 and move on
				if(cellWork.getCellOpt() != CellOpt.NULL) {
					cellWork.setO_Util(0);
					cellWork.setS_Util(0);
					continue;
				}
				
				// update edges and centers
				if(x == 0 || x == boardsize - 1
				|| y == 0 || y == boardsize - 1) {
					
					cellWork.setS_Util(2);
					cellWork.setO_Util(1);
					
				}
				else {
					
					cellWork.setS_Util(3);
					cellWork.setO_Util(3);
					
				}
				
				
				// check if borders sos
				
				
				
				// check if sos can actually form (especially oimportant like around edges)
				
				
				
			} // end inner for
		} // end outer for
		
		
		for(CellLogical occupiedCell : occupiedCells) {
			int searchX = occupiedCell.getX();
			int searchY = occupiedCell.getY();
			
			// to prevent double counting of the O util
			SOS_PairList OUtilPairs = new SOS_PairList();
			
			// for all surrounding cells
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					
					
					
					
					// don't check the center (i.e. the cell we clicked on)
					if (i == 0 && j == 0) {
						continue;
					}

					
					
						
					// if occupied cell is S
					if (occupiedCell.getCellOpt() == CellOpt.S) {
							
						// if in bounds of the gameboard
						if (((searchX + i) >= 0 && (searchX + i) < boardsize) 
						  && (searchY + j) >= 0 && (searchY + j) < boardsize) {
							
							CellLogical potentialOCell = grid.get(searchX + i).get(searchY + j);

							
							
							// if the other side of the potential O is in bounds
							if (((searchX + (2 * i)) >= 0 && (searchX + (2 * i)) < boardsize)
							  && (searchY + (2 * j)) >= 0 && (searchY + (2 * j)) < boardsize) {
								
								// reference to the other side of the potential O
								CellLogical potentialSCell = grid.get(searchX + (2 * i)).get(searchY + (2 * j));
								
								// check if cells surrounding the selected one has an O
								if (potentialOCell.getCellOpt() == CellOpt.O) {

									// check if cell on other side of O is an S
									if (potentialSCell.getCellOpt() == CellOpt.NULL) {

										// Potential S Cell would make SOS, thus it has max utility
										potentialSCell.setS_Util(potentialSCell.getS_Util() + 99);

										continue;
									}

								} 
								else if (potentialOCell.getCellOpt() == CellOpt.NULL) {
									// ^ if the center is a blank
									
									// if the other side of blank is an S
									if(potentialSCell.getCellOpt() == CellOpt.S
									) {
										// blank would make SOS if place an O there
										potentialOCell.setO_Util(potentialOCell.getO_Util() + 49);
										OUtilPairs.addPair(new SOS_Pair(occupiedCell, potentialSCell, gameStateManager.getCurrentGameTurn()));
									}
									else if (potentialSCell.getCellOpt() == CellOpt.NULL) {
										// ^ else if it is [S __ __ ]

										// Potential S Cell would make SOS, thus it has max utility
										//potentialSCell.setS_Util(potentialSCell.getS_Util() + 99);
										
										// We dont want to hand the opponent an sos so decentivize placing an O there
										if(potentialOCell.getO_Util() - 2 > 0) {
											potentialOCell.setO_Util(potentialOCell.getO_Util()-2); 
										}
										else if(potentialOCell.getO_Util() - 1 > 0) {
											potentialOCell.setO_Util(potentialOCell.getO_Util()-1); 
										}
										
										
										continue;
										
									}
									else if(potentialSCell.getCellOpt() == CellOpt.O) {
										// if [ S __ O ]
										
										// incentivize S and decentivize O
										potentialOCell.setS_Util(potentialOCell.getS_Util() + 1);
										
										if(potentialOCell.getO_Util() - 1 > 0) {
											potentialOCell.setO_Util(potentialOCell.getO_Util()-1); 
										}
									}
									
									
									// increment O util because an O bordering the S it would be a good place to play
									potentialOCell.setO_Util(potentialOCell.getO_Util() + 2);
									
								}
								

							} else { // else if not in bounds
								// if not in bounds, Potential O loses value bc can't make SOS
								if(potentialOCell.getO_Util() - 2 > 0) {
									potentialOCell.setO_Util(potentialOCell.getO_Util()-2); 
								}
								else if(potentialOCell.getO_Util() - 1 > 0) {
									potentialOCell.setO_Util(potentialOCell.getO_Util()-1); 
								}
							}
							

						}
					} else if (occupiedCell.getCellOpt() == CellOpt.O) {

						// if in bounds of the gameboard
						if (((searchX + i) >= 0 && (searchX + i) < boardsize) 
						  && (searchY + j) >= 0 && (searchY + j) < boardsize) {
								
						
							CellLogical potentialSCell = grid.get(searchX + i).get(searchY + j);

							
							// if In bounds
							if (((searchX + (-1 * i)) >= 0 && (searchX + (-1 * i)) < boardsize)
							  && (searchY + (-1 * j)) >= 0 && (searchY + (-1 * j)) < boardsize) {
							
								CellLogical inverseDirSCell = grid.get(searchX + (-1 * i)).get(searchY + (-1 * j));

								
								// check if cells surrounding the selected one has an O
								if (potentialSCell.getCellOpt() == CellOpt.S) {

									
									
								}
								else if (potentialSCell.getCellOpt() == CellOpt.NULL){
									
									// check if the inverse direction is an O
									// [ O O __ ]
									if (inverseDirSCell.getCellOpt() == CellOpt.O) {

										if(potentialSCell.getS_Util() - 2 > 0)
											potentialSCell.setS_Util(potentialSCell.getS_Util() - 2);
										else if (potentialSCell.getS_Util() - 1 > 0)
											potentialSCell.setS_Util(potentialSCell.getS_Util() - 1);
										
										//inverseDirSCell.setS_Util(99);
									
										continue;
									}
									else if (inverseDirSCell.getCellOpt() == CellOpt.NULL) {
										
									}
									
								}
							}
							else {
								// if inverse is not in bounds
								// potentialSCell's worth lowers
								if(potentialSCell.getS_Util() - 2 > 0)
									potentialSCell.setS_Util(potentialSCell.getS_Util() - 2);
								else if (potentialSCell.getS_Util() - 1 > 0)
									potentialSCell.setS_Util(potentialSCell.getS_Util() - 1);
								
							}
						}
					} // end if-else selected == S or O
				} // end if surrounding is in gameboard
			} // end for j
		} // end FOR OCCUPIED CELLS
					
	
		
		// reconfirm not overwriting an already occupied cell
		for(int x = 0; x < boardsize; x++) {
			for(int y = 0; y < boardsize; y++) {
				
				CellLogical cellWork = grid.get(x).get(y);
				
				// if cell is already occupied, 
				// set util to 0 and move on
				if(cellWork.getCellOpt() != CellOpt.NULL) {
					cellWork.setO_Util(0);
					cellWork.setS_Util(0);
					continue;
				}
			}
		}
		
		// draw this FOR TESTING PURPOSES {{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{
		for(int x = 0; x < boardsize; x++) { for(int y = 0; y < boardsize; y++) { CellLogical cellWork = grid.get(x).get(y); Update_Utility_Event.invoke(x, y, cellWork.getS_Util(), cellWork.getO_Util());}}
		//var arr1 = getTopSUtil(5);
		//var arr2 = getTopOUtil(5);
		/*for(CellLogical c : arr1) {
			System.out.print("("+c.getX()+","+c.getY()+") ["+c.getS_Util()+","+c.getO_Util()+"]\n");
		}
		System.out.print("______\n");
		for(CellLogical c : arr2) {
			System.out.print("("+c.getX()+","+c.getY()+") ["+c.getS_Util()+","+c.getO_Util()+"]\n");
		}
		System.out.print("\n\n\n");*/
	}

	
	
	public ArrayList<CellLogical> getTopSUtil(int numberOfCellsToReturn){
		
		int boardsize = gameStateManager.getBoardsize();
		
		var arrayOfMaxes = new ArrayList<CellLogical>();
		for(int i = 0; i < numberOfCellsToReturn; i++) {
			
			CellLogical max = grid.get(0).get(0);
			
			for(int x = 0; x < boardsize; x++) {
				for(int y = 0; y < boardsize; y++) {
					
					var currentCell = grid.get(x).get(y);
					
					if(currentCell.getS_Util() > max.getS_Util()
					&& !arrayOfMaxes.contains(currentCell)) {
						max = currentCell;
					}
					
				}
			}
			
			arrayOfMaxes.add(max);
		}
		
		return arrayOfMaxes;
	}
	
	
	public ArrayList<CellLogical> getTopOUtil(int numberOfCellsToReturn){
		
		int boardsize = gameStateManager.getBoardsize();
		
		var arrayOfMaxes = new ArrayList<CellLogical>();
		for(int i = 0; i < numberOfCellsToReturn; i++) {
			
			CellLogical max = grid.get(0).get(0);
			
			for(int x = 0; x < boardsize; x++) {
				for(int y = 0; y < boardsize; y++) {
					
					var currentCell = grid.get(x).get(y);
					
					if(currentCell.getO_Util() > max.getO_Util()
					&& !arrayOfMaxes.contains(currentCell)) {
						max = currentCell;
					}
					
				}
			}
			
			arrayOfMaxes.add(max);
		}
		
		return arrayOfMaxes;
	}
	
	
	
	@Override
	public void onNewGame() {
		
		gameStateManager.setGameTurn(GameTurn.TurnRed);
		
		gameStateManager.setRedIsComputer(false);
		gameStateManager.setBlueIsComputer(false);
		
		gameStateManager.setRed_SOS_Count(0);
		gameStateManager.setBlue_SOS_Count(0);	
		
		gameStateManager.setPairList(new SOS_PairList());
		
		reset();
	}
	
	
	
	
	void reset() {
		occupiedCells = new ArrayList<CellLogical>();
		
	}
	
	
	
}
