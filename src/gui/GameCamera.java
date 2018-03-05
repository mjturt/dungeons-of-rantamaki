package gui;

import java.awt.Frame;

public class GameCamera {
	private int x, y;
	Window w;
	
	public GameCamera (int x, int y, Window w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}
	
	public void setX (int x) {
		this.x = x;
	}
	
	public void setY (int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void tick(GameObject player) {
		x = -player.getX() + w.getWidth()/2;
		y = -player.getY() + w.getHeigth()/2;
	}
}