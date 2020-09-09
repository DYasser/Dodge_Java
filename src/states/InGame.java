package states;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import Main.Game;
import Main.Game.STATE;
import entities.Beam;
import entities.Blade;
import entities.Coin;
import entities.Crown;
import entities.FixedPlat;
import entities.MovingPlat;
import entities.Pique;
import entities.Player;
import entities.SlowMotion;
import entities.Snake;
import entities.Traps;
import properties.Handler;
import properties.ID;
import properties.KeyInput;

public class InGame extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	
	private double now;

	private int spawn = -1, spawn2 = -1, spawn3 = -1, spawn4 = -1;
	
	private Random rand = new Random();
	
	public InGame(Game game, Handler handler)
	{ 
		this.game = game;
		this.handler = handler;
	}
	
	public void start()
	{
		KeyInput.pause = false;
		
		now = System.currentTimeMillis();
		
		Player player = new Player(game.getWidth()/2-10, game.getHeight()-80, ID.Player, handler, game);
		handler.addObject(player);

		Traps trap0 = new Traps(175, game.getHeight()-30, ID.Trap);
		handler.addObject(trap0);
		Traps trap1 = new Traps(game.getWidth()-325, game.getHeight()-30, ID.Trap);
		handler.addObject(trap1);
		
		FixedPlat plat0 = new FixedPlat(0, game.getHeight()-20, game.getWidth(), 20, ID.FixedPlatform, handler, game);
		handler.addObject(plat0);
		
		FixedPlat plat1 = new FixedPlat(200, game.getHeight()-300, 100, 20, ID.FixedPlatform, handler, game);
		handler.addObject(plat1);
		FixedPlat plat2 = new FixedPlat(game.getWidth()-300, game.getHeight()-580, 100, 20, ID.FixedPlatform, handler, game);
		handler.addObject(plat2);
		
		MovingPlat plat3 = new MovingPlat(40, game.getHeight()-160, 100, 20, ID.MovingPlatform, handler, game);
		handler.addObject(plat3);
		MovingPlat plat4 = new MovingPlat(game.getWidth()-140, game.getHeight()-440, 100, 20, ID.MovingPlatform, handler, game);
		handler.addObject(plat4);
		MovingPlat plat5 = new MovingPlat(1000, game.getHeight()-720, 100, 20, ID.MovingPlatform, handler, game);
		handler.addObject(plat5);
		
		Snake snake0 = new Snake(game.getWidth()-40, game.getHeight()-30, ID.Snake, game);
		handler.addObject(snake0);
		Snake snake1 = new Snake(100, game.getHeight()-30, ID.Snake, game);
		handler.addObject(snake1);
		
		Blade blade0 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
		handler.addObject(blade0);
		Blade blade1 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
		handler.addObject(blade1);
		Blade blade2 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
		handler.addObject(blade2);
		Blade blade3 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
		handler.addObject(blade3);
		Blade blade4 = new Blade(Math.abs(rand.nextInt()%game.getWidth()), 0, ID.Blade, game);
		handler.addObject(blade4);
		
		Pique pique = new Pique(game.getWidth()/2, game.getHeight()/2, ID.Pique, game);
		handler.addObject(pique);
	}
	
	public void update()
	{
		double current = System.currentTimeMillis();
		int deltaT = (int) (current - now)/1000;
		if(Hud.score % 2010 > 1990 && spawn4 != deltaT)
		{
			SlowMotion sm = new SlowMotion(game.getWidth()/2, game.getHeight()/2-375, ID.SlowMotion);
			handler.addObject(sm);
			spawn4 = deltaT;
		}
		
		if(Hud.score % 1010 > 999 && spawn3 != deltaT)
		{
			Beam beam = new Beam(Math.abs(rand.nextInt()%(game.getWidth()-20)), 0, ID.Beam, game);
			handler.addObject(beam);
			spawn3 = deltaT;
		}
		
		if(deltaT % 25 == 24 && spawn != deltaT)
		{
			int rando = Math.abs(rand.nextInt()%550);
			Coin coin0 = new Coin(Math.abs(rand.nextInt()%(game.getWidth()-20)), (rando) + 250, ID.Coin);
			handler.addObject(coin0);
			spawn = deltaT;
			
		}
		
		if(deltaT % 40 == 35 && spawn2 != deltaT)
		{
			int rando = Math.abs(rand.nextInt()%550);
			Crown crown0 = new Crown(Math.abs(rand.nextInt()%(game.getWidth()-20)), (rando) + 250, ID.Crown);
			handler.addObject(crown0);
			spawn2 = deltaT;
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
				return true;
		return false;
	}
	
	@SuppressWarnings("static-access")
	public void mouseReleased(MouseEvent e)
	{
		URL url = null;
		try {
			url = new URL("file:\\D:\\Scripts\\eclipse-workspace\\GamePract2\\src\\res\\bip.wav");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		AudioClip clip = Applet.newAudioClip(url);
		
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx, my, 50, game.getHeight()/2 -50, 200, 65) && (game.gameState == STATE.Game) && KeyInput.pause)
		{
			clip.play();
			KeyInput.pause = false;
			
		}
		
		if(mouseOver(mx, my, 50, game.getHeight()/2 + 100, 200, 65) && (game.gameState == STATE.Game) && KeyInput.pause)
		{
			clip.play();
			game.retry = true;
			game.gameState = STATE.Menu;
		}
		
		if(mouseOver(mx, my, 50, game.getHeight()/2 +250, 200, 65) && (game.gameState == STATE.Game) && KeyInput.pause)
		{
			clip.play();
			System.out.println("Quit");
			System.exit(-1);
		}
	}
	
	public void render(Graphics g)
	{
		if(KeyInput.pause)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.65f));
			g.setColor(Color.black);
			g.fillRect(0, 0, game.getWidth(), game.getHeight());
			g.setColor(Color.WHITE);
			g.fillRect(50, game.getHeight()/2 -50, 200, 65);		//unpause
			g.fillRect(50, game.getHeight()/2 + 100, 200, 65);		//Main menu	
			g.fillRect(50, game.getHeight()/2 + 250, 200, 65);		//Quit
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.drawRect(50, game.getHeight()/2 -50, 200, 65);
			g.drawRect(50, game.getHeight()/2 +100, 200, 65);
			g.drawRect(50, game.getHeight()/2 +250, 200, 65);
			Font title = Game.pixelMPlus.deriveFont(150f);
			g.setFont(title);
			g.drawString("Paused", game.getWidth()/2-200, 200);
			g.setFont(Game.pixelMPlus);
			g.drawString("Unpause", 100, game.getHeight()/2-7);
			g.drawString("Main Menu", 90, game.getHeight()/2+145);
			g.drawString("Quit", 120, game.getHeight()/2+295);
			g.drawString("-Press p to unpause-", game.getWidth()/2-120, 250);
			g.drawString(" A D    to move", game.getWidth()/2 - 90, 550);
			g.drawString("  W 	    to jump", game.getWidth()/2 - 90, 600);
			g.drawString("  Q 	    to slowMotion", game.getWidth()/2 - 90, 650);
			g.drawString("Shift   to run", game.getWidth()/2 - 90, 700);
		}
	}
}
