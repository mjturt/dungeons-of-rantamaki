package gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/* This is example class for all GameObjects */

public class Block extends GameObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private BufferedImage bushimg;
	transient private BufferedImage bushtreeimg;
	transient private BufferedImage houseimg;
	transient private BufferedImage house2img;
	transient private BufferedImage parklotimg;
	transient private BufferedImage studentsimg;
	transient private Random rand = new Random(); 
    private final int r;
    transient private SpriteSheet ss;
    private final Rectangle bounds;

    public Block(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id);
        this.ss = ss;
        this.bounds = new Rectangle();
        this.bounds.setBounds(x, y, 64, 64);
		this.bushimg = this.ss.grabImage(1, 1, 64, 64);
		this.bushtreeimg = this.ss.grabImage(2, 1, 64, 64);
		this.houseimg = this.ss.grabImage(3, 1, 64, 64);
		this.house2img = this.ss.grabImage(4, 1, 64, 64);
		this.parklotimg = this.ss.grabImage(5, 1, 64, 64);
		this.studentsimg = this.ss.grabImage(4, 2, 64, 64);
        this.r = rand.nextInt(20); 
    }

    /* Tick method, all GameObjects must have their own tick method */

    @Override
	public void tick() {
        x += velX;
        y += velY;
    }

    /* Render method, all GameObjects must have their own render method */

    @Override
	public void render(Graphics2D g) {
        if (r <= 4) {
            g.drawImage(this.houseimg, x, y, null);
        } else if (this.r > 4 && r < 10) {
            g.drawImage(this.house2img, x, y, null);
        } else if (this.r >= 10 && r < 11) {
            g.drawImage(this.studentsimg, x, y, null);
        } else if (this.r >= 11 && r < 14) {
            g.drawImage(this.bushimg, x, y, null);
        } else if (this.r >= 14 && r < 18) {
            g.drawImage(this.bushtreeimg, x, y, null);
        } else {
            g.drawImage(this.parklotimg, x, y, null);
        }

    }

    /* This is for collision detection */

    @Override
	public Rectangle getBounds() {
    	this.bounds.setBounds(x, y, 64, 64);
        return this.bounds;
    }

	@Override
	public void reloadAssets(SpriteSheet sheet) {
		this.ss = sheet;
		this.bushimg = this.ss.grabImage(1, 1, 64, 64);
		this.bushtreeimg = this.ss.grabImage(2, 1, 64, 64);
		this.houseimg = this.ss.grabImage(3, 1, 64, 64);
		this.house2img = this.ss.grabImage(4, 1, 64, 64);
		this.parklotimg = this.ss.grabImage(5, 1, 64, 64);
		this.studentsimg = this.ss.grabImage(4, 2, 64, 64);
	}

}
