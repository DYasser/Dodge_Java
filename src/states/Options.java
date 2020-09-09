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

import javax.sound.sampled.FloatControl;

import Main.Game;
import Main.Game.STATE;
import properties.Handler;
import window.GamePanel;

public class Options extends MouseAdapter{

	private Game game;
	@SuppressWarnings("unused")
	private Handler handler;
	
	private static double volume = 1f;
	
	public Options(Game game, Handler handler)
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
		
		if(mouseOver(mx, my, game.getWidth()/2-200, game.getHeight()/2-182, 50, 50) && (game.gameState == STATE.Options))
		{
			clip.play();
			volumeChange(-10);
		}
		
		if(mouseOver(mx, my, game.getWidth()/2+245, game.getHeight()/2-182, 50, 50)&& (game.gameState == STATE.Options))
		{
			clip.play();
			volumeChange(10);
		}
		
		if(mouseOver(mx, my, 25, 25, 300, 75)&& (game.gameState == STATE.Options))
		{
			clip.play();
			game.gameState = STATE.Menu;
		}
		
		if(mouseOver(mx, my, game.getWidth()/2-140, game.getHeight()/2+38, 370, 75)&& (game.gameState == STATE.Options))
		{
			clip.play();
			if(!GamePanel.fullscreen)
				GamePanel.mainScreenTurnOn();
			else
				GamePanel.mainScreenTurnOff();
			game.requestFocus();
		}
		
		if(mouseOver(mx, my, game.getWidth()-225, 25, 200, 75) && (game.gameState == STATE.Options))
		{
			clip.play();
			game.gameState = STATE.About;
		}
	}
	
	@SuppressWarnings("static-access")
	private void volumeChange(double change)
	{
        FloatControl gainControl = (FloatControl) game.clip.getControl(FloatControl.Type.MASTER_GAIN);
        if(volume+ (change/100) > 1)
        {
        	volume = 1f;
        }
        else if(volume+ (change/100) < 0)
        {
        	volume = 0.0;
        }
        else
        {
        	volume += change/100;
        }
        double dB =  (float)(Math.log(volume) / Math.log(10.0) * 20.0);
        
        if(dB < -317)
        	dB = Double.POSITIVE_INFINITY*-1;
        
        try {	gainControl.setValue((float) dB);	}catch(Exception e){e.printStackTrace();}
        
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
	
	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.black);
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.45f));
		g.fillRect(0, 0, game.getWidth(), game.getHeight());
		g.setColor(Color.white);
		
		g.fillRect(game.getWidth()/2-200, game.getHeight()/2-182, 50, 50); // -
		g.fillRect(game.getWidth()/2+245, game.getHeight()/2-182, 50, 50); // +
		g.fillRect(game.getWidth()/2-140, game.getHeight()/2+38, 370, 75);	//Windowed/FullScreen
		g.fillRect(25, 25, 300, 75); // Back
		g.fillRect(game.getWidth()-225, 25, 200, 75); // About
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		Font nF = Game.pixelMPlus.deriveFont(45f);
		g.setFont(nF);
		
		g.drawString("Volume", game.getWidth()/2 - 480, game.getHeight()/2-140);
		g.drawString("-", game.getWidth()/2 - 182, game.getHeight()/2-140);
		g.drawString("+", game.getWidth()/2 + 260, game.getHeight()/2-140);
		g.drawString("Back", 120, 80);
		g.drawString("About", game.getWidth()-180, 80);
		
		if(GamePanel.fullscreen)
			g.drawString("Windowed", game.getWidth()/2-45, game.getHeight()/2+95);
		else
			g.drawString("Fullscreen", game.getWidth()/2-65, game.getHeight()/2+95);
		g.setFont(Game.pixelMPlus);
		
		g.drawRect(game.getWidth()/2-200, game.getHeight()/2-182, 50, 50); // -
		g.drawRect(game.getWidth()/2+245, game.getHeight()/2-182, 50, 50); // +
		g.drawRect(game.getWidth()/2-140, game.getHeight()/2+38, 370, 75);	//Windowed/FullScreen
		g.drawRect(25, 25, 300, 75); // Back
		g.drawRect(game.getWidth()-225, 25, 200, 75); // About
		g.drawRect(game.getWidth()/2-100, game.getHeight()/2-172, 300, 25); // Volume bar
		g.setColor(Color.green);
		g.fillRect(game.getWidth()/2-100, game.getHeight()/2-172, (int)(300*volume), 25);
		
	}
	
}
