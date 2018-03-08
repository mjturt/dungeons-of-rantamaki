package combat;

import java.util.Random;

import combat.CombatEngine;

public class CombatTest {
	public static void main(String[] args) {
		Random r = new Random();
		AttackIDList lista = new AttackIDList();
		SpellIDList grimoire = new SpellIDList();
		MonsterGenerator monsu = new MonsterGenerator();
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		p.addExp(100);
		p.CheckLevelUp();
		Monster m = monsu.getMonster(r.nextInt(monsu.getMonsterListSize()+1), p.getLevel());
		p.addAttack(lista.getAttack(0));
		p.addSpell(grimoire.getSpell(0));
		
		
		
		CombatEngine.combat(p, m);
	}
}
