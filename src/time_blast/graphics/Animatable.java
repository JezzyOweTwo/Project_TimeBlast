package time_blast.graphics;

import time_blast.graphics.assets.Direction;

import java.awt.*;

public interface Animatable extends Drawable{
    public boolean getisIdle();
    public void setisIdle(boolean isIdle);
    public Direction getDirection();

}
