package sprint1_0.PoC_GRAPHICS_TEST;

import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TEST_WINDOW_LISTENER extends JFrame implements WindowStateListener {
	
	
	public void windowStateChanged(WindowEvent e)
    {
        JOptionPane.showMessageDialog(this, "window state changed");
    }
}
