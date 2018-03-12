package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for main menu
 *
 * @author Maks Turtiainen
 */
public class Menu {
    FontLoader fl;
    ImageLoader il;
    BufferedImage menubg;
    Font font1;
    Font font2;
    Font font3;

    /**
     * Menu constructor
     */
    public Menu() {
        this.fl = new FontLoader();
        this.il = new ImageLoader();
        this.font1 = fl.loadFont("/fonts/viking.ttf", 90); 
        this.font2 = fl.loadFont("/fonts/eclipse.ttf", 45);
        this.font3 = fl.loadFont("/fonts/morris.ttf", 30);
        this.menubg = il.loadImage("/images/menubg.png");
    }


    /**
     * Renders menu to the screen
     *
     * @param g
     */
    public void render(Graphics2D g) {
        
        g.drawImage(this.menubg, 0, 0, null);

        /* Title */

        g.setFont(this.font1);
        g.setColor(Color.red);
        g.drawString("Dungeons of", 90, 70);
        g.setFont(this.font2);
        g.drawString("Räntämäki", 150, 140);

        /* Buttons */

        g.setFont(this.font3);
        g.setColor(Color.yellow);

        g.drawString("New Game", 70, 250);
        g.drawString("Load", 290, 250);
        g.drawString("Save", 470, 250);

        g.drawString("Exit", 290, 350);

        g.drawString("About", 530, 450);
        
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
