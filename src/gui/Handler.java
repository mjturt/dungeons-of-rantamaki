package gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

/* Handler for all GameObjects so that Game class doesn't have to tick and render them separetly
 * All objects must be added through this class, so that it can loop through list of all objects
 * */


/*
 * Due to handler class being called from multiple other classes,
 *  and all game objects being stored in objects ArrayList<GameObject> is the use of enhanced for loop rendered
 *  impossible due to conflict in modification from multiple threads.
 */

/**
 * Handler is a class used for updating and storing game related events. Like it's name states,
 * it handles everything.
 */
public class Handler implements java.io.Serializable  {

    ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private JFrame frame;
    
    public Handler(JFrame frame) {
    	this.frame = frame;
    }
    
    public JFrame getFrame () {
    	return this.frame;
    }
    /**
     * Updates game events by calling GameObjects to tick()
     */
    public void tick() {
        for (int i=0;i<objects.size();i++) {
            objects.get(i).tick();
        }
    }
    /**
     * Calls all GameObjects to render(using the GameObjects own render method)
     * @param g Graphics object.
     */
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            temp.render(g);
        }
    }
    /**
     * Add a new GameObject to this Handler
     * @param temp GameObject to add.
     */
    public void addObject(GameObject temp) {
        objects.add(temp);
    }
    /**
     * Remove GameObject from this Handler
     * @param temp GameObject to remove.
     */
    public void removeObject(GameObject temp) {
        objects.remove(temp);
    }
    /*
     * The following are related to controlling the player position in relation to other objects.
     * STARTS HERE
     */
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
    
    /*
     * ENDS HERE
     */
    
    public void releaseKeys() {
    	setLeft(false);
    	setRight(false);
    	setUp(false);
    	setDown(false);
    }
}
