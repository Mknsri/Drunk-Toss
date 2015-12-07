package com.mknsri.drunktoss.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mknsri.drunktoss.DrunkToss;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Drunk Toss";
		config.width = 640;
		config.height = 480;
		new LwjglApplication(new DrunkToss(), config);
	}
}
