package combat;

public class Monster extends Creature {
	final protected int EXPGAIN;

	public Monster(int hp, String name, int strength, int defense, int magic, int exp) {
		super(hp, name, strength, defense, magic);
		this.EXPGAIN = exp;
	}

	public int getExpGain() {
		return this.EXPGAIN;
	}
}
