package production.GameEvents;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import production.GameLogic.GameStateManager.GameType;
import production.GameLogic.SOS_Game_General;
import production.GameLogic.SOS_Game_Main;
import production.GameLogic.SOS_Game_Simple;

public class Initiate_Game_Event_Manager {

	SOS_Game_Simple gameSimple;
	SOS_Game_General gameGeneral;
	
	public interface Initiate_Game_Event {
		void onInitiateGame(SOS_Game_Main game);
	}
	
	ArrayList<Initiate_Game_Event> listeners = new ArrayList<Initiate_Game_Event>();
	
	public void addListener(Initiate_Game_Event g) {
		listeners.add(g);
	}
	
	public void addSimpleGame(SOS_Game_Simple g) {
		gameSimple = g;
	}
	public void addGeneralGame(SOS_Game_General g) {
		gameGeneral = g;
	}
	
	public void invoke(GameType gameType) {
		//JOptionPane.showMessageDialog(null, "CONNECTING GAME Initiate_Game_Event_Manager.Invoke();");
		for(Initiate_Game_Event listener : listeners) {
			//System.out.print(listener + "\n");
			if(gameType == GameType.Simple)
				listener.onInitiateGame(gameSimple);
			else
				listener.onInitiateGame(gameGeneral);
		}
	}
}
