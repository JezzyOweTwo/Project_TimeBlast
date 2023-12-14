package time_blast.utilities;

import java.awt.event.KeyEvent;

public enum DefaultBinds {
    up(KeyEvent.VK_W),
    down(KeyEvent.VK_S),
    left(KeyEvent.VK_A),
    right(KeyEvent.VK_D),
    accept(KeyEvent.VK_R),
    cancel(KeyEvent.VK_F);
    final public int KEY_CODE;
    DefaultBinds(int keyCode){this.KEY_CODE = keyCode;}
}
