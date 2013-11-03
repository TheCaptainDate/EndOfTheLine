package com.strangeiron.endoftheline;


import java.awt.Dimension;
import java.awt.Toolkit;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.strangeiron.endoftheline.EndOfTheLine;

public class Main {
	
	private static final String VERSION = "0.0.1";
	private static final String BUILD = "1";
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ERROR: <End of the line> | ver: " + VERSION + " & Build: " + BUILD;
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 1024;
		cfg.fullscreen = false;
		cfg.vSyncEnabled = false;
		
		
		new LwjglApplication(new EndOfTheLine(), cfg);
	}
}
