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

package com.mknsri.drunktoss.Loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundLib {
	public static boolean muteSound = false;
	public static boolean muteMusic = false;
	
	public static Sound buttonClick;
	public static Music menuMusic;
	public static Sound fightSounds;
	public static Sound boing1;
	public static Sound boing2;
	public static Sound boing3;
	public static Sound throwSound;
	
	public static float sVolume = 1f;
		
	public static void loadSoundLib() {
		buttonClick = loadSound("data/click.ogg");
		menuMusic = loadMusic("data/menu.ogg");
		fightSounds = loadSound("data/fight.ogg");
		boing1 = loadSound("data/boing1.ogg");
		boing2 = loadSound("data/boing2.ogg");
		boing3 = loadSound("data/boing3.ogg");
		throwSound = loadSound("data/throw.ogg");
	}
	
	
	public static Sound loadSound(String path) {
		return Gdx.audio.newSound(Gdx.files.internal(path));
	}
	
	public static Music loadMusic(String path) {
		return Gdx.audio.newMusic(Gdx.files.internal(path));
	}
	
	public static void playSound(Sound s) {
		if (muteSound) {
			sVolume = 0;
		} else {
			sVolume = 1;
		}
		s.play(sVolume);
	}

	public static void muteMusic() {
		if (muteMusic) {
			menuMusic.setVolume(0f);
		} else {
			menuMusic.setVolume(1f);
		}
	}
}
