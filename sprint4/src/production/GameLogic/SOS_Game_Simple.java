package production.GameLogic;

import javax.swing.JOptionPane;

import production.GameLogic.GameStateManager.*;
import production.GameEvents.SOS_Created_Event_Manager.SOS_Created_Event;;

public class SOS_Game_Simple extends SOS_Game_Main  {
	
	
	/**
	 * Preconditions:
	 * validates that X and Y are actual cells on the grid
	 * 
	 * Passes in:
	 * The option to draw, the grid location of the cell, and who it is
	 * 
	 * Outcomes:
	 * draws letter on the grid, if makesSOS it will call that function to handle it. 
	 * If so, prevent toggle turn, but check if the SOS was the last cell placed.
	 */
	public void processMove() {
		/*
		CellLogical selectedCell = grid.get(x).get(y);
		
		selectedCell.setCellOpt(option);
		
		 * occupiedCells.add(selectedCell);
		 * 
		 * // draw letter option that is passed in. // [ turning the enum to string,
		 * then turning string into char ]
		 * gameboard.drawLetter(option.toString().charAt(0)); // <<<<<<< CALL THIS FROM
		 * GAMEBOARD INSTEAD??
		 * 
		 * remainingCells--;
		 */
		
		

		// get the selected cell from the player object
		CellLogical selectedCell = Get_Selected_Cell_Event.invoke();

		// protects against error happening when clicked computer before placing a cell via human
		if(selectedCell == null) return;
		//System.out.print(selectedCell+"\n");
		
		if (isCellUnoccupied(selectedCell.getX(), selectedCell.getY())) {
			
			//JOptionPane.showMessageDialog(null, "SIMPLE GAME, CELL WAS _NOT_ OCCUPIED");
			
			//System.out.print(gameStateManager.getRemainingCells());
			
			
			// update and replace selected cell if it isn't already
			// a cell on the board.
			selectedCell = validateCell(selectedCell);
			
			//JOptionPane.showMessageDialog(null, se);
			gameStateManager.decrementRemainingCells();
			Draw_Cell_Event.invoke(selectedCell);
			occupiedCells.add(selectedCell);
			

			//System.out.print( " "+ gameStateManager.getRemainingCells() + "\n");
			
			if (checkMadeSOS(selectedCell)) {
				// DONT TOGGLE TURN

				// if made SOS, then the game is over
				Game_Over_Event.invoke();
				
			} else { // if didn't make sos
				if(gameStateManager.getRemainingCells() < 1) {
					Game_Over_Event.invoke();
				} else {
				Toggle_Turns_Event.invoke();
				}
			}

		}
		else {
			
			//JOptionPane.showMessageDialog(null, "SIMPLE GAME, CELL WAS OCCUPIED\n(" + selectedCell.getX() + "," + selectedCell.getY() + ")");
		}
		
		
		// ============= 
		
		/*
		
		int red_SOS_Count = gameStateManager.getRed_SOS_Count();
		int blue_SOS_Count = gameStateManager.getBlue_SOS_Count();
		
		System.out.print("    SOS_Game_Simple: entered processMove\n");
		
		if (checkMadeSOS(selectedCell)) {
			// DONT TOGGLE TURN
			
			System.out.print("\n\tSIMPLE GAME END:\n\t  red_SOS_Count: " + red_SOS_Count 
					     + "\n\t  blue_SOS_Count: " + blue_SOS_Count + "\n");
			
			Game_Over_Event.invoke();
			
			
		} else {
			System.out.print("\tToggling turn. \n");
			//ToggleTurn();
			Toggle_Turns_Event.invoke();
		}
		
		*/
	}
	
	@Override
	public void onToggleTurns() {
//		// TODO Auto-generated method stub
//		if(gameStateManager.getGameType() == GameType.Simple) {
//			updateUtility();
//		}
		
		//System.out.print("Game simple toggle turns. Current turn: " + gameStateManager.getCurrentGameTurn() );
		if(gameStateManager.getGameType() == GameType.Simple) {
		if((gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed && gameStateManager.isRedComputer()) ||
		   (gameStateManager.getCurrentGameTurn() == GameTurn.TurnBlue && gameStateManager.isBlueComputer())) {
			// invoke computer?
			processMove();
			
		}
		updateUtility();
		}
	}
	
	@Override
	public void onPlayerClick() {
		
		// if the active game is simple, then process the move
		if(gameStateManager.getGameType() == GameType.Simple) {
			//System.out.print("playerlicked Simple\n");
			processMove();
		}
		
	}

	

	
}
