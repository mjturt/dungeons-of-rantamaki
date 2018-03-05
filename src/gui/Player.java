package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/* Player class, must be joined with combat.Player */

public class Player extends GameObject {

	Handler handler;
	private BufferedImage playerimg = null;
	int tempX;
	int tempY;

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
			velY = -2;
		} else if (!handler.isDown()) {
			velY = 0;
		}
		if (handler.isDown()) {
			velY = 2;
		} else if (!handler.isUp()) {
			velY = 0;
		}
		if (handler.isRight()) {
			velX = 2;
		} else if (!handler.isLeft()) {
			velX = 0;
		}
		if (handler.isLeft()) {
			velX = -2;
		} else if (!handler.isRight()) {
			velX = 0;
		}
		tempX = x;
		tempX += velX;
		tempY = y;
		tempY += velY;
		
		updatePos(tempY, tempX);
	}


	public void render(Graphics g) {
		// g.setColor(Color.green);
		// g.fillRect(x, y, 32, 32);
		/* Current image is just example and its too big */
		g.drawImage(playerimg, x, y, null);

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	/**
	 * @param newY == the position the player will be next rendered to
	 * @param newX == the position the player will be next rendered to
	 * 
	 * This method needs another collision check to see if the player collides with an enemy. If it does, initiate combat routine.
	 */
	public void updatePos(int newY, int newX) {
		Rectangle newPos = new Rectangle(newX, newY, 32, 32);
		for (GameObject o : handler.objects) {
			if (newPos.intersects(o.getBounds()) && o.getClass() == Block.class) {
				return;
			}
		}
		y = tempY;
		x = tempX;
	}
}
