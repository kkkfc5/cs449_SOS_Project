package production.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import production.GUI.CellPanel;
import production.GameLogic.GameStateManager.CellOpt;
import production.GameLogic.GameStateManager.GameTurn;

public class SOS_Player_Computer extends SOS_Player_Main {

	//public SOS_Player_Computer(SOS_Game_Main g) {
	//	game = g;
	//}

	

	@Override
	public CellLogical getSelectedCell() {
		// TODO Auto-generated method stub

		// gen selected cell
		
		System.out.print("COMPUTER ENTER getSelectedCell()\n");
		
		conjureMove();
		
		//JOptionPane.showMessageDialog(null, "computer get selected cell:\n("+selectedCell.getX() + "," + selectedCell.getY() + ")\n");
		return selectedCell;
	}

	// COMPUTER VERS
	private void conjureMove() {
		
		boolean continueProcessing = waitForTime();
		
		// if waitForTime doesn't have computer selected still, cancel out of selecting a cell
		if(!continueProcessing) {
			return;
		}
		
		CellLogical chosenS, chosenO;

		int numberOfUtilsToCompare;

		// if comp is hard, numofutils is small
		numberOfUtilsToCompare = 5;
		// else if comp is ~~
		// ~~~~

		var topSUtils = gameStateManager.getCurrentGame().getTopSUtil(numberOfUtilsToCompare);
		var topOUtils = gameStateManager.getCurrentGame().getTopOUtil(numberOfUtilsToCompare);

		// if hard, go for SOS immediately

		// else: have a chance they wont capitalize on SOS :
		// decide which between SO to check for SOS first
		Random rand = new Random();

		// MEDIUM DIFF WOULD COMPARE THE TWO CHOSEN AND DETERMINE WHICH WEIGHT IS HIGHER
		// ONLY DIFF BETWEEN MEDIUM AND HARD IS THAT MEDIUM HAS 1/5 CHANCE OF MISSING
		// SOS?

		//
		// ######## EDIT BELOW TO HAVE && !easyComputerLevel
		if (topSUtils.get(0).getS_Util() > 90) {
			// then it forms an SOS. So play that.

			// gather all potential SOS sequence-making moves.
			var potentialSOSSequencePlays = new ArrayList<CellLogical>();
			for (CellLogical c : topSUtils) {
				if (c.getS_Util() > 90) {
					potentialSOSSequencePlays.add(c);
				}
			}

			// select a rand SOS sequence-making move
			int randIndex = rand.nextInt(potentialSOSSequencePlays.size());
			chosenS = potentialSOSSequencePlays.get(randIndex);

		} else { // ELSE IF COMP is easy level and/or top plays aren't SOS sequences
					// choose a random cell in the array

			int randIndex = rand.nextInt(numberOfUtilsToCompare);

			chosenS = topSUtils.get(randIndex);

		}

		// ######## EDIT BELOW TO HAVE && !easyComputerLevel
		if (topOUtils.get(0).getO_Util() > 90) {
			// then it forms an SOS. So play that.

			// gather all potential SOS sequence-making moves.
			var potentialSOSSequencePlays = new ArrayList<CellLogical>();
			for (CellLogical c : topOUtils) {
				if (c.getO_Util() > 90) {
					potentialSOSSequencePlays.add(c);
				}
			}

			// select a rand SOS sequence-making move
			int randIndex = rand.nextInt(potentialSOSSequencePlays.size());
			chosenO = potentialSOSSequencePlays.get(randIndex);
		} else { // ELSE IF COMP is easy level and/or top plays aren't SOS sequences
					// choose a random cell in the array

			int randIndex = rand.nextInt(numberOfUtilsToCompare);

			chosenO = topOUtils.get(randIndex);

		}

		
		// now check who computer plays
		if (chosenS.getS_Util() > chosenO.getO_Util()) {
			// PROCESSMOVE() S....
			var tempCell = new CellLogical();
			tempCell.setX(chosenS.getX());
			tempCell.setY(chosenS.getY());
			tempCell.setCellOpt(CellOpt.S);
			selectedCell = tempCell;
		} else {
			// PROCESSMOVE() O....
			var tempCell = new CellLogical();
			tempCell.setX(chosenO.getX());
			tempCell.setY(chosenO.getY());
			tempCell.setCellOpt(CellOpt.O);
			selectedCell = tempCell;
		}
		
	}
	
	
	boolean waitForTime() {
		
		int time = 0;
		
		int sliderValue;
		
		if(gameStateManager.getGameTurn() == GameTurn.TurnRed) {
			sliderValue = gameStateManager.getRedSliderValue();
		}
		else {
			sliderValue = gameStateManager.getBlueSliderValue();
			//System.out.print("slidervalue: " + sliderValue);
		}
		
		Instant start = Instant.now();
		Instant end = start.plusSeconds(sliderValue);
		
		
		// while time is less than that current turn's computer slider value
		//        && current turn is supposed to be computer 
		while(time < (sliderValue) ) {
		//while(Instant.now().isBefore(end)) {	
		//System.out.print("")
			try {
				//Thread.sleep(1000);
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			
			time++;
			System.out.print("time: " + time + "\n");
			
			//System.out.print("\tgameTurn: "+gameStateManager.getGameTurn() + "\n");
		}
		
		ActionListener waitAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		new Timer(sliderValue * 1000, waitAction).start();
		
		// after the while loop, check if current player still has computer selected. 
		if(gameStateManager.getGameTurn() == GameTurn.TurnRed ? gameStateManager.isRedComputer() : gameStateManager.isBlueComputer()) {
			return true;
		}
		return false;
	}
}
