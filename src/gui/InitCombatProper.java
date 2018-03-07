package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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

	public InitCombatProper(Player p, Monster m, JFrame jf) {
		this.running = true;
		this.jf = jf;
		jf.setEnabled(false);
		this.jd = new JDialog(jf);
		this.m = m;
		this.p = p;
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

	private void createConsumables() {
		System.out.println("started creating consumables");
		this.jd.getContentPane().removeAll();
		this.jd.setVisible(true);
		JPanel selectConsumable = new JPanel();
		ArrayList<JButton> buttons = createButtons(getConsumableNames());
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
	
	private void createAttacks() {
		
	}

	public ArrayList<JButton> createButtons(ArrayList<String> names) {
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

	public ArrayList<String> getSpellNames() {
		ArrayList<String> spellnames = new ArrayList<>();
		for (combat.Attack a : this.p.getSpellbook()) {
			spellnames.add(a.getName());
		}
		return spellnames;
	}

	public ArrayList<String> getAttackNames() {
		ArrayList<String> attacks = new ArrayList<>();
		for (combat.Attack a : this.p.getMovelist()) {
			attacks.add(a.getName());
		}
		return attacks;
	}

	public ArrayList<String> getConsumableNames() {
		ArrayList<String> consumables = new ArrayList<>();
		for (Consumable c : this.p.getInventory()) {
			consumables.add(c.getConsumableName());
		}
		return consumables;
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

	/*
	 * GETTERS AND SETTERS END
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("ActionCommand: " + e.getActionCommand());
		if ("CONSUMABLES".equals(e.getActionCommand())) {
			createConsumables();
		} else if ("ATTACK".equals(e.getActionCommand())) {
			createAttacks();
		} else if ("RETURN".equals(e.getActionCommand())) {
			jd.getContentPane().removeAll();
			createMain();
		} else {
		}
		for (int i = 0; i < this.p.getInventory().size(); i++) {
			if (e.getActionCommand().equals(this.p.getInventory().get(i).getConsumableName())) {
				this.p.useItem(i);
				jd.getContentPane().removeAll();
				createMain();
			}
		}
	}
}