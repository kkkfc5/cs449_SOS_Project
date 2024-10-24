package sprint3_0.test;

import static org.junit.Assert.*;
import org.junit.*;

import junit.framework.TestListener;
import sprint3_0.production.GUI.Gameboard;
import sprint3_0.production.GUI.SOS_Window;
import sprint3_0.production.GameLogic.SOS_Game_Main;
import sprint3_0.production.GameLogic.SOS_Game_General;

public class TestMoveGeneral {

	private SOS_Window testWindow;
	private SOS_Game_Main testGameLogic;
	private Gameboard testGameboard;

	@Before
	public void setUp() {
		testGameLogic = new SOS_Game_General();
		testWindow = new SOS_Window(testGameLogic);
		testGameLogic.connectGUIWindow(testWindow);

	}

	// AC 6.1 Selecting S
	@Test
	public void testSelectS() {
		testWindow.setTitle("SELECTING S");

		// precondition
		testWindow.blueSelectO();
		testWindow.redSelectO();

		// keep window visible so you can verify results
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.redSelectS();
		testWindow.blueSelectS();

		// keep window visible so you can verify results
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 6.2 Selecting O
	@Test
	public void testSelectO() {
		testWindow.setTitle("SELECTING O");

		// precondition
		testWindow.blueSelectS();
		testWindow.redSelectS();

		// keep window visible so you can verify results
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.redSelectO();
		testWindow.blueSelectO();

		// keep window visible so you can verify results
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// AC 6.3 Placing S
	@Test
	public void testPlaceS() {
		testWindow.setTitle("PLACING S");

		testGameboard = testWindow.getGameboard();

		// testGameboard.setSelectedCell(2, 2);
		// testGameboard.drawLetter('S');
		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testGameboard.setSelectedCell(1, 1);
		testGameLogic.processIfValidMove(1, 1);

		// keep window visible so you can verify results
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// AC 6.4 Placing O
	@Test
	public void testPlaceO() {
		testWindow.setTitle("PLACING O");

		testGameboard = testWindow.getGameboard();

		testWindow.redSelectO();

		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.blueSelectO();

		testGameboard.setSelectedCell(1, 1);
		testGameLogic.processIfValidMove(1, 1);

		// VERIFY SELECTEDCELL.GETCELLOPT == O

		// keep window visible so you can verify results
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// AC 6.5 Player tries to place on an occupied cell
	@Test
	public void testPlaceOccupied() {
		testWindow.setTitle("PLACING ON OCCUPIED");

		testGameboard = testWindow.getGameboard();

		testGameboard.setSelectedCell(2, 2);
		testGameLogic.processIfValidMove(2, 2);

		assertFalse(testGameLogic.processIfValidMove(2, 2));
	}

	// AC 6.6 A valid SOS sequence for Blue Player with S missing
	@Test
	public void testBlueSOX() {
		testWindow.setTitle("SOX Sequence Blue Player");

		testGameboard = testWindow.getGameboard();

		// burn red first play
		testGameboard.setSelectedCell(0, 0);
		testGameLogic.processIfValidMove(0, 0);

		// blue play
		testGameboard.setSelectedCell(0, 3);
		testGameLogic.processIfValidMove(0, 3);

		// red play O
		testWindow.redSelectO();
		testGameboard.setSelectedCell(1, 3);
		testGameLogic.processIfValidMove(1, 3);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// blue play S, creating SOS
		testGameboard.setSelectedCell(2, 3);
		testGameLogic.processIfValidMove(2, 3);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(1, testGameLogic.getBlueSOSCount());
	}

	// AC 6.7 A valid SOS sequence for Red Player with S missing
	@Test
	public void testRedSOX() {
		testWindow.setTitle("SOX Sequence Red Player");

		testGameboard = testWindow.getGameboard();

		// red play
		testGameboard.setSelectedCell(0, 3);
		testGameLogic.processIfValidMove(0, 3);

		// blue play O
		testWindow.blueSelectO();
		testGameboard.setSelectedCell(1, 3);
		testGameLogic.processIfValidMove(1, 3);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// red play S, creating SOS
		testGameboard.setSelectedCell(2, 3);
		testGameLogic.processIfValidMove(2, 3);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(1, testGameLogic.getRedSOSCount());
	}
	
	
	// AC 6.8 A valid SOS sequence for Blue Player with O missing
	@Test
	public void testBlueSXS() {
		testWindow.setTitle("SXS Sequence Blue Player");

		testGameboard = testWindow.getGameboard();
		
		// red play S
		testGameboard.setSelectedCell(0, 3);
		testGameLogic.processIfValidMove(0, 3);
		
		// blue play S
		testGameboard.setSelectedCell(2, 3);
		testGameLogic.processIfValidMove(2, 3);
		
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
		testGameboard.setSelectedCell(1, 3);
		testGameLogic.processIfValidMove(1, 3);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, testGameLogic.getBlueSOSCount());
	}
	
	// AC 6.9 A valid SOS sequence for Red Player with O missing
		@Test
		public void testRedSXS() {
			testWindow.setTitle("SXS Sequence Red Player");

			testGameboard = testWindow.getGameboard();
			
			// red play S
			testGameboard.setSelectedCell(0, 3);
			testGameLogic.processIfValidMove(0, 3);
			
			// blue play S
			testGameboard.setSelectedCell(2, 3);
			testGameLogic.processIfValidMove(2, 3);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// red play O, creating SOS
			testWindow.redSelectO();
			testGameboard.setSelectedCell(1, 3);
			testGameLogic.processIfValidMove(1, 3);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			assertEquals(1, testGameLogic.getRedSOSCount());
		}
}
