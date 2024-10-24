package production.mainapp;


import production.GUI.Gameboard;
import production.GUI.SOS_Window;
import production.GameLogic.SOS_Game_Main;
import production.GameLogic.SOS_Game_Simple;

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
