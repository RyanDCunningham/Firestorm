package World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Textures.Sprite;

public class Tile {

	protected float x, y;
	protected Sprite sprite;
	protected boolean solid;
	
	public Tile(float x, float y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = true;
	}
	
	public void render(Graphics2D g){
		sprite.render(g, x, y);
		
		// Visual Bounds for collision
		g.setColor(Color.RED);
		//g.draw(getTop());
		g.setColor(Color.BLUE);
		//g.draw(getBottom());
		g.setColor(Color.MAGENTA);
		//g.draw(getLeft());
		g.setColor(Color.ORANGE);
		//g.draw(getRight());
		
	}
	
	//tried to make the collision suck less, 
	//still needs a tweek but it is close enough to perfect for now
	// 1-31-2017
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, sprite.getWidth(), sprite.getHeight());
	}
	
	public Rectangle getTop() {
		return new Rectangle((int)x + 1, (int)y - 1, sprite.getWidth() - 2, 1);
	}
	
	public Rectangle getBottom() {
		return new Rectangle((int)x + 1, (int) y + sprite.getHeight() + 1, sprite.getWidth() - 2, 1);
	}
	
	public Rectangle getLeft() {
		return new Rectangle((int)x - 1, (int)y + 1, 1, sprite.getHeight() - 2);
	}

	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() + 1, (int)y + 1, 1, sprite.getHeight() - 2);
	}
	
}
