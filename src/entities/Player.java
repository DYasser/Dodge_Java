package entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Main.Game;
import properties.GameObject;
import properties.Handler;
import properties.ID;
import properties.KeyInput;
import states.Difficulty;
import states.Hud;

public class Player extends GameObject {

	private int fall = 2, c = 0, dmg, dmg1, dmg2, jumpStrength = 24;
	private double now, deltaT = 0, update;
	private boolean jumpPossible = true, playJump = true, targetable;

	private Handler handler;
	private Game game;
	private Random rand;
	
	public static boolean timeStop = false;
	
	public Player(int x, int y, ID id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game; 
		rand = new Random();
		now = System.currentTimeMillis();
		KeyInput.jump  = false;
		targetable = true;
		dmg = 10 * Difficulty.difficulty;
		dmg1 = 5 * Difficulty.difficulty;
		dmg2 = 20 * Difficulty.difficulty;
	}

	public void update() {
		deltaT =(update - now)/1000 - Hud.paused3;
		update = System.currentTimeMillis();

		if(deltaT > 3)
		{
			targetable = true;
		}
		else
			targetable = false;
		x += velX;
		if(KeyInput.jump)
		{
			y += velY;
		}
		else
		{	fall += 1;
			y += fall;
		}
		if(jumpPossible)
		{
			if(!playJump)
			{	Game.clipJump.play();
				playJump = false;
			}
			c++;
			if(c < 30)
			{
				velY = -jumpStrength;
				jumpStrength -= 2;
			}
		}
		
		if(x+20 >= game.getWidth())
		{
			x = game.getWidth()-18; 
		}
		
		if(x <= 0)
		{
			x = 0;
		}
		
		collision();
	}

	
	public Rectangle getBounds() {
		return new Rectangle(x,y,20,50);
	}

	private void attacked()
	{
		Hud.paused3 = 0;
		Game.clipHit.play();
		targetable = false;
		now = System.currentTimeMillis();
	}
	
	private void collision()
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject temp = handler.object.get(i);
			
			if(temp.getID() != ID.Player && temp.pass && timeStop)
			{
				temp.setVelX(temp.getVelX()/4);
				temp.setVelY(temp.getVelY()/4);
				temp.pass = false;
			}
			
			if(temp.getID() != ID.Player && !temp.pass && !timeStop)
			{
				temp.setVelX(temp.getVelX()*4);
				temp.setVelY(temp.getVelY()*4);
				temp.pass = true;
			}
			
			if(temp.getID() == ID.Beam)
			{
				if(Beam.deltaT > 10)
				{
					handler.removeObject(temp);
				}
				
				if(getBounds().intersects(temp.getBounds()) && targetable)
				{
					attacked();
					Hud.health -= dmg2;
				}
			}
			
			if(temp.getID() == ID.FixedPlatform || temp.getID() == ID.MovingPlatform)
			{
				if((new Rectangle(x,y+30,20,20)).intersects(temp.getBounds()))
				{
					fall = 5;
					jumpPossible = true;
					playJump = true;
					jumpStrength = 24;
					c = 0;
					velY = 0;
					KeyInput.jump = false;
					y = temp.getY() - 50;
					x += temp.getVelX();
				}
			}
			
			if(temp.getID() == ID.Snake || temp.getID() == ID.Blade)
			{
				if(getBounds().intersects(temp.getBounds()) && targetable)
				{
					attacked();
					Hud.health -= dmg;
					handler.removeObject(temp);
					Blade blade0 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
					handler.addObject(blade0);
					Snake snake0 = new Snake(Math.abs(rand.nextInt()%(game.getWidth()-80)) + 40, game.getHeight() - 30, ID.Snake, game);
					handler.addObject(snake0);
				}
			}
			
			if(temp.getID() == ID.Pique)
			{
				if(getBounds().intersects(temp.getBounds()) && targetable)
				{
					attacked();
					Hud.health -= dmg;
					Blade blade0 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
					handler.addObject(blade0);
					Snake snake0 = new Snake(Math.abs(rand.nextInt()%(game.getWidth()-80)) + 40, game.getHeight() - 30, ID.Snake, game);
					handler.addObject(snake0);
				}
			}
			
			if(temp.getID() == ID.Trap)
			{
				if(getBounds().intersects(temp.getBounds()) && targetable)
				{
					attacked();
					Hud.health -= dmg1;
				}
			}
			
			if(temp.getID() == ID.SlowMotion)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					Game.clipCrown.play();
					Hud.slow += 20;
					handler.removeObject(temp);
				}
			}
			
			if(temp.getID() == ID.Coin)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					Game.clipCoin.play();
					Hud.score += 100;
					handler.removeObject(temp);
				}
			}
			
			if(temp.getID() == ID.Crown)
			{
				if(getBounds().intersects(temp.getBounds()))
				{
					Game.clipCrown.play();
					Hud.health += 10;
					handler.removeObject(temp);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(!targetable)
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, 20, 50);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
	}

	public void setVelX(int x)
	{
		velX = x;
	}

	public void setVelY(int y)
	{
		velY = y;
	}
}
