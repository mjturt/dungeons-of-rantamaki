package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Class for handilng all keyboard inputs
 *
 * @author Maks Turtiainen
 */
public class KeyInput extends KeyAdapter {

    Handler handler;
    Game game;

    /**
     * KeyInput contructor
     *
     * @param handler
     * @param game
     */
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    /**
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        final int key = e.getKeyCode();

        /*
         * Keyboard input for menus
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
            final GameObject temp= handler.objects.get(i);

            /*
             * Player character movement
             * Loops through Handlers objects list until Player object is found 
             */

            if (temp.getId() == ID.Player) {
                if (game.getKeyOption() == OPTION.ARROW) {
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
                } else if (game.getKeyOption() == OPTION.HJKL) {
                    if (key == KeyEvent.VK_K) {
                        this.handler.setUp(true);
                    }
                    if (key == KeyEvent.VK_J) {
                        this.handler.setDown(true);
                    }
                    if (key == KeyEvent.VK_H) {
                        this.handler.setLeft(true);
                    }
                    if (key == KeyEvent.VK_L) {
                        this.handler.setRight(true);
                    }
                } else if (game.getKeyOption() == OPTION.WASD) {
                    if (key == KeyEvent.VK_W) {
                        this.handler.setUp(true);
                    }
                    if (key == KeyEvent.VK_S) {
                        this.handler.setDown(true);
                    }
                    if (key == KeyEvent.VK_A) {
                        this.handler.setLeft(true);
                    }
                    if (key == KeyEvent.VK_D) {
                        this.handler.setRight(true);
                    }
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

    /**
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        final int key = e.getKeyCode();

        for (int i = 0; i < handler.objects.size(); i++) {
            final GameObject temp= handler.objects.get(i);
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

    /**
     * @return handler
     */
    public Handler getHandler() {
        return handler;
    }

    /**
     * @param handler
     */
    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
