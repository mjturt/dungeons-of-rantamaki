package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for pause menu
 *
 * @author Maks Turtiainen
 */
public class PauseMenu {
    FontLoader fl;
    ImageLoader il;
    BufferedImage menubg;
    Font font1;
    Font font2;
    Font font3;
    
    /**
     * PauseMenu constructor
     */
    public PauseMenu() {
        this.il = new ImageLoader();
        this.fl = new FontLoader();
        this.font1 = fl.loadFont("/fonts/viking.ttf", 90); 
        this.font2 = fl.loadFont("/fonts/eclipse.ttf", 45);
        this.font3 = fl.loadFont("/fonts/morris.ttf", 30);
        this.menubg = il.loadImage("/images/menubg.png");
    }


    /**
     * Renders out the pause menu
     *
     * @param g
     */
    public void render(Graphics2D g) {

        g.drawImage(this.menubg, 0, 0, null);

        /* Title */

        g.setFont(font1);
        g.setColor(Color.red);
        g.drawString("Dungeons of", 90, 70);
        g.setFont(font2);
        g.drawString("Räntämäki", 150, 140);
        g.setFont(font3);
        g.setColor(Color.blue);
        g.drawString("Paused", 270, 200);

        /* Buttons */

        g.setColor(Color.yellow);
        g.drawString("Continue", 70, 250);
        g.drawString("Load", 290, 250);
        g.drawString("Save", 470, 250);

        g.drawString("Exit", 290, 350);
        
    }

    /**
     * @return fl
     */
    public FontLoader getFl() {
        return this.fl;
    }


    /**
     * @param fl
     */
    public void setFl(FontLoader fl) {
        this.fl = fl;
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
     * @return menubg
     */
    public BufferedImage getMenubg() {
        return this.menubg;
    }


    /**
     * @param menubg
     */
    public void setMenubg(BufferedImage menubg) {
        this.menubg = menubg;
    }
}
