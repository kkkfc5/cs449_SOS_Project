package sprint1_0.production.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class test_panel extends JPanel {
	private JLabel label = new JLabel();
	int gridX, gridY;
	
	public test_panel() {
		// give the visible cell a border
		setBorder(BorderFactory.createLineBorder(Color.black));
		// give the visible cell a background color
		setBackground(Color.cyan);

		label.setVisible(true);
		
		//this.x = x;
		//this.y = y;
		
		label.setText(" ");
		label.setVisible(true);
		
		add(label);
		
		drawS();
		
		setVisible(false);
		setVisible(true);
	}
	
	public test_panel(int x, int y){
		// give the visible cell a border
				setBorder(BorderFactory.createLineBorder(Color.black));
				// give the visible cell a background color
				setBackground(Color.cyan);

				label.setVisible(true);
				
				this.gridX = x;
				this.gridY = y;
				
				label.setText(" ");
				label.setVisible(true);
				
				add(label);
				
				drawS();
				
				setVisible(false);
				setVisible(true);
				
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
		
	}
	
	public void drawClear() {
		
	}
	
	public int getGridX() {
		return gridX;
	}
	public int geGridY() {
		return gridY;
	}
}
