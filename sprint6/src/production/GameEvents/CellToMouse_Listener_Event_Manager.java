package production.GameEvents;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import production.GameLogic.SOS_Player_Human;



public class CellToMouse_Listener_Event_Manager {

	public interface CellToMouse_Event {
		void onCellToMouseConnect(SOS_Player_Human human);
		void onCellToMouseDisconnect(SOS_Player_Human human);
	}
	
	ArrayList<CellToMouse_Event> listeners = new ArrayList<CellToMouse_Event>();
	
	public void addListener(CellToMouse_Event g) {
		listeners.add(g);
	}
	
	public void invokeConnect(SOS_Player_Human human) {
		for(CellToMouse_Event listener : listeners) {
			//JOptionPane.showMessageDialog(null, listener);
			listener.onCellToMouseConnect(human);
		}
	}
	public void invokeDisconnect(SOS_Player_Human human) {
		for(CellToMouse_Event listener : listeners) {
			listener.onCellToMouseDisconnect(human);
		}
	}
}
