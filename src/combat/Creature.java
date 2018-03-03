/* 
 *  TODO: Implement list of known attacks and spells
 * 
 */
package combat;

import java.util.ArrayList;

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
	protected ArrayList<Attack> moveList;
	protected ArrayList<Attack> spellBook;
	protected ArrayList<Consumable> inventory;

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
		this.moveList = new ArrayList<>();
		this.spellBook = new ArrayList<>();
		this.inventory = new ArrayList<>();
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

	public void addSpell(Attack a) {
		if (a.getType() == AttackType.MAGICAL) {
			this.spellBook.add(a);
		} else {
			System.out.println("Can't add " + a.getType() + " to spellbook");
		}
	}
	
	public Attack getSpell(int a) {
		return this.spellBook.get(a);
	}

	public void listSpellbook() {
		int i = 0;
		for (Attack a : this.spellBook) {
			System.out.println(i + ": " + a.getName());
			i++;
		}
		System.out.println(i + ": Return");
	}
	
	public int getSpellbookLength() {
		return this.spellBook.size();
	}

	public void addAttack(Attack a) {
		if (a.getType() == AttackType.PHYSICAL) {
			this.moveList.add(a);
		} else {
			System.out.println("Can't add " + a.getType() + " to movelist");
		}
	}

	public void listMoveList() {
		int i = 0;
		for (Attack a : this.moveList) {
			System.out.println(i + ": " + a.getName());
		}
		i++;
		System.out.println(i + ": Return");
	}
	
	public int getMoveListLength() {
		return this.moveList.size();
	}

	public Attack getMove(int a) {
		return this.moveList.get(a);
	}
	
	public void addItem(Consumable cons) {
		this.inventory.add(cons);
	}

	public void listInventory() {
		int i = 0;
		for (Consumable cons : this.inventory) {
			System.out.println(i + ": " + cons.getConsumableName());
			i++;
		}
		System.out.println(i + ": Return");
	}
	
	public int getInventoryLength () {
		return this.inventory.size();
	}
	
	public Consumable getItem(int a) {
		return this.inventory.get(a);
	}
	
	public void useItem(int index) {
		Consumable tmp = this.inventory.get(index);
		this.setHP(this.getHP() + tmp.getRestoreHp());
		System.out.println("You restore " + tmp.getRestoreHp() + " and ");
		this.setMana(this.getMana() + tmp.getRestoreMana());
		System.out.print(tmp.getRestoreMana() + " mana!");
		tmp.setUses(tmp.getUses() - 1);
		if (tmp.getUses() < 1) {
			inventory.remove(index);
		}
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
	public void DealDamage(Creature defender, Attack a) {
		AttackType type = a.getType();
		int pwr;
		double AD, dmg, lvl;
		switch (type) {
		case PHYSICAL:
			AD = this.strength / defender.getDf();
			break;
		case MAGICAL:
			AD = this.magic / defender.getMagic();
			break;
		default:
			AD = 0.5; // should never come to this
		}
		lvl = this.level * 2;
		lvl = lvl / 5;
		lvl += 2;
		pwr = a.getPwr();
		dmg = (lvl * pwr * AD) / 50;
		dmg += 2;
		System.out.println(this.name + " used " + a.getName() + ", it deals " + dmg + " damage!");
		defender.setHP((int)(defender.getHP()-Math.floor(dmg)));
		this.mana+=a.getMana();
	}

	/*
	 * Levelup function, just increments stats by using formula of: ((stat *
	 * level)*80)/50, also sets new maximum HP and mana and restores stats
	 */
	public void LevelUp() {
		this.level++;
		int deltaHP = this.maxHP * 6;
		deltaHP = deltaHP / 5;
		this.maxHP = deltaHP;
		this.hp = this.maxHP;
		int deltaStrength = this.strength * 6;
		deltaStrength = deltaStrength / 5;
		this.strength = deltaStrength;
		int deltaDefense = this.defense * 6;
		deltaDefense = deltaDefense / 5;
		this.defense = deltaDefense;
		int deltaMagic = this.magic * 6;
		deltaMagic = deltaMagic / 5;
		this.magic = deltaMagic;
		this.maxMana = deltaMagic;
		this.mana = deltaMagic;
	}
}
