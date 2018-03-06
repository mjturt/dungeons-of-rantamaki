package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends GameObject {

	public Monster(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 64, 64);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
