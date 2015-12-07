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

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mknsri.drunktoss.Loader.SoundLib;

public class Button {
	
	public boolean activated = false;
	public boolean toggleButton = false;
	
	public static Sound clickSound = SoundLib.buttonClick;
	
	public TextureRegion btnBackground;
	public TextureRegion btnBackgroundActive;
	// Current button bg
	public TextureRegion bg;
	
	public float x,y;
	public int width, height;
	
	public Button() {
		if (activated) {
			bg = btnBackgroundActive;
		} else {
			bg = btnBackground;
		}
	}
	
	public boolean click(double currentTouchX, double currentTouchY) {
		if ((x < currentTouchX && x + width > currentTouchX ) && (y < currentTouchY && y+height > currentTouchY )) {
			// Toggle button
			if (toggleButton) {
				if (!activated) {
					bg = btnBackgroundActive;
					activated = true;
				} else if (activated) {
					bg = btnBackground;
					activated = false;
				}
			// Non-toggle button
			} else {
				bg = btnBackgroundActive;
				activated = true;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void clickOn() {
		activated = true;
		bg = btnBackgroundActive;
	}
	
	public void clickOff() {
		activated = false;
		bg = btnBackground;
	}
}
