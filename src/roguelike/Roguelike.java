package roguelike;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import roguelike.entities.Creature;
import roguelike.helppanel.*;
import roguelike.ui.UiDesk;
import roguelike.world.World;

public class Roguelike {
	
	private boolean isRunning;
	private int framesPerSecond = 60;
	private int timePerLoop = 1000000000 / framesPerSecond;
	
	private World world;
	private Creature player;
	
	private Map<String, Map<String, String>> creatureData;
	private Map<String, Map<String, String>> tileData;
	private Map<String, Map<String, String>> itemData;
	
	private int screenWidth;
	private int screenHeight;
	
	private Rectangle gameViewArea;
	
	private static final int mapWidth = 100;
	private static final int mapHeight = 100;
	
	private HelpPanel ui;
	
	public Roguelike(int screenWidth, int sreenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		gameViewArea = new Rectangle(screenWidth, screenHeight-5);
		
		ui = new HelpPanel(screenWidth, screenHeight, new Rectangle(mapWidth, mapHeight));
		
		creatureData = loadData(Paths.get("src", "roguelike", "creature.txt").toString());
		tileData = loadData(Paths.get("src", "roguelike", "tiles.txt").toString());
		itemData = loadData(Paths.get("src", "rouguelike", "item.txt").toString());
		
		createWorld();
	}
	
	public Map<String, Map<String, String>> loadData(String file){
		Map<String, Map<String, String>> entityMap = new HashMap<>();
		String line = "";
		String [] attributeNames = new String[10];
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			line = br.readLine();
			
			if(line != null) {
				attributeNames = line.split(", ");
			}
			
			while((line = br.readLine()) != null) {
				String [] data = line.split(", ");
				Map<String, String> entityData = new HachMap<>();
				
				for(int i=0; i<attributeNames.length; i++) {
					entityDate.put(attributeNames[i], data[i]);
				}
				
				String name = data[1];
				entityMap.put(name, entityData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return entityMap;
	}
	
	public void createWorld() {
		player = new Creature(creatureData.get("player"), 10, 10);
		world = new WorldBuilder(tileData, creatureData, mapWidth, mapHeight)
				.fill("wall")
				.createRandomWalkCave(12232, 10, 6000)
				.populateWorld(10)
				.build();
		world.player = player;
		world.addEntity(player);
	}
	
	public static void main(String [] args) {
		Roguelike game = new Roguelike(80, 24);
		game.run();
	}
	
	public void run() {
		
	}
}
