package gui;

import java.awt.Color;
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

    public Block(int x, int y, ID id) {
        super(x, y, id);
		ImageLoader loader = new ImageLoader();
		bushimg = loader.loadImage("/bushes.png");
		bushtreeimg = loader.loadImage("/bushesAndTree.png");
		houseimg = loader.loadImage("/house.png");
		house2img = loader.loadImage("/house2.png");
		parklotimg = loader.loadImage("/parklot.png");
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
