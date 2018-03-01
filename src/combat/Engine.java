/*
 * TODO: List of monsters, list of available spells and attacks, random Monster spawn method to World.class
 */

package combat;

import world.World;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
	
	public static void main (String[] args) {
		Random r = new Random();
		World testi = new World(150, 150);
		Player p = new Player(25, "Kaitsu", 10, 10, 20);
		p.setLocation(testi.getStart()[0], testi.getStart()[1]);
		Scanner sc = new Scanner(System.in);
		ArrayList<Monster> monsters = new ArrayList<>();
		Monster m = new Monster(50, "Spurgu", 5, 5, 5, 5);
		monsters.add(m);
		
		while (testi.getGoal()[0] != p.getLocation()[0] && testi.getGoal()[1] != p.getLocation()[1] && p.getHP() > 0) {
			testi.testPopulate(p);
			String key = sc.nextLine();
			System.out.println(key);
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
			
			if (testi.getTile(p.getY(), p.getX()).hasMonster()) {
				CombatEngine.main(p, monsters.get(r.nextInt((monsters.size()) + 1)));
			}
		}
		sc.close();
	}
}
