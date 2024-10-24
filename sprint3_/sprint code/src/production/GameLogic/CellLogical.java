package production.GameLogic;

import production.GameLogic.SOS_Game_Main.CellOpt;

public class CellLogical {
	// COORDS
	int x, y;
	
	// UTILITY : used to determine how important playing in this cell would be
	int S_Util, O_Util;
	
	// CELL OPTION : uses enumerator to determine its option
	CellOpt cellOpt = CellOpt.NULL;
	
	
	// COORDS GET/SET
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setXY(int i, int j) { x = i; y = j; }
	
	// UTILITY GET/SET
	public int getS_Util() { return S_Util; }
	public int getO_Util() { return O_Util; }
	public void setS_Util(int s) { S_Util = s; }
	public void setO_Util(int o) { O_Util = o; }
	
	// CELL OPTION GET/SET
	public CellOpt getCellOpt() { return cellOpt; }
	
	public void setCellOpt(CellOpt c) { 
		
		cellOpt = c; 
		
		// if the cell is not being deselected, clear it's utility
		if(c != CellOpt.NULL) {
			S_Util = -1;
			O_Util = -1;
		}
	}
	
}


