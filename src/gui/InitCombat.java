package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import combat.*;
/**
 * Class for initiating a new combat window
 */
public class InitCombat implements ActionListener {
	private Monster m;
	private Player p;
	private JDialog jd;
	/**
	 * True, if InitCombatProper is still running.
	 */
	private boolean running;
	private JFrame jf;
	/**
	 * Displays player related information.
	 */
	private JTextArea playerText = new JTextArea();
	/**
	 * Displays enemy related information.
	 */
	private JTextArea enemyText = new JTextArea();
	private ArrayList<Consumable> loot;
	/**
	 * @param p Player fighting
	 * @param m Monster fighting
	 * @param jf Games JFrame object to be passed to create new dialogs and other Swing objects
	 */
	public InitCombat(Player p, Monster m, JFrame jf) {
		this.running = true;
		this.jf = jf;
		jf.setEnabled(false);
		this.jd = new JDialog(jf);
		this.m = m;
		this.p = p;
		this.jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.loot = generateLoot();
	}
	/**
	 * Creates the main combat window with basic selections. Adds swing elements. Invoked by action listener
	 */
	public void createMain() {
		this.jd.setSize(640, 480);
		this.jd.setTitle("COMBAT");
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
		this.playerText.setText(this.p.getName() +" Lvl: " +  this.p.getLevel() + "\n HP: " + this.p.getHP() + "/" + this.p.getMaxHP() + "\n Mana: " + this.p.getMana() + "/" + this.p.getMaxMana());
		this.enemyText.setText(this.m.getName() +" Lvl: " +  this.m.getLevel() + "\n HP:" + this.m.getHP() + "/" + this.m.getMaxHP()+ "\n Mana: " + this.m.getMana() + "/" + this.m.getMaxMana());
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
	/**
	 * Auto-generates the usable consumables menu and its buttons based on the players inventory. Invoked by action listener
	 */
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
	/**
	 * Creates the attack type selection menu, invoked by ActionListener
	 */
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
	/**
	 * Auto-generates buttons for physical attacks, invoked by ActionListener
	 */
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
	/**
	 * Auto-generates buttons for magical attacks, invoked by ActionListener
	 */
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
	/**
	 * Auto-generates an ArrayList<JButton> to be used in other menu creators
	 * @param names ArrayList<String> of names to be used for button titles and ActionEvent.ActionCommand
	 * @return ArrayList<JButton> for generating menu objects.
	 */
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
	/**
	 * Separate button generation method for loot window, sets a different ActionListener to the buttons, which is used only in looting situations to avoid unwanted side-effects.
	 * @param names ArrayList<String> of names to be used for button titles and ActionEvent.ActionCommand
	 * @return ArrayList<JButton> of JButtons for generating menu objects.
	 */
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
	/**
	 * a void method invoked after every attack or consumable use, to check if the player or the monster has been killed, and act according to the situation.
	 */
	public void stillAlive() {
		if (this.p.getHP() > 0 && this.m.getHP() > 0) {
			monsterAttack();
		} else if (this.p.getHP() > 0 && this.m.getHP() <= 0 && this.loot.size() > 0) {
			this.p.addExp(this.m.getHP());
			this.p.CheckLevelUp();
			createLootMenu();
		} else {
			this.p.addExp(this.m.getHP());
			this.p.CheckLevelUp();
			kill();
		}
	}
	
	/**
	 * kills the window and sets its running variable to false, to notify the main thread that combat has ended.
	 */
	public void kill() {
		this.running = false;
		this.jf.setEnabled(true);
		this.jd.dispatchEvent(new WindowEvent(this.jd, WindowEvent.WINDOW_CLOSING));
	}
	/**
	 * Auto-generates a loot menu based on generated loot(if any available)
	 */
	public void createLootMenu() {
		this.jd.setTitle("LOOT");
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
	/**
	 * Used for generating loot based on the monsters level
	 * @return ArrayList<Consumable> loot containing the items rewarded as loot.
	 */
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
	/*
	 * Helper methods for parsing names for different player or loot related objects 
	 * STARTS HERE
	 */
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
	
	/*
	 *  ENDS HERE
	 */
	/**
	 * Select the attack best suit for the scenario, utilizes combat.Monster.selectAttack
	 */
	private void monsterAttack() {
		this.m.DealDamage(this.p, this.m.selectAttack(this.p));
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
	/**
	 * @return True if InitCombat is still running
	 */
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

	public JTextArea getPlayerText() {
		return playerText;
	}

	public void setPlayerText(JTextArea playerText) {
		this.playerText = playerText;
	}

	public JTextArea getEnemyText() {
		return enemyText;
	}

	public void setEnemyText(JTextArea enemyText) {
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

	/**
	 * Action listener for everything else, except looting. Looting has its own ActionListener due to risk of conflict with using items.
	 * After invoking a action in src.combat, it removes all objects from the JDialog and invokes a generator to create new ones, thus "showing" a new submenu.
	 * Not best practice or industry standard.
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
				/*
				 * casts the action event to a JButton, to get the mnemonic given to it during autogeneration, to use the corresponding item in players inventory.
				 * using objects causes all instances of the selected item to be used, instead of the one preferred
				 */
				if (e.getActionCommand().equals(this.p.getInventory().get(i).getConsumableName())) {
				Component comp = (Component) e.getSource();
				JButton b = (JButton) comp;
				if (b.getMnemonic() == i) {
					this.p.useItem(i);
					jd.getContentPane().removeAll();
					createMain();
					stillAlive();
				}
				}
			}
			for (int i = 0; i < this.p.getSpellbook().size(); i++) {
				Attack a = this.p.getSpellbook().get(i);
				/*
				 *  iterates all attacks in the spellbook until a corresponding one has been found, then deals damage.
				 */
				if (e.getActionCommand().equals(a.getName())) {
					this.p.DealDamage(this.m, a);
					jd.getContentPane().removeAll();
					createMain();
					stillAlive();
				}
			}
			for (int i = 0; i < this.p.getMovelist().size(); i++) {
				Attack a = this.p.getMovelist().get(i);
				/*
				 * same as above, but for physical attacks.
				 */
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
/**
 *	Listens for loot related buttons and acts accordingly. 
 */
class Loot implements ActionListener {

	InitCombat ic;
	/**
	 * @param ic the InitCombat objects that invoked the ActionEvent
	 */
	public Loot(InitCombat ic) {
		this.ic = ic;
	}
	//very similar to the one above
	@Override
	public void actionPerformed(ActionEvent e) {
		this.ic.getP().listInventory();
		if ("DISCARD ALL".equals(e.getActionCommand())) {
			this.ic.setRunning(false);
			this.ic.getJf().setEnabled(true);
			this.ic.getJd().dispatchEvent(new WindowEvent(this.ic.getJd(), WindowEvent.WINDOW_CLOSING));
		}
		ArrayList<Consumable> temp = this.ic.getLoot();
		if (temp.size() == 0) {
			this.ic.setRunning(false);
			this.ic.getJf().setEnabled(true);
			this.ic.getJd().dispatchEvent(new WindowEvent(this.ic.getJd(), WindowEvent.WINDOW_CLOSING));
		}
		for (int i = 0; i < temp.size(); i++) {
			if (e.getActionCommand().equals(temp.get(i).getConsumableName())) {
				this.ic.getP().addItem(temp.get(i));
				this.ic.getLoot().remove(temp.get(i));
				System.out.println("After removing item from loot player inventory contains: ");
				this.ic.getP().listInventory();
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