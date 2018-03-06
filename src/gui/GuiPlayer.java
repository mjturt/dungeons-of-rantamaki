package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import combat.MonsterGenerator;
import combat.Player;

/* Player class, must be joined with combat.Player */

public class GuiPlayer extends GameObject {
	
	Handler handler;
	private BufferedImage playerimg = null;
	int tempX;
	int tempY;
	Player p;
	MonsterGenerator mg;
	
	public GuiPlayer(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		ImageLoader loader = new ImageLoader();
		playerimg = loader.loadImage("/player.png");
		p = new Player(25, "Kaitsu", 10, 10, 20);
		mg = new MonsterGenerator();
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
	 * Method for updating player position based on velocity. Checks if the player collides with other gameobject
	 * than itself, and then updates its position based on the result of collision. Also checks if a monster is present
	 * in a block and initiates a combat sequence if necessary.
	 */
	public void updatePos(int newY, int newX) {
		Random r = new Random();
		Rectangle newPos = new Rectangle(newX, newY, 32, 32);
		for (int i = 0; i < handler.objects.size(); i++) {
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == Block.class) {
				return;
			}
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == GuiMonster.class) {
				InitCombat.main(this.handler, handler.objects.get(i), this.mg.getMonster(r.nextInt(this.mg.getMonsterListSize()), p.getLevel() + 3), this.p);
			}
		}
		y = tempY;
		x = tempX;
	}
	
}
