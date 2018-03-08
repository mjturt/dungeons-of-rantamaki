package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/* This is example class for all GameObjects */

public class Block extends GameObject {

	private BufferedImage bushimg = null;
	private BufferedImage bushtreeimg = null;
	private BufferedImage houseimg = null;
	private BufferedImage house2img = null;
	private BufferedImage parklotimg = null;
    private Random rand = new Random(); 
    private int r;
    private SpriteSheet ss;

    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id);
        this.ss = ss;
		bushimg = ss.grabImage(1, 1, 64, 64);
		bushtreeimg = ss.grabImage(2, 1, 64, 64);
		houseimg = ss.grabImage(3, 1, 64, 64);
		house2img = ss.grabImage(4, 1, 64, 64);
		parklotimg = ss.grabImage(5, 1, 64, 64);
        r = rand.nextInt(20); 
    }

    /* Tick method, all GameObjects must have their own tick method */

    public void tick() {
        x += velX;
        y += velY;
    }

    /* Render method, all GameObjects must have their own render method */

    public void render(Graphics g) {
        if (r <= 5) {
            g.drawImage(houseimg, x, y, null);
        } else if (r > 5 && r < 10) {
            g.drawImage(house2img, x, y, null);
        } else if (r >= 10 && r < 14) {
            g.drawImage(bushimg, x, y, null);
        } else if (r >= 14 && r < 17) {
            g.drawImage(bushtreeimg, x, y, null);
        } else {
            g.drawImage(parklotimg, x, y, null);
        }

    }

    /* This is for collision detection */

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

}
