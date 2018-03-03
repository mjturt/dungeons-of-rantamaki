/*
 * TODO: List of monsters, list of available spells and attacks, method for recreating slain monsters and also scaling them according to player level
 */

package combat;

import world.World;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Engine {
	
	public static void main (String[] args) {
		World testi = new World(150, 150);
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		p.setLocation(testi.getStart()[0], testi.getStart()[1]);
		Scanner s = new Scanner(System.in);
		ArrayList<Monster> monsters = new ArrayList<>();
		Monster m = new Monster(50, "Spurgu", 5, 5, 5);
		monsters.add(m);
		Attack a = new Attack("Bash", AttackType.PHYSICAL, 50);
		Attack b = new Attack( "Fireball", AttackType.MAGICAL, 100, 5);
		Consumable jallu = new Consumable("Jallu", 10, 10);

		p.addAttack(a);
		p.addSpell(b);
		m.addAttack(a);
		p.addItem(jallu);
		
		while (testi.getGoal()[0] != p.getLocation()[0] && testi.getGoal()[1] != p.getLocation()[1] && p.getHP() > 0) {
			testi.testPopulate(p);
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
					CombatEngine.combat(p, m);
					m = new Monster(50, "Spurgu", 5, 5, 5);
					m.addAttack(a);
					continue;
				}
			}
		s.close();
		}
		
	}
