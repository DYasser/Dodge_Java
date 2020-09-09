package properties;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	protected int velX;
	protected int velY;
	private ID id;
	
	public boolean pass = true;
	
	public GameObject(int x, int y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setVelX(int vx)
	{
		velX = vx;
	}
	
	public void setVelY(int vy)
	{
		velY = vy;
	}

	public void setID(ID id)
	{
		this.id = id;
	}

	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getVelX()
	{
		return velX;
	}
	
	public int getVelY()
	{
		return velY;
	}
	
	public ID getID()
	{
		return id;
	}
}
