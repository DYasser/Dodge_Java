package states;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
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

public class Menu extends MouseAdapter {

	private Game game;
	@SuppressWarnings("unused")
	private Handler handler;
	
	public Menu(Game game, Handler handler)
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
		
		if(mouseOver(mx, my, game.getWidth()/2-95, 600, 190, 50) && (game.gameState == STATE.Menu))
		{
			clip.play();
			game.gameState = STATE.Difficulty;
			
		}
		
		if(mouseOver(mx, my, game.getWidth()/2-95, 700, 190, 50) && (game.gameState == STATE.Menu))
		{
			clip.play();
			game.gameState = STATE.Options;
		}
		
		if(mouseOver(mx, my, game.getWidth()/2-95, 800, 190, 50) && (game.gameState == STATE.Menu))
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
	
	int c = 0;
	int k = 1;
	
	@SuppressWarnings("static-access")
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
		
		c += k;
		if(c > 74 || c < 1)
			k *= -1;
		
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.white);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
		if(c>30) {
			if(mouseOver(x, y, game.getWidth()/2-95, 600, 190, 50) && (game.gameState == STATE.Menu))
			{
				g2.fillRect(game.getWidth()/2-95, 600, 190, 50);
				g2.fillRect(game.getWidth()/2-95, 700, 190, 50);
				g2.fillRect(game.getWidth()/2-95, 800, 190, 50);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

				g.drawRect(game.getWidth()/2-95, 600, 190, 50);
				g.drawRect(game.getWidth()/2-95, 700, 190, 50);
				g.drawRect(game.getWidth()/2-95, 800, 190, 50);
			}
			else 
				if(mouseOver(x, y, game.getWidth()/2-95, 700, 190, 50)&& (game.gameState == STATE.Menu))
				{
					g2.fillRect(game.getWidth()/2-95, 700, 190, 50);
					g2.fillRect(game.getWidth()/2-95, 600, 190, 50);
					g2.fillRect(game.getWidth()/2-95, 800, 190, 50);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

					g.drawRect(game.getWidth()/2-95, 700, 190, 50);
					g.drawRect(game.getWidth()/2-95, 600, 190, 50);
					g.drawRect(game.getWidth()/2-95, 800, 190, 50);

				}
				else
					if(mouseOver(x, y, game.getWidth()/2-95, 800, 190, 50)&& (game.gameState == STATE.Menu))
					{
						g2.fillRect(game.getWidth()/2-95, 800, 190, 50);
						g2.fillRect(game.getWidth()/2-95, 600, 190, 50);
						g2.fillRect(game.getWidth()/2-95, 700, 190, 50);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

						g.drawRect(game.getWidth()/2-95, 800, 190, 50);
						g.drawRect(game.getWidth()/2-95, 600, 190, 50);
						g.drawRect(game.getWidth()/2-95, 700, 190, 50);
					}
					else{
			g2.fillRect(game.getWidth()/2-95, 600, 190, 50);
			g2.fillRect(game.getWidth()/2-95, 700, 190, 50);
			g2.fillRect(game.getWidth()/2-95, 800, 190, 50);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

			g.drawRect(game.getWidth()/2-95, 600, 190, 50);
			g.drawRect(game.getWidth()/2-95, 700, 190, 50);
			g.drawRect(game.getWidth()/2-95, 800, 190, 50);
			}
		}		
		else
		{	
			if(mouseOver(x, y, game.getWidth()/2-95, 600, 190, 50) && (game.gameState == STATE.Menu))
			{
				g2.fillRect(game.getWidth()/2-95, 600, 190, 50);
				g2.fillRect(game.getWidth()/2-110, 695, 220, 60);
				g2.fillRect(game.getWidth()/2-110, 795, 220, 60);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
				g.drawRect(game.getWidth()/2-95, 600, 190, 50);
				g.drawRect(game.getWidth()/2-110, 695, 220, 60);
				g.drawRect(game.getWidth()/2-110, 795, 220, 60);
			}
			else 
				if(mouseOver(x, y, game.getWidth()/2-95, 700, 190, 50)&& (game.gameState == STATE.Menu))
				{
					g2.fillRect(game.getWidth()/2-95, 700, 190, 50);
					g2.fillRect(game.getWidth()/2-110, 595, 220, 60);
					g2.fillRect(game.getWidth()/2-110, 795, 220, 60);
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					g.drawRect(game.getWidth()/2-110, 595, 220, 60);
					g.drawRect(game.getWidth()/2-95, 700, 190, 50);
					g.drawRect(game.getWidth()/2-110, 795, 220, 60);
				}
				else
					if(mouseOver(x, y, game.getWidth()/2-95, 800, 190, 50)&& (game.gameState == STATE.Menu))
					{
						g2.fillRect(game.getWidth()/2-95, 800, 190, 50);
						g2.fillRect(game.getWidth()/2-110, 595, 220, 60);
						g2.fillRect(game.getWidth()/2-110, 695, 220, 60);
						g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
						g.drawRect(game.getWidth()/2-110, 595, 220, 60);
						g.drawRect(game.getWidth()/2-110, 695, 220, 60);
						g.drawRect(game.getWidth()/2-95, 800, 190, 50);
					}
					else{
			g2.fillRect(game.getWidth()/2-110, 595, 220, 60);
			g2.fillRect(game.getWidth()/2-110, 695, 220, 60);
			g2.fillRect(game.getWidth()/2-110, 795, 220, 60);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.drawRect(game.getWidth()/2-110, 595, 220, 60);
			g.drawRect(game.getWidth()/2-110, 695, 220, 60);
			g.drawRect(game.getWidth()/2-110, 795, 220, 60);
			}
		}

		g.setFont(Game.pixelMPlus);
		Font nF = Game.pixelMPlus.deriveFont(200f);
		g.setFont(nF);
		g.drawString("DODGE!", game.getWidth()/2-250, 200);
		g.setFont(Game.pixelMPlus);
		g.drawString("START", game.getWidth()/2-30, 635);
		g.drawString("OPTIONS", game.getWidth()/2-45, 735);
		g.drawString(" QUIT", game.getWidth()/2-40, 835);
	}
}
