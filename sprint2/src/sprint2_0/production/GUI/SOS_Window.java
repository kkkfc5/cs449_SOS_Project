package sprint1_0.production.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import sprint1_0.production.GameLogic.CellOpt;
import sprint1_0.production.GameLogic.GameLogicManager;
import sprint1_0.production.GameLogic.GameLogicManager.GameTurn;
import sprint1_0.production.GameLogic.GameLogicManager.GameType;
//import sprint1_0.production.GameLogic.ToggleTurnsEvent;
//import sprint1_0.production.GameLogic.ToggleTurnsInterface;

public class SOS_Window extends JFrame {

	private JMenuBar menuBar; // The menu bar
	private JMenu OptionsMenu; // The Options menu
	private JMenu FileMenu; // The File menu
	JMenu EditMenu;
	private JMenuItem newGameButtonItem;
	private JMenuItem loadGameButtonItem;
	private JMenuItem saveGameButtonItem;
	private JCheckBoxMenuItem recordGameItem;
	JMenuItem loginButtonItem;
	private JMenuItem boardsizeTextItem;
	private int boardsize;
	private JRadioButtonMenuItem simpleGameItem;
	private JRadioButtonMenuItem generalGameItem;
	JMenuItem undoButtonItem;
	JMenuItem redoButtonItem;

	PlayerOptionsPanel redPlayerPanel;
	PlayerOptionsPanel bluePlayerPanel;
	JPanel centerPanel;
	JPanel westPanel;
	JPanel eastPanel;
	JPanel southPanel;

	BoxLayout boxLayout;

	JLabel turnLabel;

	int gameboardDimensions;
	GameType gameType;
	GameTurn gameTurn;

	GameLogicManager game;
	Gameboard gameboard;
	//ToggleTurnsEvent toggleTurnsEvent;

	public SOS_Window(GameLogicManager game) {
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

//  
		this.game = game;
		gameboard = game.initGame(boardsize, gameboardDimensions, GameType.Simple);

		add(createWestPanel(), BorderLayout.WEST);
		add(createEastPanel(), BorderLayout.EAST);
		add(createCenterPanel(), BorderLayout.CENTER);
		add(createSouthPanel(), BorderLayout.SOUTH); // <----- need to use grid bag to align center
		// add(turnLabel, BorderLayout.SOUTH);

		// Build the menu bar.
		buildMenuBar();

		revalidate();
		repaint();
		pack();
		setVisible(false);
		setVisible(true);
		pack();

		// check if red is computer, and kick it off
	} // end constructor

	/*
	 * ###############################################################
	 * ############################################################### WINDOW PANELS
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
		// create the panel to return
		// JPanel mainPanel = new JPanel();
		// %%%%%%%%%%%%%boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		// %%%%%%%%%%%%%mainPanel.setLayout(boxLayout);

		centerPanel = new JPanel();

		// set its size
		centerPanel.setPreferredSize(new Dimension(gameboardDimensions, gameboardDimensions));
		turnLabel.setPreferredSize(new Dimension(100, 100));

		// add gameboard object to this panel
		centerPanel.add(gameboard);

		// %%%%%%%%%%%%%
		/*
		 * turnLabel.setForeground(Color.red); turnLabel.setVisible(false);
		 * turnLabel.setVisible(true);
		 */
		// mainPanel.add(centerPanel);
		// mainPanel.add(turnLabel);

