package app;

import java.awt.GridLayout;

import javax.swing.*;


public class MainWindow extends JFrame {

	JPanel mainPanel = new JPanel(new GridLayout(4,1));
	
	public MainWindow() {
		// set size of the window
		setSize(500,500);
		// Specify an action for the window close button.
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	    // create panel for the text label
	    JPanel text = new JPanel();
	    text.add(new JLabel("LABEL"));
	     
	    // create panel for the checkbox
	    JCheckBox box = new JCheckBox();
	    box.setText("CHECKBOX");
	    
	    // create panel for the radio button
	    JRadioButton button = new JRadioButton();
	    button.setText("BUTTON");
	    
	    // attach all objects to the grid
	    mainPanel.add(text);
	    mainPanel.add(add(new JPanelGraphicsTest()));
	    mainPanel.add(box);
	    mainPanel.add(button);
	    
	    // attach grid to the window
		add(mainPanel);

		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
