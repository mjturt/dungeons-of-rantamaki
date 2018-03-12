package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Monster class
 *
 * @author Maks Turtiainen, Vili Ahava
 */
public class GuiMonster extends GameObject {
    
    private static final long serialVersionUID = 1L;
    private final Rectangle bounds = new Rectangle();
    
    /**
     * GuiMonster constructor
     * @param x
     * @param y
     * @param id
     */
    public GuiMonster(int x, int y, ID id) {
        super(x, y, id);
        this.bounds.setBounds(this.x, this.y, 64, 64);
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    /**
     * @param g
     */
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.cyan);
        //g.fillRect(this.x, this.y, 64, 64);
    }

    /**
     * @return bounds
     */
    @Override
    public Rectangle getBounds() {
        this.bounds.setBounds(this.x, this.y, 64, 64);
        return this.bounds;
    }

    /**
     * @param sheet
     */
    @Override
    public void reloadAssets(SpriteSheet sheet) {
    }

}
