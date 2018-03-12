package gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * GameObject interface
 *
 * @author Maks Turtiainen
 */
public abstract class GameObject implements java.io.Serializable{
    
    private static final long serialVersionUID = 1L;
    protected int x;
    protected int y;
    protected float velX = 0;
    protected float velY = 0;
    protected ID id;

    /**
     * GameObject constructor
     *
     * @param x
     * @param y
     * @param id
     */
    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    /**
     * Updates GameObjects. Including but not limited to: position, velocity, rendering, Enum.id ...
     */
    public abstract void tick();

    /**
     * Calls the GameObject to repaint itself. 
     * @param g Graphics (usually BufferedImage) that contains the graphics used to render the object.
     */
    public abstract void render(Graphics2D g);

    /**
     * Used for collision detection
     * 
     * @return new Rectangle() that corresponds to the object dimensions
     */
    public abstract Rectangle getBounds();

    /**
     * @param sheet
     */
    public abstract void reloadAssets(SpriteSheet sheet);
    
    /**
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return velX
     */
    public float getVelX() {
        return velX;
    }

    /**
     * @param velX
     */
    public void setVelX(float velX) {
        this.velX = velX;
    }
    
    /**
     * @return velY
     */
    public float getVelY() {
        return velY;
    }

    /**
     * @param velY
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }

    /**
     * @return id
     */
    public ID getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(ID id) {
        this.id = id;
    }

}
