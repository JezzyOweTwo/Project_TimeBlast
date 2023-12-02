package time_blast.graphics;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	JFrame window = new JFrame();
	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	final int[] resolution = {gd.getDisplayMode().getWidth(),gd.getDisplayMode().getHeight()};
	final int TILE_SIZE=16;
	final int[] resolutionScale = {resolution[0]/16,resolution[1]/9};	
	final int[] size = {resolutionScale[0]/TILE_SIZE,resolutionScale[1]/TILE_SIZE};
}
