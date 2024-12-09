package production.GameEvents;

import java.util.ArrayList;

import production.GameLogic.CellLogical;

public class Draw_Cell_Event_Manager {
	
	public interface Draw_Cell_Event {
		void onDrawCell(CellLogical dataCell);
	}
	
	ArrayList<Draw_Cell_Event> listeners = new ArrayList<Draw_Cell_Event>();
	
	public void addListener(Draw_Cell_Event g) {
		listeners.add(g);
	}
	
	public void invoke(CellLogical dataCell) {
		for(Draw_Cell_Event listener : listeners) {
			listener.onDrawCell(dataCell);
		}
	}
}
