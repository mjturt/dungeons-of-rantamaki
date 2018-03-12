package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for start screen
 *
 * @author Maks Turtiainen
 */
public class StartScreen {
    ImageLoader il;
    BufferedImage start;

    /**
     * StartScreen constructor
     */
    public StartScreen() {
        this.il = new ImageLoader();
        this.start = il.loadImage("/images/start.png");
    }

    /**
     * @param g
     */
    public void render(Graphics2D g) {
        g.drawImage(this.start, 0, 0, null);
    }

    /**
     * @return il
     */
    public ImageLoader getIl() {
        return this.il;
    }


    /**
     * @param il
     */
    public void setIl(ImageLoader il) {
        this.il = il;
    }


    /**
     * @return start
     */
    public BufferedImage getStartScreenbg() {
        return this.start;
    }


    /**
     * @param start
     */
    public void setStartScreenbg(BufferedImage start) {
        this.start = start;
    }
}
