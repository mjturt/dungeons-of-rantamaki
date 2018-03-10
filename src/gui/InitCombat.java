package gui;

/*
 * TODO: Restructure createMain() to a more readable form!
 */
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import combat.*;

/**
 * Class for initiating a new combat window
 */
public class InitCombat implements ActionListener, Runnable {
	private Monster m;
	private Player p;
	private JDialog jd;
	private boolean gameOver = false;
	/**
	 * True, if InitCombat is still running.
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
	 * Initiates a new combat event
	 * 
	 * @param p
	 *            Player fighting
	 * @param m
	 *            Monster fighting
	 * @param jf
	 *            Games JFrame object to be passed to create new dialogs and other
	 *            Swing objects
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
		this.jd.setModal(true);
	}

	/**
	 * Creates the main combat window with basic selections. Adds swing elements.
	 * Invoked by action listener
	 * @throws IOException 
	 */
	/*
	 * Could use some restructuring and helper methods for more general purpose use.
	 */
	protected void createMain() {
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
		this.playerText.setText(this.p.getName() + " Lvl: " + this.p.getLevel() + "\n HP: " + this.p.getHP() + "/"
				+ this.p.getMaxHP() + "\n Mana: " + this.p.getMana() + "/" + this.p.getMaxMana());
		this.enemyText.setText(this.m.getName() + " Lvl: " + this.m.getLevel() + "\n HP:" + this.m.getHP() + "/"
				+ this.m.getMaxHP() + "\n Mana: " + this.m.getMana() + "/" + this.m.getMaxMana());
		selectAction.add(cons);
		selectAction.add(att);
		information.add(this.playerText);
		information.add(this.enemyText);
		selectAction.setSize(640, 480 / 2);
		this.jd.add(selectAction);
		this.jd.add(information);
		JScrollPane sp = new JScrollPane();
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.jd.setSize(640, 480);
		jd.setLayout(new FlowLayout());
		this.jd.pack();
		this.jd.setLocationRelativeTo(this.jf);
		this.jd.setEnabled(true);
		this.jd.setVisible(true);
		this.jd.repaint();
		System.out.println("Is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());
	}

	/**
	 * Auto-generates the usable consumables menu and its buttons based on the
	 * players inventory. Invoked by action listener
	 */
	private void createConsumablesMenu() {
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getInventoryItemNames(), new UseItem(this));
		for (int i = 0; i < buttons.size(); i++) {

			selectConsumable.add(buttons.get(i));
		}
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
		System.out.println("Is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());
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
		System.out.println("Is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());
	}

	/**
	 * Auto-generates buttons for physical attacks, invoked by ActionListener
	 */
	protected void createPhysicalsMenu() {
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getAttackNames(), new Physicals(this));
		for (int i = 0; i < buttons.size(); i++) {
			selectConsumable.add(buttons.get(i));
		}
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
		System.out.println("Is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());

	}

	/**
	 * Auto-generates buttons for magical attacks, invoked by ActionListener
	 */
	protected void createMagicalsMenu() {
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getSpellNames(), new Spells(this));
		for (int i = 0; i < buttons.size(); i++) {
			selectConsumable.add(buttons.get(i));
		}
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
		System.out.println("Is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());

	}

	/**
	 * Auto-generates a loot menu based on generated loot(if any available)
	 */
	protected void createLootMenu() {
		this.jd.setTitle("LOOT");
		if (this.loot.size() == 0) {
			kill();
		}
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getLootNames(), new Loot(this));
		for (int i = 0; i < buttons.size(); i++) {
			selectConsumable.add(buttons.get(i));
		}
		this.playerText.setSize(64, 64);
		this.enemyText.setSize(64, 64);
		this.jd.getContentPane().add(selectConsumable);
		this.jd.setLayout(new FlowLayout());
		this.jd.revalidate();
		this.jd.repaint();
		this.jd.setVisible(true);
		System.out.println("Is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());
	}

	/**
	 * Method for generating JButtons for combat menus
	 * 
	 * @param names
	 *            Button names, parsed from combat.Attack objects with helper
	 *            methods.
	 * @param al
	 *            ActionListener to set for the button set.
	 * @return ArrayList of JButton objects to be used in menu generation.
	 */
	private ArrayList<JButton> createButtons(ArrayList<String> names, ActionListener al) {
		ArrayList<JButton> buttons = new ArrayList<>();
		int n = 0;
		for (int i = 0; i < names.size(); i++) {
			JButton b = new JButton(names.get(i));
			b.setName(names.get(i));
			b.addActionListener(al);
			b.setActionCommand(names.get(i));
			b.setSize(64, 32);
			b.setMnemonic(i);
			buttons.add(b);
			n = i;
		}
		if (al.getClass() == Loot.class) {
			JButton disc = new JButton("DISCARD ALL");
			disc.setName("DISCARD ALL");
			disc.addActionListener(al);
			disc.setActionCommand("DISCARD ALL");
			disc.setSize(64, 32);
			disc.setMaximumSize(new Dimension(64, 32));
			disc.setMnemonic(n);
			buttons.add(disc);
		} else {
			JButton ret = new JButton("RETURN");
			ret.setName("RETURN");
			ret.addActionListener(al);
			ret.setActionCommand("RETURN");
			ret.setSize(64, 32);
			ret.setMaximumSize(new Dimension(64, 32));
			ret.setMnemonic(n);
			buttons.add(ret);
		}
		return buttons;
	}

	/**
	 * a void method invoked after every attack or consumable use, to check if the
	 * player or the monster has been killed, and act according to the situation.
	 */
	public void stillAlive() {
		if (this.p.getHP() > 0 && this.m.getHP() > 0) {
			this.m.DealDamage(this.p, this.m.selectAttack(this.p));
			if (this.p.getHP() > 0) {
				createMain();
			} else {
				this.gameOver = true;
				kill();
			}
		} else if (this.p.getHP() > 0 && this.m.getHP() <= 0 && this.loot.size() > 0) {
			this.p.addExp(this.m.getHP());
			this.p.CheckLevelUp();
			createLootMenu();
		} else if (this.p.getHP() <= 0) {
			this.gameOver = true;
			kill();
		} else {
			this.p.addExp(this.m.getHP());
			this.p.CheckLevelUp();
			kill();
		}
	}

	/**
	 * kills the window and sets its running variable to false, to notify the main
	 * thread that combat has ended.
	 */
	public void kill() {
		this.running = false;
		this.jf.setEnabled(true);
		this.jd.dispatchEvent(new WindowEvent(this.jd, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Used for generating loot based on the monsters level
	 * 
	 * @return ArrayList<Consumable> loot containing the items rewarded as loot.
	 */
	private ArrayList<Consumable> generateLoot() {
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
	/*
	 * Helper methods for parsing names for different player or loot related objects
	 * STARTS HERE
	 */

	/**
	 * Auto-generates ArrayList of player spell names, used in JButton
	 * auto-generation
	 * 
	 * @return ArrayList of spell names
	 */
	private ArrayList<String> getSpellNames() {
		ArrayList<String> spellnames = new ArrayList<>();
		for (combat.Attack a : this.p.getSpellbook()) {
			spellnames.add(a.getName());
		}
		return spellnames;
	}

	/**
	 * @return list of Consumable (loot rewards) names, used in JButton
	 *         auto-generation
	 */
	private ArrayList<String> getLootNames() {
		ArrayList<String> itemNames = new ArrayList<>();
		for (Consumable c : this.loot) {
			itemNames.add(c.getConsumableName());
		}
		return itemNames;
	}

	/**
	 * Auto-generates ArrayList of player attack names, used in JButton
	 * auto-generation
	 * 
	 * @return ArrayList of Attack names
	 */
	private ArrayList<String> getAttackNames() {
		ArrayList<String> attacks = new ArrayList<>();
		for (combat.Attack a : this.p.getMovelist()) {
			attacks.add(a.getName());
		}
		return attacks;
	}

	/**
	 * Auto-generates ArrayList that contains inventory items names, used in JButton
	 * auto-generation
	 * 
	 * @return ArrayList of consumable names
	 */
	private ArrayList<String> getInventoryItemNames() {
		ArrayList<String> consumables = new ArrayList<>();
		for (Consumable c : this.p.getInventory()) {
			consumables.add(c.getConsumableName());
		}
		return consumables;
	}

	/*
	 * ENDS HERE
	 */

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

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/*
	 * GETTERS AND SETTERS END
	 */
	/**
	 * Acts on action events in the main menu
	 * 
	 * @param e
	 *            ActionEvent to act on
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
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
		}
	}

	@Override
	public void run() {
		createMain();
	}
}

/**
 * Listens for loot related buttons and acts accordingly.
 */
class Loot implements ActionListener {

	InitCombat ic;
	Player player;

	/**
	 * @param ic
	 *            the InitCombat object that invoked the ActionEvent
	 */
	public Loot(InitCombat ic) {
		this.ic = ic;
		this.player = ic.getP();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("DISCARD ALL") || this.ic.getLoot().size() == 0) {
			this.ic.kill();
		} else {
			for (int i = 0; i < this.ic.getLoot().size(); i++) {
				if (e.getActionCommand().equals(this.ic.getLoot().get(i).getConsumableName())) {
					this.player.addItem(this.ic.getLoot().get(i));
					this.ic.getLoot().remove(i);
					this.ic.getJd().getContentPane().removeAll();
					this.ic.stillAlive();
				}
			}
		}
	}
}

/**
 * Listens for spell related button actions and acts accordingly.
 *
 */
class Spells implements ActionListener {

	InitCombat ic;
	Player player;
	Monster monster;

	public Spells(InitCombat ic) {
		this.ic = ic;
		this.player = ic.getP();
		this.monster = ic.getM();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("RETURN")) {
			this.ic.getJd().getContentPane().removeAll();
			this.ic.createMain();
		} else {
			for (Attack a : this.ic.getP().getSpellbook()) {
				if (e.getActionCommand().equals(a.getName())) {
					if (this.player.ManaCheck(a)) {
						this.ic.getJd().getContentPane().removeAll();
						this.player.DealDamage(this.monster, a);
						this.ic.stillAlive();
					} else {
						System.out.println("Not enough mana! Spell cast unsuccesfull");
						this.ic.createMagicalsMenu();
					}
				}
			}
		}
	}
}

/**
 * Listens for Physical attack related button actions and acts accordingly.
 *
 */
class Physicals implements ActionListener {

	InitCombat ic;
	Player player;
	Monster monster;

	public Physicals(InitCombat ic) {
		this.ic = ic;
		this.player = ic.getP();
		this.monster = ic.getM();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("RETURN")) {
			this.ic.getJd().getContentPane().removeAll();
			this.ic.createMain();
		} else {
			for (Attack a : this.player.getMovelist()) {
				if (e.getActionCommand().equals(a.getName())) {
					this.ic.getJd().getContentPane().removeAll();
					this.player.DealDamage(this.monster, a);
					this.ic.stillAlive();
				}
			}
		}
	}
}

/**
 * Listens to Consumable use actions and acts accordingly
 *
 */
class UseItem implements ActionListener {

	InitCombat ic;
	Player player;
	Monster monster;

	public UseItem(InitCombat ic) {
		this.ic = ic;
		this.player = ic.getP();
		this.monster = ic.getM();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("RETURN")) {
			this.ic.getJd().getContentPane().removeAll();
			this.ic.createMain();
		} else {
			for (int i = 0; i < this.player.getInventory().size(); i++) {
				Component comp = (Component) e.getSource();
				JButton b = (JButton) comp;
				if (b.getMnemonic() == i) {
					this.ic.getJd().getContentPane().removeAll();
					this.player.useItem(this.player.getItem(i));
					this.ic.stillAlive();
				}
			}
		}
	}
}