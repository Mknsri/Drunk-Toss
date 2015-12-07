package com.mknsri.drunktoss.Objects;

import java.util.ArrayList;
import java.util.List;

import com.mknsri.drunktoss.Screen;
import com.mknsri.drunktoss.Objects.GameObject;

public class ObjectFactory {
	public List<GameObject> objectList = new ArrayList<GameObject>();
	public static enum ObjectTypes { BOMB };
	private GroupThrower fightingObject;
	
	public void tickList(PlayerObject player) {
		if (player.moving) {
			for (int i = 0; i < objectList.size(); i++) {
				GameObject obj = objectList.get(i);
				// Check for collision
				if(player.checkCollision(player, obj) && obj.collisionDetected == false) {
					// BOMB BLOCK
					obj.collisionDetected = true;
					if (obj instanceof BumBomb) {
						float launchPower = 0;
						if (player.yVel > 0) {
							launchPower = player.yVel * 3f;
						} else {
							launchPower = player.yVel * -3f;
						}
						player.launchPlayer(launchPower, 5);
					// THROWER BLOCK
					} else if (obj instanceof GroupThrower) {
						player.fighting = true;
						fightingObject = (GroupThrower) obj;
						fightingObject.fightStart();
					}
				}
	
				// Cleanup items that the player has passed
				if(player.x - obj.x > 300) {
					deleteObject(obj);
				}
			}
			// Don't spawn objects if player is not moving	
			if (Math.random() * 1000 < 10) {
				int y = (int) (10 + ((Math.random() * 4)+1));
				spawnObject(player.x + 300, y);
			}
		}
		if (!player.fighting && fightingObject != null ) {
			fightingObject.fightEnd();
		}
	}
	
	public void drawList(Screen g) {
		for (int i = 0; i < objectList.size(); i++) {
			GameObject obj = objectList.get(i);
			obj.render(g);
		}
	}
	
	public void deleteObject(GameObject object) {
		objectList.remove(object);
	}
	
	public void addObject(GameObject object) {
		objectList.add(object);
	}
	
	public void spawnObject(float x, float y) {
		int dice = (int)((Math.random() * 3)+1);
		if (dice == 1 || dice == 2) {
			addObject(new BumBomb(x, y));
		} else if (dice == 3) {
			addObject(new GroupThrower(x,y));
		}
	}
	
	public void tick() {
		
	}
}