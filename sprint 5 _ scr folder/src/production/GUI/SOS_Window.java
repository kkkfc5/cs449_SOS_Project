package production.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.*;

import production.GameEvents.Game_Over_Event_Manager.Game_Over_Event;
import production.GameEvents.Get_Selected_SO_Event_Manager.Get_Selected_SO_Event;
import production.GameEvents.Initiate_Game_Event_Manager;
import production.GameEvents.Initiate_Game_Event_Manager.Initiate_Game_Event;
import production.GameEvents.New_Game_Event_Manager;
import production.GameEvents.Redo_Event_Manager;
import production.GameEvents.Toggle_Display_Utils_Event_Manager;
import production.GameEvents.Toggle_Turns_Event_Manager.Toggle_Turns_Event;
import production.GameEvents.Undo_Event_Manager;
import production.GameLogic.GameStateManager;
import production.GameLogic.GameStateManager.*;

//import production.GameLogic.SOS_Game_General;
import production.GameLogic.SOS_Game_Main;
//import production.GameLogic.SOS_Game_Simple;
//import sprint1_0.production.GameLogic.ToggleTurnsEvent;
//import sprint1_0.production.GameLogic.ToggleTurnsInterface;

public class SOS_Window extends JFrame implements Game_Over_Event, Toggle_Turns_Event, Get_Selected_SO_Event {

	MenuBarManager menuBar;
	
	
	PlayerOptionsPanel redPlayerPanel;
	PlayerOptionsPanel bluePlayerPanel;
	JPanel centerPanel;
	JPanel westPanel;
	JPanel eastPanel;
	JPanel southPanel;

	BoxLayout boxLayout;

	JLabel turnLabel;
	
	String fileName;
	
	//int menuBarBoardsize;

	//int gameboardDimensions, boardsize;
	//GameType gameType;
	//GameTurn gameTurn;

	//SOS_Game_Main game;
	Gameboard gameboard;
	GameStateManager gameStateManager;
	
	New_Game_Event_Manager New_Game_Event;
	Initiate_Game_Event_Manager Initiate_Game_Event;
	Undo_Event_Manager Undo_Event;
	Redo_Event_Manager Redo_Event;
	Toggle_Display_Utils_Event_Manager Toggle_Display_Utils_Event;

	public SOS_Window() {
		// set the layout to be N/E/S/W/C
		setLayout(new BorderLayout());
		// set size of the window ( 800 wide, 500 tall )
		setSize(800, 500);
		// Specify an action for the window close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialize defaults
		//gameboardDimensions = 300;
		//boardsize = 5;
		turnLabel = new JLabel(" Red Player's Turn : Simple");
		turnLabel.setForeground(Color.red);

		//this.game = game;
		//gameboard = game.initGame(boardsize, gameboardDimensions, GameType.Simple);

		// THIS EXISTS ONLY TO PREVENT THE NULL ERROR WHEN CALLING CREATE__PANEL()
		gameStateManager = new GameStateManager();
		
		add(createWestPanel(), BorderLayout.WEST);
		add(createEastPanel(), BorderLayout.EAST);
		add(createCenterPanel(), BorderLayout.CENTER);
		add(createSouthPanel(), BorderLayout.SOUTH); // <----- need to use grid bag to align center

		// Build the menu bar.
		menuBar = new MenuBarManager(this);
		setJMenuBar(menuBar.buildMenuBar());
		
		revalidate();
		repaint();
		pack();
		setVisible(false);
		setVisible(true);
		pack();

	} // end constructor

	public void connectGameboard(Gameboard g) {
		gameboard = g;
		setGameboard(g);
	}
	
