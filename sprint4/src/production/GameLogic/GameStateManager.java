package production.GameLogic;

import javax.swing.JOptionPane;

import production.GameEvents.Initiate_Game_Event_Manager.Initiate_Game_Event;
import production.GameEvents.New_Game_Event_Manager.New_Game_Event;
import production.GameEvents.Toggle_Turns_Event_Manager.Toggle_Turns_Event;
import production.GameLogic.GameStateManager.GameTurn;
import production.GameLogic.GameStateManager.GameType;
import production.GameLogic.GameStateManager.GameWinState;

public class GameStateManager implements Toggle_Turns_Event, Initiate_Game_Event {
	// ==============================

		public enum GameType {
			// Game Type
			Simple, 
			General
		}

		public enum GameTurn {
			TurnRed, 
			TurnBlue
		}

		public enum GameWinState {
			InProgress, 
			RedWin, 
			BlueWin, 
			Tie
		}

		public enum CellOpt {
			S, 
			O, 
			NULL
		}
		
		public enum PlacedBy {
			Human,
			Computer
		}

		// ==============================
		
		SOS_Game_Main currentGame;
		
		GameType gameType;
		GameTurn gameTurn;
		GameWinState gameWinState;
		//CellOpt cellOpt;
		
		int boardsize;
		int gameboardDimensions;
		int remainingCells;
		// --
		
		
		int red_SOS_Count, blue_SOS_Count;
		
		// computer
		boolean redIsComputer, blueIsComputer;
		int redSliderValue, blueSliderValue;
		
		// 
		SOS_PairList pairList;
		
		
		public GameStateManager() {
			gameType = GameType.Simple;
			gameTurn = GameTurn.TurnRed;
			gameWinState = GameWinState.InProgress;
			
			boardsize = 5;
			gameboardDimensions = 300;
			remainingCells = boardsize*boardsize;
			
			red_SOS_Count = 0;
			blue_SOS_Count = 0;
			
			redIsComputer = false;
			blueIsComputer = false;
			redSliderValue = 2;
			blueSliderValue = 2;
			
			pairList = new SOS_PairList();
		}

		public void addPair(SOS_Pair p) {
			pairList.addPair(p);
		}
		
		public int getRemainingCells() {
			return remainingCells;
		}
		public void decrementRemainingCells() {
			remainingCells--;
		}


		public int getRed_SOS_Count() {
			return red_SOS_Count;
		}



		public int getBlue_SOS_Count() {
			return blue_SOS_Count;
		}



		public SOS_PairList getPairList() {
			return pairList;
		}



		public void setRemainingCells(int remainingCells) {
			this.remainingCells = remainingCells;
		}



		public void setRed_SOS_Count(int red_SOS_Count) {
			this.red_SOS_Count = red_SOS_Count;
		}
		
		public void increment_Red_SOS_Count() {
			red_SOS_Count++;
		}



		public void setBlue_SOS_Count(int blue_SOS_Count) {
			this.blue_SOS_Count = blue_SOS_Count;
		}
		
		public void increment_Blue_SOS_Count() {
			blue_SOS_Count++;
		}



		public void setPairList(SOS_PairList pairs) {
			this.pairList = pairs;
		}



		public GameType getCurrentGameType() {
			return gameType;
		}

		public GameTurn getCurrentGameTurn() {
			return gameTurn;
		}

		public GameWinState getCurrentGameWinState() {
			return gameWinState;
		}

	

		public void setGameType(GameType gameType) {
			this.gameType = gameType;
		}

		public void setGameTurn(GameTurn gameTurn) {
			this.gameTurn = gameTurn;
		}

		public void setGameWinState(GameWinState gameWinState) {
			this.gameWinState = gameWinState;
		}



		public int getBoardsize() {
			return boardsize;
		}



		public int getGameboardDimensions() {
			return gameboardDimensions;
		}



		public void setBoardsize(int boardsize) {
			this.boardsize = boardsize;
			remainingCells = boardsize*boardsize;
		}



		public void setGameboardDimensions(int gameboardDimensions) {
			this.gameboardDimensions = gameboardDimensions;
		}

		public GameType getGameType() {
			return gameType;
		}

		public GameTurn getGameTurn() {
			return gameTurn;
		}

		public GameWinState getGameWinState() {
			return gameWinState;
		}

		public boolean isRedComputer() {
			return redIsComputer;
		}

		public boolean isBlueComputer() {
			return blueIsComputer;
		}

		public void setRedIsComputer(boolean redIsComputer) {
			this.redIsComputer = redIsComputer;
		}

		public void setBlueIsComputer(boolean blueIsComputer) {
			this.blueIsComputer = blueIsComputer;
		}

		public int getRedSliderValue() {
			return redSliderValue;
		}

		public int getBlueSliderValue() {
			return blueSliderValue;
		}

		public void setRedSliderValue(int redSliderValue) {
			this.redSliderValue = redSliderValue;
		}

		public void setBlueSliderValue(int blueSliderValue) {
			this.blueSliderValue = blueSliderValue;
		}

		public SOS_Game_Main getCurrentGame() {
			return currentGame;
		}

		public void setCurrentGame(SOS_Game_Main currentGame) {
			this.currentGame = currentGame;
		}
		
		

		@Override
		public void onToggleTurns() {
			
			
			
		}

		@Override
		public void onInitiateGame(SOS_Game_Main game) {
			currentGame = game;
			currentGame.initGame();
			//JOptionPane.showMessageDialog(null, "INITIATED GAME");
		}

		
		
		
		
		
}
