package combat;

import java.util.Scanner;
import java.util.Random;

public class CombatEngine {

	public static void combat(Player p, Monster m) {
		Random r = new Random();
		Scanner sc = new Scanner(System.in);
		int turn = 1;
		System.out.println("A wild " + m.getName() + " appears!");
		while (p.getHP() > 0 && m.getHP() > 0) {
			System.out.println();
			System.out.println("Turn: " + turn);
			System.out.println("Your opponent: " + "\n");
			printStats(m);
			System.out.println("You: " + "\n");
			printStats(p);
			System.out.println("");
			System.out.println("1. CONSUMABLES" + "\n2. ATTACK");
			int n = sc.nextInt();
			if (n > 2) {
				System.out.println("Invalid selection! Please reselect");
				n = sc.nextInt();
				continue;
			}
			
			switch (n) {
			case 1:
				p.listInventory();
				int a = sc.nextInt();
				
				if (a == p.getInventoryLength()) {
					continue;
				} else {
					p.getItem(a).useConsumable(p);
					break;
				}
			case 2:
				while (true) {
					System.out.flush();
					System.out.println("1: PHYSICAL \n2: MAGICAL \n3: return;");
					int b = sc.nextInt();
					switch (b) {
					case 1:
						p.listMoveList();
						b = sc.nextInt();
						
						if (b == p.getMoveListLength()) {
							continue;
						} else {
							p.DealDamage(m, p.getMove(b));
							break;
						}
					case 2:
						p.listSpellbook();
						int c = sc.nextInt();
						
						if (c == p.getMoveListLength()) {
							continue;
						} else {
							try {
								if (p.getMana() < p.getSpell(c).getMana()) {
									throw new NotEnoughMana("Not enough mana!");
								} else {
									p.DealDamage(m, p.getSpell(c));
									break;
								}
							} catch (NotEnoughMana e) {
								e.getMessage();
								continue;
							}
						}
					case 3:
						break;
					}
					break;
				}
			}
			int j = r.nextInt(m.getMoveListLength());
			m.DealDamage(p, m.getMove(j));
			turn++;
		}
		if (m.getHP() <= 0) {
			System.out.println(p.getName() + " defeated " + m.getName() + "!");
		} else {
			System.out.println("You didn't make it out of R�nt�m�ki...");
		}
		
	}

	private static void printStats(Creature c) {
		System.out.println(c.getName() + "'s Level: " + c.getLevel());
		System.out.println("HP: " + c.getHP() + "/" + c.getMaxHP());
		System.out.println("STR: " + c.getStr());
		System.out.println("MAGIC: " + c.getMagic());
		System.out.println("MANA: " + c.getMana() + "/" + c.getMaxMana());
		System.out.println("DEF: " + c.getDf());
	}
}
