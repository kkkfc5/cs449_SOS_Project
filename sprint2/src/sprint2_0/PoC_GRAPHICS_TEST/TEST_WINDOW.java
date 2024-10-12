package sprint1_0.PoC_GRAPHICS_TEST;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TEST_WINDOW extends JFrame implements WindowStateListener {
	
	TEST_GAMEBOARD_2 panel;
	
	
	public TEST_WINDOW() {
		setLayout(new BorderLayout());
		// set size of the window
		setSize(500, 500);
		// Specify an action for the window close button.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//JPanel panel = new JPanel(new GridLayout(10,10));
		panel = new TEST_GAMEBOARD_2(20,20, 10);
		//panel = new TEST_GAMEBOARD_3(10);
		
		/*
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++) {
				JButton b = new JButton();
				b.setText(i + " " + j);
				panel.add(b);
			}
		}*/
		
		//JButton button = new JButton();
		//button.setText("TEST BUTTON");
		//button.setSize((this.getWidth() / 1000), (this.getHeight() / 2));
		
		//add(button);
		panel.setVisible(true);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		
		addWindowStateListener(this);
		
		//pack();
	}
	
	public void windowStateChanged(WindowEvent e)
    {
		//panel.updateButtonSizes();
		var x = new JPanel();
		x.setBackground(Color.BLUE);
		add(x, BorderLayout.NORTH);
		//pack();
		
		
        JOptionPane.showMessageDialog(this, "window state changed");
    }
}
