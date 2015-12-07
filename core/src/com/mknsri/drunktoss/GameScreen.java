/*
The MIT License (MIT)

Copyright (c) 2015 Markus Mikonsaari

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */

package com.mknsri.drunktoss;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.mknsri.drunktoss.Input;
import com.mknsri.drunktoss.Leaderboard.LeaderboardConnector;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;
import com.mknsri.drunktoss.Menu.Button;
import com.mknsri.drunktoss.Menu.TitleMenu;
import com.mknsri.drunktoss.Objects.ObjectFactory;
import com.mknsri.drunktoss.Objects.PlayerLauncher;
import com.mknsri.drunktoss.Objects.PlayerObject;

public class GameScreen extends Screen {
	private float launchAng = 0, launchPwr = 0;
	PlayerObject ilpo;
	PlayerLauncher launcher;
	ObjectFactory factory;
	Background bg;
	private int time;
	private int tutorialPos;
	int scoreToUpload = 0;
	private boolean launched, gameOver, paused, scoresUploaded = false;
	private Button uploadCurrentBtn;
	private Button uploadHiScoreBtn;
	private LeaderboardConnector ldc = new LeaderboardConnector();
	private TextListener uploadName = new TextListener();
	
	public GameScreen() {
		
		/* Upload Hi Score -Button */
		uploadHiScoreBtn = new Button();
		uploadHiScoreBtn.bg = Art.uploadB;
		uploadHiScoreBtn.btnBackground = Art.uploadB;
		uploadHiScoreBtn.btnBackgroundActive = Art.uploadBA;
		uploadHiScoreBtn.width = Art.uploadB.getRegionWidth();
		uploadHiScoreBtn.height = Art.uploadB.getRegionHeight();
		uploadHiScoreBtn.x = 160;
		uploadHiScoreBtn.y = 48;
		
		if (DrunkToss.prefs.getBoolean("firstrun",true)) {
			tutorialPos = 0;
		} else {
			tutorialPos = 2;
		}
				
		ilpo = new PlayerObject(10,11);
		launcher = new PlayerLauncher();
		factory = new ObjectFactory();
		bg = new Background(-50, -100);
		bg.loadBackgroundSet(0);
				
		time = 0;
		launched = false;
		gameOver = false;
		paused = false;
		
	}
	
	public void render() {
		batch.begin();
		
		bg.drawBackground(this);
		factory.drawList(this);
		ilpo.render(this);
		
		batch.end();
		
		// Camera
		batch.setProjectionMatrix(ebingeimi.camera.combined);
		hudBatch.setProjectionMatrix(ebingeimi.hudCamera.combined);
		ebingeimi.camera.position.set(ilpo.x + 100, ilpo.y + 50, 0);
		
		
		// HUD drawing
		hudBatch.begin();
		
		// Tutorial drawing
		if (tutorialPos < 2) {
			if (tutorialPos == 0) {
				drawSpriteOnHUD(Art.tut1, 0,0,0);
			} else if (tutorialPos == 1) {
				drawSpriteOnHUD(Art.tut2, 0,0,0);
			}
		} else {
			// Distance
			drawStringOnHUD("Distance: " + Math.round(ilpo.getPoints()/20) + "m", Color.YELLOW, 10, DrunkToss.VIEWPORT_HEIGHT-10);
			
			// Game over
			if (gameOver == true) {
				int endPoints = (int) (ilpo.getPoints()/20);
				drawSpriteOnHUD(Art.hiScoreScreen, 70, 40, 0);
				drawStringOnHUD(endPoints + "m",Color.YELLOW, 100, 160);
				int highScore = DrunkToss.prefs.getInteger("hiscore",0);
				// New high score
				if (endPoints > highScore) {
					DrunkToss.prefs.putInteger("hiscore", endPoints);
					DrunkToss.prefs.flush();
					highScore = endPoints;
					drawStringOnHUD("(NEW!)", Color.YELLOW, 180, 100);
				}
				drawStringOnHUD(highScore + "m",Color.YELLOW, 100, 100);
				// Draw upload button
				drawSpriteOnHUD(uploadHiScoreBtn.bg, uploadHiScoreBtn.x, uploadHiScoreBtn.y,0);
				drawSpriteOnHUD(Art.okB, 100, 50, 0);
				if (scoresUploaded == true) {
					drawStringOnHUD("Done!", Color.GREEN, 175, 100);
				}
			}
			
			// Game running
			else {
	
				// Start position
				if (!ilpo.launched) {
					drawSpriteOnHUD(Art.angle, 10, 90, launcher.getAngle()*0.1f);
				} else {
				}
				// Fighting
				if (ilpo.fighting) {
					Color fc = Color.RED;
					if (ilpo.fightHealth < 30) {
						fc = Color.RED;
					}
					else if (ilpo.fightHealth < 60) {
						fc = Color.YELLOW;
					} else if (ilpo.fightHealth < 100) {
						fc = Color.GREEN;
					}
					drawStringOnHUDCenter("TAP TO WIN THE FIGHT!", fc);
				}
			}
		}
		hudBatch.end();
		
	}
	
	public void tick(Input input) {
		if (input.menuPressed == true) {
			input.menuPressed = false;
			setScreen(new TitleMenu());
		}
		// Tutorials
		if (tutorialPos < 2) {
			paused = true;
			if (input.tapped()) {
				tutorialPos++;
			}
		} else {
			DrunkToss.prefs.putBoolean("firstrun", false);
			DrunkToss.prefs.flush();
		}
		if (!paused) {
			time++;
			
			if (launched == false) {
				if (input.isTouched() && launcher.pressedState == false) {
					launchAng = launcher.getAngle();
					launcher.pressedState = true;
				} else if (input.isTouched() && launcher.pressedState == true) {
					if (time % 10 == 0) {
						launcher.calculatePower();
					}
				} else if (!input.isTouched() && launcher.pressedState == true) {
					launchPwr = launcher.getPower();
					ilpo.launchPlayer(launchPwr, launchAng);
					SoundLib.playSound(SoundLib.throwSound);
					launched = true;
					ilpo.launched = true;
					ebingeimi.gamesPlayed++;
				} else {
					if (time % 10 == 0) {
						launcher.calculateAngle();
					}
				}
			} else if (input.isTouched() && ilpo.xVel > 0 && !ilpo.fighting){
				ilpo.checkBounce();
			} else if (ilpo.fighting) {
				if (input.tapped()) {
					ilpo.fightTouch();
				}
				ilpo.fightTick();
			}

			if (launched == true && ilpo.xVel == 0 && ilpo.yVel == 0) {
				gameOver = true;

				if (gameOver == true && input.tapped()) {
					// If upload buttons were not pressed
					if (!uploadHiScoreBtn.click(input.currentTouchX, input.currentTouchY) || scoresUploaded == true) {
						setScreen(new GameScreen());
					} else {
						// Upload scores
						if (uploadHiScoreBtn.click(input.currentTouchX, input.currentTouchY)) {
							scoreToUpload = DrunkToss.prefs.getInteger("hiscore");
						}
						Gdx.input.getTextInput(uploadName, "Insert your name", "", "Name");
					}
				}

				if (uploadName.value != "") {
					ldc.insertHighScore(scoreToUpload, uploadName.value);
					scoresUploaded = true;
				}
			}
			
			bg.tickBackground(ilpo);
			ilpo.tick(launcher.getPower());
			factory.tickList(ilpo);
			
		}
		// pause check
		if (input.isPaused()) {
			paused = true;
		}
		else if (!input.isPaused()) {
			paused = false;
		}
	}
}