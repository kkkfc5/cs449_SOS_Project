package production.GameEvents;

import java.util.ArrayList;



public class Game_Over_Event_Manager {

	public interface Game_Over_Event {
		void onGameOver();
	}
	
	ArrayList<Game_Over_Event> listeners = new ArrayList<Game_Over_Event>();
	
	public void addListener(Game_Over_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(Game_Over_Event listener : listeners) {
			listener.onGameOver();
		}
	}
}
