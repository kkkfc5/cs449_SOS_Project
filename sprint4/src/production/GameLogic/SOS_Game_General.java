package production.GameLogic;

import javax.swing.JOptionPane;

import production.GameLogic.GameStateManager.GameTurn;
import production.GameLogic.GameStateManager.GameType;

public class SOS_Game_General extends SOS_Game_Main {

	/**
	 * Preconditions: validates that X and Y are actual cells on the grid
	 * 
	 * Passes in: The option to draw, the grid location of the cell, and who it is
	 * 
	 * Outcomes: draws letter on the grid, if makesSOS it will call that function to
	 * handle it. If so, prevent toggle turn, but check if the SOS was the last cell
	 * placed.
	 */
	public void processMove() {
		/*
		 * CellLogical selectedLogiCell = grid.get(x).get(y);
		 * 
		 * selectedLogiCell.setCellOpt(option); occupiedCells.add(selectedLogiCell);
		 * 
		 * // draw letter option that is passed in. // [ turning the enum to string,
		 * then turning string into char ]
		 * gameboard.drawLetter(option.toString().charAt(0));
		 * 
		 * remainingCells--;
		 */
		
		
		// get the selected cell from the player object
		CellLogical selectedCell = Get_Selected_Cell_Event.invoke();

		
		if(selectedCell == null) return;
		
		if (isCellUnoccupied(selectedCell.getX(), selectedCell.getY())) {
			
			
			// update and replace selected cell if it isn't already
			// a cell on the board.
			selectedCell = validateCell(selectedCell);
			
			
			gameStateManager.decrementRemainingCells();
			Draw_Cell_Event.invoke(selectedCell);
			occupiedCells.add(selectedCell);
			
			if (checkMadeSOS(selectedCell)) {
				// DONT TOGGLE TURN

				if (gameStateManager.getRemainingCells() < 1) {
					Game_Over_Event.invoke();
					return;
				}

				updateUtility();
				// if the game is continuing and hasn't ended,
				// have the game process again.
				processMove();

			} else { // if didn't make sos
				if(gameStateManager.getRemainingCells() < 1) {
					Game_Over_Event.invoke();
				} else {
				Toggle_Turns_Event.invoke();
				}
			}

		}else {
			//JOptionPane.showMessageDialog(null, "GENERAL : FAILED\n" + selectedCell.getX() + "," + selectedCell.getY());
		}
		// ELSE IF CELL IS OCCUPIED
		// then there was a bad input OR control swapped from computer to human.
		// return out of the input loop.
	}

	@Override
	public void onToggleTurns() {
		// TODO Auto-generated method stub
		if(gameStateManager.getGameType() == GameType.General) {
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

		// if the active game is general, then process the move
		if (gameStateManager.getGameType() == GameType.General) {
			processMove();
		}

	}
}
