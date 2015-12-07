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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Art {
	public static TextureRegion bg;
	public static TextureRegion titleLogo;
	public static TextureRegion angle;
	public static TextureRegion pommi1;
	public static TextureRegion pommi1r;
	public static TextureRegion[] bgSetDbl;

	public static TextureRegion ilpo;
	public static TextureRegion ilpoEnd;


	public static TextureRegion[] ilpoAndPoke;
	public static TextureRegion	pokeBody;
	public static TextureRegion	pokeBackArm;
	public static TextureRegion	pokeFrontArm;	
	
	public static TextureRegion ilpoDuck;
	public static TextureRegion[] ilpoFly;
	
	public static TextureRegion[] pummiIdle;
	public static TextureRegion[] pummiTriggered;

	public static TextureRegion[] groupFighting;
	public static TextureRegion groupIdle;
	public static TextureRegion groupEnd;

	public static TextureRegion[] menuBg;
	public static TextureRegion newgameB;
	public static TextureRegion newgameBA;	
	public static TextureRegion optionsB;
	public static TextureRegion optionsBA;	
	public static TextureRegion hiscoresB;	
	public static TextureRegion hiscoresBA;
	public static TextureRegion quitB;
	public static TextureRegion quitBA;	
	public static TextureRegion checkB;
	public static TextureRegion checkBA;
	public static TextureRegion optionsBg;
	public static TextureRegion okB;
	public static TextureRegion okBA;

	public static TextureRegion tut1;
	public static TextureRegion tut2;
	
	public static TextureRegion btnAllTime;
	public static TextureRegion btnAllTimeA;
	public static TextureRegion btnWeekly;
	public static TextureRegion btnWeeklyA;
	public static TextureRegion btnPersonal;
	public static TextureRegion btnPersonalA;
	
	public static TextureRegion uploadB;
	public static TextureRegion uploadBA;

	public static TextureRegion hiScoreMenu;
	
	public static TextureRegion hiScoreScreen;
	
	public static void loadArt() {
		titleLogo = loadSprite("data/title.png", 0, 0);
		angle = loadSprite("data/angle.png", 0, 0);
		
		ilpoFly = loadAnimation("data/ilponanim.png",70,58,3,1);
		ilpoDuck = loadSprite("data/ilponduck.png",0,0);
		ilpoEnd = loadSprite("data/nilpoend.png",0,0);
		
		pokeBody = loadSprite("data/npokebody.png",0,0);
		pokeFrontArm = loadSprite("data/pokefrontarm.png", 0,0);
		pokeBackArm = loadSprite("data/npokebackarm.png", 0,0);
		
		pummiIdle = loadAnimation("data/pumanim.png",100,50,2,1);
		pummiTriggered = loadAnimation("data/pumtriggered.png",100,50,2,1);
		
		groupFighting = loadAnimation("data/groupfight.png",150,150,3,1);
		groupIdle = loadSprite("data/shadygroup.png", 0, 0);
		groupEnd = loadSprite("data/shadygroupend.png", 0, 0);

		menuBg = loadAnimation("data/mmanim.png",320,240,3,1);
		optionsBg = loadSprite("data/optionsmenu.png",320,240);
		newgameB = loadSprite("data/newgameb.png",0,0);
		newgameBA = loadSprite("data/newgameba.png",0,0);
		optionsB = loadSprite("data/optionsb.png",0,0);
		optionsBA = loadSprite("data/optionsba.png",0,0);
		hiscoresB = loadSprite("data/hiscoresb.png",0,0);
		hiscoresBA = loadSprite("data/hiscoresba.png",0,0);
		quitB = loadSprite("data/quitb.png",0,0);
		quitBA = loadSprite("data/quitba.png",0,0);
		checkB = loadSprite("data/checkb.png",0,0);
		checkBA = loadSprite("data/checkba.png",0,0);
		okB = loadSprite("data/okb.png",0,0);
		okBA = loadSprite("data/okba.png",0,0);
		hiScoreScreen = loadSprite("data/hiscore.png",0,0);
		
		tut1 = loadSprite("data/tut1.png",0,0);
		tut2 = loadSprite("data/tut2.png",0,0);
		
		btnAllTime = loadSprite("data/lb_alltime.png",0,0);
		btnAllTimeA = loadSprite("data/lb_alltimea.png",0,0);
		btnWeekly = loadSprite("data/lb_weekly.png",0,0);
		btnWeeklyA = loadSprite("data/lb_weeklya.png",0,0);
		btnPersonal = loadSprite("data/lb_personal.png",0,0);
		btnPersonalA = loadSprite("data/lb_personala.png",0,0);
		
		uploadB = loadSprite("data/uploadb.png",0,0);
		uploadBA = loadSprite("data/uploadba.png",0,0);
		
		hiScoreMenu = loadSprite("data/leaderboardbg.png",0,0);
		
		
		// Load 1st set
		bgSetDbl = new TextureRegion[10];
		for (int i = 0; i < 10; i++) {
			int x = i+1;
			bgSetDbl[i] = loadSprite("data/bg_" + x + ".png", 0, 0);
		}
		
	}
	
	public static TextureRegion loadSprite (String name, int width, int height) {
     
		Texture texture = new Texture(Gdx.files.internal(name));
		if (width == 0) {
			width = texture.getWidth();
		}
		if (height == 0) {
			height = texture.getHeight();
		}
        TextureRegion region = new TextureRegion(texture, 0, 0, width, height);
        //region.flip(false, true);
        return region;
	}
	
	public static TextureRegion[] loadAnimation(String fileName, int width, int height,
												int frameCols, int frameRows) {
		TextureRegion img = loadSprite(fileName, width * frameCols, height * frameRows);
		TextureRegion[][] spritesheetTemp = img.split(width, height);
		TextureRegion[] spritesheet = new TextureRegion[frameRows*frameCols];
		
		// Fix the indices
		frameCols--;
		frameRows--;
		
		for (int i = 0; i <= frameRows; i++) {
			for (int y = 0; y <= frameCols; y++) {
				spritesheet[i+y] = spritesheetTemp[i][y];
			}
		}
		return spritesheet;
	}
	
	public static TextureRegion[] returnBgSet(String setName) {
		if (setName == "dbl") {
			return bgSetDbl;
		} else {
			return bgSetDbl;
		}	
	}
}