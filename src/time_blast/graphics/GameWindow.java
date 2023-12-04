package time_blast.graphics;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class GameWindow {
	private JFrame window = new JFrame();
	public static final Dimension MONITOR_RESOLUTION = new Dimension(getGD().getDisplayMode().getWidth(),getGD().getDisplayMode().getHeight());
	public static final Dimension DISPLAY_FACTOR = new Dimension(MONITOR_RESOLUTION.width/16,MONITOR_RESOLUTION.height/9);
	public static final Dimension WINDOW_SIZE = new Dimension(DISPLAY_FACTOR.width*16,DISPLAY_FACTOR.height*9);
	
	private static GraphicsDevice getGD() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	}
	
	public GameWindow(GamePanel gamepanel) {
		window.setSize(WINDOW_SIZE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(gamepanel);
		window.setVisible(true);
	}

}
