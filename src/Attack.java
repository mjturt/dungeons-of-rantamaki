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
	
	public Attack(AttackType type, int power, String name) {
		this.type=type;
		this.power=power;
		this.name=name;
	}
	
	public AttackType getType() {
		return this.type;
	}
	
	public int getPwr() {
		return this.power;
	}
	
	public String getName() {
		return this.name;
	}
}

