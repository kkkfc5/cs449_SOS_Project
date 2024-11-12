package production.GameLogic;

public abstract class SOS_Player_Main {
	CellLogical selectedCell;
	GameStateManager gameStateManager;
	
	//public abstract void getMove();
	public void connectGameStateManager(GameStateManager g) {
		gameStateManager = g;
	}
	
	
	public abstract CellLogical getSelectedCell();
}
