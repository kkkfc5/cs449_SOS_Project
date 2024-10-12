package sprint1_0.production.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class CellPanel extends JPanel{
	private JLabel label = new JLabel();
	int gridX, gridY;
	
	public CellPanel(int x, int y) {
	//public CellPanel() {
		// give the visible cell a border
		setBorder(BorderFactory.createLineBorder(Color.black));
		// give the visible cell a background color
		setBackground(Color.cyan);
		
		gridX = x;
		gridY = y;
		
		label.setText(" ");
		label.setVisible(true);
		
		add(label);
		
		//drawS();
		
		setVisible(false);
		setVisible(true);
	}
	
	public void drawS() {
		//label.setSize(getParent().getSize());
		label.setText(" S ");
		label.setFont(new Font( "Serif", Font.BOLD, 30));
		label.setForeground(Color.red);
		
		label.setVisible(true);
		//JOptionPane.showMessageDialog(null, "ADDED S");
	}
	
	public void drawO() {
		label.setText(" O ");
		label.setFont(new Font( "Serif", Font.BOLD, 30));
		label.setForeground(Color.red);
		
		label.setVisible(true);
	}
	
	public void drawClear() {
		
	}
	
	public int getGridX() {
		return gridX;
	}
	public int getGridY() {
		return gridY;
	}
}
