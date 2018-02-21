package combat;
/* TODO: Implement leveling up + stat gain
 * 
 */

import world.World;

class Player extends Creature {
	private int experience;
	private int posX;
	private int posY;

	public Player(int hp, String name, int strength, int defense, int magic) {
		super(hp, name, strength, defense, magic);
		this.experience = 0;
	}

	public int getExp() {
		return this.experience;
	}

	/**
	 * @param: gained
	 *             experience
	 * @return: nothing just adds the experience to the player
	 */
	public void addExp(int exp) {
		this.experience += exp;
	}
	//for setting starting location, otherwise use moveDirecton()
	public void setLocation (int x, int y) {
		this.posX = x;
		this.posY = y;
	}
	
	public int getX () {
		return this.posX;
	}
	
	public int getY () {
		return this.posY;
	}
	
	
	//temporary solution
	public int[] getLocation () {
		int[] pos = new int[2];
		pos[0] = this.posX;
		pos[1] = this.posY;
		return pos;
	}
	/*
	 * should implement an Exception to handle illegal moves
	 * think of a way of passing the world to player class (maybe in constructor(?))
	 */
	
	public void moveUp (World world) {
		if (this.posY - 1 >= 0 && world.getTile(this.posY - 1, this.posX).getPassable()) {
			this.posY --;
		} else {
			System.out.println("Illegal move");
		}
		
	}
	
	public void moveDown (World world) {
		if (this.posY + 1 < world.getHeight() && world.getTile(this.posY + 1, this.posX).getPassable()) {
			this.posY++;
		} else {
			System.out.println("Illegal move");
		}
		
	}
	
	public void moveLeft (World world) {
		if (this.posX - 1 >= 0 && world.getTile(this.posY, this.posX - 1).getPassable()) {
			this.posX--;
		} else {
			System.out.println("Illegal move"); 
		}
		
	}
	
	public void moveRight (World world) {
		if (this.posX + 1 < world.getWidth() && world.getTile(this.posY, this.posX + 1).getPassable()) {
			this.posX++;
		} else {
			System.out.println("Illegal move");
		}	
	}
}
