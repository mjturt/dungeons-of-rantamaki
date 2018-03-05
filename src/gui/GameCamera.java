package gui;

public class GameCamera {
	private int x, y;
	private int width = Game.WIDTH;
	private int height = Game.HEIGHT;
	
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
		x = -player.getX() + Game.WIDTH/2;
		//y = player.getY() - Game.HEIGHT/2;
	}
}