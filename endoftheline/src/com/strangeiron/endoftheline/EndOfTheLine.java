package com.strangeiron.endoftheline;

import com.badlogic.gdx.Game;
import com.strangeiron.endoftheline.engine.Renderer;
import com.strangeiron.endoftheline.gamestate.GameState;

public class EndOfTheLine extends Game {
	
	public GameState GAME_STATE;
	public static int HEIGHT = 1280;
	public static int WIDTH = 1240;
	
	@Override
	public void create() {		
		Renderer.init();
		GAME_STATE = new GameState();
		setScreen(GAME_STATE);	
	}

	@Override
	public void dispose() {

	}
}
