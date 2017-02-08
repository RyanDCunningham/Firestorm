package Entities;

import Entities.Entity;
import Entities.Mob;
import States.GameState;
import Textures.Sprite;
import World.Tile;

public class BlockTile extends Mob {
	
	protected double dx, dy;
	//private GameState state;

	public BlockTile(double y, double x, Sprite sprite, GameState state) {
		super(sprite, x, y, state);
	}
	
	public void tick() {
		move();
	}

	//Mob mob = new Mob();
	//private ArrayList<Mob> mobs;
	//mobs = new ArrayList<Mob>();
	//Mob m : mobs;
	
	
	public void move() {
		//if(hasHorizontalCollision()) x += dx;
		//if(hasVerticalCollision()) y += dy;
		if(isPushingHorizontal() && !hasHorizontalCollision()) x += dx;
		if(isPushingVertical() && !hasVerticalCollision()) y += dy;
	}
	
	protected boolean isPushingVertical() {
		for(int i = 0; i < state.getEntity().size(); i++) {
			Entity e = state.getEntity().get(i);
			if(getBounds().intersects(e.getTop())) {
				dy = -2;
				System.out.println("Down");
				return true;
			}
			if(getBounds().intersects(e.getBottom())) {
				dy = 2;
				System.out.println("Up");
				return true;
			}
		}
		
		return false;
	}
	
	protected boolean isPushingHorizontal() {
		for(int i = 0; i < state.getEntity().size(); i++) {
			Entity e = state.getEntity().get(i);
			if(getBounds().intersects(e.getRight())) {
				dx = 2;
				System.out.println("Left");
				return true;
			}
			if(getBounds().intersects(e.getLeft())) {
				dx = -2;
				System.out.println("Right");
				return true;
			}	
		}
		
		return false;
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
