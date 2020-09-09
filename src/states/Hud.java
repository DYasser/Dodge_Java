package states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import Main.Game;
import Main.Game.STATE;
import entities.Player;
import properties.KeyInput;

public class Hud {
	
	public static int health, score = 0;
	public static boolean stop = true;
	
	protected static int deltaT , savedScore;
	protected static boolean highscore;
	
	private double now, pausedTime = 0, paused;
	public static double slow;
	private int greenV;
	
	public static int x, timePause;
	
	private File save;
	private PrintWriter output;
	private Scanner input;
	private Game game;
	
	public static boolean pass;
	public static double pause, paused2, paused3;
	
	public Hud(Game game)
	{
		this.game = game;
	}
	
	public void start()
	{
		now = System.currentTimeMillis();
		paused = System.currentTimeMillis();
		highscore = false;
		stop = true;
		health = 100;
		slow = 100;
		x = 0;
		score = 0;
		greenV = (int) (health*2.5);
		timePause = 0;
		pass = true;
		paused2 = 0;
		paused3 = 0;
	}
	
	public void update2()
	{
		double current = System.currentTimeMillis();

		if(!KeyInput.pause && !pass)
		{
			pass = true;
			paused2 += (current - pause)/1000;
			paused3 += (current - pause)/1000;
		}
		if(KeyInput.pause && pass)
		{
			System.out.println("Clicked");
			pause = System.currentTimeMillis();
			pass = false;
		}
		
		if(Player.timeStop && !KeyInput.pause && !stop)
		{
			slow -= 0.1;
		}

		if(KeyInput.pause && KeyInput.pausePoss)
		{
			paused = System.currentTimeMillis();
			KeyInput.pausePoss = false;
		}
		
		if(KeyInput.pause || stop)
		{
			pausedTime = (int) ((System.currentTimeMillis()-paused) / 1000);
		}
		
		if((!KeyInput.pause && KeyInput.pausePoss))
		{
			timePause += pausedTime;
			KeyInput.pausePoss = false;
		}
		
		deltaT = (int) ((current - now)/1000 - timePause);
		
		if(deltaT == 4 && stop) { 
			stop = false;
			timePause += pausedTime;
		}
	}
	
	public void update()
	{
		greenV = (int) (health*2.5);
		
		if(slow <= 0)
		{
			Player.timeStop = false;
			slow = 0;
		}
		if(slow > 100)
			slow = 100;
		
		if(health > 100)
			health = 100;
		
		if(health <= 0)
		{
			health = 0;
			Game.clipDead.play();
			switch(Difficulty.difficulty)
			{
			case 1:
				save = new File("D:\\Scripts\\eclipse-workspace\\GamePract2\\src\\res\\save1.txt");
				break;
			case 2:
				save = new File("D:\\Scripts\\eclipse-workspace\\GamePract2\\src\\res\\save2.txt");
				break;
			case 3:
				save = new File("D:\\Scripts\\eclipse-workspace\\GamePract2\\src\\res\\save3.txt");
				break;
			}
			
			try {
				input = new Scanner(save);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
			savedScore = input.nextInt();
			
			if(savedScore < Hud.score)
			{
				savedScore = Hud.score;
				highscore = true;
			}
			
			try {
				output = new PrintWriter(save);
				output.println(savedScore);
				output.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Game.gameState = STATE.GameOver;
		}
		if(greenV < 0)
			greenV = 0;
		if(greenV > 255)
			greenV = 255;
	}
	
	public void render(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;

		if(stop)
		{
			g.setColor(Color.black);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
			g.fillRect(0, 0, game.getWidth(), game.getHeight());
			g.setColor(Color.white);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			g.setFont(Game.pixelMPlus.deriveFont(200f));
			if(deltaT == 3)
				g.drawString("Go!", game.getWidth()/2-100, game.getHeight()/2);
			else
				g.drawString(3-deltaT + "", game.getWidth()/2, game.getHeight()/2);
		}
		g.setFont(Game.pixelMPlus);
		g.setColor(Color.white);
		g.drawRect(19, 19, 201, 21);
		g.fillRect(game.getWidth()-250, 20, 203, 20);
		if(!KeyInput.pause && !stop)
			x = deltaT;
		g.drawString("Time: " + x + " s", 860, 35);
		g.drawString("Score: " + score, 20, 70);
		g.drawString("SlowMotion", game.getWidth()-230, 70);
		g.setColor(new Color(80, greenV, 0));
		g.fillRect(20, 20, health*2, 20);
		g.setColor(Color.cyan);
		g.fillRect(game.getWidth()-248, 22, (int)slow*2, 16);
	}
	
}
