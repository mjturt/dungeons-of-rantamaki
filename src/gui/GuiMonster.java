package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GuiMonster extends GameObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle bounds = new Rectangle();
	
	public GuiMonster(int x, int y, ID id) {
		super(x, y, id);
		this.bounds.setBounds(this.x, this.y, 64, 64);
	}

	
	public void tick() {
		x += velX;
		y += velY;
		
	}

	public void render(Graphics2D g) {
		//g.setColor(Color.cyan);
		g.fillRect(this.x, this.y, 64, 64);
	}

	
	public Rectangle getBounds() {
		this.bounds.setBounds(this.x, this.y, 64, 64);
		return this.bounds;
	}


	@Override
	public void reloadAssets(SpriteSheet sheet) {
	}

}
