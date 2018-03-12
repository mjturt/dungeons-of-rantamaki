package gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * GameObject goal, when player collides with it game ends
 *
 * @author Maks Turtiainen
 */
public class Goal extends GameObject {
    
    private static final long serialVersionUID = 1L;
    transient private BufferedImage bus;

    /**
     * Goal constructor
     *
     * @param x
     * @param y
     * @param id
     * @param bus
     */
    public Goal(int x, int y, ID id, BufferedImage bus) {
        super(x, y, id);
        this.bus = bus;
    }


    @Override
    public void tick() {
    }


    /**
     * @param g
     */
    @Override
    public void render(Graphics2D g) {
        g.drawImage(bus, x, y, null);
    }

    /**
     * @return new Rectangle
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }
    
    /**
     * @param bimg
     */
    public void setBus(BufferedImage bimg) {
        this.bus = bimg;
    }

    /**
     * @param img
     */
    public void reloadAssets(BufferedImage img) {
        this.bus = img;
    }

    /**
     * @param sheet
     */
    @Override
    public void reloadAssets(SpriteSheet sheet) {
    }
    

}
