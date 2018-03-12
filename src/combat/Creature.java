package combat;

import java.util.ArrayList;

public class Creature implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	/**
	 * Increment creatures maxHP
	 * @param hp amount to increment
	 */
	public void incrementMaxHP(int hp) {
		this.maxHP += hp;
	}

	public int getMaxHP() {
		return this.maxHP;
	}

	public int getHP() {
		return this.hp;
	}
	/**
	 * @param hp the amount of HP set
	 * sets the HP to specified amount, 
	 * or maxHP in case the parameter is too high 
	 */
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
	/**
	 * @param hp the amount of mana set
	 * sets the mana to specified amount, 
	 * or maxMana in case the parameter is too high 
	 */
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
	/**
	 * Returns the Creatures spellbook
	 * @return Creature spellbook
	 */
	public ArrayList<Attack> getSpellbook() {
		return this.spellBook;
	}
	/**
	 * Returns the Creatures movelist
	 * @return Creature movelist
	 */
	public ArrayList<Attack> getMovelist() {
		return this.moveList;
	}
	
	public int getSpellbookLength() {
		return this.spellBook.size();
	}
	/**
	 * Adds attack to Creature.movelist.
	 * @param a Attack to add.
	 */
	public void addAttack(Attack a) {
		if (a.getType() == AttackType.PHYSICAL) {
			this.moveList.add(a);
		} else {
			System.out.println("Can't add " + a.getType() + " to movelist");
		}
	}
	/**
	 * Deprecated method, used only in text-only environments
	 */
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
	/**
	 * Adds a Consumable() to Creature.inventory
	 * @param cons Consumable() to add
	 */
	public void addItem(Consumable cons) {
		this.inventory.add(cons);
	}
	/**
	 * Deprecated method, used in text-only environments
	 */
	public void listInventory() {
		int i = 0;
		for (Consumable cons : this.inventory) {
			System.out.println(i + ": " + cons.getConsumableName());
			i++;
		}
		System.out.println(i + ": Return");
	}
	
	public ArrayList<Consumable> getInventory() {
		return this.inventory;
	}
	
	public int getInventoryLength () {
		return this.inventory.size();
	}
	
	public Consumable getItem(int a) {
		return this.inventory.get(a);
	}
	/**
	 * Uses item in given index from Creature.inventory
	 * @param index index from which the Consumable is retrieved
	 */
	public void useItem(int index) {
		Consumable tmp = this.inventory.get(index);
		this.setHP(this.getHP() + tmp.getRestoreHp());
		System.out.print("You restore " + tmp.getRestoreHp() + " HP and ");
		this.setMana(this.getMana() + tmp.getRestoreMana());
		System.out.println(tmp.getRestoreMana() + " mana!");
		tmp.setUses(tmp.getUses() - 1);
		if (tmp.getUses() > 0) {
			System.out.println("Your " + tmp.getConsumableName() + " has " + tmp.getUses() + " uses left!");
		}
		if (tmp.getUses() < 1) {
			inventory.remove(index);
		}
	}
	/**
	 * Deprecated, unsafe to use due to risk of using multiple items per invocation.
	 * @param c Consumable to use
	 */
	public void useItem(Consumable c) {
		
		this.setHP(this.getHP() + c.getRestoreHp());
		System.out.print("You restore " + c.getRestoreHp() + " HP and ");
		this.setMana(this.getMana() + c.getRestoreMana());
		System.out.println(c.getRestoreMana() + " mana!");
		c.setUses(c.getUses() - 1);
		if (c.getUses() > 0) {
			System.out.println("Your " + c.getConsumableName() + " has " + c.getUses() + " uses left!");
		}
		if (c.getUses() < 1) {
			inventory.remove(c);
		}
	}

	/**
	 * Deals damage to target creature by this.Creature.
	 * 
	 * @param defender
	 *            - Creature that defends
	 * @param a
	 *            - attack that hits
	 * @return: nothing
	 */
	public void DealDamage(Creature defender, Attack a) {
			double dmg = calculateDamage(defender, a);
			System.out.println(this.name + " used " + a.getName() + ", it deals " + dmg + " damage!");
			defender.setHP((int)(defender.getHP()-Math.round(dmg)));
			this.mana-=a.getMana();
	}
	
	/**
	 * 
	 * @param defender - defending creature
	 * @param a 	- attack that hits
	 * @return nothing
	 * 
	 *calculates the amount of damage that could be done using specified attack
	 *to specified target
	 */
	public double calculateDamage(Creature defender, Attack a) {
		AttackType type = a.getType();
		int pwr;
		double AD, dmg, lvl;
		switch (type) {
		case PHYSICAL:
			AD = this.strength / defender.getDf();
		case MAGICAL:
			AD = this.magic / defender.getMagic();
		default:
			AD = 0.5; // should never come to this
		}
		lvl = this.level * 2;
		lvl = lvl / 5;
		lvl += 2;
		pwr = a.getPwr();
		dmg = (lvl * pwr * AD) / 50;
		dmg += 2;
		return dmg;
	}
	/**
	 * Checks if the player has the sufficient mana to cast Spell.
	 * @param a Attack to check against
	 * @return True, if the player has the sufficient mana to use spell given as parameter.
	 */
	public boolean ManaCheck(Attack a) {
		if(this.mana>=a.getMana()) {
			return true;
		}
		return false;
	}


	/**
	 * Levelup function, just increments stats by using formula of: 10 + level / 10, rounded downward
	 * also sets new maximum HP and mana and restores stats
	 */
	public void LevelUp() {
		this.level++;
		int up = (int) Math.floor((10.0+this.level)/10);
		this.maxHP += up;
		this.hp = this.maxHP;
		this.maxMana += up;
		this.mana = this.maxMana;
		this.magic += up;
		this.strength += up;
		this.defense += up;
	}
	
	/**
	 * Checks if there's enough XP for levelup, prints out stats and calls itself again
	 */
	public void CheckLevelUp() {
		int nxtLvl = (int) Math.pow(this.level+1, 3);
		nxtLvl = (5/4)*nxtLvl;
		if(this.getExp() >= nxtLvl) {
			this.LevelUp();
			System.out.println("Level up!");
			this.dumpStats();
			this.CheckLevelUp();
		}
	}
	/**
	 * nuthin' fancy, just dump the stats on console
	 */
	public void dumpStats() {
		System.out.println(this.getName() + "'s LVL: " + this.getLevel());
		System.out.println("HP: " + this.getHP() + "/" + this.getMaxHP());
		System.out.println("MP: " + this.getMana() + "/" + this.getMaxMana());
		System.out.println("EXP: " + this.getExp());
		System.out.println("Str: " + this.getStr());
		System.out.println("Def: " + this.getDf());
		System.out.println("Magic: " + this.getMagic());
		System.out.println("");
	}
}
