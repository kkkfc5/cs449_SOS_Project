package test;


import static org.junit.Assert.*;
import org.junit.*;

import production.GUI.SOS_Window;
import production.GameLogic.GameStateManager;
import production.GameLogic.GameStateManager.CellOpt;
import production.GameLogic.SOS_Game_Main;
import production.GameLogic.SOS_Player_Human;
import production.mainapp.SOS_Main;

public class TestComputerSelect {

SOS_Main mainObj;
GameStateManager gameStateObj;
SOS_Window windowObj;
//SOS_Game_Main gameObj;
SOS_Player_Human humanObj;
	
	@Before
	public void setUp() {
		mainObj = new SOS_Main();
		windowObj = mainObj.getWindow();
		gameStateObj = mainObj.getGameStateManager();
		humanObj = mainObj.getPlayerHuman();
	}
	
	
	
	// AC 9.1 Red player selecting computer when it is red turn
	/*
	 * Given it is Red turn
When red player selects computer
Then red player’s computer should play

	 */
	@Test
	public void testRedTurnRedSelectComputer() {
		windowObj.setTitle("RedTurn -- Red Select Computer");
		
		windowObj.test_redSelectComputer();
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertEquals(24,gameStateObj.getRemainingCells());
		
	}
	
	
	
	// AC 9.2 Red player selecting computer when it is blue turn
	/*
	 * 
Given it is blue turn
When red player selects computer
Then red player should be set to computer, and play computer when it is red turn.

	 */
	@Test
	public void testBlueTurnRedSelectComputer() {
		windowObj.setTitle("BlueTurn -- Red Select Computer");
		
		// swaps turn
		humanObj.test_selectCell(0, 0, CellOpt.S);
		gameStateObj.getCurrentGame().processMove();
		
		windowObj.test_redSelectComputer();
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertEquals(24,gameStateObj.getRemainingCells());
	}
	
	
	
	// AC 9.3 Blue player selecting computer when it is blue turn
	/*
	 * Given it is blue turn
When blue player selects computer
Then blue player’s computer should play

	 */
	@Test
	public void testBlueTurnBlueSelectComputer() {
		windowObj.setTitle("BlueTurn -- Blue Select Computer");
		
		// swaps turn
		humanObj.test_selectCell(0, 0, CellOpt.S);
		gameStateObj.getCurrentGame().processMove();
		
		windowObj.test_blueSelectComputer();
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertEquals(23,gameStateObj.getRemainingCells());
	}
	
	
	// AC 9.4 Blue player selecting computer when it is red turn
	/*
	 * Given it is red turn
When blue player selects computer
Then blue player should be set to computer, and play computer when it is blue turn.

	 */
	@Test
	public void testRedTurnBlueSelectComputer() {
		windowObj.setTitle("RedTurn -- Blue Select Computer");
		
		windowObj.test_blueSelectComputer();
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertEquals(25,gameStateObj.getRemainingCells());
	}
	
}
