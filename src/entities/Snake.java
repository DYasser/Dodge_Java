package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Game;
import properties.GameObject;
import properties.ID;

public class Snake extends GameObject{

	private int WIDTH = 20, HEIGTH = 10;
	private Game game;
	
	public Snake(int x, int y, ID id, Game game) {
		super(x, y, id);
		velX = 8;
		this.game = game;
	}

	@Override
	public void update() {
		x += velX;
		
		if(x+WIDTH >= game.getWidth() || x <= 0)
		{
			velX *= -1;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, WIDTH, HEIGTH);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle( x, y, WIDTH, HEIGTH);
	}
}