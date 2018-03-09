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

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        /* "New Game" button */

        if (mx >= 70 && mx <= 200 ) {
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

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}
