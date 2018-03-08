package combat;

import world.World;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Random;
/**
 * Engine for playing in text-only environment.
 */
public class Engine {

	public static void main (String[] args) {
		Random r = new Random();
		World testi = new World(150, 150);
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		p.setLocation(testi.getStart()[0], testi.getStart()[1]);
		Scanner s = new Scanner(System.in);
		MonsterGenerator mg = new MonsterGenerator();
		SpellIDList tome = new SpellIDList();
		AttackIDList atk = new AttackIDList();
		p.addAttack(atk.getAttack(r.nextInt(2)));
		p.addSpell(tome.getSpell(r.nextInt(2)));
		
		while (testi.getGoal()[0] != p.getLocation()[0] && testi.getGoal()[1] != p.getLocation()[1] && p.getHP() > 0) {
			testi.render(p);
			//testi.printWorld();
			if (!s.hasNext()) {
				s.close();
				s = new Scanner(System.in);
			}
			try {
				String key = s.nextLine();
				if (key.equals("s")) {
					p.moveDown(testi);
				} else if (key.equals("w")) {
					p.moveUp(testi); 
				} else if (key.equals("a")) {
					p.moveLeft(testi);
				} else if (key.equals("d")) {
					p.moveRight(testi);
				} else {
					System.out.println("Invalid selection!");
					continue;
				}
			} catch (NoSuchElementException e) {
				System.out.println(e.getMessage());
				System.out.println(e.getCause());
				continue;
			}

			if (testi.getTile(p.getY(), p.getX()).hasMonster()) {
					Monster m = mg.getMonster(0, r.nextInt(p.getLevel() + 3) + 1);
					CombatEngine.combat(p, m);
					continue;
				}
			}
		s.close();
		}

}
