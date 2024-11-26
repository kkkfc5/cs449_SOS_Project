package production.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;


/**
 * The visible cell panel. 
 * Has a x and y location for the grid
 */
public class CellPanel extends JPanel{
	private JLabel label = new JLabel();
	int gridX, gridY;
	
	public CellPanel(int x, int y) {
		// give the visible cell a border
		setBorder(BorderFactory.createLineBorder(Color.black));
		// give the visible cell a background color
		setBackground(Color.cyan);
		
		gridX = x;
		gridY = y;
		
		label.setText(" ");
		label.setVisible(true);
		
		add(label);
		
		setVisible(false);
		setVisible(true);
	}
	
	public void drawS() {
		//label.setSize(getParent().getSize());
		label.setText(" S ");
		label.setFont(new Font( "Serif", Font.BOLD, 30));
		//label.setForeground(Color.red);
		
		label.setVisible(true);
	}
	
	public void drawO() {
		label.setText(" O ");
		label.setFont(new Font( "Serif", Font.BOLD, 30));
		//label.setForeground(Color.red);
		
		label.setVisible(true);
	}
	
	public void drawClear() {
		label.setText("");
		label.setFont(new JLabel().getFont());
		label.setForeground(new JLabel().getForeground());
	}
	
	public void drawUtil(int i,int j) {
		// v prevent overwriting the S/O choices
		if(!label.getText().contains("S") && !label.getText().contains("O")) 
			if(i != -1 && j != -1)
				label.setText(String.valueOf(i) + "," + String.valueOf(j));
			else 
				label.setText("");
			
	}
	
	public int getGridX() {
		return gridX;
	}
	public int getGridY() {
		return gridY;
	}
}
