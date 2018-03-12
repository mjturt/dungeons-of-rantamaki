package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 *
 *
 * @author Vili Ahava
 */
@SuppressWarnings("serial")
public class GameOver extends JDialog implements ActionListener, Runnable {
    JButton newGame = new JButton("New Game");
    JButton exit = new JButton("Exit");
    JPanel panel = new JPanel();
    private boolean running = false;
    JFrame frame;
    public GameOver(JFrame frame) {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.frame = frame;
        this.running = true;
        }

    public void createAndShow() {
        this.frame.setEnabled(false);
        this.frame.setVisible(false);
        this.setAlwaysOnTop(true);
        this.add(this.panel);
        this.exit.setSize(64, 32);
        this.exit.setActionCommand("EXIT");
        this.exit.addActionListener(this);
        this.newGame.setSize(64, 32);
        this.newGame.setActionCommand("NEW GAME");
        this.newGame.addActionListener(this);
        this.panel.add(this.newGame);
        this.panel.add(this.exit);
        this.setSize(320, 240);
        this.setBackground(Color.DARK_GRAY);
        this.pack();
        this.setLocationRelativeTo(this.frame);
        this.setEnabled(true);
        this.setVisible(true);
        this.validate();
        this.repaint();
        System.out.println("Constructed GameOver, is this a event dispatch thread: " + SwingUtilities.isEventDispatchThread());
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("NEW GAME")) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            System.out.println(Thread.currentThread());
            this.frame.dispose();
            System.out.println("Closed the window");
            Game.main(null);
        } else if (e.getActionCommand().equals("EXIT")) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            this.frame.dispatchEvent(new WindowEvent(this.frame, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void run() {
        createAndShow();
    }
}