	public void connectGameStateManager(GameStateManager g) {
		gameStateManager = g;
	}
	public void connectNewGameEventManager(New_Game_Event_Manager g){
		New_Game_Event = g;
	}
	public void connectInitiateGameEventManager(Initiate_Game_Event_Manager g) {
		Initiate_Game_Event = g;
	}
	public void connectUndoEventManager(Undo_Event_Manager g) {
		Undo_Event = g;
	}
	public void connectRedoEventManager(Redo_Event_Manager g) {
		Redo_Event = g;
	}
	public void connectToggleDisplayUtilsEventManager(Toggle_Display_Utils_Event_Manager g) {
		Toggle_Display_Utils_Event = g;
	}
	
	
	/*
	 * ###############################################################
	 * ############################################################### 
	 * 							WINDOW PANELS
	 * ###############################################################
	 * ###############################################################
	 */

	private JPanel createWestPanel() {
		// initialize panel we're going to return
		westPanel = new JPanel(new GridLayout(2, 1));

		// create and add a new option panel for the red player
		redPlayerPanel = new PlayerOptionsPanel("Red Player", this);
		westPanel.add(redPlayerPanel);
		
		// set toggle for computer brain
		redPlayerPanel.getComputerButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) {
					//game.setComputerBrain();
					//game.processMove();
				}
			}
		});

		// create a spacer and add it.
		var spacer = new JLabel("");
		spacer.setPreferredSize(new Dimension(((this.getSize().width - gameStateManager.getGameboardDimensions()) / 2), 10));
		// ^ preferred size = 1/2 of the width of the non-gameboard screen

		// add spacer
		westPanel.add(spacer);

		return westPanel;
	}

	private JPanel createEastPanel() {
		// initialize panel we're going to return
		eastPanel = new JPanel(new GridLayout(2, 1));

		// create and add a new option panel for a blue player
		bluePlayerPanel = new PlayerOptionsPanel("Blue Player", this);
		eastPanel.add(bluePlayerPanel);
		
		// activate computer brain
		bluePlayerPanel.getComputerButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) {
					//game.setComputerBrain();
					//game.processMove();
				}
			}
		});

		// create a spacer and add it.
		var spacer = new JLabel("");
		spacer.setPreferredSize(new Dimension(((this.getSize().width - gameStateManager.getGameboardDimensions()) / 2), 10));
		// ^ preferred size = 1/2 of the width of the non-gameboard screen

		// add spacer
		eastPanel.add(spacer);

		return eastPanel;
	}

	private JPanel createCenterPanel() {

		centerPanel = new JPanel();

		// set its size
		centerPanel.setPreferredSize(new Dimension(gameStateManager.getGameboardDimensions(), gameStateManager.getGameboardDimensions()));
		turnLabel.setPreferredSize(new Dimension(100, 100));

		// add gameboard object to this panel
		if(gameboard != null)
			centerPanel.add(gameboard);

		return centerPanel;
	}

	private JPanel createSouthPanel() {
		
		southPanel = new JPanel();

		turnLabel.setPreferredSize(new Dimension(200, 100));
		southPanel.add(turnLabel);

		southPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		return southPanel;
	}


	public boolean isComputer(boolean isRedTurn) {
		if (isRedTurn) {
			return redPlayerPanel.isComputer();
		} else {
			return bluePlayerPanel.isComputer();
		}
	}

	public CellOpt getRed_SO_Option() {
		return redPlayerPanel.selectedSO();
	}

	public CellOpt getBlue_SO_Option() {
		return bluePlayerPanel.selectedSO();
	}

	/*
	 * ############################################################### 
	 * DROPDOWN MENU
	 * BUTTON HANDLING
	 * ###############################################################
	 */

	public boolean newGamePressed() {
		try {
			// clear old gameboard
			//centerPanel.remove(gameboard);
			
			
			// FIRST,, UPDATE GAMESTATEMANAGER
			gameStateManager.setBoardsize(menuBar.getBoardsize());
			gameStateManager.setGameType(menuBar.getSelectedGameType());
			
			gameboard.initGameboard(gameStateManager.getBoardsize());
			
			New_Game_Event.invoke();
			//gameStateManager
			
			
		
			
		// selects game type
		//gameStateManager.setGameType(menuBar.getSimpleGameItem().isSelected() ? GameType.Simple : GameType.General);
		
		if(gameStateManager.getCurrentGameType() == GameType.Simple) {
			//game = new SOS_Game_Simple();
			//game.connectGUIWindow(this);
			Initiate_Game_Event.invoke(GameType.Simple);
		}
		else {
			//game = new SOS_Game_General();
			//game.connectGUIWindow(this);
			Initiate_Game_Event.invoke(GameType.General);
		}
		
		// create new gameboard and add it to the window
		//gameboard = game.initGame(boardsize, gameboardDimensions, gameType);
		//centerPanel.add(gameboard);

		
		// redraw the new gameboard
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);

		// sets buttons back to default positions
		bluePlayerPanel.reset();
		redPlayerPanel.reset();

		turnLabel.setText(" Red Player's Turn : " + gameStateManager.getCurrentGameType());
		turnLabel.setForeground(Color.red);

		// INVOKE GAMELOGICMANAGER TO START ITS GAME 
			// if RED PLAYER IS COMPUTER? Otherwise human goes
				// but the gamelogic manager would know whose turn and who is who yeah?
				// so just have it busywait
		//JOptionPane.showMessageDialog(null, "soswindow newgamepressed(); gameboards == eachother: "+getGameboard().equals(game.getGameboard()));
		
		return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "SOMETHING FAILED IN \n\tSOS_Window.newGamePressed()");
			return false;
		}
		
		
	
	}

	public void loadGamePressed() {
		
		newGamePressed();
		
		File file;
		
		JFileChooser fileChooser = new JFileChooser();
		
		int returnValue = fileChooser.showOpenDialog(this);
		
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			gameStateManager.loadGame(file);
		}
		
	}

	public void saveGamePressed() {
		//JOptionPane.showMessageDialog(this, "save game pressed");
		fileName = JOptionPane.showInputDialog("What would you like to name the save-file?");
		gameStateManager.saveGame(fileName);
	}

	
	

	private void loginPressed() {
//		if (loginButtonItem.getText().contains("in")) {
//			loginButtonItem.setText("Logout");
//		} else if (loginButtonItem.getText().contains("out")) {
//			loginButtonItem.setText("Login");
//		}

	}

	public void undoPressed() {
		System.out.print("\n&&&&&&&&  [SOS_Window]  Undo pressed ");
		Undo_Event.invoke();
	}

	public void redoPressed() {
		System.out.print("\n&&&&&&&&  [SOS_Window]  Redo pressed ");
		Redo_Event.invoke();
	}
	
	public void toggleDisplayUtils() {
		Toggle_Display_Utils_Event.invoke();
	}
	

	public void updateTurnLabel() {
		
		if(gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) {
			turnLabel.setText(" Red Player's Turn : " + gameStateManager.getCurrentGameType());
			turnLabel.setForeground(Color.red);
		} else {
			turnLabel.setText("Blue Player's Turn : " + gameStateManager.getCurrentGameType());
			turnLabel.setForeground(Color.blue);
		}
		
		/*
		if (turnLabel.getText().contains("Red")) {
			turnLabel.setText("Blue Player's Turn : " + gameStateManager.getCurrentGameType());
			turnLabel.setForeground(Color.blue);
		} else {
			turnLabel.setText(" Red Player's Turn : " + gameStateManager.getCurrentGameType());
			turnLabel.setForeground(Color.red);
		}
		*/
	}
	
	public void setWinnerText(GameTurn turn) {
		if(turn == null) {
			turnLabel.setText("TIE GAME");
			turnLabel.setForeground(Color.black);
		}
		else if(turn == GameTurn.TurnRed) {
			turnLabel.setText("RED PLAYER WON");
			turnLabel.setForeground(Color.red);
		}
		else {
			turnLabel.setText("BLUE PLAYER WON");
			turnLabel.setForeground(Color.blue);
		}
	}

	
	public int getWindowBoardsize() {
		return gameStateManager.getBoardsize();
	}
	//public int getBoardsize() {
		//return game.getBoardSize();
	//}
	
	public GameType getWindowGameType() {
		return gameStateManager.getCurrentGameType();
	}
	//public GameType getGameType() {
	//	return game.getGameType();
	//}
	public Gameboard getGameboard() {
		return gameboard;
	}
	
	public void selectGameTypeSimple() {
		menuBar.getSimpleGameItem().setSelected(true);
	}
	public void selectGameTypeGeneral(){
		menuBar.getGeneralGameItem().setSelected(true);
	}
	public void redSelectS() {
		redPlayerPanel.selectS();
	}
	public void redSelectO() {
		redPlayerPanel.selectO();
	}
	public void blueSelectS() {
		bluePlayerPanel.selectS();
	}
	public void blueSelectO() {
		bluePlayerPanel.selectO();
	}
	public MenuBarManager getMenuBarManager() {
		return menuBar;
	}
	//public SOS_Game_Main getGameLogic() {
	//	return game;
	//}
	
	//public void setBoardsize(int b) {
	//	menuBarBoardsize = b;
	//}
	
	public void setGameboard(Gameboard g) {
		//centerPanel.remove(gameboard);
		//gameboard = g;
		centerPanel.add(gameboard);
		
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
		
		//setVisible(false);
		//setVisible(true);
	}
	
	//public JSlider getSlider() {
	//	return (gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) ? redPlayerPanel.getSlider() : bluePlayerPanel.getSlider();
	//}

	@Override
	public void onGameOver() {
		if(gameStateManager.getRed_SOS_Count() > gameStateManager.getBlue_SOS_Count()) {
			setWinnerText(GameTurn.TurnRed);
		}
		else if(gameStateManager.getRed_SOS_Count() < gameStateManager.getBlue_SOS_Count()) {
			setWinnerText(GameTurn.TurnBlue);
		}
		else {
			setWinnerText(null);
		}
	}
	
	
	
	// ======================== PLAYER OPTION PANEL'S BUTTON ACTION LISTENER CALLS THIS FUNC
	public void updatePlayerOptionsPanelSettingsInRelationToGameStateManager() {
		// grab red player settings from player option panel
		gameStateManager.setRedSliderValue(redPlayerPanel.getSlider().getValue());
		gameStateManager.setRedIsComputer(redPlayerPanel.isComputer());
		// grab blue player settings from player uption panel
		gameStateManager.setBlueSliderValue(bluePlayerPanel.getSlider().getValue());
		gameStateManager.setBlueIsComputer(bluePlayerPanel.isComputer());
		// update gameStateManager with those grabbed values
	}
	
	public void computerSelected() {
		//JOptionPane.showMessageDialog(null, "COMPUTERSELCTED() SOS_WINDOW");
		gameStateManager.getCurrentGame().processMove();
	}

	@Override
	public void onToggleTurns() {
		
		
		
		//System.out.print(gameStateManager.getCurrentGameTurn() + " => ");
		if(gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) {
			gameStateManager.setGameTurn(GameTurn.TurnBlue);
		}else if (gameStateManager.getCurrentGameTurn() == GameTurn.TurnBlue) {
			gameStateManager.setGameTurn(GameTurn.TurnRed);
		}
		//System.out.print(gameStateManager.getCurrentGameTurn() + "\n");
		
		updateTurnLabel();
		
		gameStateManager.setRedIsComputer(redPlayerPanel.isComputer());
		gameStateManager.setBlueIsComputer(bluePlayerPanel.isComputer());
		
	}
	
	@Override
	public CellOpt onGetSelectedSO() {
		if(gameStateManager.getCurrentGameTurn() == GameTurn.TurnRed) {
			return redPlayerPanel.selectedSO();
		}
		else {
			return bluePlayerPanel.selectedSO();
		}
	}
	
	
	
	
	
	
	
	// ==========================
	
	public void test_redSelectComputer() {
		redPlayerPanel.test_selectComputer();
	}
	public void test_blueSelectComputer() {
		bluePlayerPanel.test_selectComputer();	
	}
}
