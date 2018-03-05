package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/* Monster example class */

public class Monster extends GameObject {

	private BufferedImage monster1 = null;

    public Monster(int x, int y, ID id) {
        super(x, y, id);
		ImageLoader loader = new ImageLoader();
		monster1 = loader.loadImage("/monster1.png");
    }


    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
       //g.setColor(Color.black);
       //g.fillRect(x, y, 64, 64);
		g.drawImage(monster1, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

}
