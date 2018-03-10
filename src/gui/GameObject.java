package gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/* GameObject interface, all objects extends it
 * Look for Block.java for example
 * */

public abstract class GameObject {
    protected int x;
    protected int y;
    protected float velX = 0;
    protected float velY = 0;
    protected ID id;

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
    public abstract Rectangle getBounds(); // for collision detection

    /* Just getters and setters */

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }
    
    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

}
