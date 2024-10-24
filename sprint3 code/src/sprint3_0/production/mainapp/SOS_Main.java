package sprint3_0.production.mainapp;


import sprint3_0.production.GUI.Gameboard;
import sprint3_0.production.GUI.SOS_Window;
import sprint3_0.production.GameLogic.SOS_Game_Main;
import sprint3_0.production.GameLogic.SOS_Game_Simple;

public class SOS_Main {

	public static void main(String[] args) {
		
		// create default game
		// create gui window
		// connect the two
		var gameLogic = new SOS_Game_Simple();
		var GUI_Window = new SOS_Window(gameLogic);

		gameLogic.connectGUIWindow(GUI_Window);

	}
}
