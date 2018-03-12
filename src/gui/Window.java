package gui;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Class that creates window with swing and where we render everything else with AWT
 *
 * @author Maks Turtiainen
 */
public class Window {
    /**
     *
     */
    private final JFrame frame;

    /**
     * Window Constructor
     *
     * @param width
     * @param height
     * @param title
     * @param game
     */
    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * @return int frame.getWidth()
     */
    public int getWidth() {
        return frame.getWidth();
    }
    
    /**
     * @return int frame.getHeight()
     */
    public int getHeigth() {
        return frame.getHeight();
    }
    
    /**
     * @return frame
     */
    public JFrame getFrame() {
        return this.frame;
    }
}
