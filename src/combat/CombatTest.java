package combat;

import java.util.Random;

import combat.CombatEngine;

public class CombatTest {
	public static void main(String[] args) {
		final Random r = new Random();
		final AttackIDList lista = new AttackIDList();
		final SpellIDList grimoire = new SpellIDList();
		final MonsterGenerator monsu = new MonsterGenerator();
		final Player p = new Player(25, "Kaitsu", 10, 10, 20);
		p.addExp(100);
		p.CheckLevelUp();
		final Monster m = monsu.getMonster(r.nextInt(monsu.getMonsterListSize()+1), p.getLevel());
		p.addAttack(lista.getAttack(0));
		p.addSpell(grimoire.getSpell(0));
		
		
		
		CombatEngine.combat(p, m);
	}
}
