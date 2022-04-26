package roguelike;

import java.awt.Rectangle;
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
		
		ui = new HelpPanel(screenWidth, screenHeight, new Rectangle(mapWidth, mapHeight));
	}
	
	public static void main(String [] args) {
		Roguelike game = new Roguelike(80, 24);
		game.run();
	}
	
	public void run() {
		
	}
}
