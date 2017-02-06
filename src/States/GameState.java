package States;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Entities.BlockTile;
import Entities.Entity;
import Entities.Player;
import Firestorm.Game;
import Textures.Sprite;
import Textures.SpriteSheet;
import Textures.Texture;
import World.FloorTile;
import World.Tile;

public class GameState implements State {

	private ArrayList<Entity> entities;
	private ArrayList<Tile> tiles;
	private ArrayList<FloorTile> floorTiles;
	
	@Override
	public void init() {
		entities = new ArrayList<Entity>();
		tiles = new ArrayList<Tile>();
		floorTiles = new ArrayList<FloorTile>();
		new Player(new Sprite("rocks"), 100, 100, this);
		float x = 0;
		float y = 0;
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 10; j++) {
				floorTiles.add(new FloorTile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 4, 1)));
				x+=64;
			}
			x=0;
			y+=64;
		}
		for(int i = 0; i < 9; i++) {
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1)));
			x+=64;
		}
		for(int i = 0; i < 7; i++) {
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1)));
			y-=64;
		}
		for(int i = 0; i < 9; i++) {
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1)));
			x-=64;
		}
		for(int i = 0; i < 6; i++) {
			tiles.add(new Tile(x, y, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1)));
			y+=64;
		}
		entities.add(new BlockTile(250, 250, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1), this));
		//tiles.add(new Tile(400, 150, new Sprite(new SpriteSheet(new Texture("terrain"), 64), 2, 1)));
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
		for(FloorTile ft : floorTiles)
			ft.render(g);
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
	
	//public void addBlockTile(BlockTile tile) {
	//	tiles.add(tile);
		
	//}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	public ArrayList<FloorTile> getFloorTiles() {
		return floorTiles;
	}
	
	public ArrayList<Entity> getEntity() {
		return entities;
	}
	
}
