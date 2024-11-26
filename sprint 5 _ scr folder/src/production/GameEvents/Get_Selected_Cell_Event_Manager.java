package production.GameEvents;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import production.GameLogic.CellLogical;
import production.GameLogic.GameStateManager;
import production.GameLogic.GameStateManager.GameTurn;
import production.GameLogic.SOS_PairList;
import production.GameLogic.SOS_Player_Computer;
import production.GameLogic.SOS_Player_Human;
import production.GameLogic.SOS_Player_Main;

public class Get_Selected_Cell_Event_Manager {
	
	public interface Get_Selected_Cell_Event {
		CellLogical onGetSelectedCell();
	}
	
	GameStateManager gameStateManager;
	SOS_Player_Human player_Human;
	SOS_Player_Computer player_Computer;
	
	public void connectGameStateManager(GameStateManager g) {
		gameStateManager = g;
	}
	public void connectPlayerHuman(SOS_Player_Human g) {
		player_Human = g;
	}
	public void connectPlayerComputer(SOS_Player_Computer g) {
		player_Computer = g;
	}
	
	
	public CellLogical invoke() {
		if(gameStateManager.getGameTurn() == GameTurn.TurnRed
		&& gameStateManager.isRedComputer()) {
				return player_Computer.getSelectedCell();
		}
		else if(gameStateManager.getGameTurn() == GameTurn.TurnBlue
				&& gameStateManager.isBlueComputer()) {
			return player_Computer.getSelectedCell();
		}
		
		//JOptionPane.showMessageDialog(null, "Getselected cell event manager:  Running return playerHuman's get selected cell. Cellopt: " + player_Human.getSelectedCell().getCellOpt());
		
		return player_Human.getSelectedCell();
	}
}
