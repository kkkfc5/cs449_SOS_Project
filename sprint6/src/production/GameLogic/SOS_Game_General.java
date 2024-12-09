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
	//public void processMove() {
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
		
		
		
			
			

		
		// ELSE IF CELL IS OCCUPIED
		// then there was a bad input OR control swapped from computer to human.
		// return out of the input loop.
	//}
	


	@Override
	public boolean moveEndedGame(CellLogical selectedCell) {
		if (checkMadeSOS(selectedCell)) {
			// DONT TOGGLE TURN

			if (gameStateManager.getRemainingCells() < 1) {
				//Game_Over_Event.invoke();
				return true;
			}
			else {
				return false;
			}

			

		} else { // if didn't make sos
			if(gameStateManager.getRemainingCells() < 1) {
				//Game_Over_Event.invoke();
				return true;
			} else {
			//Toggle_Turns_Event.invoke();
				return false;
			}
		}
	}
	

	

	@Override
	public void onToggleTurns() {
		// TODO Auto-generated method stub
		if(gameStateManager.getGameType() == GameType.General) {
			
			updateUtility();
			
			if((gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed && gameStateManager.isRedComputer()) ||
			   (gameStateManager.getCurrentGameTurn() == GameTurn.TurnBlue && gameStateManager.isBlueComputer())) {
				// invoke computer?
				processMove();
				
			}
		}
	}
	
	@Override
	public void onPlayerClick() {

		// if the active game is general, then process the move
		if (gameStateManager.getGameType() == GameType.General) {
			processMove();
		}

	}
	
	
	@Override
	public void onToggleDisplayUtils(){
		if(gameStateManager.getGameType() == GameType.General)
			displayUtility(gameStateManager.shouldShowUtils());
	}
}
