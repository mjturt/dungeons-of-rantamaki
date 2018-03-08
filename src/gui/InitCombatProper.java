package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import combat.*;

@SuppressWarnings("serial")
public class InitCombatProper extends JDialog implements ActionListener {
	private Monster m;
	private Player p;
	private JDialog jd;
	private boolean running;
	private JFrame jf;
	private JTextField playerText = new JTextField();
	private JTextField enemyText = new JTextField();
	private ArrayList<Consumable> loot;

	public InitCombatProper(Player p, Monster m, JFrame jf) {
		this.running = true;
		this.jf = jf;
		jf.setEnabled(false);
		this.jd = new JDialog(jf);
		this.m = m;
		this.p = p;
		this.jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.loot = generateLoot();
	}

	public void createMain() {
		this.jd.setSize(640, 480);
		JButton cons = new JButton("CONSUMABLES");
		cons.setActionCommand("CONSUMABLES");
		cons.addActionListener(this);
		cons.setSize(64, 32);
		JButton att = new JButton("ATTACK");
		att.setActionCommand("ATTACK");
		att.addActionListener(this);
		att.setSize(64, 32);
		JPanel selectAction = new JPanel();
		JPanel information = new JPanel();
		information.setSize(128, 64);
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.playerText.setText(this.p.getName() + "\n" + this.p.getHP() + "/" + this.p.getMaxHP());
		this.enemyText.setText(this.m.getName() + "\n" + this.m.getHP() + "/" + this.m.getMaxHP());
		selectAction.add(cons);
		selectAction.add(att);
		information.add(this.playerText);
		information.add(this.enemyText);
		selectAction.setSize(640, 480 / 2);
		this.jd.add(selectAction);
		this.jd.add(information);
		this.jd.setSize(640, 480);
		// jd.setPreferredSize(new Dimension(640, 480));
		jd.setLayout(new FlowLayout());
		this.jd.pack();
		this.jd.setEnabled(true);
		this.jd.setVisible(true);
		this.jd.revalidate();
		this.jd.repaint();
	}

