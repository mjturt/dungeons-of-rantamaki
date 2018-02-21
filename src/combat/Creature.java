/* 
 *  TODO: Implement list of known attacks and spells
 * 
 */
package combat;

public class Creature {
	protected int maxHP;
	protected int hp;
	protected String name;
	protected int strength;
	protected int magic;
	protected int defense;
	protected int mana;
	protected int maxMana;
	protected int level;
	protected int exp;

	public Creature(int hp, String name, int strength, int defense, int magic) {
		this.hp = hp;
		this.maxHP = hp;
		this.name = name;
		this.strength = strength;
		this.magic = magic;
		this.mana = magic * 2;
		this.maxMana = mana;
		this.defense = defense;
		this.level = 1;
		this.exp = 0;
	}

	public void incrementMaxHP(int hp) {
		this.maxHP += hp;
	}

	public int getMaxHP() {
		return this.maxHP;
	}

	public int getHP() {
		return this.hp;
	}

	public void setHP(int hp) {
		if (hp >= this.maxHP) {
			this.hp = this.maxHP;
		} else {
			this.hp = hp;
		}
	}

	public String getName() {
		return this.name;
	}

	public int getStr() {
		return this.strength;
	}

	public int getMagic() {
		return this.magic;
	}

	public int getMana() {
		return this.mana;
	}

	public void setMana(int mana) {
		if (mana >= this.maxMana) {
			this.mana = this.maxMana;
		} else {
			this.mana = mana;
		}
	}

	public int getMaxMana() {
		return this.maxMana;
	}

	public void incrementMaxMana(int mana) {
		this.maxMana += mana;
	}

	public int getDf() {
		return this.defense;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getExp() {
		return this.exp;
	}
	
	public void addExp(int addition) {
		this.exp += addition;
	}

	/**
	 * Deals damage to creature by another.
	 * 
	 * @param attacker
	 *            - Creature that attacks
	 * @param a
	 *            - attack that hits
	 * @return: nothing 
	 */
	public void DealDamage(Creature attacker, Attack a) {
		AttackType type = a.getType();
		int pwr;
		double AD, dmg, lvl;
		switch (type) {
		case PHYSICAL:
			AD = attacker.getStr() / this.defense;
			break;
		case MAGICAL:
			AD = attacker.getMagic() / this.magic;
			break;
		default:
			AD = 0.5; // should never come to this
		}
		lvl = this.level * 2;
		lvl = lvl/5;
		lvl += 2;
		pwr = a.getPwr();
		dmg = (lvl * pwr * AD) / 50;
		dmg += 2;
		this.hp -= Math.round(dmg);
	}
	
	
	/*
	 * Levelup function, just increments stats by using formula of:
	 * ((stat * level)*80)/50, also sets new maximum HP and mana and 
	 * restores stats
	 */
	public void LevelUp() {
		this.level++;
		int deltaHP = this.maxHP*6;
		deltaHP = deltaHP / 5;
		this.maxHP = deltaHP;
		this.hp = this.maxHP;
		int deltaStrength = this.strength*6;
		deltaStrength = deltaStrength / 5;
		this.strength = deltaStrength;
		int deltaDefense = this.defense*6;
		deltaDefense = deltaDefense / 5;
		this.defense = deltaDefense;
		int deltaMagic = this.magic*6;
		deltaMagic = deltaMagic / 5;
		this.magic = deltaMagic;
		this.maxMana = deltaMagic;
		this.mana = deltaMagic;
	}
}
