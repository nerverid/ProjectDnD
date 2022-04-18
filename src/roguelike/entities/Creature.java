package roguelike.entities;

import roguelike.world.World;

import java.util.Map;
import java.util.Random;

public class Creature extends Entity{
	
	private int hitpoints;
	private String behaviour;
	
	public Creature (Map<String, String> creatureData, int x, int y) {
		super(creatureData, x, y);
		behaviour = creatureData.get("behaviour");
	}
	
	private int getHitpoints() {
		return hitpoints;
	}
	
	private void setHitpoints(int amount) {
		hitpoints -= 10;
	}
	
	public void move(World world, int dx, int dy) {
		if(world.isBlocked(x + dx, y + dy)!= true) {
			x += dx;
			y += dy;
		} else {
			Entity entity = world.getEntityAt(x + dx, y + dy);
			
			if(entity instanceof Item) {
				useItem((Item) entity);
			} else if (entity instanceof Creature) {
				attackCreature((Creature) entity);
			}
		}
	}
	
	public void useItem(Item item) {
		if (item.getEffect() == "health" && hitpoints <= 90) {
			hitpoints += 10;
		}
	}
}
