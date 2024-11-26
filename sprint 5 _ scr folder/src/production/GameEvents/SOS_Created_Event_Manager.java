package production.GameEvents;

import java.util.ArrayList;

import production.GameLogic.SOS_Pair;


public class SOS_Created_Event_Manager {

	public interface SOS_Created_Event {
		void onSOSCreation(SOS_Pair pair);
	}
	
	ArrayList<SOS_Created_Event> listeners = new ArrayList<SOS_Created_Event>();
	
	public void addListener(SOS_Created_Event e) {
		listeners.add(e);
	}
	
	public void invoke(SOS_Pair pair) {
		for(SOS_Created_Event listener : listeners) {
			listener.onSOSCreation(pair);
		}
	}
	
}
