package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerExample implements KeyListener {
    private Character ch = null;

    public Character getChar(){
        return  ch;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        ch = e.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
