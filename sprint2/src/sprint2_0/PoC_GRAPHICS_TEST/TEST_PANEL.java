package sprint1_0.PoC_GRAPHICS_TEST;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class TEST_PANEL extends JPanel {

	private JLabel label = new JLabel();
	
	public TEST_PANEL() {
		// TODO Auto-generated constructor stub
		setBorder(BorderFactory.createLineBorder(Color.white));
		
		label.setVisible(true);
		
		add(label);
	}

	public void drawS() {
		label.setSize(getParent().getSize());
		label.setText(" S ");
		label.setFont(new Font( "Serif", Font.BOLD, 30));
		label.setForeground(Color.red);
		
		label.setVisible(true);
		JOptionPane.showMessageDialog(null, "ADDED S");
	}

}
