package combat;


public class CombatTest {
	public static void main(String[] args) {
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		Monster m = new Monster(50, "Spurgu", 5, 5, 5, 5);
		Attack a = new Attack(AttackType.PHYSICAL, 50, "Bash");
		Attack b = new Attack(AttackType.MAGICAL, 100, 5, "Fireball");
		Consumable jallu = new Consumable("Jallu", 10, 10);
		int i = 1;
		System.out.println(p.getName() +"'s Level: " + p.getLevel());
		System.out.println("max HP: " +p.getMaxHP());
		System.out.println("Str: " +p.getStr());
		System.out.println("magic: " +p.getMagic());
		System.out.println("max Mana: " +p.getMaxMana());
		System.out.println("defense: "+p.getDf());
		while (p.getHP() > 0 && m.getHP() > 0) {
			System.out.println("Turn: " + i);
			System.out.println("Level " + p.getLevel() + " " + p.getName() + " HP: " + p.getHP() + "/" + p.getMaxHP() + " Mana: " + p.getMana() + "/"
					+ p.getMaxMana());
			System.out.println(m.getName() + " HP: " + m.getHP() + "/" + m.getMaxHP() + " Mana: " + m.getMana() + "/"
					+ m.getMaxMana());
			System.out.println("==============================================");
			try {
				if (b.getMana() > p.getMana()) {
					throw new NotEnoughMana();
				} else {
					m.DealDamage(p, b);
					System.out.println(p.getName() + " used " + b.getName() + "!");
				}
			} catch (NotEnoughMana manaError) {
				System.out.println("not enough mana, fool");
			}
			p.DealDamage(m, a);
			System.out.println(m.getName() + " used " + a.getName() + "!");
			i++;
			System.out.println("==============================================");
			System.out.println(p.getName() + " HP: " + p.getHP() + ", Mana: " + p.getMana());
			jallu.useConsumable(p);
			System.out.println(p.getName() + " used " + jallu.getConsumableName() + ", it restores " + jallu.getRestoreHp() + " hp and " + jallu.getRestoreMana() + " mana.");
			System.out.println(p.getName() + " HP: " + p.getHP() + ", Mana: " + p.getMana());
		}
		if (p.getHP() < 0) {
			System.out.println("You're dead, kiddo");
		} else if (m.getHP() < 0) {
			System.out.println("You vanquised " + m.getName());
			p.addExp(m.getExpGain());
			System.out.println(m.getExpGain() + " points of experience awarded!");
		}
		System.out.println(p.getName() + "'s EXP: " + p.getExp());
		if(p.getExp() >= Math.pow(p.getLevel(), 3)) { p.LevelUp(); }
		System.out.println(p.getName() +"'s Level: " + p.getLevel());
		System.out.println("max HP: " +p.getMaxHP());
		System.out.println("Str: " +p.getStr());
		System.out.println("magic: " +p.getMagic());
		System.out.println("max Mana: " +p.getMaxMana());
		System.out.println("defense: "+p.getDf());

	}
}
