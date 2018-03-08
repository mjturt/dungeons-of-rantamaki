package combat;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class for combat simulation in the console environment. Has multiple faults, is unmaintained and unsupported.
 *
 */
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

				if (a >= p.getInventoryLength()) {
					continue;
				} else {
					p.useItem(a);
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

						if (b >= p.getMoveListLength()) {
							continue;
						} else {
							p.DealDamage(m, p.getMove(b));
							break;
						}
					case 2:
						p.listSpellbook();
						int c = sc.nextInt();

						if (c >= p.getMoveListLength()) {
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
			if (m.getHP() > 0) {
				m.DealDamage(p, m.selectAttack(p));
			}
			turn++;
		}
		if (m.getHP() <= 0) {
			System.out.println(p.getName() + " defeated " + m.getName() + "!");
			p.addExp(m.getMaxHP());
			p.CheckLevelUp();
			ArrayList<Consumable> tmp = generateLoot(m);
			if (tmp.size() > 0) {
				while (true) {
					if (tmp.size() <= 0) {
						break;
					}
					printLoot(tmp);
					int n = sc.nextInt();
					if (n >= tmp.size()) {
						break;
					} else {
						p.addItem(tmp.get(n));
						tmp.remove(n);
						continue;
					}
				}
			} else {
				System.out.println("Nothing to loot!");
			}
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

	private static ArrayList<Consumable> generateLoot(Monster m) {
		ArrayList<Consumable> loot = new ArrayList<>();
		ItemGenerator lst = new ItemGenerator();
		Random r = new Random();
		int maxLoot = m.getLevel() / 10;
		if (maxLoot < 1) {
			maxLoot = r.nextInt(2);
		}
		if (maxLoot > 0) {
			for (int i = 0; i < maxLoot; i++) {
				loot.add(lst.getItem(r.nextInt(lst.getListSize())));
			}
		}
		return loot;
	}

	private static void printLoot(ArrayList<Consumable> loot) {
		int i = 0;
		for (Consumable c : loot) {
			System.out.println(i + ": " + c.getConsumableName());
			i++;
		}
		System.out.println(i + ": DISCARD ALL");
	}

}
