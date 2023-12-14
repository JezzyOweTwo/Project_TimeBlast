package time_blast.utilities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Gamepad extends HashMap<Integer,Key> implements KeyListener {
// this hashmap maps key codes to keys.

    public Gamepad(){
        for(DefaultBinds df:DefaultBinds.values())
            this.put(df.KEY_CODE,new Key(df));
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Key key = this.get(keyCode);
        if (key == null) return;
        key.isPressed = true;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        Key key = this.get(keyCode);
        if (key == null) return;
        key.isPressed = false;
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}