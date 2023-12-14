package time_blast.graphics;

import time_blast.game_logic.entities.Player;
import time_blast.utilities.DefaultBinds;
import time_blast.utilities.Gamepad;

import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.security.Key;

public class OverworldPanel  extends GamePanel {
    Gamepad gamepad = new Gamepad();
    private Player player;
    public OverworldPanel(Player player){
        super();
        this.setBackground(Color.cyan);
        this.addKeyListener(gamepad);
        this.setFocusable(true);
        this.player = player;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(50,50,100,100);
        BufferedImage img =  player.getImage();
        g2.drawImage(player.getImage(),player.getX() ,player.getY(),null);
        g2.dispose();
    }

    @Override
    public void update() {
        if (gamepad.get(KeyEvent.VK_W).isPressed){
            player.move(new Dimension(0,-1));   // note that y values are inverted in Java
            System.out.println("Y: "+player.getY());
            player.setisIdle(false);
        }
        if (gamepad.get(KeyEvent.VK_A).isPressed){
            player.move(new Dimension(-1,0));   // note that y values are inverted in Java
            System.out.println("X: "+player.getX());
            player.setisIdle(false);
        }
        if (gamepad.get(KeyEvent.VK_S).isPressed){
            player.move(new Dimension(0,1));   // note that y values are inverted in Java
            System.out.println("Y: "+player.getY());
            player.setisIdle(false);
        }
        if (gamepad.get(KeyEvent.VK_D).isPressed){
            player.move(new Dimension(1,0));   // note that y values are inverted in Java
            System.out.println("X: "+player.getX());
            player.setisIdle(false);
        }
    }
    public GameState getGameState() {return GameState.overworld;}

}
