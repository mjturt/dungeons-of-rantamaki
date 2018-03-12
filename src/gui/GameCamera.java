package gui;

/**
 * Game camera class that follows player
 *
 * @author Maks Turtiainen
 */
public class GameCamera {
    private double x, y;
    private final double cx;
    private final double cy;
    
    /**
     * GameCamera constructor
     *
     * @param x
     * @param y
     * @param cx
     * @param cy
     */
    public GameCamera (double x, double y, double cx, double cy) {
        this.x = x;
        this.y = y;
        this.cx = cx;
        this.cy = cy;
    }
    
    /**
     * @param x
     */
    public void setX (double x) {
        this.x = x;
    }
    
    /**
     * @param y
     */
    public void setY (double y) {
        this.y = y;
    }
    
    /**
     * @return x
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Updates viewport position based on player position and map borders
     *
     * @param player
     */
    public void tick(GameObject player) {
        x = player.getX() - cx/2;
        y = player.getY() - cy/2;

        if (x <= 0) {
            x = 0;
        }
        if (x >= 2560 - 570) {
            x = 2560 - 570;
        }
        if (y <= 0) {
            y = 0;
        }
        if (y >= 2560 - 400) {
            y = 2560 - 400;
        }
        
    }
}
