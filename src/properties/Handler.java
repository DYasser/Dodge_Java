package properties;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void update()
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject temp = object.get(i);
			temp.update();
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			GameObject temp = object.get(i);
			temp.render(g);
		}
	}
	
	public void addObject(GameObject obj)
	{
		object.add(obj);
	}
	
	public void removeObject(GameObject obj)
	{
		object.remove(obj);
	}
}
