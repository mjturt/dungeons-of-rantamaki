package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class InitCombat {

	private static boolean running;

	public static void main(Handler h, GameObject o) {
		running = true;
		JFrame jf = h.getFrame();
		jf.setEnabled(false);
		JDialog jd = new JDialog(jf);
		jd.setSize(640, 480);
		jd.setVisible(true);
		jd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		System.out.println("InitCombat reached HIDE ON CLOSE");
		Iterator<GameObject> iter = h.objects.iterator();
		jd.setVisible(false);
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
}