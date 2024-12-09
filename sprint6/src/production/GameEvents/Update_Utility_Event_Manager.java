package production.GameEvents;

import java.util.ArrayList;

import production.GameEvents.SOS_Created_Event_Manager.SOS_Created_Event;
import production.GameLogic.SOS_Pair;

public class Update_Utility_Event_Manager {

	public interface Update_Utility_Event {
		void onUpdateUtility(int x, int y, int s, int o);
	}
	
	ArrayList<Update_Utility_Event> listeners = new ArrayList<Update_Utility_Event>();
	
	public void addListener(Update_Utility_Event e) {
		listeners.add(e);
	}
	
	public void invoke(int x, int y, int s, int o) {
		for(Update_Utility_Event listener : listeners) {
			listener.onUpdateUtility(x, y, s, o);
		}
	}
}
