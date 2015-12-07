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

import com.badlogic.gdx.graphics.Color;

import com.mknsri.drunktoss.Input;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;
import com.mknsri.drunktoss.Menu.TitleMenu;

public class TitleScreen extends Screen {
	Art titleScreen;
	private int time;
	CharSequence msg;
	
	
	public TitleScreen() {
		if (DrunkToss.prefs.getBoolean("music") == false) {
			SoundLib.muteMusic = true;
			SoundLib.muteMusic();
		}
		SoundLib.menuMusic.play();
		SoundLib.menuMusic.setLooping(true);
	}
	
	public void render() {
		batch.begin();
		if (time < 100 ) {
			drawStringOnCenter("a mknsri game", Color.WHITE);
		}
		else {
			this.drawSprite(Art.titleLogo, 0, 0, 0);
			if (time > 150) {
				drawString("Touch to start", Color.WHITE, 110, 60);
				if (time > 250) {
					time = 100;
				}
			}
		}
		
		batch.end();
		batch.setProjectionMatrix(ebingeimi.camera.combined);
		ebingeimi.cameraReset();
		hudBatch.begin();
		hudBatch.end();
	}
	
	public void tick(Input input) {
		time++;
		if (input.isTouched()) {
			input.touchUp(0, 0, 0, 0);
			setScreen(new TitleMenu());
		}
		
	}
	
	
}