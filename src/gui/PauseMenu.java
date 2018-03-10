package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/* 
 * Pause menu class
 */


public class PauseMenu {
	
	private ImageLoader il;	
	private FontLoader fl;
	private BufferedImage menubg = null;
	
	public PauseMenu() {
		this.il = new ImageLoader();
		this.fl = new FontLoader();
	}


    public void render(Graphics2D g) {

        

        menubg = il.loadImage("/menubg.png");
        g.drawImage(menubg, 0, 0, null);

        Font font1 = fl.loadFont("/fonts/viking.ttf", 90); 
        Font font2 = fl.loadFont("/fonts/eclipse.ttf", 45);
        Font font3 = fl.loadFont("/fonts/morris.ttf", 30);

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
}
