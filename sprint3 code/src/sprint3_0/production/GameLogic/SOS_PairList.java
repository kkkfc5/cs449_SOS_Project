package sprint3_0.production.GameLogic;

import java.util.ArrayList;



/**
 * Creates a list of pairs
 */
public class SOS_PairList {
	ArrayList<SOS_Pair> pairs;
	
	public SOS_PairList() {
		pairs = new ArrayList<SOS_Pair>();
	}
	
	public boolean hasPair(CellLogical c1, CellLogical c2) {
	
		// check if any saved pair is what is passed in
		for(SOS_Pair p : pairs) {
			if((p.getS1().equals(c1) && p.getS2().equals(c2))
			|| (p.getS1().equals(c2) && p.getS2().equals(c1))) {
				return true;
			}
		}
		
		// doesnt have pair
		return false;
	}
	
	public void addPair(SOS_Pair pair) {
		pairs.add(pair);
	}
	
	public ArrayList<SOS_Pair> getPairs(){
		return pairs;
	}
}
