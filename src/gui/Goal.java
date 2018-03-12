package gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 *  Goal class 
 */

public class Goal extends GameObject {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private BufferedImage bus;

    public Goal(int x, int y, ID id, BufferedImage bus) {
        super(x, y, id);
        this.bus = bus;
    }


    @Override
	public void tick() {
    }

    /* Drawing bus-image */

    @Override
	public void render(Graphics2D g) {
        g.drawImage(bus, x, y, null);

    }

    /* This is for collision detection */

    @Override
	public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }
    
    public void setBus(BufferedImage bimg) {
    	this.bus = bimg;
    }

	public void reloadAssets(BufferedImage img) {
		this.bus = img;
	}

	@Override
	public void reloadAssets(SpriteSheet sheet) {
	}
	

}
