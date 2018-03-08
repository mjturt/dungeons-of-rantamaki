package combat;
/**
 * Consumable class is to represent items that the player can obtain in the world. 
 * For now is only used for regenerative items. 
 */
public class Consumable {

	private String consumableName; //name
	private int restoreHp; //how much the item restores HP
	private int restoreMana; //how much the item restore mana
	private int uses; //how many times it can be used before its consumed/destroyed

	public Consumable(String name, int hp, int mana, int uses) {
		this.consumableName = name;
		this.restoreHp = hp;
		this.restoreMana = mana;
		this.uses = uses;
	}

	public void setRestoreHp(int a) {
		this.restoreHp = a;
	}

	public int getRestoreHp() {
		return this.restoreHp;
	}

	public void setRestoreMana(int a) {
		this.restoreMana = a;
	}

	public int getRestoreMana() {
		return this.restoreMana;
	}

	public void setConsumableName(String name) {
		this.consumableName = name;
	}

	public String getConsumableName() {
		return this.consumableName;
	}
	
	public void setUses (int i) {
		this.uses = i;
	}
	
	public int getUses () {
		return this.uses;
	}
}
