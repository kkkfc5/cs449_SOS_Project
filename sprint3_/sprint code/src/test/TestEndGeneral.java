package test;

import static org.junit.Assert.*;

import javax.swing.JOptionPane;

import org.junit.*;

import production.GUI.*;
import production.GameLogic.*;
import production.GameLogic.SOS_Game_Main.GameWinState;

public class TestEndGeneral {

	private SOS_Window testWindow;
	private SOS_Game_Main testGameLogic;
	private Gameboard testGameboard;

	@Before
	public void setUp() {
		testGameLogic = new SOS_Game_General();
		testWindow = new SOS_Window(testGameLogic);
		testGameLogic.connectGUIWindow(testWindow);

		testWindow.getMenuBarManager().boardsizePressed(3);
		testWindow.selectGameTypeGeneral();
		testWindow.newGamePressed();
		
		testGameLogic = testWindow.getGameLogic();
		
		testGameboard = testWindow.getGameboard();
		testGameLogic.setGameboard(testGameboard);
		
	}

	// AC 7.1 Final cell is placed by Blue player
	@Test
	public void testFinalPlacedBlue() {
		testWindow.setTitle("Blue Player Place Final");

		testWindow.getMenuBarManager().boardsizePressed(4);
		testWindow.selectGameTypeGeneral();
		testWindow.newGamePressed();

		testGameLogic = testWindow.getGameLogic();
		
		testGameboard = testWindow.getGameboard();
		testGameLogic.setGameboard(testGameboard);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

				if (i == 1 && j == 1) {
					continue;
				}
		
				testGameboard.setSelectedCell(i, j);
				testGameLogic.processIfValidMove(i, j);
			}

		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.blueSelectO();
		testGameboard.setSelectedCell(1, 1);
		testGameLogic.processIfValidMove(1, 1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(4, testGameLogic.getBlueSOSCount());

	}

	
	
	// AC 7.3 Tie game
	@Test
	public void testFinalPlacedRed() {
		testWindow.setTitle("Red Player Place Final");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				if (i == 1 && j == 1) {
					continue;
				}
				testGameboard.setSelectedCell(i, j);
				testGameLogic.processIfValidMove(i, j);
			}

		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.redSelectO();
		testGameboard.setSelectedCell(1, 1);
		testGameLogic.processIfValidMove(1, 1);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(4, testGameLogic.getRedSOSCount());
	}

	
	
	// AC 7.3 Tie
	@Test
	public void testTieGame() {
		testWindow.setTitle("Tie");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				testGameboard.setSelectedCell(i, j);
				testGameLogic.processIfValidMove(i, j);
			}
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(GameWinState.Tie, testGameLogic.getGameWinState());
	}
}
