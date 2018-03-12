/**
 * TODO: Implement mana system
 * TODO: Impelement accuracy
 *
 */
package combat;

public class Attack implements java.io.Serializable{
	final private AttackType type;
	final private int power;
	final private String name;
	final private int mana;

	public Attack(String name, AttackType type, int power, int mana) {
		this.type = type;
		this.power = power;
		this.name = name;
		this.mana = mana;
	}

	public Attack(String name, AttackType type, int power) {
		this.type = type;
		this.power = power;
		this.name = name;
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
