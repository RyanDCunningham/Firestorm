package Entities;

import java.util.ArrayList;

import Entities.Entity;
import Entities.Mob;
import States.GameState;
import Textures.Sprite;


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
		if(hasHorizontalCollision()) x += dx;
		if(hasVerticalCollision()) y += dy;
		if(isMovingHorizontal()) x += dx;
		if(isMovingVertical()) y += dy;
	}
	
	protected boolean isMovingVertical() {
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
	
	protected boolean isMovingHorizontal() {
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

}
