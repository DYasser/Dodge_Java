package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import properties.GameObject;
import properties.ID;

public class Crown extends GameObject{

	private int WIDTH = 20, HEIGTH = 20;
	
	public Crown(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(x, y, WIDTH, HEIGTH);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle( x, y, WIDTH, HEIGTH);
	}
}