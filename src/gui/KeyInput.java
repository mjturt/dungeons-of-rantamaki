package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Basic keyinput stuff
 */

public class KeyInput extends KeyAdapter {

    Handler handler;
    Game game;

    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        /*
         * Player character movement
         * Loops through Handlers objects list until Player object is found 
         */

        if (key == KeyEvent.VK_ESCAPE) {
        	if (game.getState() == STATE.GAME) {
        		game.setState(STATE.PAUSE);
        	} else if (game.getState() == STATE.PAUSE) {
        		game.setState(STATE.GAME);
        	} else if (game.getState() == STATE.ABOUT) {
        		game.setState(STATE.MENU);
        	}
        }
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject temp= handler.objects.get(i);
            /*
             * Keyboard input for menus
             */
            if (temp.getId() == ID.Player) {
                if (key == KeyEvent.VK_UP) {
                    this.handler.setUp(true);
                }
                if (key == KeyEvent.VK_DOWN) {
                	this.handler.setDown(true);
                }
                if (key == KeyEvent.VK_LEFT) {
                	this.handler.setLeft(true);
                }
                if (key == KeyEvent.VK_RIGHT) {
                	this.handler.setRight(true);
                }
            }
     
        }

        if (key == KeyEvent.VK_ENTER) {
            if (game.getState() == STATE.START) {
                game.setState(STATE.GAME);
            }
        }
    }

    /*
     * Key release events, only for character movement
     */

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

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}
}
