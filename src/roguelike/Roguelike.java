package roguelike;

import roguelike.helppanel.*;
import roguelike.ui.UiDesk;

public class Roguelike {
	
	private boolean isRunning;
	private int framesPerSecond = 60;
	private int timePerLoop = 1000000000 / framesPerSecond;
	private int screenWidth, screenHeight;
	
	private World world;
	private Creature player;
	
	private HelpPanel ui;
	
	public Roguelike(int screenWidth, int sreenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		
		ui = new UiDesk(screenWidth, screenHeight, new Rectangle(mapWidth, mapHeight));
	}
	
	public static void main(String [] args) {
		Roguelike game = new Roguelike(80, 24);
		game.run();
	}
	
	public void run() {
		
	}
}
