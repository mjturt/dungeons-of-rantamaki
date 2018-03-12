package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


/**
 * Class is used to display goal screen
 *
 * @author Maks Turtiainen
 */
public class GoalScreen {
    ImageLoader il;
    BufferedImage goal;

    /**
     * Goal constructor
     */
    public GoalScreen() {
        this.il = new ImageLoader();
        this.goal = il.loadImage("/images/goal.png");
    }

    /**
     * @param g
     */
    public void render(Graphics2D g) {
        g.drawImage(this.goal, 0, 0, null);
    }

    /**
     * @return il
     */
    public ImageLoader getIl() {
        return this.il;
    }

    /**
     * @param il
     */
    public void setIl(ImageLoader il) {
        this.il = il;
    }

    /**
     * @return goal
     */
    public BufferedImage getGoalScreenbg() {
        return this.goal;
    }

    /**
     * @param goal
     */
    public void setGoalScreenbg(BufferedImage goal) {
        this.goal = goal;
    }
}
