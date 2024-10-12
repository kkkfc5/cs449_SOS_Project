package sprint1_0.PoC_GRAPHICS_TEST;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class TEST_GAMEBOARD extends JPanel{
	
	public int WIDTH, HEIGHT;
	
	public TEST_GAMEBOARD(int width, int height, int boardsize) {
		
		WIDTH = width;
		HEIGHT = height;
		
		setLayout(new BorderLayout(boardsize,boardsize));
        add(new JLabel("Look ma, I'm a square", JLabel.CENTER));
        setBorder(new LineBorder(Color.GRAY));
	}

    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, HEIGHT);
    }
    

}
