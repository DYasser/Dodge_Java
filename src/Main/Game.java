package Main;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;

import properties.GameObject;
import properties.Handler;
import properties.KeyInput;
import states.About;
import states.Difficulty;
import states.GameOver;
import states.Hud;
import states.InGame;
import states.Menu;
import states.Options;
import window.GamePanel;


public class Game extends Canvas implements Runnable, KeyListener{
	private static final long serialVersionUID = -5053284251856439163L;
	//Resolution of the Window
		private final int WIDTH = 1820;
		private final int HEIGHT = 900;
		
	//Audio
		public static Clip clip;
		public static AudioClip clipJump;
		public static AudioClip clipCoin;
		public static AudioClip clipCrown;
		public static AudioClip clipHit;
		public static AudioClip clipDead;
		
	//Video
		private Image img;
		
	//-----------
		private Thread thread;
		private boolean running = false;
		private Handler handler;
		public static boolean retry = true;

	//Game States Classes
		private Menu menu;
		private Options options;
		private InGame game;
		private Hud hud;
		private GameOver go;
		private About about;
		private Difficulty diff;
		
	//Font
		public static Font pixelMPlus;
		
	//Game States
		public enum STATE
		{
			Menu,
			Options,
			About,
			Game,
			Difficulty,
			GameOver;
		};
		
	//Initial Game State
		public static STATE gameState = STATE.Menu;
		
	//Paint Background
		public void paint(Graphics g)
		{
			super.paint(g);
			
			g.setColor(new Color(0,0,0)); 
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			img= new ImageIcon("C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//Background3.png").getImage();
		    g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		}
		
		Game()
		{
			handler = new Handler();
			menu = new Menu(this, handler);
			options = new Options(this, handler);
			game = new InGame(this, handler);
			hud = new Hud(this);
			go = new GameOver(this, handler); 
			about = new About(this);
			diff = new Difficulty(this); 
			this.addMouseListener(menu);
			this.addMouseListener(options);
			this.addMouseListener(go);
			this.addKeyListener(new KeyInput(handler));
			this.addKeyListener(this);
			this.addMouseListener(game);
			this.addMouseListener(about);
			this.addMouseListener(diff);
			
			@SuppressWarnings("unused")
			GamePanel window = new GamePanel(WIDTH, HEIGHT, "Dodge!", this);
			
			try {
				//Create pixelPlus Font
				pixelMPlus = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/LENOVO/Documents/GitHub/Dodge_Java/src/res/PixelMplus10-Regular.ttf")).deriveFont(30f);
			} catch(IOException | FontFormatException e){e.printStackTrace();}
			
		}
		
	//Starts the game
		public void start()
		{
			thread = new Thread(this);
			thread.start();
			running = true;
		}
		
	//Stops the game
		public void stop()
		{
			try {thread.join(); 
				running = false;}
			catch(Exception e)
				{e.printStackTrace();}
		}
		
		//The game loop
		public void run()
		{
			this.requestFocus();	//Focus on the window
			
			long lastTime = System.currentTimeMillis();
			
			double amountOfTicks = 60.0;	
			double ms = 1000 / amountOfTicks;	//1 000 
			
			double delta = 0;
			
			while(running)
			{
				long now = System.currentTimeMillis();
				delta += (now - lastTime) / ms;
				lastTime = now;
				
				while(delta >= 1)
				{
					tick();	//Update
					delta--;
				}
				
				render();	//Render everything
			}
			stop();
		}
		
	//Updates everything
		private void tick()
		{
			if(gameState == STATE.Game) {
				if(retry)
				{
					hud.start();
					game.start();
					retry = false;
				}

				if(!KeyInput.pause && !Hud.stop)
				{
					handler.update();
					hud.update();
					game.update();
				}
				
				hud.update2();
			}
			
			else if(gameState == STATE.Menu)
			{
				for(int i = 0; i < handler.object.size(); i++)
				{
				GameObject temp = handler.object.get(i);
				handler.removeObject(temp);
				}
				menu.update();
			}
			
			else if(gameState == STATE.Difficulty)
			{
				diff.update();
			}
			
			else if(gameState == STATE.Options)
			{
				options.update();
			}
			
			else if(gameState == STATE.GameOver)
			{
				go.update();
			}
			
			else if(gameState == STATE.About)
			{
				about.update();
			}
		}
		
	//Renders everything
		private void render()
		{
			BufferStrategy bs =  this.getBufferStrategy();
			if(bs == null)
			{
				this.createBufferStrategy(3);
				return;
			}
			
		//Graphics
			Graphics g = bs.getDrawGraphics();
			
		//Paint
			paint(g);
			
		//Render every State and handler
			handler.render(g);
			
			if(gameState == STATE.Game) {
				game.render(g);
				hud.render(g);
			}
			
			else if(gameState == STATE.Difficulty)
			{
				diff.render(g);
			}
			
			else if(gameState == STATE.Menu)
			{
				menu.render(g);
			}
			
			else if(gameState == STATE.Options)
			{
				options.render(g);
			}
			
			else if(gameState == STATE.GameOver)
			{
				 go.render(g);
				for(int i = 0; i < handler.object.size(); i++)
				{
					GameObject temp = handler.object.get(i);
					handler.removeObject(temp);
				}
			}
			
			else if(gameState == STATE.About)
			{
				about.render(g);
			}
			
			g.dispose();
			bs.show();
		}
		
		public static void main(String[] args) throws Exception
		{
			new Game();
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(
						new File("C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//Allof0HitpointsLeft.wav")
																		  );
			clip = AudioSystem.getClip();
			URL url0 = null;
			URL url1 = null;
			URL url2 = null;
			URL url3 = null;
			URL url4 = null;
			try {
				url0 = new URL("file:\\C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//jump.wav");
				url1 = new URL("file:\\C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//coin.wav");
				url2 = new URL("file:\\C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//crown.wav");
				url3 = new URL("file:\\C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//hit.wav");
				url4 = new URL("file:\\C://Users//LENOVO//Documents//GitHub//Dodge_Java//src//res//dead.wav");
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			clipJump = Applet.newAudioClip(url0);
			clipCoin = Applet.newAudioClip(url1);
			clipCrown = Applet.newAudioClip(url2);
			clipHit = Applet.newAudioClip(url3);
			clipDead = Applet.newAudioClip(url4);
			
	        clip.open(inputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);						
		}

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ESCAPE && gameState != STATE.Game)
			{
				if(!GamePanel.fullscreen)
				{
					GamePanel.mainScreenTurnOn();
					this.requestFocus();
				}
				else if(GamePanel.fullscreen)
				{
					GamePanel.mainScreenTurnOff();
					this.requestFocus();
				}
			}
		}

		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}

	}
