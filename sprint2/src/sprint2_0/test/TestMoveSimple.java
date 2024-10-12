package sprint1_0.test;

import static org.junit.Assert.*;
import org.junit.*;

import sprint1_0.production.GUI.Gameboard;
import sprint1_0.production.GUI.SOS_Window;
import sprint1_0.production.GameLogic.GameLogicManager;

public class TestMoveSimple {

	private SOS_Window testWindow;
	private GameLogicManager testGameLogic;
	private Gameboard testGameboard;

	@Before
	public void setUp() {
		testGameLogic = new GameLogicManager();
		testWindow = new SOS_Window(testGameLogic);
		testGameLogic.connectGUIWindow(testWindow);
	}

	// AC 4.1 Selecting S
	@Test
	public void testSelectS() {
		testWindow.setTitle("SELECTING S");
		
		// precondition
		testWindow.blueSelectO();
		testWindow.redSelectO();

		// keep window visible so you can verify results
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.redSelectS();
		testWindow.blueSelectS();

		// keep window visible so you can verify results
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// 4.2 Selecting O
	@Test
	public void testSelectO() {
		testWindow.setTitle("SELECTING O");
		
		// precondition
		testWindow.blueSelectS();
		testWindow.redSelectS();

		// keep window visible so you can verify results
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		testWindow.redSelectO();
		testWindow.blueSelectO();

		// keep window visible so you can verify results
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// AC 4.3 Placing S
	@Test
	public void testPlaceS() {
		testWindow.setTitle("PLACING S");
		
		testGameboard = testWindow.getGameboard();

		//testGameboard.setSelectedCell(2, 2);
		//testGameboard.drawLetter('S');
		testGameboard.setSelectedCell(2, 2);
		testGameLogic.isValidMove(2, 2);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		testGameboard.setSelectedCell(1, 1);
		testGameLogic.isValidMove(1,1);
		
		// keep window visible so you can verify results
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// AC 4.4 Placing O
		@Test
		public void testPlaceO() {
			testWindow.setTitle("PLACING O");
			
			testGameboard = testWindow.getGameboard();
			
			
			testWindow.redSelectO();
			
			testGameboard.setSelectedCell(2, 2);
			testGameLogic.isValidMove(2, 2);
			
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			testWindow.blueSelectO();
			
			testGameboard.setSelectedCell(1, 1);
			testGameLogic.isValidMove(1,1);
			
			
			// VERIFY SELECTEDCELL.GETCELLOPT == O
			
			// keep window visible so you can verify results
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		
		// AC 4.5 Player tries to place on an occupied cell
		@Test
		public void testPlaceOccupied() {
			testWindow.setTitle("PLACING ON OCCUPIED");
			
			testGameboard = testWindow.getGameboard();
			
			testGameboard.setSelectedCell(2, 2);
			testGameLogic.isValidMove(2, 2);
			
			assertFalse(testGameLogic.isValidMove(2, 2));
		}
}
