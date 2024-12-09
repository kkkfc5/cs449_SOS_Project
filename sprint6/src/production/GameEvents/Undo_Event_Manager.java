package production.GameEvents;

import java.util.ArrayList;


public class Undo_Event_Manager {
	public interface Undo_Event {
		void onUndo();
	}
	
	ArrayList<Undo_Event> listeners = new ArrayList<Undo_Event>();
	
	public void addListener(Undo_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(Undo_Event listener : listeners) {
			listener.onUndo();
		}
	}
}
