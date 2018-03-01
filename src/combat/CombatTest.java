package combat;

import combat.CombatEngine;

public class CombatTest {
	public static void main(String[] args) {
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		Monster m = new Monster(50, "Spurgu", 5, 5, 5, 5);
		Attack a = new Attack(AttackType.PHYSICAL, 50, "Bash");
		Attack b = new Attack(AttackType.MAGICAL, 100, 5, "Fireball");
		Consumable jallu = new Consumable("Jallu", 10, 10);

		p.addAttack(a);
		p.addSpell(b);
		m.addAttack(a);
		p.addItem(jallu);

		CombatEngine.combat(p, m);
	}
}
