package test;

import static org.junit.Assert.*;
import org.junit.*;

import production.GUI.Gameboard;
import production.GUI.SOS_Window;
import production.GameLogic.SOS_Game_Main;
import production.GameLogic.SOS_Game_Main.GameType;
import production.GameLogic.SOS_Game_Simple;

public class TestStartNewGame {

	private SOS_Window testWindow;
	private SOS_Game_Main testGameLogic;
	private Gameboard testGameboard;

	// The setUp method runs before each test
	@Before
	public void setUp() {
		// testGameLogic = new GameLogicManager();

		// Create an instance of the SOS_Window object before each test
		testWindow = new SOS_Window(new SOS_Game_Simple());
		testGameboard = testWindow.getGameboard();
	}

	// ___ CHATGPT GENERATED ___
	// AC 3.1 Prevent size changes during a game.
	@Test
	public void testPreventSizeChangeDuringGame() {
		// Call boardsizePressed() with any number between 3 and 30
		testWindow.getMenuBarManager().boardsizePressed(10);

		// Verify that getBoardsize() returns 5
		assertEquals(5, testWindow.getBoardsize());
	}

	// ___ CHATGPT GENERATED ___
	// AC 3.2 Prevent mode change during a game.
	@Test
	public void testPreventModeChangeDuringGame() {
		// Call the selectGameTypeGeneral() function
		testWindow.selectGameTypeGeneral();

		// Check if getGameType() returns "GameType.Simple"
		assertEquals(GameType.Simple, testWindow.getGameType());
	}

	// AC 3.3 Initialization of game.
	@Test
	public void testNewGameInitialized() {
		testWindow.newGamePressed();

		// test if current gameboard == original gameboard
		assertFalse(testWindow.getGameboard() == testGameboard);
	}

	// AC 3.4 Initialize game with defaults.
	@Test
	public void testInitWithDefault() {
		testWindow.newGamePressed();

		assertTrue(testWindow.getBoardsize() == 5 && testWindow.getGameType() == GameType.Simple);
	}

	// AC 3.5 Visually confirm the changes to game mode and board size.
	@Test
	public void testVisuallyConfirmChanges() {
		testWindow.getMenuBarManager().boardsizePressed(25);
		testWindow.selectGameTypeGeneral();
		testWindow.newGamePressed();

		// keep window visible so you can verify results
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// AC 3.6 Remove previous game’s data.
	@Test
	public void testClearPrevGameData() {
		testGameboard = testWindow.getGameboard();

		testGameboard.setSelectedCell(2, 2);
		testGameboard.drawLetter('S');

		// keep window visible so you can verify results
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		testWindow.newGamePressed();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	
}
