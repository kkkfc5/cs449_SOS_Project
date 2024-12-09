package production.GameLogic;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

import production.GUI.Gameboard;
import production.GameEvents.Draw_Cell_Event_Manager.Draw_Cell_Event;
import production.GameEvents.Initiate_Game_Event_Manager.Initiate_Game_Event;
import production.GameEvents.New_Game_Event_Manager.New_Game_Event;
import production.GameEvents.Redo_Event_Manager.Redo_Event;
import production.GameEvents.Toggle_Display_Utils_Event_Manager.Toggle_Display_Utils_Event;
import production.GameEvents.Toggle_Turns_Event_Manager;
import production.GameEvents.Toggle_Turns_Event_Manager.Toggle_Turns_Event;
import production.GameEvents.Undo_Event_Manager.Undo_Event;
import production.GameLogic.GameStateManager.GameTurn;
import production.GameLogic.GameStateManager.GameType;
import production.GameLogic.GameStateManager.GameWinState;

public class GameStateManager implements Toggle_Turns_Event, Initiate_Game_Event, Undo_Event, Redo_Event, Toggle_Display_Utils_Event {
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
		Gameboard gameboard;
		
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
		
		Stack<CellLogical> undoStack;
		Stack<CellLogical> redoStack;
		
		boolean showUtils;
		
		Toggle_Turns_Event_Manager Toggle_Turns_Event;
		
		FileOperations fileOperations;
		
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
			
			undoStack = new Stack<CellLogical>();
			redoStack = new Stack<CellLogical>();
		}
		
		public void connectGameboard(Gameboard g) {
			gameboard = g;
		}
		
		public void connectToggleTurnsEventManager(Toggle_Turns_Event_Manager g) {
			Toggle_Turns_Event = g;
		}

		public void connectFileOperations(FileOperations f) {
			fileOperations = f;
		}
		
		public void pushUndoStack(CellLogical c) {
			System.out.print("\n&&&&&&&&  [GameStateManager]  pushUndoStack()");
			undoStack.push(c);
		}
		public void clearUndoStack() {
			undoStack = new Stack<CellLogical>();
		}
		
		void pushRedoStack(CellLogical c) {
			redoStack.push(c);
		}
		public void clearRedoStack() {
			redoStack = new Stack<CellLogical>();
		}
		
		public void undo() {
			
			if(undoStack.size() > 0) {

				var undoneCell = undoStack.pop();
				var pairs = pairList.getPairs();
				
				var placeholderCell = new CellLogical();
				placeholderCell.setXY(undoneCell.getX(), undoneCell.getY());
				placeholderCell.cellOpt = undoneCell.getCellOpt();
				
				pushRedoStack(placeholderCell);
				
				for(int i = 0; i < pairs.size(); i++) {
					if(pairs.get(i).getS1() == undoneCell || 
					   pairs.get(i).getS2() == undoneCell) {
						// UNDO THE SOS
						pairs.remove(i);
					}
				}
				
				undoneCell.setCellOpt(CellOpt.NULL);
				
				gameboard.onDrawCell(undoneCell);
				Toggle_Turns_Event.invoke();
			}
		}
		
		public void redo() {
			//JOptionPane.showMessageDialog(null, redoStack);
			//System.out.print("\n&&&&&&&&  [GameStateManager]  redo()");
			if(redoStack.size() > 0) {
				
				//System.out.print("\n&&&&&&&&  [GameStateManager]  redo() -> if(size>0)");
				
				var redoneCell = redoStack.pop();
				
				currentGame.redoMove(redoneCell);
				
				pushUndoStack(currentGame.validateCell(redoneCell));
				
				
			}
			
		}
		
		
		
		public void loadGame(File file) {
			var moveArray = fileOperations.ReadFile(file);
			
			System.out.print("\n\n\n || " + moveArray + " ||\n\n\n");
			
			clearUndoStack();
			clearRedoStack();
			currentGame.initGame();
			//gameboard.clearBoard();
			
			for(int i = 0; i < moveArray.size(); i++) {
				redoStack.push(moveArray.get(i));
				System.out.print("\n<" + i + ">");
			}
			
			
			while(redoStack.size() > 0) {
				redo();

			}
		}
		
		public void saveGame(String fileName){
			
			var tempArray = new ArrayList<CellLogical>();
			
			//System.out.print("\n [[[[[[[[[[[[[[[[[[]]]]]]]]]]" + undoStack.size() + "\n");
			
//			for(int i = 0; i <= undoStack.size(); i++) {
//				tempArray.add(undoStack.pop());
//			}
			
			while(undoStack.size() > 0) {
				tempArray.add(undoStack.pop());
			}
			
			//System.out.print("\n [[[[[[[[[[[[[[[[[[]]]]]]]]]]" + tempArray.size() + "\n");
			
			for(int j = tempArray.size(); j < 0; j--) {
				undoStack.push(tempArray.get(j));
			}
			
			fileOperations.WriteFile(tempArray, fileName);
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
		
		public boolean shouldShowUtils() {
			return showUtils;
		}
		
		
		

		@Override
		public void onToggleTurns() {
			
			
			
		}

		@Override
		public void onInitiateGame(SOS_Game_Main game) {
			currentGame = game;
			currentGame.initGame();
		}

		@Override
		public void onRedo() {
			// TODO Auto-generated method stub
			redo();
		}

		@Override
		public void onUndo() {
			// TODO Auto-generated method stub
			System.out.print("\n&&&&&&&&  [GameStateManager]  onUndo event");
			undo();
		}

		@Override
		public void onToggleDisplayUtils() {
			// TODO Auto-generated method stub
			showUtils = !showUtils;
		}

		
		
		
		
}
