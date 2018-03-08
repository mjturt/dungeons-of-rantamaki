package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JDialog;

import combat.MonsterGenerator;
import combat.Player;
import combat.*;

/* Player class, must be joined with combat.Player */

public class GuiPlayer extends GameObject {
	
	Handler handler;
	private BufferedImage playerimg = null;
	private BufferedImage playerimgL = null;
	private BufferedImage playerimgR = null;
	private BufferedImage playerimgB = null;
	int tempX;
	int tempY;
	Player p;
	MonsterGenerator mg;
	/**
	 * @param x position in the x-axis
	 * @param y position in the y-axis
	 * @param id Enum.ID
	 * @param handler game event handler
	 */
	public GuiPlayer(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		Random r = new Random();
		this.handler = handler;
		ImageLoader loader = new ImageLoader();
		playerimg = loader.loadImage("/player.png");
		playerimgL = loader.loadImage("/playerLeftAni.gif");
		playerimgR = loader.loadImage("/playerRight.png");
		playerimgB = loader.loadImage("/playerBack.png");
		p = new Player(25, "Kaitsu", 10, 10, 20);
		this.p.addItem(new Consumable("Testi", 1, 1, 1));
		AttackIDList aid = new AttackIDList();
		SpellIDList sid = new SpellIDList();
		p.addAttack(aid.getAttack(r.nextInt(1)));
		p.addSpell(sid.getSpell(r.nextInt(1)));
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

        /* Player character direction */

        if ( velX > 0 ) {
		    g.drawImage(playerimgR, x, y, null);
        } else if ( velX < 0 ) {
		    g.drawImage(playerimgL, x, y, null);
        } else if ( velY < 0 && velX == 0 ) {
            g.drawImage(playerimgB, x, y, null);
        } else {
            g.drawImage(playerimg, x, y, null);
        }

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
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
		Rectangle newPos = new Rectangle(newX, newY, 16, 16);
		for (int i = 0; i < handler.objects.size(); i++) {
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == Block.class) {
				return;
			}
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == GuiMonster.class) {
				InitCombatProper combat = new InitCombatProper(this.p, mg.getMonster(mg.getMonsterListSize() - 1, r.nextInt(p.getLevel() + 3)), this.handler.getFrame());
				combat.createMain();
				while (combat.isRunning()) {
					try {
						Thread.sleep(500);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				handler.releaseKeys();
				handler.removeObject(handler.objects.get(i));
			}
		}
		y = tempY;
		x = tempX;
	}
	
}
