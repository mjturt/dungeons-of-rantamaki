package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import combat.Player;

public class InitCombat {

	private static boolean running;
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
		running = true;
		JFrame jf = h.getFrame();
		jf.setEnabled(false);
		JDialog jd = new JDialog(jf);
		jd.setSize(640, 480);
		jd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Player tempPlayer = p;
		JPanel test = new JPanel();
		ArrayList<JButton> tmp = createButtons(getSpellNames(p));
		for (JButton b : tmp) {
			test.add(b);
		}
		test.setLayout(new GridLayout(5, 5));
		jd.getContentPane().add(test);
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
	/**
	 * 
	 */
	public static void stop() {
		running = false;
	}
	/*
	 * Implement the main loop for drawing windows and fighting
	 */
	public void fightEvent(combat.Monster m, combat.Player p) {
		
	}
	/*
	 * UNFINISHED, NEEDS MORE RESEARCH
	 */
	public void actionPerformed(ActionEvent e, ArrayList<JButton> btns) {
		Object o = e;
		for (JButton button : btns) {
			if (button.getName() == "ATTACK") {
				
			}
		}
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