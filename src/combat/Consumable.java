package combat;

public class Consumable {

	private String consumableName;
	private int restoreHp;
	private int restoreMana;
	private int uses;

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
