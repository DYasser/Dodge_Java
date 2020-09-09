package entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.Game;
import properties.GameObject;
import properties.ID;
import properties.KeyInput;
import states.Hud;

public class Beam extends GameObject {

	private Game game;
	private int width;
	
	protected static double start, now, deltaT, plus, minus;
	
	private boolean pass;
	private double pause, paused;
	
	public Beam(int x, int y, ID id, Game game) {
		super(x, y, id);
		this.game = game;
		start = System.currentTimeMillis();
		deltaT = 0;
		minus = 0;
		width = 0;
		pass = true;
		pause = System.currentTimeMillis();
		paused = 0;
		Hud.paused2 = 0;
	}

	@Override
	public void update() {
		plus = (now-start)/1000 + 4 - minus - Hud.paused2;
		deltaT = (now-start)/1000 + 4 - Hud.paused2;
		now = System.currentTimeMillis();
		
		if(deltaT > 8 && deltaT < 10)
			{
				minus += 0.5;
			}
		else if(plus >= 5)
			{
				plus = 5;
			}
		if(deltaT > 4)
			width = (int) (plus*30);
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(Game.pixelMPlus.deriveFont(45f));
		g.setColor(Color.white); 
		if(deltaT < 4) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
			g.setColor(Color.black);
			g.fillRect(x-30, y+10, 150, 50);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.setColor(Color.white); 
			g.drawString((int)(3-deltaT) + "...!", x, y+50);
			g.drawLine(x, 0, x, game.getHeight());
		}
		else	
			g.fillRect((int) (x-plus*15), 0, width, game.getHeight());
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) (x-plus*15), y, width, game.getHeight());
	}

}
