package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/* 
 * Goal screen class
 */

public class GoalScreen {
	ImageLoader il;
	BufferedImage goal;

	public GoalScreen() {
		this.il = new ImageLoader();
		this.goal = il.loadImage("/images/goal.png");
	}

    public void render(Graphics2D g) {
        g.drawImage(this.goal, 0, 0, null);
    }

	public ImageLoader getIl() {
		return this.il;
	}


	public void setIl(ImageLoader il) {
		this.il = il;
	}


	public BufferedImage getGoalScreenbg() {
		return this.goal;
	}


	public void setGoalScreenbg(BufferedImage goal) {
		this.goal = goal;
	}
}
