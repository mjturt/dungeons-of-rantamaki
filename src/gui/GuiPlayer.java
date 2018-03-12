package gui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import combat.MonsterGenerator;
import combat.Player;
import combat.*;

/**
 * Class for GUI aspects of the player.
 */
public class GuiPlayer extends GameObject  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Handler handler;
	transient private SpriteSheet ss;
	transient private BufferedImage playerimg;
	transient private BufferedImage playerimgL;
	transient private BufferedImage playerimgR;
	transient private BufferedImage playerimgB;
	transient private PrintStream stdout = System.out;
	transient private Game game;
	int tempX;
	int tempY;
	private final Player p;
	private final Rectangle bounds;
	/**
	 * @param x position in the x-axis
	 * @param y position in the y-axis
	 * @param id Enum.ID
	 * @param handler game event handler
	 */
	public GuiPlayer(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) {
		super(x, y, id);
		final Random r = new Random();
		this.handler = handler;
        this.ss = ss;
		this.playerimg = this.ss.grabImage(1, 1, 16, 16);
		this.playerimgL = this.ss.grabImage(4, 1, 16, 16);
		this.playerimgR = this.ss.grabImage(3, 1, 16, 16);
		this.playerimgB = this.ss.grabImage(2, 1, 16, 16);
        this.game = game;
		this.bounds = new Rectangle();
		this.bounds.setBounds(x, y, 16, 16);
		this.p = new Player(25, "Kaitsu", 15, 15, 20);
		//this.p.addItem(new Consumable("Testi", 1, 1, 1));
		final AttackIDList aid = new AttackIDList();
		final SpellIDList sid = new SpellIDList();
		this.p.addAttack(aid.getAttack(r.nextInt(1)));
		this.p.addSpell(sid.getSpell(r.nextInt(1)));
	}

	/**
	 * Updates the player velocity information based on key events, and calls updatePos() to check for collision and finally updating the player position.
	 */

	@Override
	public void tick() {

		/* Moving coordinates based on velocity */

		/* Moving, using velocity instead of coordinates for smooth movement */
		
		if (this.handler.isUp()) {
			this.velY = -2;
		} else if (!handler.isDown()) {
			this.velY = 0;
		}
		if (this.handler.isDown()) {
			this.velY = 2;
		} else if (!this.handler.isUp()) {
			this.velY = 0;
		}
		if (this.handler.isRight()) {
			velX = 2;
		} else if (!this.handler.isLeft()) {
			this.velX = 0;
		}
		if (this.handler.isLeft()) {
			this.velX = -2;
		} else if (!this.handler.isRight()) {
			this.velX = 0;
		}
		this.tempX = x;
		this.tempX += velX;
		this.tempY = y;
		this.tempY += velY;
		updatePos(this.tempY, this.tempX);
	}

	/*
	 * Draws picture of the player depending on direction of its movement.
	 */
	@Override
	public void render(Graphics2D g) {

        /* Player character direction */

        if ( velX > 0 ) {
		    g.drawImage(this.playerimgR, x, y, null);
        } else if ( velX < 0 ) {
		    g.drawImage(this.playerimgL, x, y, null);
        } else if ( velY < 0 && velX == 0 ) {
            g.drawImage(this.playerimgB, x, y, null);
        } else {
            g.drawImage(this.playerimg, x, y, null);
        }

	}
	/**
	 * Returns Rectangle of player dimension, used for collision checking
	 */
	@Override
	public Rectangle getBounds() {
		this.bounds.setBounds(x, y, 16, 16);
		return this.bounds;
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
		final Random r = new Random();
		final Rectangle newPos = new Rectangle(newX, newY, 16, 16);
		for (int i = 0; i < this.handler.objects.size(); i++) {
			if (newPos.intersects(this.handler.objects.get(i).getBounds()) && this.handler.objects.get(i).getClass() == Block.class) {
				return;
			}
			if (newPos.intersects(this.handler.objects.get(i).getBounds()) && this.handler.objects.get(i).getClass() == GuiMonster.class) {
				MonsterGenerator mg = new MonsterGenerator();
				//Generates a random opponent.
				final InitCombat combat = new InitCombat(this.p, mg.getMonster(r.nextInt(mg.getMonsterListSize()), this.p.getLevel()), this.handler.getFrame());
				SwingUtilities.invokeLater(combat);//thread safe way to invoke swing.
				while (combat.isRunning()) {
					try {
						Thread.sleep(100);
					} catch (final Exception e) {
						e.printStackTrace();
					}
				}
				mg = null;
				if (combat.isGameOver()) {
					System.out.println("Game was over");
					final JDialog test = new GameOver(this.handler.getFrame());
					final GameOver go = (GameOver) test;
					SwingUtilities.invokeLater(go); //thread safe way to run swing. Not really necessary, but just to avoid thread racing issues. 
					/*
					 * Sleeps the thread for 100 millisecs until the combat event has been finished.
					 */
					while (go.isRunning()) {
						try {
							Thread.sleep(100);
						} catch (final InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				this.handler.releaseKeys(); //stops all player movement, so the player wont start moving to the direction last moved after returning from combat.
				/*
				 * resets sysout to default
				 */
				System.setOut(this.stdout);
				this.handler.removeObject(this.handler.objects.get(i));
			}
			/*
			 * Checking if player has reached Goal. 
			 */
			if (newPos.intersects(handler.objects.get(i).getBounds()) && handler.objects.get(i).getClass() == Goal.class) {
                game.setState(STATE.GOAL);
		    }
        }
		y = tempY;
		x = tempX;
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
    
    public Handler getHandler() {
    	return this.handler;
    }

	@Override
	public void reloadAssets(SpriteSheet sheet) {
		this.ss = sheet;
		this.playerimg = this.ss.grabImage(1, 1, 16, 16);
		this.playerimgL = this.ss.grabImage(4, 1, 16, 16);
		this.playerimgR = this.ss.grabImage(3, 1, 16, 16);
		this.playerimgB = this.ss.grabImage(2, 1, 16, 16);
	}
}
