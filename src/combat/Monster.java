package combat;

public class Monster extends Creature {
	final protected int EXPGAIN;

	public Monster(int hp, String name, int strength, int defense, int magic, int level) {
		super(hp, name, strength, defense, magic);
		this.level = level;
		this.EXPGAIN = this.hp;
	}

	public int getExpGain() {
		return this.EXPGAIN;
	}
}
