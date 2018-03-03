package combat;

import combat.CombatEngine;

public class CombatTest {
	public static void main(String[] args) {
		AttackIDList lista = new AttackIDList();
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		Monster m = new Monster(50, "Spurgu", 5, 5, 5, 5);
		Consumable jallu = new Consumable("Jallu", 10, 10);

		p.addAttack(lista.getAttack(0));
		p.addAttack(lista.getAttack(1));
		m.addAttack(lista.getAttack(0));
		p.addItem(jallu);

		CombatEngine.combat(p, m);
	}
}
