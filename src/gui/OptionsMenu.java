package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for options menu
 *
 * @author Maks Turtiainen
 */
public class OptionsMenu {
    FontLoader fl;
    ImageLoader il;
    BufferedImage menubg;
    Font font1;
    Font font2;
    Font font3;
    Font font4;
    
    /**
     * OptionsMenu constructor
     */
    public OptionsMenu() {
        this.il = new ImageLoader();
        this.fl = new FontLoader();
        this.font1 = fl.loadFont("/fonts/viking.ttf", 90); 
        this.font2 = fl.loadFont("/fonts/eclipse.ttf", 95);
        this.font3 = fl.loadFont("/fonts/morris.ttf", 30);
        this.font4 = fl.loadFont("/fonts/droidsansmono.ttf", 15);
        this.menubg = il.loadImage("/images/menubg.png");
    }


    /**
     * Renders out the options menu
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
        g.drawString("Räntämäki", 150, 160);
        g.setFont(font3);
        g.setColor(Color.blue);
        g.drawString("Options", 270, 200);
        g.drawString("Movement", 70, 240);

        /* Buttons */

        g.setFont(this.font4);
        g.setColor(Color.yellow);
        g.drawString("Arrow keys", 70, 270);
        g.drawString("HJKL keys", 270, 270);
        g.drawString("WASD keys", 450, 270);
        g.setColor(Color.blue);
        g.setFont(font3);
        g.drawString("Audio", 70, 320);
        g.setFont(this.font4);
        g.setColor(Color.yellow);
        g.drawString("Enable/Disable", 70, 350);

        g.setFont(font3);
        g.drawString("Back", 290, 430);
        
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
