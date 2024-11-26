package production.GameLogic;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import production.GUI.CellPanel;
import production.GameEvents.CellToMouse_Listener_Event_Manager;
import production.GameEvents.Get_Selected_SO_Event_Manager;
import production.GameEvents.Player_Clicked_Event_Manager;
import production.GameEvents.Game_Over_Event_Manager.Game_Over_Event;
import production.GameEvents.Get_Selected_SO_Event_Manager.Get_Selected_SO_Event;
import production.GameEvents.Initiate_Game_Event_Manager.Initiate_Game_Event;
import production.GameEvents.New_Game_Event_Manager.New_Game_Event;
import production.GameEvents.Player_Clicked_Event_Manager.Player_Clicked_Event;
import production.GameLogic.GameStateManager.CellOpt;

public class SOS_Player_Human extends SOS_Player_Main implements MouseListener, New_Game_Event, Game_Over_Event {
	
	
	CellToMouse_Listener_Event_Manager CellToMouse_Listener_Event;
	Player_Clicked_Event_Manager Player_Clicked_Event;
	Get_Selected_SO_Event_Manager Get_Selected_SO_Event;
	
	//public SOS_Player_Human(SOS_Game_Main g){
	//	game = g;
	//}
	public void connectCellToMouseListenerEventManager(CellToMouse_Listener_Event_Manager g) {
		CellToMouse_Listener_Event = g;
	}
	public void connectPlayerClickEventManager(Player_Clicked_Event_Manager g) {
		Player_Clicked_Event = g;
	}
	public void connectGetSelectedSOEventManager(Get_Selected_SO_Event_Manager g) {
		Get_Selected_SO_Event = g;
	}
	
	
	@Override
	public CellLogical getSelectedCell() {
		return selectedCell;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		var selectedCellPanel = (CellPanel) e.getSource();
		//if(game.isCellUnoccupied(selectedCellPanel.getGridX(), selectedCellPanel.getGridY())) {
			
			//game.updateGameboardSelectedCell((CellPanel) e.getSource());
			//selectedCell = game.getCellAt(selectedCellPanel.getGridX(), selectedCellPanel.getGridY());
			//selectedCell.setCellOpt(null);
		//	game.processMove();
		//}
		if(selectedCell == null) {
			selectedCell = new CellLogical();
		}
		
		selectedCell.setX(selectedCellPanel.getGridX());
		selectedCell.setY(selectedCellPanel.getGridY());
		selectedCell.setCellOpt(Get_Selected_SO_Event.invoke());
		
		//JOptionPane.showMessageDialog(null, "mouseclicked human, cellopt: " + selectedCell.getCellOpt());
		
		Player_Clicked_Event.invoke();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	@Override
	public void onNewGame() {
		//JOptionPane.showMessageDialog(null,"HUMAN onNewGame()");
		selectedCell = null;
		CellToMouse_Listener_Event.invokeConnect(this);
	}
	
	@Override
	public void onGameOver() {
		CellToMouse_Listener_Event.invokeDisconnect(this);
	}
	
	
	// ==========================================
	public void test_selectCell(int x, int y, CellOpt opt) {
		var tempCell = new CellLogical();
		tempCell.setX(x);
		tempCell.setY(y);
		tempCell.setCellOpt(opt);
		
		selectedCell = tempCell;
	}
	
}
