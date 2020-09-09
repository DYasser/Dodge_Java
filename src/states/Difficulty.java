package states;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import Main.Game;
import Main.Game.STATE;

public class Difficulty extends MouseAdapter{
	
	public static int difficulty = 1;
	private Game game;
	
	public Difficulty(Game game)
	{
		this.game = game;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
				return true;
		return false;
	}
	
	@SuppressWarnings("static-access")
	public void mousePressed(MouseEvent e)
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
		
		if(mouseOver(mx, my, 75, game.getHeight()/2-200, 500, 500) && game.gameState == STATE.Difficulty)
		{
			clip.play();
			difficulty = 1;
			game.retry = true;
			game.gameState = STATE.Game;
		}
		
		if(mouseOver(mx, my, game.getWidth()/2-250, game.getHeight()/2-200, 500, 500) && game.gameState == STATE.Difficulty)
		{
			clip.play();
			difficulty = 2;
			game.retry = true;
			game.gameState = STATE.Game;
		}
		
		if(mouseOver(mx, my, game.getWidth()-575, game.getHeight()/2-200, 500, 500) && game.gameState == STATE.Difficulty)
		{
			clip.play();
			difficulty = 3;
			game.retry = true;
			game.gameState = STATE.Game;
		}
		
		if(mouseOver(mx, my, 25, 25, 200, 50) && game.gameState == STATE.Difficulty)
		{
			clip.play();
			game.gameState = STATE.Menu;
		}
	}
	
	public void update() {}
	
	@SuppressWarnings("static-access")
	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(game.pixelMPlus.deriveFont(80f));
		g.setColor(Color.white);
		g.drawString("Difficulty level", game.getWidth()/2-320, 75);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		g.fillRect(75, game.getHeight()/2-200, 500, 500);
		g.fillRect(game.getWidth()/2-250, game.getHeight()/2-200, 500, 500);
		g.fillRect(game.getWidth()-575, game.getHeight()/2-200, 500, 500);
		g.fillRect(25, 25, 200, 50);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.drawRect(25, 25, 200, 50);
		g.drawRect(75, game.getHeight()/2-200, 500, 500);
		g.drawRect(game.getWidth()/2-250, game.getHeight()/2-200, 500, 500);
		g.drawRect(game.getWidth()-575, game.getHeight()/2-200, 500, 500);
		g.setColor(Color.black);
		g.drawString("EASY", 250, game.getHeight()/2+100);
		g.drawString("NORMAL", game.getWidth()/2-110, game.getHeight()/2+100);
		g.drawString("HARD", game.getWidth()-400, game.getHeight()/2+100);
		g.setFont(game.pixelMPlus.deriveFont(45f));
		g.drawString("BACK", 75, 68);
		
	}
}
