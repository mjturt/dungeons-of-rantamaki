package combat;

public class Consumable {

	private String consumableName;
	private int restoreHp;
	private int restoreMana;

	public Consumable(String name, int hp, int mana) {
		this.consumableName = name;
		this.restoreHp = hp;
		this.restoreMana = mana;
	}

	public void useConsumable(Creature creature) {
		creature.setHP(this.restoreHp + creature.hp);
		creature.setMana(this.restoreMana + creature.mana);
		creature.inventory.remove(this);
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
}
