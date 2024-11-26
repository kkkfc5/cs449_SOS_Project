package production.GameEvents;

import java.util.ArrayList;

import production.GameLogic.GameStateManager.CellOpt;


public class Get_Selected_SO_Event_Manager {

	public interface Get_Selected_SO_Event {
		CellOpt onGetSelectedSO();
	}
	
	ArrayList<Get_Selected_SO_Event> listeners = new ArrayList<Get_Selected_SO_Event>();
	
	public void addListener(Get_Selected_SO_Event g) {
		listeners.add(g);
	}
	
	public CellOpt invoke() {
		for(Get_Selected_SO_Event listener : listeners) {
			return listener.onGetSelectedSO();
		}
		return null;
	}
}
