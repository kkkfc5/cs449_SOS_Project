package sprint3_0.production.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import sprint3_0.production.GameLogic.SOS_Game_Main.*;
import sprint3_0.production.GameLogic.SOS_Game_General;
import sprint3_0.production.GameLogic.SOS_Game_Main;
import sprint3_0.production.GameLogic.SOS_Game_Simple;
//import sprint1_0.production.GameLogic.ToggleTurnsEvent;
//import sprint1_0.production.GameLogic.ToggleTurnsInterface;

public class SOS_Window extends JFrame {

	MenuBarManager menuBar;
	
	
	PlayerOptionsPanel redPlayerPanel;
	PlayerOptionsPanel bluePlayerPanel;
	JPanel centerPanel;
	JPanel westPanel;
	JPanel eastPanel;
	JPanel southPanel;

	BoxLayout boxLayout;

	JLabel turnLabel;

	int gameboardDimensions, boardsize;
	GameType gameType;
	GameTurn gameTurn;

	SOS_Game_Main game;
	Gameboard gameboard;

	public SOS_Window(SOS_Game_Main game) {
		// set the layout to be N/E/S/W/C
		setLayout(new BorderLayout());
		// set size of the window ( 800 wide, 500 tall )
		setSize(800, 500);
		// Specify an action for the window close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialize defaults
		gameboardDimensions = 300;
		boardsize = 5;
		turnLabel = new JLabel(" Red Player's Turn : Simple");
		turnLabel.setForeground(Color.red);

		this.game = game;
		gameboard = game.initGame(boardsize, gameboardDimensions, GameType.Simple);

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
		redPlayerPanel = new PlayerOptionsPanel("Red Player");
		westPanel.add(redPlayerPanel);

		// create a spacer and add it.
		var spacer = new JLabel("");
		spacer.setPreferredSize(new Dimension(((this.getSize().width - gameboardDimensions) / 2), 10));
		// ^ preferred size = 1/2 of the width of the non-gameboard screen

		// add spacer
		westPanel.add(spacer);

		return westPanel;
	}

	private JPanel createEastPanel() {
		// initialize panel we're going to return
		eastPanel = new JPanel(new GridLayout(2, 1));

		// create and add a new option panel for a blue player
		bluePlayerPanel = new PlayerOptionsPanel("Blue Player");
		eastPanel.add(bluePlayerPanel);

		// create a spacer and add it.
		var spacer = new JLabel("");
		spacer.setPreferredSize(new Dimension(((this.getSize().width - gameboardDimensions) / 2), 10));
		// ^ preferred size = 1/2 of the width of the non-gameboard screen

		// add spacer
		eastPanel.add(spacer);

		return eastPanel;
	}

	private JPanel createCenterPanel() {

		centerPanel = new JPanel();

		// set its size
		centerPanel.setPreferredSize(new Dimension(gameboardDimensions, gameboardDimensions));
		turnLabel.setPreferredSize(new Dimension(100, 100));

		// add gameboard object to this panel
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
		centerPanel.remove(gameboard);
		
		// selects game type
		gameType = menuBar.getSimpleGameItem().isSelected() ? GameType.Simple : GameType.General;
		
		if(gameType == GameType.Simple) {
			game = new SOS_Game_Simple();
			game.connectGUIWindow(this);
		}
		else {
			game = new SOS_Game_General();
			game.connectGUIWindow(this);
		}
		
		// create new gameboard and add it to the window
		gameboard = game.initGame(boardsize, gameboardDimensions, gameType);
		centerPanel.add(gameboard);

		
		// redraw the new gameboard
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);

		// sets buttons back to default positions
		bluePlayerPanel.reset();
		redPlayerPanel.reset();

		turnLabel.setText(" Red Player's Turn : " + game.getGameType());
		turnLabel.setForeground(Color.red);

		// INVOKE GAMELOGICMANAGER TO START ITS GAME 
			// if RED PLAYER IS COMPUTER? Otherwise human goes
				// but the gamelogic manager would know whose turn and who is who yeah?
				// so just have it busywait
		//JOptionPane.showMessageDialog(null, "soswindow newgamepressed(); gameboards == eachother: "+getGameboard().equals(game.getGameboard()));
		
		return true;
		} catch (Exception e) {
			return false;
		}
		
		
	
	}

	public void loadGamePressed() {
		JOptionPane.showMessageDialog(this, "load game pressed");

	}

	public void saveGamePressed() {
		JOptionPane.showMessageDialog(this, "save game pressed");
	}

	/*
	

	private void loginPressed() {
		if (loginButtonItem.getText().contains("in")) {
			loginButtonItem.setText("Logout");
		} else if (loginButtonItem.getText().contains("out")) {
			loginButtonItem.setText("Login");
		}

	}

	private void undoPressed() {

	}

	private void redoPressed() {

	}
	*/

	public void ToggleTurn() {
		if (turnLabel.getText().contains("Red")) {
			turnLabel.setText("Blue Player's Turn : " + game.getGameType());
			turnLabel.setForeground(Color.blue);
		} else {
			turnLabel.setText(" Red Player's Turn : " + game.getGameType());
			turnLabel.setForeground(Color.red);
		}
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
		return boardsize;
	}
	public int getBoardsize() {
		return game.getBoardSize();
	}
	
	public GameType getWindowGameType() {
		return gameType;
	}
	public GameType getGameType() {
		return game.getGameType();
	}
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
	public SOS_Game_Main getGameLogic() {
		return game;
	}
	
	public void setBoardsize(int b) {
		boardsize = b;
	}
	
	public void setGameboard(Gameboard g) {
		centerPanel.remove(gameboard);
		gameboard = g;
		centerPanel.add(gameboard);
		
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	
}
