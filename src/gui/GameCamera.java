package gui;

class GameCamera {
	private int x, y;
	private int width;
	private int height;
	
	public GameCamera (int x, int y) {
		this.x = x;
		this.y = y;
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
	//test, you see the rendering problem here
	public void tick(GameObject player) {
		y--;	
	}
}