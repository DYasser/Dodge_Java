package properties;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entities.Player;
import states.Hud;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	private boolean shift = false,
			a = false,
			d = false,
			direction = false;
	
	public static boolean jump = false;
	protected static boolean first = true;
	
	public static boolean pause = false,
			pausePoss = false;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player)
			{
				
				if((key == KeyEvent.VK_P || key == KeyEvent.VK_ESCAPE) && !Hud.stop)
				{
					pause = !pause;
					pausePoss = true;
				}
				
				if(key == KeyEvent.VK_SHIFT) 
				{
					shift = true;
				}
				
				if(key == KeyEvent.VK_Q) 
				{
					Player.timeStop = !Player.timeStop;
				}
				
				if(key == KeyEvent.VK_A) 
				{
					a = true;
				}
				
				if(key == KeyEvent.VK_W) 
				{
					jump = true;
				}
				
				if(key == KeyEvent.VK_D) 
				{
					d = true;
				}
				
				if(key == KeyEvent.VK_A)
				{
					tempObject.setVelX(-5);
					direction = false;
				}
				
				if(key == KeyEvent.VK_D)
				{
					tempObject.setVelX(5);
					direction = true;
				}
				
				if(shift && a || shift && d)
				{
					int q;
					if(direction)
					{
						q = 1;
					}else
						q = -1;
					tempObject.setVelX(10*q);
				}
				
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player)
			{					
				
				if(key == KeyEvent.VK_SHIFT) 
				{
					shift = false;
					tempObject.setVelX(tempObject.getVelX() / 2);
				}

				if(key == KeyEvent.VK_A) 
				{
					a = false;
				}
				
				if(key == KeyEvent.VK_D) 
				{
					d = false;
				}
				
				if(!d && !a) 
				{
					tempObject.setVelX(0);
				}
				
				
			}
		}
	}

}
