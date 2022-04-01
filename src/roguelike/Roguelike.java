package roguelike;

import roguelike.helppanel.*;

public class Roguelike {
	
	private int screenWidth, screenHeight;
	
	private HelpPanel ui;
	
	public Roguelike(int screenWidth, int sreenHeight){
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public static void main(String [] args) {
		Roguelike game = new Roguelike(80, 24);
		game.run();
	}
	
	public void run() {
		
	}
}
