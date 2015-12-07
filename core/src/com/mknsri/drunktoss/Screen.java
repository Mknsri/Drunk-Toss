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
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public abstract class Screen {
	public DrunkToss ebingeimi;
	public BitmapFont font;
    public GlyphLayout layout;
	public SpriteBatch batch;
	public SpriteBatch hudBatch;
	
	
	public void removed() {
		batch.dispose();
		font.dispose();
		hudBatch.dispose();
	}
	
	public final void init(DrunkToss ebingeimi) {	
		this.ebingeimi = ebingeimi;
		batch = new SpriteBatch(50);
		hudBatch = new SpriteBatch(10);
		batch.setProjectionMatrix(this.ebingeimi.camera.combined);
		hudBatch.setProjectionMatrix(this.ebingeimi.hudCamera.combined);
		font = new BitmapFont();
        layout = new GlyphLayout(font, "");
	}
	
	public void drawTextureRegion(TextureRegion region, float x, float y) {
		int width = region.getRegionWidth();
		if (width < 0) width = -width;
			batch.draw(region, x, y, width, region.getRegionHeight());
	}
	
	public void drawSprite(TextureRegion region, float x, float y, float rotation) {
		Sprite sprite = new Sprite(region);
		
		sprite.setPosition(x, y);
		
		sprite.setRotation((float) Math.toDegrees(rotation));		
		sprite.draw(batch);
		
	}
	
	 protected void setScreen (Screen screen) {
         ebingeimi.setScreen(screen);
	 }
	
	public void drawString(CharSequence msg, Color color, int x, int y) {
		font.setColor(color);
		font.draw(batch, msg, x, y );
	}
	
	public void drawStringOnCenter(CharSequence msg, Color color) {
		font.setColor(color);
        layout.setText(font, msg);
        int x = (int) ((DrunkToss.VIEWPORT_WIDTH / 2) - (layout.width / 2));
        int y = (int) ((DrunkToss.VIEWPORT_HEIGHT / 2)- (layout.height / 2));
		font.draw(batch, msg, x, y );
	}
	
	public void drawStringOnHUD(CharSequence msg, Color color, int x, int y ) {
		font.setColor(color);
		font.draw(hudBatch, msg, x, y );
	}	
	
	public void drawStringOnHUDCenter(CharSequence msg, Color color) {
		font.setColor(color);
		layout.setText(font, msg);
        int x = (int) ((DrunkToss.VIEWPORT_WIDTH / 2) - (layout.width / 2));
		int y = (int) ((DrunkToss.VIEWPORT_HEIGHT / 2)- (layout.height / 2));
		font.draw(hudBatch, msg, x, y );
	}
	
	public void drawSpriteOnHUD(TextureRegion region, float x, float y, float rotation) {
		Sprite sprite = new Sprite(region);
		
		sprite.setPosition(x, y);
		
		sprite.setRotation((float) Math.toDegrees(rotation));	
		sprite.draw(hudBatch);
		
	}
	
	public abstract void render();
	public abstract void tick(Input input);
	
}