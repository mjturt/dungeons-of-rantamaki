package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for handling all mouse inputs, used only in menus
 *
 * @author Maks Turtiainen
 */
public class MouseInput implements MouseListener {
    private final Game game;

    /**
     * MouseInput constructor
     *
     * @param game
     */
    public MouseInput(Game game) {
        this.game = game;
    }

    /**
     * @param arg0
     */
    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    /**
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        final int mx = e.getX();
        final int my = e.getY();

        /* Taking mouse input only in menus */

        if (game.getState() == STATE.MENU || game.getState() == STATE.PAUSE) {

            if (game.getState() == STATE.MENU) {

                /*
                 * Main menu
                 */

                /* "New Game" button */

                if (mx >= 70 && mx <= 200 ) {
                    if (my >= 220 && my <= 250) {
                        game.setLastState(game.getLastState());
                        game.setState(STATE.START);
                    }
                }

            } else if (game.getState() == STATE.PAUSE) {

                /* 
                 * Pause menu
                 */

                /* "Continue" button */

                if (mx >= 70 && mx <= 170 ) {
                    if (my >= 220 && my <= 250) {
                        game.setState(STATE.GAME);
                    }
                }

                /* "Options" button */

                if (mx >= 70 && mx <= 200 ) {
                    if (my >= 320 && my <= 350) {
                        game.setState(STATE.OPTIONS);
                    }
                }

                /* "About" button */

                if (mx >= 290 && mx <= 350 ) {
                    if (my >= 320 && my <= 350) {
                        game.setState(STATE.ABOUT);
                    }
                }
            }

            /* 
             * Main and Pause menu
             */

            /* "Load" button */

            if (mx >= 290 && mx <= 330 ) {
                if (my >= 220 && my <= 250) {
                    try {
                        final ArrayList<GameObject> objects = HandlerIO.readHandler();
                        System.out.println("Read the file (or atleast tried)");
                        this.game.getHandler().setObjects(objects);
                        this.game.setState(STATE.START);
                        System.out.println("Game STATE: " + this.game.getState());
                        System.out.println("Game STATE: " + this.game.getState());
                        System.out.println("Handler: " + this.game.getHandler());
                        this.game.reloadAssets();
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
                    } catch (final IOException e1) {
                        e1.printStackTrace();
                    }
                    game.setState(STATE.GAME);
                }
            }

            /* "Options" button */

            if (mx >= 70 && mx <= 200 ) {
                if (my >= 320 && my <= 350) {
                    game.setState(STATE.OPTIONS);
                }
            }

            /* "About" button */

            if (mx >= 290 && mx <= 350 ) {
                if (my >= 320 && my <= 350) {
                    game.setState(STATE.ABOUT);
                }
            }

            /* "Exit" button */

            if (mx >= 470 && mx <= 530 ) {
                if (my >= 320 && my <= 350) {
                    System.exit(1);
                }
            }

        } else if (game.getState() == STATE.ABOUT || game.getState() == STATE.OPTIONS) {

            /*
             * About and Options menu
             * "Back" button 
             **/

            if (mx >= 290 && mx <= 340 ) {
                if (my >= 400 && my <= 430) {
                    System.out.println(game.getLastState() );
                    if (game.getLastState() == STATE.PAUSE) {
                        game.setState(STATE.PAUSE);
                    } else if (game.getLastState() == STATE.MENU) {
                        game.setState(STATE.MENU);
                    }
                }
            } else if (game.getState() == STATE.GOAL) {

                /*
                 * Goal screen
                 * "Exit" button 
                 **/

                if (mx >= 385 && mx <= 505 ) {
                    if (my >= 300 && my <= 360) {
                        System.exit(1);
                    }
                }
            }
        }
    }

    /**
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
