/* 
 *  TODO: Implement list of known attacks and spells
 * 
 */
package dungeons_of_rantamaki;
public class Creature {
	protected int maxHP;
	protected int hp;
	protected String name;
	protected int strength;
	protected int magic;
	protected int defense;
	protected int mana;
	protected int maxMana;
	
	public Creature(int hp, String name, int strength, int defense, int magic) {
		this.hp = hp;
		this.maxHP = hp;
		this.name = name;
		this.strength = strength;
		this.magic = magic;
		this.mana = magic*2;
		this.maxMana = mana;
		this.defense=defense;
	}
	public void incrementMaxHP(int hp) {
		this.maxHP += hp;
	}
	
	public int getMaxHP() {
		return this.maxHP;
	}
	
	public int getHP(){
		return this.hp;
	}
	
	public void setHP(int hp) {
		this.hp=hp;
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
		this.mana = mana;
	}
	
	public int getMaxMana() {
		return this.maxMana;
	}
	
	public void incrementMaxMana(int mana) {
		this.maxMana+=mana;
	}
	
	public int getDf() {
		return this.defense;
	}
	/**
	 * Deals damage to creature by another.
	 * @param attacker - Creature that attacks
	 * @param a - attack that hits
	 * @return: nothing
	 * TODO: implement level modifier to damage, for example ((2*Level)/2)+2 
	 */
	public void DealDamage(Creature attacker, Attack a) {
		AttackType type = a.getType();
		int pwr;
		double AD, dmg;
		switch(type) {
		case PHYSICAL:	AD =  attacker.getStr()/this.defense; 
						break;
		case MAGICAL:   AD = attacker.getMagic()/this.magic;
						break;
		default: 		AD = 0.5; //should never come to this
		}
		pwr = a.getPwr();
		dmg = (pwr*AD)/50;
		dmg += 2;
		this.hp -= Math.round(dmg);
	}
}
