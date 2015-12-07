package com.mknsri.drunktoss.Objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mknsri.drunktoss.Screen;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Objects.GameObject;


public class BumBomb extends GameObject {
	private static Animation pummiIdle;
	private static Animation pummiTriggered;
	private float animTime = 0f;
	private static TextureRegion pummiFrame;
	
	public BumBomb(float startX, float startY) {
		x = startX;
		y = startY;

		pummiIdle = new Animation(0.5f, Art.pummiIdle);
		pummiTriggered = new Animation(1f, Art.pummiTriggered);
		this.setCollisionBoxSize(100, 50, 5);
	}
	
	@Override
	public void render(Screen g) {
		animTime += Gdx.graphics.getDeltaTime();
		if (collisionDetected) {
			pummiFrame = pummiTriggered.getKeyFrame(animTime);
			g.drawSprite(pummiFrame, x, y, 0);
		} else {
			pummiFrame = pummiIdle.getKeyFrame(animTime, true);
			g.drawSprite(pummiFrame, x, y, 0);
		}
	}
	
}