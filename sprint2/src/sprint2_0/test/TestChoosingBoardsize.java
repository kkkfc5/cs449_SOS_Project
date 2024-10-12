package sprint1_0.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.Test;

import sprint1_0.production.GUI.SOS_Window;
import sprint1_0.production.GameLogic.GameLogicManager;


public class TestChoosingBoardsize {
	
	SOS_Window testWindow;
	
	@Before
	public void setUp() throws Exception {
		testWindow = new SOS_Window(new GameLogicManager()); 
		System.out.print("Running JUnit;\n");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	
	// AC 1.1 Choosing boardsize
	@Test
	public void testIntegerOverThirty() {
		assertFalse(testWindow.boardsizePressed(35));
	}
	
	@Test
	public void testIntegerUnderThree() {
		assertFalse(testWindow.boardsizePressed(1));
	}
	
	@Test
	public void testIntegerBetweenThreeAndThirty() {
		assertTrue(testWindow.boardsizePressed(12));
	}
	
	
	// AC 1.2 Default Boardsize
	@Test
	public void testDefaultBoardsize() {
		assertEquals(5, testWindow.getBoardsize());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
