package gui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JDialog;

import combat.MonsterGenerator;
import combat.Player;
import combat.*;

/**
 * Class for GUI aspects of the player.
 */
public class GuiPlayer extends GameObject {
	
	Handler handler;
    private SpriteSheet ss;
	private BufferedImage playerimg = null;
	private BufferedImage playerimgL = null;
	private BufferedImage playerimgR = null;
	private BufferedImage playerimgB = null;
	int tempX;
	int tempY;
	Player p;
	/**
	 * @param x position in the x-axis
	 * @param y position in the y-axis
	 * @param id Enum.ID
	 * @param handler game event handler
	 */
	public GuiPlayer(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id);
		Random r = new Random();
		this.handler = handler;
        this.ss = ss;
		playerimg = ss.grabImage(1, 1, 16, 16);
		playerimgL = ss.grabImage(4, 1, 16, 16);
		playerimgR = ss.grabImage(3, 1, 16, 16);
		playerimgB = ss.grabImage(2, 1, 16, 16);

		p = new Player(25, "Kaitsu", 10, 10, 20);
		this.p.addExp(10000);
		this.p.CheckLevelUp();
		this.p.addItem(new Consumable("Testi", 1, 1, 1));
		AttackIDList aid = new AttackIDList();
		SpellIDList sid = new SpellIDList();
		p.addAttack(aid.getAttack(r.nextInt(1)));
		p.addSpell(sid.getSpell(r.nextInt(1)));
	}

	/**
	 * Updates the player velocity information based on key events, and calls updatePos() to check for collision and finally updating the player position.
	 */

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

	/*
	 * Draws picture of the player depending on direction of its movement.
	 */
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
	/**
	 * Returns Rectangle of player dimension, used for collision checking
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	/**
	 * 
	 * Method for updating player position based on velocity. Checks if the player collides with other GameObject
	 * than itself, and then updates its position based on the result of collision. Also checks if a monster is present
	 * in a block and initiates a combat sequence if necessary.
	 * 
	 * @param newY the Y-axis position the player will be next rendered to
	 * @param newX the X-axis position the player will be next rendered to
	 * 
	 */
	public void updatePos(int newY, int newX) {
		Random r = new Random();
		Rectangle newPos = new Rectangle(newX, newY, 16, 16);
		for (int i = 0; i < handler.objects.size(); i++) {
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == Block.class) {
				return;
			}
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == GuiMonster.class) {
				MonsterGenerator mg = new MonsterGenerator();
				InitCombat combat = new InitCombat(this.p, mg.getMonster(r.nextInt(mg.getMonsterListSize()), generateMonLevel()), this.handler.getFrame());
				combat.createMain();
				while (combat.isRunning()) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (combat.isGameOver()) {
					System.out.println("Game was over");
					JDialog test = new GameOver(this.handler.getFrame());
					GameOver go = (GameOver)test;
					while (go.isRunning()) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				handler.releaseKeys(); //stops all player movement, so the player wont start moving to the direction last moved after returning from combat.
				handler.removeObject(handler.objects.get(i));
			}
		}
		y = tempY;
		x = tempX;
	}
	//quick bit of code that generates monster level by using
	//pseudo-random numbers. uses math to generate level that varies
	//0.9*playerlevel - 1.1*playerlevel
	private int generateMonLevel() {
		Random r = new Random();
		int delta = (int) Math.floor(this.p.getLevel()/10);
		int lvl = this.p.getLevel() + (r.nextInt(delta+1)-delta);
		return lvl;
	}

    /* 
     * Getters for player statistics that is shown in game windows 
     */

    public int getHP() {
        return this.p.getHP();
    }
    public int getMaxHP() {
        return this.p.getMaxHP();
    }
    public int getLevel() {
        return this.p.getLevel();
    }
    public int getMana() {
        return this.p.getMana();
    }
    public int getMaxMana() {
        return this.p.getMaxMana();
    }
}
