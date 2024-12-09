package production.GameEvents;

import java.util.ArrayList;


public class Toggle_Display_Utils_Event_Manager {
	public interface Toggle_Display_Utils_Event {
		void onToggleDisplayUtils();
	}
	
	ArrayList<Toggle_Display_Utils_Event> listeners = new ArrayList<Toggle_Display_Utils_Event>();
	
	public void addListener(Toggle_Display_Utils_Event g) {
		listeners.add(g);
	}
	
	public void invoke() {
		for(Toggle_Display_Utils_Event listener : listeners) {
			listener.onToggleDisplayUtils();
		}
	}
}
