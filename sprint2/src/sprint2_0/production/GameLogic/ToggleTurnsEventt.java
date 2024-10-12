package sprint1_0.production.GameLogic;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import sprint1_0.production.GUI.SOS_Window;

public class ToggleTurnsEventt {
	
	ArrayList<ToggleTurnsInterfacee> listeners;
	/*
	GameLogicManager gameLogicManager;
	SOS_Window sosWindow;
	
	public ToggleTurnsEvent(SOS_Window s, GameLogicManager g) {
		gameLogicManager = g;
		sosWindow = s;
	}
	*/
	public ToggleTurnsEventt() {
		listeners = new ArrayList<ToggleTurnsInterfacee>();
	}
	
	public void InvokeEvent() {
		for(ToggleTurnsInterfacee ln : listeners) {
			ln.ToggleTurn();
		}
	}
	
	public boolean AddListener(ToggleTurnsInterfacee t) {
		try {
			listeners.add(t);
			return true;
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
}
