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

import com.mknsri.drunktoss.DrunkToss;
import com.mknsri.drunktoss.Input;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;

public class OptionsMenu extends Menu {
	public Button checkButtonSound;
	public Button checkButtonMusic;
	public Button okButton;
	private int buttonBuffer = 20;
	
	public OptionsMenu() {
	
	background = Art.optionsBg;	
	
	/* Sound button */
	checkButtonSound = new Button();
	checkButtonSound.bg = Art.checkB;
	checkButtonSound.btnBackground = Art.checkB;
	checkButtonSound.btnBackgroundActive = Art.checkBA;
	checkButtonSound.width = Art.checkB.getRegionWidth() + buttonBuffer;
	checkButtonSound.height = Art.checkB.getRegionHeight();
	checkButtonSound.x = 220;
	checkButtonSound.y = 40;
	checkButtonSound.toggleButton = true;
	checkButtonSound.activated = DrunkToss.prefs.getBoolean("sound",true);
	if (checkButtonSound.activated) {
		checkButtonSound.clickOn();
	}

	/* Music button */
	checkButtonMusic = new Button();
	checkButtonMusic.bg = Art.checkB;
	checkButtonMusic.btnBackground = Art.checkB;
	checkButtonMusic.btnBackgroundActive = Art.checkBA;
	checkButtonMusic.width = Art.checkB.getRegionWidth() + buttonBuffer;
	checkButtonMusic.height = Art.checkB.getRegionHeight();
	checkButtonMusic.x = 220;
	checkButtonMusic.y = 80;
	checkButtonMusic.toggleButton = true;
	checkButtonMusic.activated = DrunkToss.prefs.getBoolean("music",true);
	if (checkButtonMusic.activated) {
		checkButtonMusic.clickOn();
	}
	
	/* OK button */
	okButton = new Button();
	okButton.bg = Art.okB;
	okButton.btnBackground = Art.okB;
	okButton.btnBackgroundActive = Art.okBA;
	okButton.width = Art.okB.getRegionWidth();
	okButton.height = Art.okB.getRegionHeight();
	okButton.x = 60;
	okButton.y = 12;
	
	}
	
	public void render() {
		batch.begin();
		
		drawSprite(background, 0, 0, 0);
		drawSprite(checkButtonSound.bg,checkButtonSound.x+10,checkButtonSound.y,0);
		drawSprite(checkButtonMusic.bg,checkButtonMusic.x+10,checkButtonMusic.y,0);
		drawSprite(okButton.bg,okButton.x,okButton.y,0);
		
		batch.end();
		
		batch.setProjectionMatrix(ebingeimi.camera.combined);
		ebingeimi.cameraReset();
		
		hudBatch.begin();
		
		
		hudBatch.end();
	}
	
	public void tick(Input input) {		
		if (input.isTouched()) {
			okButton.click(input.currentTouchX, input.currentTouchY);
			if (checkButtonSound.click(input.currentTouchX, input.currentTouchY) || 
				checkButtonMusic.click(input.currentTouchX, input.currentTouchY)) {
				SoundLib.playSound(Button.clickSound);
				input.tapped();
			}
		}
		if (!input.isTouched()) {
			if (!checkButtonSound.activated) {
				DrunkToss.prefs.putBoolean("sound",false);
				SoundLib.muteSound = true;
			} else {
				DrunkToss.prefs.putBoolean("sound",true);
				SoundLib.muteSound = false;
			}
			
			if (!checkButtonMusic.activated) {
				DrunkToss.prefs.putBoolean("music",false);
				SoundLib.muteMusic = true;
				SoundLib.muteMusic();
			} else {
				DrunkToss.prefs.putBoolean("music",true);
				SoundLib.muteMusic = false;
				SoundLib.muteMusic();
			}
			if (okButton.activated || input.menuPressed ) {
				input.menuPressed = false;
				SoundLib.playSound(Button.clickSound);
				DrunkToss.prefs.flush();
				this.setScreen(new TitleMenu());
			}
		}
	}	
}
