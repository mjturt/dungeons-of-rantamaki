package gui;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import world.World;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;


/* Handler for all GameObjects so that Game class doesn't have to tick and render them separetly
 * All objects must be added through this class, so that it can loop through list of all objects
 * */


/*
 * Due to handler class being called from multiple other classes,
 *  and all game objects being stored in objects ArrayList<GameObject> is the use of enhanced for loop rendered
 *  impossible due to conflict in modification from multiple threads.
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

    public void tick() {
        for (int i=0;i<objects.size();i++) {
            objects.get(i).tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i);
            temp.render(g);
        }
    }

    public void addObject(GameObject temp) {
        objects.add(temp);
    }

    public void removeObject(GameObject temp) {
        objects.remove(temp);
    }

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
    
    public void releaseKeys() {
    	setLeft(false);
    	setRight(false);
    	setUp(false);
    	setDown(false);
    }
    
    public void loadLevel() {

        World world = new World(39, 39);
        int w = world.getHeight();
        int h = world.getWidth();
        int[] start = world.getStart();
        int[] goal = world.getGoal();

        /* First background texture */
        
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (world.getTile(y, x).getPassable() && !(goal[0] == y && goal[1] == x) ) {
                    addObject(new Road(x*64, y*64, ID.Road));
                }
            }
        }

        /* Then all other objects */

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                if (!world.getTile(y, x).getPassable()) {
                    addObject(new Block(x*64, y*64, ID.Block));
                }
                if (world.getTile(y, x).getPassable() && !(goal[0] == y && goal[1] == x) ) {
                    addObject(new Road(x*64, y*64, ID.Road));
                }
                if(start[0] == y && start[1] == x) {
                    addObject(new GuiPlayer(x*64, y*64, ID.Player, this));
                }
                if(goal[0] == y && goal[1] == x) {
                    addObject(new Goal(x*64, y*64, ID.Goal));
                }
                if (world.getTile(y, x).hasMonster()) {
                	addObject(new GuiMonster(x*64, y*64, ID.Enemy));
                }
               
            }
        }
    }
}
