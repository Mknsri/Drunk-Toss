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
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

public class Input implements InputProcessor {
	public boolean touched = false;
	public boolean menuPressed = false;
	public double currentTouchX, currentTouchY;
	
	public Input() {
	}
	
	private void getRealPos(double x, double y){
		double tempY = y / Gdx.graphics.getHeight();
				
		currentTouchY = (DrunkToss.VIEWPORT_HEIGHT - (tempY * DrunkToss.VIEWPORT_HEIGHT));
		currentTouchX =  ((x - DrunkToss.cropX) / DrunkToss.scale);
	}
	
	 @Override
     public boolean keyDown (int keycode) {
		 if ( keycode == Keys.X || keycode == Keys.BACK) {
			 if (!menuPressed) {
				 menuPressed = true;
			 }
		 }
		 return false;
     }

     @Override
     public boolean keyUp (int keycode) {
    	 if (menuPressed) {
			 menuPressed = false;
		 }
             return false;
     }

     @Override
     public boolean keyTyped (char character) {
             return false;
     }

     @Override
     public boolean touchDown (int x, int y, int pointer, int button) {
    	 	touched = true;
    	 	getRealPos(x,y);
    	 	return false;
     }

     @Override
     public boolean touchUp (int x, int y, int pointer, int button) {
            touched = false; 
    	 	return false;
     }

     @Override
     public boolean touchDragged (int x, int y, int pointer) {
    	 	getRealPos(x,y);
            return false;
     }

     @Override
     public boolean mouseMoved (int x, int y) {
             return false;
     }

     @Override
     public boolean scrolled (int amount) {
             return false;
     }
     
     public boolean isTouched() {
    	 if (touched) {
    		 return true;
    	 } else {
    		 return false;
    	 }    	 
     }
     
     public boolean tapped() {
    	 if (touched) {
    		 touched = false;
    		 return true;
    	 } else {
    		 return false;
    	 }    	 
     }
     
     public boolean isPaused() {
    	 if (menuPressed) {
    		 return true;
    	 } else {
    		 return false;
    	 }
     }
}