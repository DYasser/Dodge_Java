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

public class About extends MouseAdapter{
	
	private Game game;
	
	public About(Game game)
	{
		this.game = game;
	}

	@SuppressWarnings({ "unused", "static-access" })
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
		
		if(mouseOver(mx, my, 25, 25, 300, 75) && (game.gameState == STATE.About))
		{
			clip.play();
			game.gameState = STATE.Options;
		}
		
		if(mouseOver(mx, my, 25, 125, 300, 75) && (game.gameState == STATE.About))
		{
			clip.play();
			game.gameState = STATE.Menu;
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
			if(my > y && my < y + height)
			return true;
		return false;
	}
	
	public void update() 
	{}
	
	@SuppressWarnings("static-access")
	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.65f));
		g.setColor(Color.black);
		g.fillRect(0, 0, game.getWidth() ,game.getHeight());
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.68f));
		g.fillRect(50, 310, 820, 375);
		g.fillRect(game.getWidth()-660, 310, 570, 325);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.45f));
		g.setColor(Color.white);
		g.fillRect(25, 25, 300, 75);
		g.fillRect(25, 125, 300, 75);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g.setFont(game.pixelMPlus.deriveFont(45f));
		g.drawRect(25, 25, 300, 75); // Back
		g.drawRect(25, 125, 300, 75); // Main Menu
		g.drawString("Back", 120, 80);
		g.drawString("Main Menu", 70, 180);
		g.drawString("Movements:", 80, 300);
		g.drawString("Goal:", game.getWidth()-700, 300);
		g.setFont(game.pixelMPlus.deriveFont(30f));
		g.drawString("    A 	    : move left", 80, 350);
		g.drawString("	    D     : move right", 80, 400);
		g.drawString("LeftShift : run", 80, 450);
		g.drawString("	    W     	: jump", 80, 500);
		g.drawString("	    Q     	: SlowMotion", 80, 550);
		g.drawString("	    P     	: Pause the game [escape ingame]", 80, 600);
		g.drawString("	 escape   : toggle FullScreen/Windowed [out of game]", 80, 650);
		
		g.drawString("You have to dodge all obstacles", game.getWidth()-600, 350);
		g.drawString("and survive", game.getWidth()-465, 400);
		g.drawString("as", game.getWidth()-400, 450);
		g.drawString("long", game.getWidth()-420, 500);
		g.drawString("as", game.getWidth()-400, 550);
		g.drawString("possible", game.getWidth()-440, 600);
	}
}
