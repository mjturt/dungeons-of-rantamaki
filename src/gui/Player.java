package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/* Player class, must be joined with combat.Player */

public class Player extends GameObject {

    Handler handler;
    private BufferedImage playerimg = null;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        ImageLoader loader = new ImageLoader();
        playerimg = loader.loadImage("/player.png");
    }

    /* Players own ticking and rendering methods */

    public void tick() {

        /* Moving coordinates based on velocity */

        

        /* Moving, using velocity instead of coordinates for smooth movement */

        if (handler.isUp()) {
            if (collides(this.handler)) {
            	velY = 2;
            	this.handler.setUp(true);
            } else {
        	velY = -2;
            }
        } else if (!handler.isDown()) {
            velY = 0;
        }
        if (handler.isDown()) {
        	if (collides(this.handler)) {
        		velY = -2;
        		this.handler.setDown(true);
        	} else {
        		velY = 2;
        	}
        } else if (!handler.isUp()) {
            velY = 0;
        }
        if (handler.isRight()) {
            if (collides(this.handler)) {
            	velX = -2;
            	this.handler.setRight(true);
            } else {
            	velX = 2;
            }
        } else if (!handler.isLeft()) {
            velX = 0;
        }
        if (handler.isLeft()) {
        	if (collides(this.handler)) {
        		velX = 2;
        		this.handler.setLeft(true);
        	} else {
        		velX = -2;
        	}
        } else if (!handler.isRight()) {
            velX = 0;
        }
        
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        //g.setColor(Color.green);
        //g.fillRect(x, y, 32, 32);
        /* Current image is just example and its too big */
        g.drawImage(playerimg, x, y, null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    
    public boolean collides(Handler h) {
    	for (GameObject go : h.objects) {
    		if (go.getBounds().intersects(this.getBounds()) && go.getClass() == Block.class) {
    			return true;
    		}
    	}
    	return false;
    }
}
