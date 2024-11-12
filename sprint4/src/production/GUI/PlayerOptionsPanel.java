package production.GUI;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import production.GameLogic.GameStateManager.CellOpt;

public class PlayerOptionsPanel extends JPanel {
	
	JLabel title;
	JRadioButton humanButton, sButton, oButton, computerButton;
	JSlider slider;
	
	SOS_Window window;
	
	public PlayerOptionsPanel(String player, SOS_Window w) {
		window = w;
		
		setLayout(new GridLayout(2,1));
		
		// initialize the title field of the panel
		title = new JLabel(player);
		// add title to top panel
		var topPanel = new JPanel();
		topPanel.add(title);
		// add top panel to main panel
		add(topPanel);
		
		
		// initialize buttons
		humanButton = new JRadioButton("Human");
		sButton = new JRadioButton("S");
		oButton = new JRadioButton("O");
		computerButton = new JRadioButton("Computer");
		
		// init button values
		humanButton.setSelected(true);
		sButton.setSelected(true);
		// set button events
		humanButton.addActionListener(e -> toggleButtonGroups());
		computerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "COMPUTER TOGGLED");
				
				toggleButtonGroups();
				window.updatePlayerOptionsPanelSettingsInRelationToGameStateManager();
				window.computerSelected();
			}
		});
		
		// add buttons to groups
		var bgSO = new ButtonGroup();
		bgSO.add(sButton);
		bgSO.add(oButton);
		var bgHC = new ButtonGroup();
		bgHC.add(humanButton);
		bgHC.add(computerButton);
		
		// create computer slider
		slider = new JSlider(0,5);
		slider.setMajorTickSpacing(1);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.addChangeListener(e -> window.updatePlayerOptionsPanelSettingsInRelationToGameStateManager());
		
		// add buttons to bottom panel
		var bottomPanel = new JPanel(new GridLayout(5,2));
		bottomPanel.add(humanButton);    // row 1, left
		bottomPanel.add(new JLabel());   // row 1, right
		bottomPanel.add(new JLabel());   // row 2, left
		bottomPanel.add(sButton);        // row 2, right
		bottomPanel.add(new JLabel());   // row 3, left
		bottomPanel.add(oButton);        // row 3, right
		bottomPanel.add(computerButton); // row 4, left
		bottomPanel.add(new JLabel());
		bottomPanel.add(slider);
		
		// add bottompanel to main panel
		add(bottomPanel);
		
	}
	
	
	
	public void toggleButtonGroups() {
		
		if(humanButton.isSelected()) {
			// if human button is selected activate s and o options
			sButton.setEnabled(true);
			oButton.setEnabled(true);
		}
		else {
			// if computer is selected
			sButton.setEnabled(false);
			oButton.setEnabled(false);
			
			// activate computer brain
			
		}
	}
	
	public void reset() {
		humanButton.setSelected(true);
		sButton.setSelected(true);
		sButton.setEnabled(true);
		oButton.setEnabled(true);
	}
	
	
	public boolean isComputer() {
		return computerButton.isSelected();
	}
	
	public CellOpt selectedSO() {
		if (sButton.isSelected()) 
			return CellOpt.S; 
		else
			return CellOpt.O;
	}
	
	public void selectS() {
		sButton.setSelected(true);
	}
	public void selectO() {
		oButton.setSelected(true);
	}



	public JSlider getSlider() {
		return slider;
	}
	
	public JRadioButton getComputerButton() {
		return computerButton;
	}
	
	
	
	// ====================================
	public void test_selectComputer() {
		computerButton.setSelected(true);
		toggleButtonGroups();
		window.updatePlayerOptionsPanelSettingsInRelationToGameStateManager();
		window.computerSelected();
	}
	
}
