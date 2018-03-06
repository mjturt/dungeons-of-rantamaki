package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class InitCombat {

	private static boolean running;

	public static void main(Handler h, GameObject o, combat.Monster m, combat.Player p) {
		/*////////////////////////////////////////////////////
		///CONFIGURATION OF WINDOW STARTS
		////////////////////////////////////////////////////*/
		running = true;
		JFrame jf = h.getFrame();
		jf.setEnabled(false);
		JDialog jd = new JDialog(jf);
		jd.setSize(640, 480);
		jd.setVisible(true);
		jd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JButton cons = new JButton("CONSUMABLES");
		JButton att = new JButton("ATTACK");
		/*//////////////////////////////////////////////////////
		//CONFIGURATION OF WINDOW ENDS
		//////////////////////////////////////////////////////*/
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
	
	public static boolean getRunning() {
		return running;
	}
	
	public static void stop() {
		running = false;
	}
	
	public void fightEvent(combat.Monster m, combat.Player p) {
		
	}
}