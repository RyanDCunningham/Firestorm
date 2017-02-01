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
		return new Rectangle((int)x, (int)y - 2, sprite.getWidth(), 4);
	}
	
	public Rectangle getBottom() {
		return new Rectangle((int)x, (int) y + sprite.getHeight() - 3, sprite.getWidth(), 4);
	}
	
	public Rectangle getLeft() {
		return new Rectangle((int)x - 2, (int)y, 4, sprite.getHeight());
	}

	public Rectangle getRight() {
		return new Rectangle((int) x + sprite.getWidth() - 3, (int)y, 4, sprite.getHeight());
	}
	
}