package production.GameLogic;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import production.GUI.SOS_Window;
import production.GameLogic.GameStateManager.CellOpt;

public class FileOperations {

//	SOS_Window window;
//	GameStateManager gameStateManager;
//
//	public void connectWindow(SOS_Window w) {
//		window = w;
//	}
//
//	public void connectGameStateManager(GameStateManager g) {
//		gameStateManager = g;
//	}

	public ArrayList<CellLogical> ReadFile(File file) {
		try {
			ArrayList<CellLogical> moveList = new ArrayList<CellLogical>();
			
			//File file = new File(fileName + ".txt" );
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				String move = scanner.nextLine();
				System.out.print("[[{{ " + move + " }}]]\n\n");
				
				String[] X_Y_Option = move.split("#");
				
				var tempCell = new CellLogical();
				
				tempCell.setXY(Integer.parseInt(X_Y_Option[0]), Integer.parseInt(X_Y_Option[1]));
				
				if(X_Y_Option[2].contains("S")) {
					tempCell.setCellOpt(CellOpt.S);
				} else {
					tempCell.setCellOpt(CellOpt.O);
				}
				
				moveList.add(tempCell);
			}
			
			scanner.close();
			return moveList;
			
		} catch (Exception e) {
			for (int i = 0; i < 6; i++) {
				System.out.print("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
			}
			System.out.print("\nFailed to read; " + e + "\n\n");
			return null;
		}
	
	}

	public void WriteFile(ArrayList<CellLogical> moveList, String fileName) {
		try {
			
			FileWriter fileWriter = new FileWriter( fileName + ".txt" );
			
			for(int i = 0; i < moveList.size(); i++) {
				var tempCell = moveList.get(i);
				
				String moveString = new String(tempCell.getX() + "#" + tempCell.getY() + "#" + tempCell.getCellOpt() + "\n");
				System.out.print(tempCell.getX() + "#" + tempCell.getY() + "#" + tempCell.getCellOpt() + "\n");
				
				fileWriter.write(moveString);
 			}
			
			fileWriter.close();
			
		} catch (Exception e) {
			for (int i = 0; i < 6; i++) {
				System.out.print("\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
			}
			System.out.print("\nFailed to write; " + e + "\n\n");
		}
	}
}
