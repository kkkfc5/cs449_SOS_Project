package production.GameEvents;

import java.util.ArrayList;


public class Player_Clicked_Event_Manager {
	
	public interface Player_Clicked_Event{
		void onPlayerClick();
	}

	ArrayList<Player_Clicked_Event> listeners = new ArrayList<Player_Clicked_Event>();
	
	public void addListener(Player_Clicked_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(Player_Clicked_Event listener : listeners) {
			listener.onPlayerClick();
		}
	}
}
