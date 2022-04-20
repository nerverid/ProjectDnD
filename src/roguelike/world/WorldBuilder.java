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
	private Set<Creature> creature;
	
	public WorldBuilder(Map<String, Map<String, String>> titleData, Map<String, Map<String, String>> creatureData,
			int widht, int height) {
		
	}
}
