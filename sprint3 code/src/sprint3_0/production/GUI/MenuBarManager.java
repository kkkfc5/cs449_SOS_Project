package sprint3_0.production.GUI;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

/**
 * Creates and manages the menu bar used by the window
 */
public class MenuBarManager {
	private JMenuBar menuBar; // The menu bar
	private JMenu OptionsMenu; // The Options menu
	private JMenu FileMenu; // The File menu
	private JMenu EditMenu;
	private JMenuItem newGameButtonItem;
	private JMenuItem loadGameButtonItem;
	private JMenuItem saveGameButtonItem;
	private JCheckBoxMenuItem recordGameItem;
	private JMenuItem loginButtonItem;
	private JMenuItem boardsizeTextItem;
	private int boardsize;
	private JRadioButtonMenuItem simpleGameItem;
	private JRadioButtonMenuItem generalGameItem;
	private JMenuItem undoButtonItem;
	private JMenuItem redoButtonItem;
	
	SOS_Window window; // reference to sos window frame
	
	public MenuBarManager(SOS_Window gw) {
		window = gw;
		boardsize = window.getBoardsize();
	}
	
	
	public JMenuBar buildMenuBar() {
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
		//setJMenuBar(menuBar);
		return menuBar;
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

	public void newGamePressed() {
		window.newGamePressed();
	}

	private void loadGamePressed() {
		JOptionPane.showMessageDialog(window, "load game pressed");
	}

	private void saveGamePressed() {
		JOptionPane.showMessageDialog(window, "save game pressed");
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
						JOptionPane.showInputDialog(window, "What size would you like the board to be?"));
			}

			// if the user input a bad size, throw error
			if (sizeInput < 3 || sizeInput > 30) {
				throw new RuntimeException();
			}

			// set the boardsize to be the inputted number
			boardsize = sizeInput;
			window.setBoardsize(boardsize);
			// update the dropdown menu's button to display the proper size
			updateBoardsizeText();
			return true;

		} catch (Exception e) {
			if (!isJUnitTest) {
				// show error
				JOptionPane.showMessageDialog(window, "Please input an integer between 3 and 30.", "Bad Input",
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

	
	
	
	
	
	
	
	
	
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}
	public JMenu getOptionsMenu() {
		return OptionsMenu;
	}
	public JMenu getFileMenu() {
		return FileMenu;
	}
	public JMenu getEditMenu() {
		return EditMenu;
	}
	public JMenuItem getNewGameButtonItem() {
		return newGameButtonItem;
	}
	public JMenuItem getLoadGameButtonItem() {
		return loadGameButtonItem;
	}
	public JMenuItem getSaveGameButtonItem() {
		return saveGameButtonItem;
	}
	public JCheckBoxMenuItem getRecordGameItem() {
		return recordGameItem;
	}
	public JMenuItem getLoginButtonItem() {
		return loginButtonItem;
	}
	public JMenuItem getBoardsizeTextItem() {
		return boardsizeTextItem;
	}
	public JRadioButtonMenuItem getSimpleGameItem() {
		return simpleGameItem;
	}
	public JRadioButtonMenuItem getGeneralGameItem() {
		return generalGameItem;
	}
	public JMenuItem getUndoButtonItem() {
		return undoButtonItem;
	}
	public JMenuItem getRedoButtonItem() {
		return redoButtonItem;
	}
	
	
	
	
	
	
	
}
