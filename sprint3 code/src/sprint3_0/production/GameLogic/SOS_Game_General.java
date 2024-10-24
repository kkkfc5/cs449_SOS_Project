package sprint3_0.production.GameLogic;

public class SOS_Game_General extends SOS_Game_Main {
	
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
	public void processMove(CellOpt option, int x, int y, GameTurn turn) {
		CellLogical selectedLogiCell = grid.get(x).get(y);
		
		selectedLogiCell.setCellOpt(option);
		occupiedCells.add(selectedLogiCell);
		
		// draw letter option that is passed in. 
		//    [ turning the enum to string, then turning string into char ]
		gameboard.drawLetter(option.toString().charAt(0));  		
		
		remainingCells--;
		
		if (checkMadeSOS(selectedLogiCell)) {
			// DONT TOGGLE TURN
			
			if(remainingCells < 1) {
				gameboard.toggleCellsEnabled();
				ToggleTurn();
			}
		}
		else { // if didn't make sos
			ToggleTurn();
		}
	}
}
