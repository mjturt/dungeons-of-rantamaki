package world;

import java.util.Random;

/**
 * passable indicates if a Person object can reside in this Tile Maasto is used
 * to indicate the type of terrain(GUI) onkoVihollista if a Monster object is
 * present in this Tile
 * 
 * TODO: EVERYTHING
 * 
 */
public class Tile {

	private boolean passable;
	private Maasto maasto;
	private boolean monster;

	public Tile() {

		this.passable = false;
		this.maasto = Maasto.MAASTO1;
		this.monster = false;
	}

	public boolean getPassable() {
		return this.passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	public boolean hasMonster() {
		return this.monster;
	}

	public void setMonster(boolean t) {
		this.monster = t;
	}

	public Maasto getMaasto() {
		return this.maasto;
	}

	public void setMaasto(Maasto m) {
		this.maasto = m;
	}

	public void setRandomMaasto() {
		if (this.passable) {
			this.maasto = Maasto.MAASTO1;
		} else {
			this.maasto = Maasto.getUnpassable();
		}
	}

	public void setRandomVihollinen() {
		Random r = new Random();
		if (this.passable) {
			this.monster = r.nextBoolean();
		}
	}

	enum Maasto {
		MAASTO1, MAASTO2, MAASTO3;

		/**
		 * @return random Maasto enumerable
		 */
		public static Maasto getUnpassable() {

			Random r = new Random();
			return values()[r.nextInt(2) + 1];

		}
	}
}