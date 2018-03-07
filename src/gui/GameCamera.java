package gui;

public class GameCamera {
	private int x, y, cx, cy;
	
	public GameCamera (int x, int y, int cx, int cy) {
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
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
		x = player.getX() + cx/2;
		y = player.getY() + cy/2;
/*
        if (x <= 0) { x = 0; }
        if (x >= 640+42) { x = 640+42; }
        if (y <= 0) {y = 0;}
        if (y >= 480+64){ y = 480+64; }
        */
    }
}
