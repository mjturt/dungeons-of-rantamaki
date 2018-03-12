package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

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
                        game.setState(STATE.START);
                    }
                }

                /* "About" button, only in main menu */

                if (mx >= 530 && mx <= 600 ) {
                    if (my >= 420 && my <= 450) {
                        game.setState(STATE.ABOUT);
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
                    try {
                    	ArrayList<GameObject> objects = HandlerIO.readHandler();
                    	System.out.println("Read the file (or atleast tried)");
						Game.loadedGame(objects);
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
                    
                }
            }        

            /* "Save" button */

            if (mx >= 470 && mx <= 530 ) {
                if (my >= 220 && my <= 250) {
                	try {
						HandlerIO.writeHandler(game.getHandler().getObjects());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                	game.setState(STATE.GAME);
                }
            }

            /* "Exit" button */

            if (mx >= 290 && mx <= 340 ) {
                if (my >= 320 && my <= 350) {
                    System.exit(1);
                }
            }

        } else if (game.getState() == STATE.ABOUT) {

            /* "Back" button in about screen */

            if (mx >= 290 && mx <= 340 ) {
                if (my >= 400 && my <= 430) {
                    game.setState(STATE.MENU);
                }
            }
        } else if (game.getState() == STATE.GOAL) {

            /* "Exit" button in goal screen */

            if (mx >= 385 && mx <= 505 ) {
                if (my >= 300 && my <= 360) {
                    System.exit(1);
                }
            }
        
        }
        
    }

    public void mouseReleased(MouseEvent e) {

    }

}
