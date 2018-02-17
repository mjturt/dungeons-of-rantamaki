/* TODO: Implement magical attacks
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
	
	public Creature(int hp, String name, int strength, int defense, int magic) {
		this.hp = hp;
		this.maxHP = hp;
		this.name = name;
		this.strength = strength;
		this.magic = magic;
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
	
	public int getDf() {
		return this.defense;
	}
	/**
	 * Deals damage to creature by another.
	 * @param attacker - Creature that attacks
	 * @param a - attack that hits
	 * @return: nothing
	 */
	public void DealDamage(Creature attacker, Attack a) {
		int AD =  attacker.getStr()/this.defense;
		int pwr = a.getPwr();
		int dmg = (AD*pwr)/50;
		dmg+=2;
		this.hp-=dmg;
		
	}
}
