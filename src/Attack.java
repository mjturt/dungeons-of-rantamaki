/**
 * TODO: Implement mana system
 * TODO: Impelement accuracy
 *
 */
package dungeons_of_rantamaki;
public class Attack {
	final private AttackType type;
	final private int power;
	final private String name;
	final private int mana;
	
	public Attack(AttackType type, int power, int mana, String name) {
		this.type=type;
		this.power=power;
		this.name=name;
		this.mana = mana;
	}
	
	public Attack(AttackType type, int power, String name) {
		this.type=type;
		this.power=power;
		this.name=name;
		this.mana = 0;
	}
	
	public AttackType getType() {
		return this.type;
	}
	
	public int getPwr() {
		return this.power;
	}
	
	public int getMana() {
		return this.mana;
	}
	
	public String getName() {
		return this.name;
	}
}

