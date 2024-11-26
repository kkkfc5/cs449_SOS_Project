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
	//public void processMove() {

//		
//		// if passed in cell is null
//		//if(selectedCell == null) {
//			// get the selected cell from the player object
//			CellLogical selectedCell = Get_Selected_Cell_Event.invoke();
//		//}
//
//		// protects against error happening when clicked computer before placing a cell via human
//		if(selectedCell == null) return;
//		
//		if (isCellUnoccupied(selectedCell.getX(), selectedCell.getY())) {
//		
//			// update and replace selected cell if it isn't already
//			// a cell on the board.
//			selectedCell = validateCell(selectedCell);
//			
//			gameStateManager.decrementRemainingCells();
//			Draw_Cell_Event.invoke(selectedCell);
//			occupiedCells.add(selectedCell);
//			
			
//			if (checkMadeSOS(selectedCell)) {
//				// DONT TOGGLE TURN
//
//				// if made SOS, then the game is over
//				Game_Over_Event.invoke();
//				
//			} else { // if didn't make sos
//				if(gameStateManager.getRemainingCells() < 1) {
//					Game_Over_Event.invoke();
//				} else {
//					Toggle_Turns_Event.invoke();
//				}
//			}

//		}
//		else {
//			
//			//JOptionPane.showMessageDialog(null, "SIMPLE GAME, CELL WAS OCCUPIED\n(" + selectedCell.getX() + "," + selectedCell.getY() + ")");
//		}
//	}
	//


	
	@Override
	public boolean moveEndedGame(CellLogical selectedCell) {
		
		if (checkMadeSOS(selectedCell)) {
			// DONT TOGGLE TURN
			
			return true;
			// if made SOS, then the game is over
			//Game_Over_Event.invoke();
			
		} else { // if didn't make sos
			if(gameStateManager.getRemainingCells() < 1) {
				//Game_Over_Event.invoke();
				return true;
			} else {
				return false;
				//Toggle_Turns_Event.invoke();
			}
		}
			
	}
	
	
	
	
	
	
	
	
	
	@Override
	public void onToggleTurns() {
//		// TODO Auto-generated method stub
//		if(gameStateManager.getGameType() == GameType.Simple) {
//			updateUtility();
//		}
		
		//System.out.print("Game simple toggle turns. Current turn: " + gameStateManager.getCurrentGameTurn() );
		if(gameStateManager.getGameType() == GameType.Simple) {
			
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
		
		// if the active game is simple, then process the move
		if(gameStateManager.getGameType() == GameType.Simple) {
			processMove();
		}
		
	}

	@Override
	public void onToggleDisplayUtils(){
		if(gameStateManager.getGameType() == GameType.Simple)
			displayUtility(gameStateManager.shouldShowUtils());
	}

	
}
