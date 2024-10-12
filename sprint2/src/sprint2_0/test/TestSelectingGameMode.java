package sprint1_0.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

import sprint1_0.production.GUI.SOS_Window;
import sprint1_0.production.GameLogic.GameLogicManager;
import sprint1_0.production.GameLogic.GameLogicManager.GameType;

public class TestSelectingGameMode {

	SOS_Window testWindow;
	GameLogicManager testGameLogic;
	
	@Before
	public void setUp() throws Exception {
		testGameLogic = new GameLogicManager();
		testWindow = new SOS_Window(testGameLogic); 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// AC 2.1 Select General Game Mode
	@Test
	public void testSelectGeneralGame() {
		// precondition
		testWindow.selectGameTypeSimple();
		
		// is newGamePressed returns true, then check if that new game creation works, and if so check the game turn and if it is the same as the gameboards
		testWindow.selectGameTypeGeneral();
		
		if(testWindow.newGamePressed()) {
			if(testWindow.getWindowGameType() == GameType.General) {
				assertEquals("", testWindow.getGameType(), GameType.General);
			} else {
				System.out.print("FAILED getWindowGameType(); returned value wasnt General\n");
				assertFalse(true);
			}
		}
		else {
			System.out.print("FAILED newGamePressed(); returned value wasnt TRUE\n");
			assertFalse(true);
		}
		
		// keep window visible so you can verify results
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	// AC 2.2
	@Test
	public void testSelectSimpleGame() {
		// precondition
		testWindow.selectGameTypeGeneral();
		
		
		testWindow.selectGameTypeSimple();
		// is newGamePressed returns true, then check if that new game creation works, and if so check the game turn and if it is the same as the gameboards
		if(testWindow.newGamePressed()) {
			if(testWindow.getWindowGameType() == GameType.Simple) {
				assertEquals("", testWindow.getGameType(), GameType.Simple);
			}
			else {
				System.out.print("FAILED getWindowGameType(); returned value wasnt Simple\n");
				assertFalse(true);
			}
		}
		else {
			System.out.print("FAILED newGamePressed(); returned value wasnt TRUE\n");
			assertFalse(true);
		}
		
		
		// keep window visible so you can verify results
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
