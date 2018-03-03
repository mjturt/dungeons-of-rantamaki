package combat;

import combat.CombatEngine;

public class CombatTest {
	public static void main(String[] args) {
		AttackIDList lista = new AttackIDList();
		SpellIDList grimoire = new SpellIDList();
		MonsterGenerator monsu = new MonsterGenerator();
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		Monster m = monsu.getMonster(0, p.getLevel());
		Consumable jallu = new Consumable("Jallu", 10, 10);

		p.addAttack(lista.getAttack(0));
		p.addSpell(grimoire.getSpell(0));
		p.addItem(jallu);

		CombatEngine.combat(p, m);
	}
}
