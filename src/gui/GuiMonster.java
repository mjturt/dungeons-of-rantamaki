package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GuiMonster extends GameObject {

	public GuiMonster(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 64, 64);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}

}
