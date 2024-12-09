package test;

import static org.junit.Assert.*;
import org.junit.*;

import production.GUI.SOS_Window;
import production.GameLogic.*;
import production.GameLogic.GameStateManager.CellOpt;
import production.mainapp.SOS_Main;

public class TestComputerPlacement {
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
	
	// AC 8.1 Deciding the best places to play S
	/*
	 * Given a player is set to computer,
When it is their turn
Then the computer decides the best S plays to make.

	 */
	@Test
	public void pickTopS() {
		
		windowObj.setTitle("Pick Top S");
		
		var game = gameStateObj.getCurrentGame();
		
		humanObj.test_selectCell(0, 0, CellOpt.S);
		game.processMove();
		
		humanObj.test_selectCell(0, 1, CellOpt.O);
		game.processMove();
		
		var topS = game.getTopSUtil(1);
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertTrue( topS.get(0).getS_Util() > 80 );
	}

	
	// AC 8.2 Deciding the best places to play O
/*
 * Given a player is set to computer,
When it is their turn
Then the computer decides the best O plays to make.

 */
	@Test
	public void pickTopO() {
		
		windowObj.setTitle("Pick Top O");
		
		var game = gameStateObj.getCurrentGame();
		
		humanObj.test_selectCell(0, 0, CellOpt.S);
		game.processMove();
		
		humanObj.test_selectCell(0, 2, CellOpt.S);
		game.processMove();
		
		var topO = game.getTopOUtil(1);
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertTrue( topO.get(0).getO_Util() > 80 );
	}
	
	
	// AC 8.3 Placing selected cell.
	/*
	 * Given it is the computer’s turn,
When the computer decides where is best to place,
Then the computer places the option.  

	 */
	@Test
	public void placeOptimal() {
		
		windowObj.setTitle("Place Optimal");
		
		var game = gameStateObj.getCurrentGame();
		
		humanObj.test_selectCell(0, 0, CellOpt.S);
		game.processMove();
		
		humanObj.test_selectCell(0, 2, CellOpt.S);
		game.processMove();
		
		var topO = game.getTopOUtil(1);
		
		windowObj.test_redSelectComputer();
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {}
		
		assertEquals( gameStateObj.getRed_SOS_Count(), 1 );
	}
	
}
