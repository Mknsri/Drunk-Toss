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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.mknsri.drunktoss.GameScreen;
import com.mknsri.drunktoss.Input;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;

public class TitleMenu extends Menu {
	public Button newGameButton;
	public Button hiscoresButton;
	public Button optionsButton;
	public Button quitButton;
	
	private Animation bgAnimation;
	private float animTime;
	
	public TitleMenu() {
		
		/* Menu */
		
		bgAnimation = new Animation(0.75f,Art.menuBg);
		
		/* New game button */
		newGameButton = new Button();
		newGameButton.bg = Art.newgameB;
		newGameButton.btnBackground = Art.newgameB;
		newGameButton.btnBackgroundActive = Art.newgameBA;
		newGameButton.width = Art.newgameB.getRegionWidth();
		newGameButton.height = Art.newgameB.getRegionHeight();
		newGameButton.x = 200;
		newGameButton.y = 160;
		
		/* Hi-scores button */
		hiscoresButton = new Button();
		hiscoresButton.bg = Art.hiscoresB;
		hiscoresButton.btnBackground = Art.hiscoresB;
		hiscoresButton.btnBackgroundActive = Art.hiscoresBA;
		hiscoresButton.width = Art.hiscoresB.getRegionWidth();
		hiscoresButton.height = Art.hiscoresB.getRegionHeight();
		hiscoresButton.x = 200;
		hiscoresButton.y = 120;
		
		/* optionsButton button */
		optionsButton = new Button();
		optionsButton.bg = Art.optionsB;
		optionsButton.btnBackground = Art.optionsB;
		optionsButton.btnBackgroundActive = Art.optionsBA;
		optionsButton.width = Art.optionsB.getRegionWidth();
		optionsButton.height = Art.optionsB.getRegionHeight();
		optionsButton.x = 200;
		optionsButton.y = 80;

		
		/* quit button */
		quitButton = new Button();
		quitButton.bg = Art.quitB;
		quitButton.btnBackground = Art.quitB;
		quitButton.btnBackgroundActive = Art.quitBA;
		quitButton.width = Art.quitB.getRegionWidth();
		quitButton.height = Art.quitB.getRegionHeight();
		quitButton.x = 200;
		quitButton.y = 40;
		
	}
	
	public void render() {
		batch.begin();
		
		animTime += Gdx.graphics.getDeltaTime();
		background = bgAnimation.getKeyFrame(animTime, true);
		drawSprite(background,0,0,0);
		
		
		drawSprite(newGameButton.bg, newGameButton.x, newGameButton.y, 0);
		drawSprite(hiscoresButton.bg, hiscoresButton.x, hiscoresButton.y, 0);
		drawSprite(optionsButton.bg, optionsButton.x, optionsButton.y, 0);
		drawSprite(quitButton.bg, quitButton.x, quitButton.y, 0);
		batch.end();
		
		batch.setProjectionMatrix(ebingeimi.camera.combined);
		ebingeimi.cameraReset();
		
		hudBatch.begin();

		hudBatch.end();
	}
	
	public void tick(Input input) {
		if (input.isTouched()) {
			newGameButton.click(input.currentTouchX, input.currentTouchY);
			hiscoresButton.click(input.currentTouchX, input.currentTouchY);
			optionsButton.click(input.currentTouchX, input.currentTouchY);
			quitButton.click(input.currentTouchX, input.currentTouchY);
		}
		if (!input.isTouched()) {
			if (newGameButton.activated) {
				SoundLib.playSound(Button.clickSound);
				SoundLib.menuMusic.stop();
				this.setScreen(new GameScreen());
			} else if (hiscoresButton.activated) {
				SoundLib.playSound(Button.clickSound);
				this.setScreen(new LeaderboardMenu());
			} else if (optionsButton.activated) {
				SoundLib.playSound(Button.clickSound);
				this.setScreen(new OptionsMenu());
			} else if (quitButton.activated || input.menuPressed) {
				Gdx.app.exit();
			}
		}
	}
	
}
