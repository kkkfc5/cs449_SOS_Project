package sprint1_0.production.mainapp;


import sprint1_0.production.GUI.Gameboard;
import sprint1_0.production.GUI.SOS_Window;
import sprint1_0.production.GameLogic.GameLogicManager;

public class SOS_Game {

	public static void main(String[] args) {
		
		//final int DEFAULT_GAMEBOARD_SIZE = 5;
		//final int DEFAULT_GAMEBOARD_DIMENSIONS = 300;
		
		
		//var gameBoard = new Gameboard(DEFAULT_GAMEBOARD_SIZE, DEFAULT_GAMEBOARD_DIMENSIONS);
		
		var gameLogic = new GameLogicManager();
		
		var GUI_Window = new SOS_Window(gameLogic);
		
		
		//gameBoard.connectGameLogicManager(gameLogic);
		gameLogic.connectGUIWindow(GUI_Window);
		//GUI_Window.connectGameLogicManager(gameLogic);
		
		/*
		gameLogic.toggleTurnsEvent.AddListener(GUI_Window);
		gameLogic.toggleTurnsEvent.AddListener(gameLogic);
		
		
		gameLogic.GameLoop();*/
	}

}
