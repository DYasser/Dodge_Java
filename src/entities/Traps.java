package entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import properties.GameObject;
import properties.ID;
import states.Hud;

public class Traps extends GameObject{

	private int width = 150, height = 10;
	private double start, now, deltaT;
	
	
	public Traps(int x, int y, ID id) {
		super(x, y, id);
		start = System.currentTimeMillis();
	}

	@Override
	public void update() {
		now = System.currentTimeMillis();
		deltaT = (now-start)/1000 - Hud.timePause + 4;
		if(deltaT % 10 > 5)
		{
			height = 0;
		}
		else
			height = 10;
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
		if(deltaT % 10 > 8)
		{
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g.fillRect(x, y, width, 10);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
