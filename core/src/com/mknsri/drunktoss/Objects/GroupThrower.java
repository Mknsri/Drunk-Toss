package com.mknsri.drunktoss.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mknsri.drunktoss.Screen;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;


public class GroupThrower extends GameObject {
	private enum STATE  { IDLE, ENGAGED, END};
	private Animation animFighting;
	private TextureRegion animCurrentFrame;
	private float animTime = 0f;
	private STATE currentState;
	private long fightID;
	
	public GroupThrower(float startX, float startY) {
		x = startX;
		y = startY;
		currentState = STATE.IDLE;
		
		animFighting = new Animation(0.25f, Art.groupFighting);
		this.setCollisionBoxSize(100, 100, 5);
	}
	
	@Override
	public void render(Screen g) {
		if (currentState == STATE.ENGAGED) {
			animTime += Gdx.graphics.getDeltaTime();
			animCurrentFrame = animFighting.getKeyFrame(animTime, true);
			g.drawSprite(animCurrentFrame, x, y, 0);
		} else if (currentState == STATE.IDLE) {
			g.drawSprite(Art.groupIdle, x, y, 0);
		} else if (currentState == STATE.END) {
			g.drawSprite(Art.groupEnd, x, y, 0);
		}
	}
	
	public void fightStart() {
		currentState = STATE.ENGAGED;
		fightID = SoundLib.fightSounds.loop(SoundLib.sVolume);
	}
	
	public void fightEnd() {
		currentState = STATE.END;
		
		
		SoundLib.fightSounds.stop();
	}
}