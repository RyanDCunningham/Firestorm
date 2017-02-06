package Entities;

import States.GameState;
import Textures.Sprite;
import World.Tile;

public abstract class Mob extends Entity {

	protected double dx, dy;
	
	public Mob(Sprite sprite, double x, double y, GameState state) {
		super(sprite, x, y, state);
	}
	

	public void tick() {
		move();
	}

	public void move() {
		if(!hasHorizontalCollision()) x += dx;
		if(!hasVerticalCollision()) y += dy;
	}
	
	protected boolean hasVerticalCollision() {
		for(int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if(getBounds().intersects(t.getTop()) && dy > 0) {
				dy = 0;
				//System.out.println("Down");
				return true;
			}
			if(getBounds().intersects(t.getBottom()) && dy < 0) {
				dy = 0;
				//System.out.println("Up");
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean hasHorizontalCollision() {
		for(int i = 0; i < state.getTiles().size(); i++) {
			Tile t = state.getTiles().get(i);
			if(getBounds().intersects(t.getRight()) && dx < 0) {
				dx = -1;
				//System.out.println("Left");
				return true;
			}
			if(getBounds().intersects(t.getLeft()) && dx > 0) {
				dx = -1;
				//System.out.println("Right");
				return true;
			}	
		}
		
		return false;
	}
	
}
