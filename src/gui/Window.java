package gui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;

/* Basic class for creating windows with Swing + AWT */ 

public class Window {
	private JFrame frame;

    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setSize(width, height);
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public int getWidth() {
    	return frame.getWidth();
    }
    
    public int getHeigth() {
    	return frame.getHeight();
    }
}
