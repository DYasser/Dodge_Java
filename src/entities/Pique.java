package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Game;
import properties.GameObject;
import properties.ID;

public class Pique extends GameObject{

	private Game game;
	private boolean pass = true;
	
	public Pique(int x, int y, ID id, Game game) {
		super(x, y, id);
		this.game = game;
		velY = -4;
	}

	public void update() {
		
		if(y <= 50 && pass)
		{
			velY *= -5;
			pass = false;
		}
		
		else if(y >= game.getHeight() - 150 && !pass)
		{
			velY /= -5;
			pass = true;
		}
		
		y += velY;
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, 50, 50);
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,50,50);
	}

}
