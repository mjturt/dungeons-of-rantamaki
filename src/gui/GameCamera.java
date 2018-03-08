package gui;

public class GameCamera {
	private double x, y, cx, cy;
	
	public GameCamera (double x, double y, double cx, double cy) {
		this.x = x;
		this.y = y;
		this.cx = cx;
		this.cy = cy;
	}
	
	public void setX (double x) {
		this.x = x;
	}
	
	public void setY (double y) {
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}

	public void tick(GameObject player) {
		x = player.getX() - cx/2;
		y = player.getY() - cy/2;

        /* Handling camera out of map problem */
        if (x <= 0) { x = 0; }
        if (x >= 2560 - 570) { x = 2560 - 570; }
        if (y <= 0) {y = 0;}
        if (y >= 2560 - 400){ y = 2560 - 400; }
        
    }
}
