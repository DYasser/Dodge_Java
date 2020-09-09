package states;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import Main.Game;
import Main.Game.STATE;
import properties.Handler;

public class GameOver extends MouseAdapter {
	
	private Game game;
	@SuppressWarnings("unused")
	private Handler handler;
	
	
	public GameOver(Game game, Handler handler)
	{
		this.game = game;
		this.handler = handler;
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
		
		if(mouseOver(mx, my, game.getWidth()/2-658, game.getHeight()/2+217, 300, 80) && (game.gameState == STATE.GameOver))
		{
			clip.play();
			game.retry = true;
			game.gameState = STATE.Game;
			
		}
		
		if(mouseOver(mx, my, game.getWidth()/2-158, game.getHeight()/2+217, 300, 80)&& (game.gameState == STATE.GameOver))
		{
			clip.play();
			game.gameState = STATE.Menu;
		}
		
		if(mouseOver(mx, my, game.getWidth()/2+342, game.getHeight()/2+217, 300, 80)&& (game.gameState == STATE.GameOver))
		{
			clip.play();
			System.out.println("Quit");
			System.exit(-1);
		}
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
				return true;
		return false;
	}
	
	public void update(){}	
	
	@SuppressWarnings("unused")
	public void render(Graphics g)
	{//Gets the coordinates of the screen
		MouseEvent e = new MouseEvent(game,0,0,0,0,0,0,true);
		int X = e.getXOnScreen();
		int Y = e.getYOnScreen();
		
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b = a.getLocation();
		
	 //Gets the coordinates of the mouse
		int mx = (int) b.getX();
		int my = (int) b.getY();
		
		int x = mx-X;
		int y = my-Y;	
		
		g.setFont(Game.pixelMPlus.deriveFont(200f));
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		g.setColor(Color.white);
		g.fillRect(game.getWidth()/2-658, game.getHeight()/2+217, 300, 80);
		g.fillRect(game.getWidth()/2-158, game.getHeight()/2+217, 300, 80);
		g.fillRect(game.getWidth()/2+342, game.getHeight()/2+217, 300, 80);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.drawString("GAME OVER", game.getWidth()/2-500, game.getHeight()/2-150);
		
	
		g.drawRect(game.getWidth()/2-658, game.getHeight()/2+217, 300, 80);
		g.drawRect(game.getWidth()/2-158, game.getHeight()/2+217, 300, 80);
		g.drawRect(game.getWidth()/2+342, game.getHeight()/2+217, 300, 80);
		
		g.setFont(Game.pixelMPlus.deriveFont(40f));
		
	
		if(Hud.highscore)
		{
			g.setFont(Game.pixelMPlus.deriveFont(60f));
			g.drawString("NEW HIGHSCORE!! ", game.getWidth()/2-230, game.getHeight()/2);
			g.setFont(Game.pixelMPlus.deriveFont(40f));
			g.drawString( "" + Hud.score, game.getWidth()/2-80, game.getHeight()/2+60);
		}
		else
		{
			g.setFont(Game.pixelMPlus.deriveFont(60f));
			g.drawString("HighScore: ", game.getWidth()/2-300, game.getHeight()/2);
			g.setFont(Game.pixelMPlus.deriveFont(40f));
			g.drawString(""+Hud.savedScore, game.getWidth()/2+100, game.getHeight()/2);
			g.drawString("Your score: " + Hud.score, game.getWidth()/2-200, game.getHeight()/2+60);
		}
		
		g.drawString("Retry", game.getWidth()/2-550 ,game.getHeight()/2+271);
		g.drawString("Main Menu", game.getWidth()/2-100 ,game.getHeight()/2+271);
		g.drawString("Quit", game.getWidth()/2+450 ,game.getHeight()/2+271);
	}

}