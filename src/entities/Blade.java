package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Main.Game;
import properties.GameObject;
import properties.ID;

public class Blade extends GameObject{

	private int WIDTH = 10, HEIGTH = 10;
	private Random rand;
	private Game game;
	
	public Blade(int x, int y, ID id, Game game) {
		super(x, y, id);
		rand = new Random();
		velY = (Math.abs(rand.nextInt()%5)+2)*4;
		velX = rand.nextInt()%2;
		this.game = game;
	}

	public void update() {
		y += velY;
		x += velX;
		if(x <= 0 || x >= game.getWidth())
		{
			x = Math.abs(rand.nextInt()%(game.getWidth()-20) + 10);
			y = 0;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGTH);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGTH);
	}
}