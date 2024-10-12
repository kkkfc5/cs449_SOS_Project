package sprint1_0.PoC_GRAPHICS_TEST;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class TEST_GAMEBOARD_3 extends JPanel implements MouseListener {

	private ArrayList<ArrayList<TEST_PANEL>> grid = new ArrayList<ArrayList<TEST_PANEL>>();
	
	private int BOARDSIZE;
	
	public TEST_GAMEBOARD_3(int boardsize) {
		BOARDSIZE = boardsize;
		System.out.println(boardsize);
		
		setLayout(new BorderLayout(boardsize, boardsize));
		
		boolean flag = true;
		for(int i = 0; i < boardsize; i++) {
			grid.add(new ArrayList<TEST_PANEL>(boardsize));
			
			for (int j = 0; j < boardsize; j++) {
				TEST_PANEL panel = new TEST_PANEL();
				panel.setSize(400/boardsize, 400/boardsize);
				panel.setPreferredSize(getSize());
				panel.setVisible(true);
				
				if (flag) { panel.addMouseListener(this); panel.setBackground(Color.CYAN); flag = !flag; System.out.print( "cyan :  ");}
				else { panel.setBackground(Color.BLACK); flag = !flag; System.out.print( "blac :  ");} 
				System.out.println( i + " : " + j);
				/*
				 * if (j % 2 == 1) panel.setBackground(Color.CYAN); 
				 *	else            panel.setBackground(Color.BLACK);
				 *
				 * if (j == 5) panel.setBackground(Color.yellow);
				 */
				//panel.setBackground(Color.yellow);
				grid.get(i).add(j, panel);
				var b = new JLabel();
				b.setSize(255, 255);
				b.setText(i + " " + j);
				add(b);
			}
		}
		
		/*setLayout(new BorderLayout(boardsize, boardsize));
		
		for(int i = 0; i < boardsize; i++) {
			for(int j = 0; j < boardsize; j++) {
				add(grid.get(i).get(j));
			}
		}*/
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		JOptionPane.showMessageDialog(null, "mouse clicked  :  " + e.getSource().toString());	
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
