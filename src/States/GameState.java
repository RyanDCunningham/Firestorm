package States;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Entities.Entity;
import Entities.Player;
import Firestorm.Game;
import Textures.Sprite;
import Textures.SpriteSheet;
import Textures.Texture;
import world.Tile;

public class GameState implements State {

	private ArrayList<Entity> entities;
	private ArrayList<Tile> tiles;
	
	@Override
	public void init() {
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		new Player(new Sprite("rocks"), 100, 100, this);
		float x = 0;
		float y = Game.HEIGHT - 64;
		for(int i = 0; i < 10; i++) {
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 1, 1)));
			x+=64;
		}
		tiles.add(new Tile(250, 250, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1)));
		tiles.add(new Tile(400, 150, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 3, 1)));
	}

	@Override
	public void enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick(StateManager stateManager) {
		for(Entity e : entities)
			e.tick();
		
	}

	@Override
	public void render(Graphics2D g) {
		for(Entity e : entities)
			e.render(g);
		for(Tile t : tiles)
			t.render(g);
				
	}

	@Override
	public void exit() {
		entities.clear();
		
	}

	@Override
	public String getName() {
		return "Level1";
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
		
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
}
