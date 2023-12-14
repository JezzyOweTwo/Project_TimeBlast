package time_blast.graphics;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GameWindow extends JFrame{

	public GameWindow(GamePanel gamepanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		this.add(gamepanel);
		this.setVisible(true);
	}

}
