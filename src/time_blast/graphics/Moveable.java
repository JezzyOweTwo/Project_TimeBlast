package time_blast.graphics;
import java.awt.*;

public interface Moveable extends Drawable {
    public Dimension getAcceleration();
    public Dimension getSpeed();
    public void move(Dimension speed);
}
