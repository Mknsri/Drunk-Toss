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

package com.mknsri.drunktoss.Menu;

import com.badlogic.gdx.graphics.Color;
import com.mknsri.drunktoss.Input;
import com.mknsri.drunktoss.Leaderboard.LeaderboardConnector;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;

public class LeaderboardMenu extends Menu {
	public Button weeklyBtn;
	public Button allTimeBtn;
	public Button personalBtn;
	public Button okButton;
	
	private boolean loading = false;
	private LeaderboardConnector ldc = new LeaderboardConnector();
	private String[][] scoreTable = new String[10][3];
	
	public LeaderboardMenu() {
	
		background = Art.hiScoreMenu;	
		
		/* Personal button */
		personalBtn = new Button();
		personalBtn.bg = Art.btnPersonal;
		personalBtn.btnBackground = Art.btnPersonal;
		personalBtn.btnBackgroundActive = Art.btnPersonalA;
		personalBtn.width = Art.btnPersonal.getRegionWidth();
		personalBtn.height = Art.btnPersonal.getRegionHeight();
		personalBtn.x = 50;
		personalBtn.y = 160;
		
		/* Weekly button */
		weeklyBtn = new Button();
		weeklyBtn.bg = Art.btnWeekly;
		weeklyBtn.btnBackground = Art.btnWeekly;
		weeklyBtn.btnBackgroundActive = Art.btnWeeklyA;
		weeklyBtn.width = Art.btnWeekly.getRegionWidth();
		weeklyBtn.height = Art.btnWeekly.getRegionHeight();
		weeklyBtn.x = 130;
		weeklyBtn.y = 160;
		
		/* Alltime button */
		allTimeBtn = new Button();
		allTimeBtn.bg = Art.btnAllTime;
		allTimeBtn.btnBackground = Art.btnAllTime;
		allTimeBtn.btnBackgroundActive = Art.btnAllTimeA;
		allTimeBtn.width = Art.btnAllTime.getRegionWidth();
		allTimeBtn.height = Art.btnAllTime.getRegionHeight();
		allTimeBtn.x = 210;
		allTimeBtn.y = 160;
		
		/* OK button */
		okButton = new Button();
		okButton.bg = Art.okB;
		okButton.btnBackground = Art.okB;
		okButton.btnBackgroundActive = Art.okBA;
		okButton.width = Art.okB.getRegionWidth();
		okButton.height = Art.okB.getRegionHeight();
		okButton.x = 150;
		okButton.y = 10;
	}
		
	public void render() {
		batch.begin();
		

		drawSprite(background, 0, 0, 0);
		drawSprite(personalBtn.bg,personalBtn.x,personalBtn.y,0);
		drawSprite(weeklyBtn.bg,weeklyBtn.x,weeklyBtn.y,0);
		drawSprite(allTimeBtn.bg,allTimeBtn.x,allTimeBtn.y,0);
		drawSprite(okButton.bg,okButton.x,okButton.y,0);
		
		if (loading) {
			if (!ldc.timedOut) {
				drawStringOnCenter("Loading, please wait...", Color.YELLOW);
			} else {
				drawStringOnCenter("Could not load scores", Color.YELLOW);
			}
		}
		if ( scoreTable != null ) {
			loading = false;
			for (int i = 0; i < 10; i++) {
				if (scoreTable[i][0] == null) {
					scoreTable[i][0] = "";
					scoreTable[i][1] = "";
					scoreTable[i][2] = "";
				}

				drawString(scoreTable[i][0] + "", Color.YELLOW, 60, 149 - (11 * i));
				drawString(scoreTable[i][1] + "", Color.YELLOW, 150, 149 - (11 * i));
				drawString(scoreTable[i][2] + "", Color.YELLOW, 220, 149  - (11 * i));
			}
		}
		
		batch.end();
		
		batch.setProjectionMatrix(ebingeimi.camera.combined);
		ebingeimi.cameraReset();
		
		hudBatch.begin();
		
		
		hudBatch.end();
	}
	
	public void tick(Input input) {		
		if (input.isTouched()) {
			if ( personalBtn.click(input.currentTouchX, input.currentTouchY) ) {
				weeklyBtn.clickOff();
				allTimeBtn.clickOff();
				scoreTable = ldc.getPersonalScores();
				input.tapped();
				
			}
			if ( weeklyBtn.click(input.currentTouchX, input.currentTouchY) ) {
				allTimeBtn.clickOff();
				personalBtn.clickOff();
				scoreTable = ldc.getWeeklyScores();
				input.tapped();
			}
			if ( allTimeBtn.click(input.currentTouchX, input.currentTouchY) ) {
				weeklyBtn.clickOff();
				personalBtn.clickOff();
				scoreTable = ldc.getAllTimeScores();
				SoundLib.playSound(Button.clickSound);
				input.tapped();
			};
			okButton.click(input.currentTouchX, input.currentTouchY);
		}
		if (!input.isTouched()) {
			if (personalBtn.activated) {
				if (scoreTable[0][0] == "") {
					loading = true;
				}
			}
			if (weeklyBtn.activated) {
				if (scoreTable[0][0] == "") {
					loading = true;
				}
			} 
			if (allTimeBtn.activated) {
				if (scoreTable[0][0] == "") {
					loading = true;
				}
			}
			if (okButton.activated || input.menuPressed) {
				input.menuPressed = false;
				this.setScreen(new TitleMenu());
			}
		}
	}	


}
