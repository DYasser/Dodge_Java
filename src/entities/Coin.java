package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import properties.GameObject;
import properties.ID;

public class Coin extends GameObject{

	private int WIDTH = 20, HEIGTH = 20;
	
	public Coin(int x, int y, ID id) {
		super(x, y, id);
	}

	public void update() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, WIDTH, HEIGTH);
	}

	public Rectangle getBounds() {
		return new Rectangle( x, y, WIDTH, HEIGTH);
	}
}
