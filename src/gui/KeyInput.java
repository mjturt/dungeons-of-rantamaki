package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/* Basic keyinput stuff */

public class KeyInput extends KeyAdapter {

    Handler handler;
    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        /* Loops through Handlers objects list until Player object is found */

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp= handler.objects.get(i);
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    handler.setUp(true);
                }
                if (key == KeyEvent.VK_DOWN) {
                    handler.setDown(true);
                }
                if (key == KeyEvent.VK_LEFT) {
                    handler.setLeft(true);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    handler.setRight(true);
                }
            }
        }
        if (key == KeyEvent.VK_ESCAPE) {
            game.setState(STATE.MENU);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp= handler.objects.get(i);
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    handler.setUp(false);
                }
                if (key == KeyEvent.VK_DOWN) {
                    handler.setDown(false);
                }
                if (key == KeyEvent.VK_LEFT) {
                    handler.setLeft(false);
                }
                if (key == KeyEvent.VK_RIGHT) {
                    handler.setRight(false);
                }
            }
        }
    }
}
