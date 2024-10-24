package production.GameLogic;

import production.GameLogic.SOS_Game_Main.GameTurn;


/**
 * Holds two logical cells as a pair and holds who created the pair
 */
public class SOS_Pair {
	CellLogical s1, s2;
	GameTurn who;
	
	public SOS_Pair(CellLogical one, CellLogical two, GameTurn w) {
		s1 = one;
		s2 = two;
		who = w;
	}

	public CellLogical getS1() {
		return s1;
	}

	public CellLogical getS2() {
		return s2;
	}
	
	public GameTurn getWho() {
		return who;
	}
	
}
