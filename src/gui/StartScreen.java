package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/* 
 * Start screen class
 */

public class StartScreen {
	ImageLoader il;
	BufferedImage start;

	public StartScreen() {
		this.il = new ImageLoader();
		this.start = il.loadImage("/images/start.png");
	}

    public void render(Graphics2D g) {
        g.drawImage(this.start, 0, 0, null);
    }

	public ImageLoader getIl() {
		return this.il;
	}


	public void setIl(ImageLoader il) {
		this.il = il;
	}


	public BufferedImage getStartScreenbg() {
		return this.start;
	}


	public void setStartScreenbg(BufferedImage start) {
		this.start = start;
	}
}
