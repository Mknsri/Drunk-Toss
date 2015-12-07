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

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Objects.PlayerObject;


public class Background extends Art {
	TextureRegion bgList[];
	TextureRegion currentBg;
	TextureRegion nextBg;
	float currentX, currentY;
	float nextX, nextY;
	
	public Background(float startX, float startY) {
		this.currentX = startX;
		this.currentY = startY;
		bgList = new TextureRegion[10];
	}
		
	public void loadBackgroundSet(int setIndex) {
		bgList = Art.returnBgSet("dbl");
		
		currentBg = bgList[(int) ((Math.random() * 9) +1)];
		nextBg = bgList[(int) ((Math.random() * 9)) +1];
		
		// Starting positions
		nextX = currentX + currentBg.getRegionWidth();
		nextY = currentY;
	}
	
	public void tickBackground(PlayerObject player) {
		float playerX = player.x;
		
		if (playerX > nextX + (nextBg.getRegionWidth()/2) ) {
			setNextBackground();
		}
		
	}
	
	//  replace nextBg with currentBg
	// load new values for nextBg
	public void setNextBackground() {
		currentBg = nextBg;

		currentX = nextX;
		currentY = nextY;
		
		while (nextBg == currentBg) {
			nextBg = bgList[(int) (Math.random() * 10)];			
		}
		nextX = currentX + currentBg.getRegionWidth();
		
	}
	
	
	public void drawBackground(Screen g) {
		g.drawSprite(currentBg, currentX, currentY, 0);
		g.drawSprite(nextBg, nextX, nextY, 0);
	}
}