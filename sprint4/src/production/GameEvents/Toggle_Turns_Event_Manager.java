package production.GameEvents;

import java.util.ArrayList;


public class Toggle_Turns_Event_Manager {

	public interface Toggle_Turns_Event {
		void onToggleTurns();
	}
	
	ArrayList<Toggle_Turns_Event> listeners = new ArrayList<Toggle_Turns_Event>();
	
	public void addListener(Toggle_Turns_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(Toggle_Turns_Event listener : listeners) {
			listener.onToggleTurns();
		}
	}
}
