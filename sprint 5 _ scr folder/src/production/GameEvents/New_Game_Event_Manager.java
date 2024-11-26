package production.GameEvents;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class New_Game_Event_Manager {

	public interface New_Game_Event {
		void onNewGame();
	}
	
	ArrayList<New_Game_Event> listeners = new ArrayList<New_Game_Event>();
	
	public void addListener(New_Game_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(New_Game_Event listener : listeners) {
			listener.onNewGame();
		}
	}
}
