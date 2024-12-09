package production.GUI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class CellPanelGrid extends JPanel {
	public CellPanelGrid(int boardsize, int gameboardDimensions) {
		setLayout(new GridLayout(boardsize, boardsize));
		setPreferredSize(new Dimension(gameboardDimensions,gameboardDimensions));
		setVisible(true);
	}
}
