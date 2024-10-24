package sprint3_0.production.GameLogic;

import javax.swing.JOptionPane;

public class SOS_Game_Simple extends SOS_Game_Main {
	
	
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
		CellLogical selectedCell = grid.get(x).get(y);
		
		selectedCell.setCellOpt(option);
		occupiedCells.add(selectedCell);
		
		// draw letter option that is passed in. 
		//    [ turning the enum to string, then turning string into char ]
		gameboard.drawLetter(option.toString().charAt(0));  // <<<<<<< CALL THIS FROM GAMEBOARD INSTEAD??
		
		remainingCells--;
		
		if (checkMadeSOS(selectedCell)) {
			// DONT TOGGLE TURN
			
			if(red_SOS_Count > 0 || blue_SOS_Count > 0) {
				// SIMPLE GAME END
				gameboard.toggleCellsEnabled();
				window.setWinnerText(gameTurn);
				
				if(red_SOS_Count > blue_SOS_Count) {
					gameWinState = GameWinState.RedWin;
				}
				else if(blue_SOS_Count > red_SOS_Count) {
					gameWinState = GameWinState.BlueWin;
				}
				else {
					gameWinState = GameWinState.Tie;
				}
			}
			
		} else {
			ToggleTurn();
		}
	}
}
