package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import production.GUI.*;
import production.GameLogic.*;
import production.GameLogic.SOS_Game_Main.GameWinState;

public class TestEndSimple {
	private SOS_Window testWindow;
	private SOS_Game_Main testGameLogic;
	private Gameboard testGameboard;

	@Before
	public void setUp() {
		testGameLogic = new SOS_Game_Simple();
		testWindow = new SOS_Window(testGameLogic);
		testGameLogic.connectGUIWindow(testWindow);

		testWindow.getMenuBarManager().boardsizePressed(3);
		// testWindow.selectGameTypeGeneral();
		testWindow.newGamePressed();

		testGameLogic = testWindow.getGameLogic();

		testGameboard = testWindow.getGameboard();
		testGameLogic.setGameboard(testGameboard);
	}

	// AC 5.1 A valid SOS sequence for Blue Player with S missing
	@Test
	public void testBlueSOX() {
		testWindow.setTitle("SOX Sequence Blue Player");

		testGameboard = testWindow.getGameboard();

		// burn red first play
		testGameboard.setSelectedCell(0, 0);
		testGameLogic.processIfValidMove(0, 0);

		// blue play
		testGameboard.setSelectedCell(0, 2);
		testGameLogic.processIfValidMove(0, 2);

		// red play O
		testWindow.redSelectO();
		testGameboard.setSelectedCell(1, 2);
		testGameLogic.processIfValidMove(1, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// blue play S, creating SOS
		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(GameWinState.BlueWin, testGameLogic.getGameWinState());
	}

	// 5.2 A valid SOS sequence for Red Player with S missing
	@Test
	public void testRedSOX() {
		testWindow.setTitle("SOX Sequence Red Player");

		testGameboard = testWindow.getGameboard();

		// red play
		testGameboard.setSelectedCell(0, 2);
		testGameLogic.processIfValidMove(0, 2);

		// blue play O
		testWindow.blueSelectO();
		testGameboard.setSelectedCell(1, 2);
		testGameLogic.processIfValidMove(1, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// red play S, creating SOS
		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(GameWinState.RedWin, testGameLogic.getGameWinState());
	}

	// AC 5.3 A valid SOS sequence for Blue Player with O missing
	@Test
	public void testBlueSXS() {
		testWindow.setTitle("SXS Sequence Blue Player");

		testGameboard = testWindow.getGameboard();

		// red play S
		testGameboard.setSelectedCell(0, 2);
		testGameLogic.processIfValidMove(0, 2);

		// blue play S
		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		// burn red play
		testGameboard.setSelectedCell(0, 0);
		testGameLogic.processIfValidMove(0, 0);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// blue play O, creating SOS
		testWindow.blueSelectO();
		testGameboard.setSelectedCell(1, 2);
		testGameLogic.processIfValidMove(1, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(GameWinState.BlueWin, testGameLogic.getGameWinState());
	}

	// AC 5.4 A valid SOS sequence for Red Player with O missing
	@Test
	public void testRedSXS() {
		testWindow.setTitle("SXS Sequence Red Player");

		testGameboard = testWindow.getGameboard();

		// red play S
		testGameboard.setSelectedCell(0, 2);
		testGameLogic.processIfValidMove(0, 2);

		// blue play S
		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// red play O, creating SOS
		testWindow.redSelectO();
		testGameboard.setSelectedCell(1, 2);
		testGameLogic.processIfValidMove(1, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(GameWinState.RedWin, testGameLogic.getGameWinState());
	}

	// AC 5.5 Nobody wins [Tie Game]
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
