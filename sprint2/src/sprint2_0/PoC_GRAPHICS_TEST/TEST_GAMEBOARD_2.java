package sprint1_0.PoC_GRAPHICS_TEST;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class TEST_GAMEBOARD_2 extends JPanel implements MouseListener{
	
	int WIDTH, HEIGHT;
	ArrayList<ArrayList<JButton>> grid;
	boolean flag = true;
	public TEST_GAMEBOARD_2(int width, int height, int boardsize) {
		
		WIDTH = width;
		HEIGHT = height;
		
		grid = new ArrayList<ArrayList<JButton>>(boardsize);
		
		setLayout(new GridLayout(boardsize, boardsize));
		
		for(int i = 0; i < boardsize; i++){
			grid.add(new ArrayList<JButton>(boardsize));
			for(int j = 0; j < boardsize; j++) {
				if(i%2 == 0) flag = !flag;
				JButton b = new JButton();
				b.setText(i + " " + j);
				b.setSize(width, height);
				var pa = new TEST_PANEL();
				//pa.setBackground(Color.cyan);
				if (flag) { pa.addMouseListener(this);; pa.setBackground(Color.CYAN); flag = !flag; System.out.println( "cyan :  ");}
				else { pa.setBackground(Color.BLACK); flag = !flag; System.out.println( "blac :  ");} 
				add(pa);
				grid.get(i).add(j, b);
			}
		}
		
	}// end constructor
	
	
	public void updateButtonSizes() {
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++) {
				//JButton b = new JButton();
				//b.setText(i + " " + j);
				grid.get(i).get(j).setSize(WIDTH, HEIGHT);
				//add(b);
			}
		}
	}

	
	private void drawS(TEST_PANEL t) { t.drawS(); }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		drawS( (TEST_PANEL) e.getSource());
		//JOptionPane.showMessageDialog(null, "mouse clicked  :  " + e.getSource().toString());	
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
