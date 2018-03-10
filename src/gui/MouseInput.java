package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * Mouse input, used only in menus
 */

public class MouseInput implements MouseListener {
    private Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    public void mouseClicked(MouseEvent arg0) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        /* Taking mouse input only in menus */

        if (game.getState() == STATE.MENU || game.getState() == STATE.PAUSE) {


            if (game.getState() == STATE.MENU) {

                /* "New Game" button, only in main menu */

                if (mx >= 70 && mx <= 200 ) {
                    if (my >= 220 && my <= 250) {
                        game.setState(STATE.GAME);
                    }
                }
            } else if (game.getState() == STATE.PAUSE) {

                /* "Continue" button, only in pause menu */

                if (mx >= 70 && mx <= 170 ) {
                    if (my >= 220 && my <= 250) {
                        game.setState(STATE.GAME);
                    }
                }
            }

            /* "Load" button */

            if (mx >= 290 && mx <= 350 ) {
                if (my >= 220 && my <= 250) {
                    game.setState(STATE.GAME);
                }
            }        

            /* "Save" button */

            if (mx >= 470 && mx <= 530 ) {
                if (my >= 220 && my <= 250) {
                    game.setState(STATE.GAME);
                }
            }


            /* "Exit" button */

            if (mx >= 290 && mx <= 340 ) {
                if (my >= 320 && my <= 350) {
                    System.exit(1);
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

}
