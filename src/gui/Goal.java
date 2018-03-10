package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/* This is example class for all GameObjects */

public class Goal extends GameObject {

    public Goal(int x, int y, ID id) {
        super(x, y, id);
    }

    /* Tick method, all GameObjects must have their own tick method */

    public void tick() {
    }

    /* Render method, all GameObjects must have their own render method */

    public void render(Graphics2D g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 64, 64);

    }

    /* This is for collision detection */

    public Rectangle getBounds() {
        return new Rectangle(x, y, 64, 64);
    }

}
