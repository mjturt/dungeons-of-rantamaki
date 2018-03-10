package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/* 
 * Main menu class
 */

public class Menu {
	FontLoader fl;
	ImageLoader il;
	BufferedImage menubg;
	
	public Menu() {
		this.fl = new FontLoader();
		this.il = new ImageLoader();
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

        /* Buttons */

        g.setFont(font3);
        g.setColor(Color.yellow);
        g.drawString("New Game", 70, 250);
        g.drawString("Load", 290, 250);
        g.drawString("Save", 470, 250);

        g.drawString("Exit", 290, 350);
        
    }


	public FontLoader getFl() {
		return fl;
	}


	public void setFl(FontLoader fl) {
		this.fl = fl;
	}


	public ImageLoader getIl() {
		return il;
	}


	public void setIl(ImageLoader il) {
		this.il = il;
	}


	public BufferedImage getMenubg() {
		return menubg;
	}


	public void setMenubg(BufferedImage menubg) {
		this.menubg = menubg;
	}
}
