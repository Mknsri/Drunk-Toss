package com.mknsri.drunktoss.Objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mknsri.drunktoss.Screen;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;

public class PlayerObject extends GameObject {
	private static float FLOOR_LEVEL = 10f;
	private double distanceTraveled = 0;
	public boolean bounced = false, launched = false;
	private float launchPower = 0;
	private Animation flyAnim;
	private float flyAnimTime;
	private TextureRegion flyFrame;
	public boolean fighting = false;
	
	private Sprite pokeBod;
	private Sprite pokeFA;
	private Sprite pokeBA;
	
	public int fightHealth = 50;
	public int fightPower = 0;
	
	public PlayerObject(int x, int y) {
		this.x = x;
		this.y = y;
		setCollisionBoxSize(69, 58, 10);
		
		pokeBod = new Sprite(Art.pokeBody);
		pokeFA =  new Sprite(Art.pokeFrontArm);
		pokeBA =  new Sprite(Art.pokeBackArm);
		
		pokeBA.setOrigin(40f, 70f);
		pokeFA.setOrigin(40f, 70f);
		pokeBA.setPosition(x, y);
		pokeFA.setPosition(y, x);
		
		
		flyAnim = new Animation(0.08f, Art.ilpoFly);
		flyAnimTime = 0f;
	}
	
	@Override 
	public void render(Screen g) {
		if (!fighting) {
			if (!launched) {
					pokeBA.setRotation(launchPower*-4f);
					pokeBA.draw(g.batch);
					g.drawSprite(pokeBod, x, y, 0);
					pokeFA.setRotation(launchPower*-4f);
					pokeFA.draw(g.batch);
			} else if (launched && !bounced && moving) {
				flyAnimTime += Gdx.graphics.getDeltaTime();
				flyFrame = flyAnim.getKeyFrame(flyAnimTime, true);
				g.drawSprite(flyFrame, x, y, yVel * 0.1f);
				
			} else if (launched && bounced && moving) {
				g.drawSprite(Art.ilpoDuck, x, y, yVel * 0.5f);
			} else if (launched && !moving) {
				g.drawSprite(Art.ilpoEnd, x, y, 0);
			}
		}
	}
	
	// Parameter needed to draw starting position
	public void tick(float lPower) {
		launchPower = lPower;
		
		if (!fighting) {
			calculateGravity();
			calculateFlight();
			calculateFriction();
			if (moving == true) {
				distanceTraveled = x;
			}
		}
	}
	
	private void calculateGravity() {
		// Gravity
		if (y > FLOOR_LEVEL) {
			yVel -= 0.1f;
		// Bouncing
		} else if (y < 11 && yVel < 0) {
			yVel = yVel * -0.6f;
			if (yVel > 2) {
				bounced = false;
			}
			int dice = (int)((Math.random() * 3)+1);
			if (moving) {
				if (dice == 1) {
					SoundLib.playSound(SoundLib.boing1);
				} else if (dice == 2) {
					SoundLib.playSound(SoundLib.boing2);
				} else if (dice == 3) {
					SoundLib.playSound(SoundLib.boing3);
				}
			}
		// Rounding to stop bouncing when velocity too low
		} else if (yVel < 1 && yVel > -1) {
			yVel = 0;
		}
		y = y + yVel;
	}
	
	private void calculateFlight() {
		x += xVel;
		y += yVel;
	}
	
	public void launchPlayer(float power, float angle) {
		xVel += power;
		yVel += angle;
		moving = true;
		bounced = false;
	}
	
	private void calculateFriction() {
		if (y > FLOOR_LEVEL - 3 && y < FLOOR_LEVEL + 3 && xVel > 0) {
			xVel = xVel * 0.8f;
		}
		
		if (xVel < 0.5f) {
			xVel = 0;
			moving = false;
		}
	}

	public void checkBounce() {
		if (bounced == false) {
			if (yVel > -5) {
				yVel--;
			} else {
				yVel = -5;
			}
			bounced = true;
		}
	}
	
	public double getPoints() {
		 return distanceTraveled;
	}
	
	public void fighting() {
		fighting = true;
		xVel = 0;
		yVel = 0;
	}
	
	public void fightTick() {
		fightHealth--;
		if (fightHealth == 0) {
			xVel = 0;
			yVel = 0;
			moving = false;
			fighting = false;
		} else if (fightHealth >= 100) {
			fighting = false;
			fightHealth = 50;
			float p;
			p = (float) (Math.random() * 10)+5;
			launchPlayer(p, 5);
		}
	}
	
	public void fightTouch() {
		fightHealth += 15;
	}
}