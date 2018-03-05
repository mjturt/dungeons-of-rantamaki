package combat;

import combat.CombatEngine;

public class CombatTest {
	public static void main(String[] args) {
		AttackIDList lista = new AttackIDList();
		SpellIDList grimoire = new SpellIDList();
		MonsterGenerator monsu = new MonsterGenerator();
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		Monster m = monsu.getMonster(0, p.getLevel());
		p.setExp(100);
		p.CheckLevelUp();
		p.dumpStats();
		p.addAttack(lista.getAttack(0));
		p.addSpell(grimoire.getSpell(0));

		//CombatEngine.combat(p, m);
	}
}
