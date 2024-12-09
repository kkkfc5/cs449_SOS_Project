package production.GameEvents;

import java.util.ArrayList;

public class Redo_Event_Manager {
	public interface Redo_Event {
		void onRedo();
	}
	
	ArrayList<Redo_Event> listeners = new ArrayList<Redo_Event>();
	
	public void addListener(Redo_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(Redo_Event listener : listeners) {
			listener.onRedo();
		}
	}
}
