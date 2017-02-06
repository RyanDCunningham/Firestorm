package Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import States.GameState;
import Textures.Sprite;

public abstract class Entity {

	protected double x, y;
	protected Sprite sprite;
	protected GameState state;
	
	public Entity(Sprite sprite, double x, double y, GameState state) {
		super();
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.state = state;
		state.addEntity(this);
	}
	
	public abstract void tick();
	
	public void render(Graphics2D g) {
		sprite.render(g, x, y);
		g.setColor(Color.GREEN);
		g.draw(getBounds()); 
	}
	
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