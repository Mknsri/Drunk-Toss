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

import java.util.UUID;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.mknsri.drunktoss.Input;
import com.mknsri.drunktoss.Screen;
import com.mknsri.drunktoss.TitleScreen;
import com.mknsri.drunktoss.Loader.Art;
import com.mknsri.drunktoss.Loader.SoundLib;

public class DrunkToss implements ApplicationListener {
	private SpriteBatch batch;
	private Screen screen;
	private final Input input = new Input();

	public static Preferences prefs;
	
	public OrthographicCamera camera, hudCamera;
	Matrix4 projection = new Matrix4();
	
	public static int VIEWPORT_WIDTH = 320;
	public static int VIEWPORT_HEIGHT = 240;
    private static final float ASPECT_RATIO = (float)VIEWPORT_WIDTH/(float)VIEWPORT_HEIGHT;
    public static  float cropX = 0, scale;

	public int gamesPlayed = 0;

    public static Rectangle viewport;

	public DrunkToss() {}
    
	@Override
	public void create() {
		Art.loadArt();
		SoundLib.loadSoundLib();
		
		// Set options
		prefs = Gdx.app.getPreferences("ebingeimi-prefs");

		// Create a unique ID for leaderboards
		if (DrunkToss.prefs.getString("playerID") == "") {
			UUID playerID = UUID.randomUUID();
			DrunkToss.prefs.putString("playerID", playerID.toString());
			DrunkToss.prefs.flush();
		}
		
		camera = new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
		camera.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
		hudCamera = new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
		hudCamera.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
		
		
		Gdx.input.setInputProcessor(input);
		Gdx.input.setCatchBackKey(true);
		
		batch = new SpriteBatch();

		setScreen(new TitleScreen());
	}
	
	

	@Override
	public void dispose() {
		batch.dispose();
		screen.removed();
	}

	@Override
	public void render() {	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		// set viewport
		Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
				(int) viewport.width, (int) viewport.height);

		
		// Graphics rendering
		screen.render();
		screen.tick(input);
		camera.update();
		hudCamera.update();
	}

    @Override
    public void resize(int width, int height)
    {
        // calculate new viewport
        float aspectRatio = (float)width/(float)height;
        scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);

        if(aspectRatio > ASPECT_RATIO)
        {
            scale = (float)height/(float)VIEWPORT_HEIGHT;
            crop.x = (width - VIEWPORT_WIDTH*scale)/2f;
        }
        else if(aspectRatio < ASPECT_RATIO)
        {
            scale = (float)width/(float)VIEWPORT_WIDTH;
            crop.y = (height - VIEWPORT_HEIGHT*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)VIEWPORT_WIDTH;
        }

        float w = (float)VIEWPORT_WIDTH*scale;
        float h = (float)VIEWPORT_HEIGHT*scale;
        viewport = new Rectangle(crop.x, crop.y, w, h);
        cropX = crop.x;
    }

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public void setScreen (Screen newScreen) {
        if (screen != null) screen.removed();
        screen = null;
        screen = newScreen;
        if (screen != null) screen.init(this);
	}
	
	public void cameraReset() {
		camera.position.set(VIEWPORT_WIDTH/2,VIEWPORT_HEIGHT/2, 0);
		hudCamera.position.set(VIEWPORT_WIDTH/2,VIEWPORT_HEIGHT/2, 0);
	}
	
}
