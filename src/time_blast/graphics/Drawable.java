package time_blast.graphics;
import time_blast.graphics.assets.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Drawable {
    public int getWidth();
    public int getHeight();
    public int getX();
    public int getY();
    public void setCurrentFrame(BufferedImage img);
    public default Dimension getDimension(){
        return new Dimension(this.getWidth(),this.getHeight());
    }
}
