package time_blast.graphics;

import time_blast.graphics.assets.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AnimationHandler <T extends Animatable>  {
    T t;
    private int currentFrameIndex;
    private Direction prevDirection;
    private final HashMap<Direction,RollOverArrayList<String>> animationByDirection = new HashMap<>(); // maps all 4 directions to their corresponding animations.

    public AnimationHandler(T t){
        this.t = t;
        prevDirection = t.getDirection();                                // sets prevDirection to the t's direction
        currentFrameIndex = 0;                                           // object is set to first frame in animation cycle
        // implement some sort of default animation directory

        for (Direction dir: Direction.values()){
            //animationByDirection.put(dir,animations from somewhere);
        }
    }

    public void animate(){
        Direction curDirection = t.getDirection();
        boolean isIdle = t.getisIdle();

        // is idle
        if (isIdle) {
            try {
                t.setCurrentFrame(ImageIO.read(new File(animationByDirection.get(curDirection).get(currentFrameIndex%2))));
                return;
            } catch (IOException e){
                e.printStackTrace();
            }
        }

        // is not idle and still facing same direction
        if (curDirection == prevDirection){currentFrameIndex++;}
        // is not idle and facing new direction
        else{currentFrameIndex=0; prevDirection = curDirection;}

        try {
            t.setCurrentFrame(ImageIO.read(new File(animationByDirection.get(curDirection).get(currentFrameIndex))));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