		return centerPanel;
	}

	private JPanel createSouthPanel() {
		//var mainPanel = new JPanel(new GridLayout(1, 2));
		southPanel = new JPanel();

		// create spacer of the width of the player settings panel
		//var spacer = new JButton();
		//spacer.setSize(new Dimension(((this.getSize().width - gameboardDimensions) / 2), 10));
		// southPanel.add(spacer);

		turnLabel.setPreferredSize(new Dimension(200, 100));
		southPanel.add(turnLabel);

		southPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		// mainPanel.add(southPanel);

		return southPanel;
	}

	/*
	 * private JPanel createSouthPanel() { var mainPanel = new JPanel(new
	 * GridLayout(2,3)); mainPanel.add(new JLabel("1")); mainPanel.add(turnLabel);
	 * mainPanel.add(new JLabel("2")); mainPanel.add(new JLabel("3"));
	 * mainPanel.add(new JLabel("4")); return mainPanel; }
	 * 
	 * 
	 * 
	 * public void toggleTurn() { if(turnLabel.getText().contains("Red")) {
	 * turnLabel.setText("Blue Player's Turn"); } else {
	 * turnLabel.setText("Red Player's Turn"); } }
	 */

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
	 * ############################################################### DROPDOWN
	 * MENUS ###############################################################
	 * ###############################################################
	 */

	private void buildMenuBar() {
		// Create the menu bar.
		menuBar = new JMenuBar();

		// Create the file and text menus.
		buildOptionsMenu();
		buildFileMenu();
		buildEditMenu();

		// Add the file and text menus to the menu bar.
		menuBar.add(FileMenu);
		menuBar.add(OptionsMenu);
		menuBar.add(EditMenu);

		// Set the window's menu bar.
		setJMenuBar(menuBar);
	}

	private void buildFileMenu() {
		// create dropdown menu tab
		FileMenu = new JMenu("File");
		FileMenu.setMnemonic(KeyEvent.VK_F);

		// initialize buttons
		newGameButtonItem = new JMenuItem("New Game");
		loadGameButtonItem = new JMenuItem("Load Game");
		saveGameButtonItem = new JMenuItem("Save Game");

		// make buttons do something when pressed
		newGameButtonItem.addActionListener(e -> newGamePressed());
		loadGameButtonItem.addActionListener(e -> loadGamePressed());
		saveGameButtonItem.addActionListener(e -> saveGamePressed());

		// add buttons to dropdown menu tab
		FileMenu.add(newGameButtonItem);
		FileMenu.add(loadGameButtonItem);
		FileMenu.add(saveGameButtonItem);

		// add separator
		FileMenu.addSeparator();

		// initialize a checkbox for recording a game and set it to true
		recordGameItem = new JCheckBoxMenuItem("Record Game");
		recordGameItem.setSelected(true);

		// add checkbox to dropdown menu tab
		FileMenu.add(recordGameItem);

		FileMenu.addSeparator();

		loginButtonItem = new JMenuItem("Login");
		loginButtonItem.addActionListener(e -> loginPressed());

		FileMenu.add(loginButtonItem);

	}

	private void buildOptionsMenu() {
		// Create a JMenu object for the Options menu.
		OptionsMenu = new JMenu("Options");
		OptionsMenu.setMnemonic(KeyEvent.VK_O);

		// board size selection button
		boardsizeTextItem = new JMenuItem();
		updateBoardsizeText();

		// set event trigger
		boardsizeTextItem.addActionListener(e -> boardsizePressed(-1));

		// add button to menu
		OptionsMenu.add(boardsizeTextItem);

		// add separator
		OptionsMenu.addSeparator();

		// group the game mode select so the button choices are exclusive
		ButtonGroup bg = new ButtonGroup();

		// init buttons
		simpleGameItem = new JRadioButtonMenuItem("Simple Game");
		generalGameItem = new JRadioButtonMenuItem("General Game");

		// set values
		simpleGameItem.setSelected(true);

		// add buttons to button group
		bg.add(simpleGameItem);
		bg.add(generalGameItem);

		// Add the buttons to the dropdown menu
		OptionsMenu.add(simpleGameItem);
		OptionsMenu.add(generalGameItem);
	}

	private void buildEditMenu() {
		// init menu
		EditMenu = new JMenu("Edit");
		EditMenu.setMnemonic(KeyEvent.VK_E);

		// init buttons
		undoButtonItem = new JMenuItem("Undo");
		redoButtonItem = new JMenuItem("Redo");

		// set the events for when the buttons are pressed
		undoButtonItem.addActionListener(e -> undoPressed());
		undoButtonItem.addActionListener(e -> redoPressed());

		// add buttons to the dropdown menu
		EditMenu.add(undoButtonItem);
		EditMenu.add(redoButtonItem);

	}

	/*
	 * ############################################################### DROPDOWN MENU
	 * BUTTON HANDLING
	 * ###############################################################
	 */

	public boolean newGamePressed() {
		try {
		// clear old gameboard
		centerPanel.remove(gameboard);
		
		// selects game type
		gameType = simpleGameItem.isSelected() ? GameType.Simple : GameType.General;
		//System.out.print("%%%%% window: 343   TYPE: " + gameType + "\n");
		
		// create new gameboard and add it to the window
		gameboard = game.initGame(boardsize, gameboardDimensions, gameType);
		centerPanel.add(gameboard);

		// add(createCenterPanel(), BorderLayout.CENTER);

		// redraw the new gameboard
		centerPanel.setVisible(false);
		// gameboard.setVisible(false);
		// JOptionPane.showMessageDialog(OptionsMenu, "pause");
		centerPanel.setVisible(true);
		// gameboard.setVisible(true);

		// sets buttons back to default positions
		bluePlayerPanel.reset();
		redPlayerPanel.reset();

		turnLabel.setText(" Red Player's Turn : " + game.getGameType());
		turnLabel.setForeground(Color.red);
		//System.out.print("JUnit TEST __ " + game.getGameType());
		
		
		// INVOKE GAMELOGICMANAGER TO START ITS GAME 
			// if RED PLAYER IS COMPUTER? Otherwise human goes
				// but the gamelogic manager would know whose turn and who is who yeah?
				// so just have it busywait
		
		
		return true;
		} catch (Exception e) {
			return false;
		}
		
		
	
	}

	private void loadGamePressed() {
		JOptionPane.showMessageDialog(this, "load game pressed");

	}

	private void saveGamePressed() {
		JOptionPane.showMessageDialog(this, "save game pressed");
	}

	public boolean boardsizePressed(int testSize) {

		// if the input is -1, then it is not the JUnit test calling this function.
		boolean isJUnitTest = (testSize != -1);
		int sizeInput;

		try {

			// if the input is for a JUnit test
			if (isJUnitTest) {
				sizeInput = testSize;
			} else { // else take in an integer that the user inputs
				sizeInput = Integer.parseInt(
						JOptionPane.showInputDialog(this, "What size would you like the board to be?"));
			}

			// if the user input a bad size, throw error
			if (sizeInput < 3 || sizeInput > 30) {
				throw new RuntimeException();
			}

			// set the boardsize to be the inputted number
			boardsize = sizeInput;
			// update the dropdown menu's button to display the proper size
			updateBoardsizeText();
			return true;

		} catch (Exception e) {
			if (!isJUnitTest) {
				// show error
				JOptionPane.showMessageDialog(this, "Please input an integer between 3 and 30.", "Bad Input",
						JOptionPane.ERROR_MESSAGE);
			}
			return false;
		}
	}

	private void updateBoardsizeText() {
		// update the dropdown menu's button to display the board size
		boardsizeTextItem.setText("Board Size: " + boardsize + "x" + boardsize);
	}

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

	public void ToggleTurn() {
		if (turnLabel.getText().contains("Red")) {
			turnLabel.setText("Blue Player's Turn : " + game.getGameType());
			turnLabel.setForeground(Color.blue);

			// JOptionPane.showMessageDialog(null, "Swapping turn to blue");
		} else {
			turnLabel.setText(" Red Player's Turn : " + game.getGameType());
			turnLabel.setForeground(Color.red);

			// JOptionPane.showMessageDialog(null, "Swapping turn to red");
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
		simpleGameItem.setSelected(true);
	}
	public void selectGameTypeGeneral(){
		generalGameItem.setSelected(true);
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
}
