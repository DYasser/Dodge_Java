package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Main.Game;
import properties.GameObject;
import properties.Handler;
import properties.ID;
import states.Hud;

public class MovingPlat extends GameObject{

	private int WIDTH, HEIGTH;
	private Handler handler;
	private Random rand;
	private Game game;
	
	public MovingPlat(int x, int y, int width, int height, ID id, Handler handler, Game game) {
		super(x, y, id);
		WIDTH = width;
		HEIGTH = height;
		velX = 5;
		this.handler = handler;
		rand = new Random();
		this.game = game;
	}

	@Override
	public void update() {
		x += velX;
		
		if(x+WIDTH >= game.getWidth() || x <= 0)
		{
			velX *= -1;
		}
		collision();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, WIDTH, HEIGTH);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle( x, y, WIDTH, HEIGTH);
	}

	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			if(temp.getID() == ID.Blade)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					Hud.score += 10;
					handler.removeObject(temp);
					Blade blade0 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
					handler.addObject(blade0);
				}
			}
		}
	}
}