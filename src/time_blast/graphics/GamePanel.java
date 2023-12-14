package time_blast.graphics;

import java.awt.*;
import java.io.Serial;

import javax.swing.JPanel;

public abstract class GamePanel extends JPanel implements Runnable{
	public static final Dimension MONITOR_RESOLUTION = new Dimension(getGD().getDisplayMode().getWidth(),getGD().getDisplayMode().getHeight());
	// note that Dimensions hold integer values, so DISPLAY_FACTOR will automatically be truncated.
	public static final Dimension DISPLAY_FACTOR = new Dimension(MONITOR_RESOLUTION.width/16,MONITOR_RESOLUTION.height/9);

	// As such, WINDOW_SIZE may not be exactly the same as MONITOR_RESOLUTION. It will be the next largest 16x9 aspect ratio.
	public static final Dimension WINDOW_SIZE = new Dimension(DISPLAY_FACTOR.width*16,DISPLAY_FACTOR.height*9);
	final private int FPS = 60;
	int i =0;
	public Thread gameThread;
	@Serial
	private static final long serialVersionUID = 1L;

	public GamePanel(){
		this.setPreferredSize(WINDOW_SIZE);
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
	}
	private static GraphicsDevice getGD() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
	public abstract void update();
	public abstract GameState getGameState();
	@Override
	public void run() {
		gameThread = new Thread(this);
		final double drawInterval  = (double) 10000000 /FPS;
		double deltaTime = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		gameThread.start();

		while(gameThread.isAlive()){
			currentTime = System.nanoTime();
			deltaTime+=(currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (deltaTime>=1){
				update();
				repaint();
				deltaTime--;
			}
		}
	}

}
