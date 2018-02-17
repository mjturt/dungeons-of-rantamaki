package dungeons_of_rantamaki;
public class CombatTest {
	public static void main(String[] args) {
		Player p = new Player(25, "Kaitsu", 10, 10, 10);
		Monster m = new Monster(50, "Spurgu", 5, 5, 5, 5);
		Attack a = new Attack(AttackType.PHYSICAL, 100, "Bash");
		int i=1;
		while(p.getHP() > 0 && m.getHP() > 0) {
			System.out.println("Turn: " + i);
			System.out.println(p.getName() +" HP: " + p.getHP() + "/" + p.getMaxHP());
			System.out.println(m.getName() +" HP: " + m.getHP() + "/" + m.getMaxHP());
			System.out.println("==============================================");
			m.DealDamage(p, a);
			System.out.println(p.getName() + " used " + a.getName() + "!");
			p.DealDamage(m, a);
			System.out.println(m.getName() + " used " + a.getName() + "!");
			i++;
			System.out.println("==============================================");
		}
		if(p.getHP() < 0) {
			System.out.println("You're dead, kiddo");
		}
		else if (m.getHP() < 0) {
			System.out.println("You vanquised " + m.getName());
			p.addExp(m.getExpGain());
			System.out.println(m.getExpGain() + " points of experience awarded!");
		}
		System.out.println(p.getName() + "'s EXP: " + p.getExp());
			
	}
}
