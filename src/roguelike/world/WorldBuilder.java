package roguelike.world;

import roguelike.entities.Creature;
import roguelike.entities.Tile;

import java.util.*;

public class WorldBuilder {
	private int width;
	private int height;
	private Tile[][] tiles;
	private Map<String, Map<String, String>> tileData;
	private Map<String, Map<String, String>> creatureData;
	private Set<Creature> creatures;
	
	public WorldBuilder(Map<String, Map<String, String>> titleData, Map<String, Map<String, String>> creatureData,
			int widht, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
		this.tileData = tileData;
		this.creatureData = creatureData;
		this.creatures = new HashSet<Creature>();
	}
	
	public WorldBuilder load(String file) {
		return this;
	}
	
	public Tile createTile(String type, int x, int y) {
		return new Tile(tileData.get(type), x, y);
	}
	
	public Creature createCreature(String type, int x, int y) {
		return new Creature(creatureData.get(type), x, y);
	}
	
	public WorldBuilder fill(String tileType) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y< height; y++) {
				tiles[x][y] = new Tile(tileData.get(tileType), x, y);
			}
		}
		return this;
	}
	
	public WorldBuilder addBorders() {
		for (int x = 0; x < width; x++) {
			tiles[x][0] = createTile("wall", x, 0);
			tiles[x][height-1] = createTile("wall", x, height-1);
		}
		
		for (int y=0; y< height; y++) {
			tiles[0][y] = createTile("wall", 0, y);
			tiles[width-1][y] = createTile("wall", width-1, y);
		}
		return this;
	}
	
	public WorldBuilder carveOutRoom(int topX, int topY, int width, int height) {
		for(int x= topX; x < topX+width; x++) {
			for(int y=topY; y < topY+height; y++) {
				tiles[x][y] = createTile("ground", x, y);
			}
		}
		return this;
	}
	
	public WorldBuilder popularWorld(int nrOfCreatures) {
		Random rnd = new Random();
		int rndX;
		int rndY;
		
		for(int i=0; i < nrOfCreatures; i++) {
			
			do {
				rndX = rnd.nextInt(width);
				rndY = rnd.nextInt(height);
			} while (tiles[rndX][rndY].isBlocked());
			
			List<String> creatureTypes = new ArrayList<String>(creatureData.keySet());
			creatureTypes.remove("player");
			String creatureType = creatureTypes.get(rnd.nextInt(creatureTypes.size()));
			
			creatures.add(createCreature(creatureType, rndX, rndY));
		}
		
		return this;
	}
}
