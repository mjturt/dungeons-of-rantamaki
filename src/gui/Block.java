package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/* This is example class for all GameObjects */

public class Block extends GameObject {

    public Block(int x, int y, ID id) {
        super(x, y, id);
    }

    /* Tick method, all GameObjects must have their own tick method */

    public void tick() {
        x += velX;
        y += velY;
    }

    /* Render method, all GameObjects must have their own render method */

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, 32, 32);

    }

    /* This is for collision detection */

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

}
