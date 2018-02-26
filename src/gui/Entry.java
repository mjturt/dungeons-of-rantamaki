package gui;

import java.awt.*;
import javax.swing.*;

import world.World;
import combat.Player;

public class Entry extends JFrame {
    public Entry() {
        initUI();
    }

    private void initUI() {
        add(new Board());
        setSize(200, 200);
        setTitle("Dungeons of Räntämäki");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Entry ex = new Entry();
                ex.setVisible(true);
            }
        });
	
        /*	
		World maailma = new World(99, 99);
		Player pelaaja = new Player(10, "Testi", 10, 10, 20);
		maailma.getTile(0, 1).setPassable(true);
		maailma.getTile(maailma.getHeight()-1, maailma.getWidth()-2).setPassable(true);
		pelaaja.setLocation(1, 1);
		maailma.testPopulate();
        */
	}
}
