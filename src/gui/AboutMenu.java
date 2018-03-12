package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * @author Maks Turtiainen
 */
public class AboutMenu {
    FontLoader fl;
    ImageLoader il;
    BufferedImage menubg;
    Font font1;
    Font font2;
    Font font3;
    Font font4;

    public AboutMenu() {
        this.fl = new FontLoader();
        this.il = new ImageLoader();
        this.font1 = fl.loadFont("/fonts/viking.ttf", 90); 
        this.font2 = fl.loadFont("/fonts/eclipse.ttf", 95);
        this.font3 = fl.loadFont("/fonts/morris.ttf", 30);
        this.font4 = fl.loadFont("/fonts/droidsansmono.ttf", 15);
        this.menubg = il.loadImage("/images/menubg.png");
    }


    /**
     * Draws about menu to the screen
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
        g.drawString("Räntämäki", 150, 160);
        g.setFont(font3);
        g.setColor(Color.blue);
        g.drawString("About", 270, 200);

        /* Authors */

        g.setColor(Color.white);
        g.drawString("Authors", 70, 250);
        g.setFont(this.font4);
        g.drawString("Maks Turtiainen", 70, 300);
        g.drawString("Vili Ahava", 270, 300);
        g.drawString("Mikko Malkavaara", 450, 300);

        /* Back button */

        g.setColor(Color.yellow);
        g.setFont(this.font3);
        g.drawString("Back", 290, 430);
        
    }

    /**
     * @return the fl
     */
    public FontLoader getFl() {
        return fl;
    }

    /**
     * @param fl
     */
    public void setFl(FontLoader fl) {
        this.fl = fl;
    }


    /**
     * @return ImageLoader
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
     * @return the menubg
     */
    public BufferedImage getAboutMenubg() {
        return this.menubg;
    }


    /**
     * @param menubg
     */
    public void setAboutMenubg(BufferedImage menubg) {
        this.menubg = menubg;
    }
}