	private void createConsumablesMenu() {
		System.out.println("started creating consumables");
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getInventoryItemNames());
		for (int i = 0; i < buttons.size(); i++) {
			System.out.println("creating...");
			selectConsumable.add(buttons.get(i));
			System.out.println("Added: " + buttons.get(i).getName());
		}
		System.out.println("finished adding buttons");
		JPanel information = new JPanel();
		information.add(this.playerText);
		information.add(this.enemyText);
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.jd.getContentPane().add(selectConsumable);
		this.jd.getContentPane().add(information);
		this.jd.setLayout(new FlowLayout());
		this.jd.revalidate();
		this.jd.repaint();
		this.jd.setVisible(true);
	}

	private void createAttacksMenu() {
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectAttackType = new JPanel();
		JPanel information = new JPanel();
		JButton mag = new JButton("MAGICAL");
		mag.setActionCommand("MAGICAL");
		mag.setSize(64, 32);
		mag.addActionListener(this);
		JButton phy = new JButton("PHYSICAL");
		phy.setActionCommand("PHYSICAL");
		phy.setSize(64, 32);
		phy.addActionListener(this);
		information.add(this.playerText);
		information.add(this.enemyText);
		selectAttackType.add(mag);
		selectAttackType.add(phy);
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.jd.getContentPane().add(selectAttackType);
		this.jd.getContentPane().add(information);
		this.jd.setLayout(new FlowLayout());
		this.jd.revalidate();
		this.jd.repaint();
		this.jd.setVisible(true);
	}

	private void createPhysicalsMenu() {
		System.out.println("started creating physical attacks");
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getAttackNames());
		for (int i = 0; i < buttons.size(); i++) {
			System.out.println("creating...");
			selectConsumable.add(buttons.get(i));
			System.out.println("Added: " + buttons.get(i).getName());
		}
		System.out.println("finished adding buttons");
		JPanel information = new JPanel();
		information.add(this.playerText);
		information.add(this.enemyText);
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.jd.getContentPane().add(selectConsumable);
		this.jd.getContentPane().add(information);
		this.jd.setLayout(new FlowLayout());
		this.jd.revalidate();
		this.jd.repaint();
		this.jd.setVisible(true);
	}

	private void createMagicalsMenu() {
		System.out.println("started creating magical attacks!");
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getSpellNames());
		for (int i = 0; i < buttons.size(); i++) {
			System.out.println("creating...");
			selectConsumable.add(buttons.get(i));
			System.out.println("Added: " + buttons.get(i).getName());
		}
		System.out.println("finished adding buttons");
		JPanel information = new JPanel();
		information.add(this.playerText);
		information.add(this.enemyText);
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.jd.getContentPane().add(selectConsumable);
		this.jd.getContentPane().add(information);
		this.jd.setLayout(new FlowLayout());
		this.jd.revalidate();
		this.jd.repaint();
		this.jd.setVisible(true);
	}

	private ArrayList<JButton> createButtons(ArrayList<String> names) {
		ArrayList<JButton> buttons = new ArrayList<>();
		int n = 0;
		for (int i = 0; i < names.size(); i++) {
			JButton b = new JButton(names.get(i));
			b.setName(names.get(i));
			b.addActionListener(this);
			b.setActionCommand(names.get(i));
			b.setSize(64, 32);
			b.setMnemonic(i);
			System.out.println(b.getActionCommand());
			buttons.add(b);
			n = i;
		}
		JButton ret = new JButton("RETURN");
		ret.setName("RETURN");
		ret.addActionListener(this);
		ret.setActionCommand("RETURN");
		ret.setSize(64, 32);
		ret.setMaximumSize(new Dimension(64, 32));
		ret.setMnemonic(n);
		buttons.add(ret);
		return buttons;
	}

	private ArrayList<JButton> createButtonsLoot(ArrayList<String> names) {
		ArrayList<JButton> buttons = new ArrayList<>();
		int n = 0;
		for (int i = 0; i < names.size(); i++) {
			JButton b = new JButton(names.get(i));
			b.setName(names.get(i));
			b.addActionListener(new Loot(this));
			b.setActionCommand(names.get(i));
			b.setSize(64, 32);
			b.setMnemonic(i);
			System.out.println(b.getActionCommand());
			buttons.add(b);
			n = i;
		}
		JButton ret = new JButton("DISCARD ALL");
		ret.setName("DISCARD ALL");
		ret.addActionListener(new Loot(this));
		ret.setActionCommand("DISCARD ALL");
		ret.setSize(64, 32);
		ret.setMaximumSize(new Dimension(64, 32));
		ret.setMnemonic(n);
		buttons.add(ret);
		return buttons;
	}

	public void stillAlive() {
		if (this.p.getHP() > 0 && this.m.getHP() > 0) {
			monsterRandomAttack();
		} else if (this.p.getHP() > 0 && this.m.getHP() <= 0 && this.loot.size() > 0) {
			this.p.addExp(this.m.getHP());
			this.p.LevelUp();
			createLootMenu();
		} else {
			this.p.addExp(this.m.getHP());
			this.p.LevelUp();
			kill();
		}
	}

	public void kill() {
		this.running = false;
		this.jf.setEnabled(true);
		this.jd.dispatchEvent(new WindowEvent(this.jd, WindowEvent.WINDOW_CLOSING));
	}

	public void createLootMenu() {
		if (this.loot.size() == 0) {
			System.out.println("no loot");
			kill();
		}
		System.out.println("started creating loot");
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtonsLoot(getConsumableNames(this.loot));
		for (int i = 0; i < buttons.size(); i++) {
			System.out.println("creating...");
			selectConsumable.add(buttons.get(i));
			System.out.println("Added: " + buttons.get(i).getName());
		}
		System.out.println("finished adding buttons");
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.jd.getContentPane().add(selectConsumable);
		this.jd.setLayout(new FlowLayout());
		this.jd.revalidate();
		this.jd.repaint();
		this.jd.setVisible(true);
	}

	public ArrayList<Consumable> generateLoot() {
		ArrayList<Consumable> loot = new ArrayList<>();
		ItemGenerator lst = new ItemGenerator();
		Random r = new Random();
		int maxLoot = m.getLevel() / 10;
		if (maxLoot < 1) {
			maxLoot = r.nextInt(2);
			System.out.println("MaxLoot: " + maxLoot);
		}
		if (maxLoot > 0) {
			for (int i = 0; i < maxLoot; i++) {
				loot.add(lst.getItem(r.nextInt(lst.getListSize())));
			}
		}
		return loot;
	}

	private ArrayList<String> getSpellNames() {
		ArrayList<String> spellnames = new ArrayList<>();
		for (combat.Attack a : this.p.getSpellbook()) {
			spellnames.add(a.getName());
		}
		return spellnames;
	}

	private ArrayList<String> getConsumableNames(ArrayList<Consumable> list) {
		ArrayList<String> itemNames = new ArrayList<>();
		for (Consumable c : this.loot) {
			itemNames.add(c.getConsumableName());
		}
		return itemNames;
	}

	private ArrayList<String> getAttackNames() {
		ArrayList<String> attacks = new ArrayList<>();
		for (combat.Attack a : this.p.getMovelist()) {
			attacks.add(a.getName());
		}
		return attacks;
	}

	private ArrayList<String> getInventoryItemNames() {
		ArrayList<String> consumables = new ArrayList<>();
		for (Consumable c : this.p.getInventory()) {
			consumables.add(c.getConsumableName());
		}
		return consumables;
	}

	private void monsterRandomAttack() {
		Random r = new Random();
		this.m.DealDamage(this.p, this.m.getMovelist().get(r.nextInt(this.m.getMovelist().size())));
	}

	/*
	 * GETTERS AND SETTERS START
	 */
	public Monster getM() {
		return m;
	}

	public void setM(Monster m) {
		this.m = m;
	}

	public Player getP() {
		return p;
	}

	public void setP(Player p) {
		this.p = p;
	}

	public JDialog getJd() {
		return jd;
	}

	public void setJd(JDialog jd) {
		this.jd = jd;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public JFrame getJf() {
		return jf;
	}

	public void setJf(JFrame jf) {
		this.jf = jf;
	}

	public JTextField getPlayerText() {
		return playerText;
	}

	public void setPlayerText(JTextField playerText) {
		this.playerText = playerText;
	}

	public JTextField getEnemyText() {
		return enemyText;
	}

	public void setEnemyText(JTextField enemyText) {
		this.enemyText = enemyText;
	}

	public ArrayList<Consumable> getLoot() {
		return loot;
	}

	public void setLoot(ArrayList<Consumable> loot) {
		this.loot = loot;
	}

	/*
	 * GETTERS AND SETTERS END
	 */


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ActionCommand: " + e.getActionCommand());
		if ("CONSUMABLES".equals(e.getActionCommand())) {
			createConsumablesMenu();
		} else if ("ATTACK".equals(e.getActionCommand())) {
			createAttacksMenu();
		} else if ("RETURN".equals(e.getActionCommand())) {
			jd.getContentPane().removeAll();
			createMain();
		} else if ("MAGICAL".equals(e.getActionCommand())) {
			createMagicalsMenu();
		} else if ("PHYSICAL".equals(e.getActionCommand())) {
			createPhysicalsMenu();
		} else {
			for (int i = 0; i < this.p.getInventory().size(); i++) {
				if (e.getActionCommand().equals(this.p.getInventory().get(i).getConsumableName())) {
					this.p.useItem(i);
					jd.getContentPane().removeAll();
					createMain();
					stillAlive();
				}
			}
			for (int i = 0; i < this.p.getSpellbook().size(); i++) {
				Attack a = this.p.getSpellbook().get(i);
				if (e.getActionCommand().equals(a.getName())) {
					this.p.DealDamage(this.m, a);
					jd.getContentPane().removeAll();
					createMain();
					stillAlive();
				}
			}
			for (int i = 0; i < this.p.getMovelist().size(); i++) {
				Attack a = this.p.getMovelist().get(i);
				if (e.getActionCommand().equals(a.getName())) {
					this.p.DealDamage(this.m, a);
					jd.getContentPane().removeAll();
					createMain();
					stillAlive();
				}
			}
		}
	}
}

class Loot implements ActionListener {

	InitCombatProper ic;

	public Loot(InitCombatProper ic) {
		this.ic = ic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("DISCARD ALL".equals(e.getActionCommand())) {
			this.ic.stillAlive();
		}
		ArrayList<Consumable> temp = this.ic.getLoot();
		if (temp.size() == 0) {
			this.ic.getJd().dispatchEvent(new WindowEvent(this.ic.getJd(), WindowEvent.WINDOW_CLOSING));
		}
		for (int i = 0; i < temp.size(); i++) {
			if (e.getActionCommand().equals(temp.get(i).getConsumableName())) {
				this.ic.getP().addItem(temp.get(i));
				this.ic.getLoot().remove(temp.get(i));
				this.ic.getJd().getContentPane().removeAll();
				if (temp.size() == 0) {
					this.ic.setRunning(false);
					this.ic.getJf().setEnabled(true);
					this.ic.getJd().dispatchEvent(new WindowEvent(this.ic.getJd(), WindowEvent.WINDOW_CLOSING));
				} else {
					this.ic.createLootMenu();
				}
			}
		}
	}
}