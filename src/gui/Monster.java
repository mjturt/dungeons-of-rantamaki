package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Monster extends GameObject {

	public Monster(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.fillRect(x, y, 64, 64);
		g.setColor(Color.cyan);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
