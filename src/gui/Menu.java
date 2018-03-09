package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/* 
 * Main menu class
 */

public class Menu {


    public void render(Graphics g) {

        FontLoader fl = new FontLoader(); 
        ImageLoader il = new ImageLoader();

        Font font1 = fl.loadFont("/fonts/viking.ttf", 50); 
        g.setFont(font1);
        g.setColor(Color.red);
        g.drawString("Dungeons of Räntämäki", 50, 60);

        Font font2 = fl.loadFont("/fonts/morris.ttf", 30);
        g.setFont(font2);
        g.drawString("New Game", 50, 150);
        g.drawString("Load", 250, 150);
        g.drawString("Save", 450, 150);
        g.drawString("Exit", 250, 250);
        
    }
}
