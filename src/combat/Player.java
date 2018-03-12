package combat;
/* TODO: Implement leveling up + stat gain
 * 
 */

import world.World;

public class Player extends Creature implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int experience;
	private int posX;
	private int posY;

	public Player(int hp, String name, int strength, int defense, int magic) {
		super(hp, name, strength, defense, magic);
		this.experience = 0;
	}
	
	public Player() {
		super(1, "kaitsu", 10, 10, 10);
	}

	@Override
	public int getExp() {
		return this.experience;
	}

	@Override
	public void addExp(int exp) {
		this.experience += exp;
	}

	// for setting starting location, otherwise use moveDirecton()
	public void setLocation(int y, int x) {
		this.posX = x;
		this.posY = y;
	}

	public int getX() {
		return this.posX;
	}

	public int getY() {
		return this.posY;
	}

	// temporary solution
	public int[] getLocation() {
		final int[] pos = new int[2];
		pos[0] = this.posY;
		pos[1] = this.posX;
		return pos;
	}
	/*
	 * should implement an Exception to handle illegal moves think of a way of
	 * passing the world to player class (maybe in constructor(?))
	 */

	public void moveUp(World world) {
		if (this.posY - 1 >= 0 && world.getTile(this.posY - 1, this.posX).getPassable()) {
			this.posY--;
		} else {
			System.out.println("Illegal move");
		}

	}

	public void moveDown(World world) {
		if (this.posY + 1 < world.getHeight() && world.getTile(this.posY + 1, this.posX).getPassable()) {
			this.posY++;
		} else {
			System.out.println("Illegal move");
		}

	}

	public void moveLeft(World world) {
		if (this.posX - 1 >= 0 && world.getTile(this.posY, this.posX - 1).getPassable()) {
			this.posX--;
		} else {
			System.out.println("Illegal move");
		}

	}

	public void moveRight(World world) {
		if (this.posX + 1 < world.getWidth() && world.getTile(this.posY, this.posX + 1).getPassable()) {
			this.posX++;
		} else {
			System.out.println("Illegal move");
		}
	}
}
