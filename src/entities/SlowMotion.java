package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import properties.GameObject;
import properties.ID;

public class SlowMotion extends GameObject{

	private int width = 25, height = 25;
	
	public SlowMotion(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
