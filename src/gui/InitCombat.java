package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

import combat.Player;
import combat.Monster;
import combat.Creature;

public class InitCombat {

	private static boolean running;
	private static Player p;
	private static combat.Monster m;
	private static ArrayList<combat.Attack> attacks;
	/**
	 * @param h the handler object for getting info on game events
	 * @param o GameObject in question
	 * @param m monster to fight
	 * @param p the player fighting
	 * 
	 * most of the drawing should be done in separate methods by passing the frame to them. 
	 * 
	 * TODO: method for browsing the menus, and actionListener(s) for buttons and corresponding events
	 * to respond clicks on buttons
	 * 
	 */
	public static void main(Handler h, GameObject o, combat.Monster m, combat.Player p) {
		/*////////////////////////////////////////////////////
		///CONFIGURATION OF WINDOW STARTS
		////////////////////////////////////////////////////*/
		attacks = new ArrayList<>();
		for (combat.Attack a : p.getMovelist()) {
			attacks.add(a);
		}
		setPlayer(p);
		setMonster(m);
		running = true;
		JFrame jf = h.getFrame();
		jf.setEnabled(false);
		JDialog jd = new JDialog(jf);
		jd.setSize(640, 480);
		jd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jd.setUndecorated(true);
		JPanel combatButtons = new JPanel();
		JPanel playerInfo = new JPanel();
		JPanel enemyInfo = new JPanel();
		playerInfo.setSize(640/2, 480/2);
		enemyInfo.setSize(640/2, 480/2);
		combatButtons.setSize(640/2, 480);
		combatButtons.setLayout(new GridLayout(0, 1, 10, 10));
		ArrayList<JButton> tmp = createButtons(getSpellNames(p));
		for (JButton b : tmp) {
			combatButtons.add(b);
		}
		combatButtons.setLayout(new GridLayout(5, 5));
		jd.getContentPane().add(combatButtons);
		jd.getContentPane().add(playerInfo);
		jd.getContentPane().add(enemyInfo);
		jd.setVisible(true);
		/*//////////////////////////////////////////////////////
		//CONFIGURATION OF WINDOW ENDS
		//////////////////////////////////////////////////////*/
		try {
			Thread.sleep(4000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<GameObject> iter = h.objects.iterator();
		System.out.println("InitCombat reached HIDE ON CLOSE");
		jd.setVisible(false);
		// to stop ConcurrentModificationError from happening
		while(iter.hasNext()) {
			GameObject temp = iter.next();	
			if (temp.equals(o)) {
				iter.remove();
			}
		}
		jf.setEnabled(true);
		stop();
		System.out.println("Init combat reached it's end");
		jd.dispatchEvent(new WindowEvent(jd, WindowEvent.WINDOW_CLOSING));
	}
	/**
	 * @return true if this thread is still running;
	 */
	public static boolean getRunning() {
		return running;
	}
	
	public static void setPlayer(Player player) {
		p = player;
	}
	
	public static Player getPlayer() {
		return p;
	}
	public static void setMonster(combat.Monster monster) {
		m = monster;
	}
	
	public static Monster getMonster() {
		return m;
	}
	/**
	 * 
	 */
	public static void stop() {
		running = false;
	}
	/*
	 * Implement the main loop for drawing windows and fighting
	 */
	public void fightEvent() {
		
	}
	
	/**
	 * @param names list of button names you want to use
	 * @return ArrayList<JButton> to be used to generate menu buttons dynamically.
	 */
	public static ArrayList<JButton> createButtons(ArrayList<String> names) {
		ArrayList<JButton> buttons = new ArrayList<>();
		int n = 0;
		for (int i = 0; i < names.size(); i++) {
			JButton b = new JButton(names.get(i));
			b.addActionListener(new AttackButton());
			b.setSize(64, 32);
			b.setMnemonic(i);
			buttons.add(b);
			n = i;
		}
		
		JButton ret = new JButton("RETURN");
		ret.setSize(64, 32);
		ret.setMaximumSize(new Dimension(64, 32));
		ret.setMnemonic(n);
		buttons.add(ret);
		return buttons;
	}
	/**
	 * @param p is the player object from which the attack names are requested.
	 * @return list of spell names to be used on auto generating buttons.
	 */
	public static ArrayList<String> getSpellNames(Player p) {
		ArrayList<String> spellnames = new ArrayList<>();
		for (combat.Attack a : p.getSpellbook()) {
			spellnames.add(a.getName());
		}
		return spellnames;
	}
	/**
	 * @param p is the player object from which the attack names are requested.
	 * @return list of attack names to be used on auto generating JButtons.
	 */
	public ArrayList<String> getAttackNames(Player p) {
		ArrayList<String> attacks = new ArrayList<>();
		for (combat.Attack a : p.getMovelist()) {
			attacks.add(a.getName());
		}
		return attacks;
	}
}

class AttackButton implements ActionListener {

	@Override	
	public void actionPerformed(ActionEvent e) {
		Monster m = InitCombat.getMonster();
		Player p = InitCombat.getPlayer();
		Component c = (Component)e.getSource();
		JButton j = (JButton)c;
		p.DealDamage(m, p.getSpell(j.getMnemonic()));
	}
	
}