package com.strangeiron.endoftheline.gamestate;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strangeiron.endoftheline.engine.OrthographicCameraWithVirtualViewport;
import com.strangeiron.endoftheline.engine.Renderer;

public class GameState implements Screen {
	
	private SpriteBatch batch;
	private OrthographicCameraWithVirtualViewport camera;

	public GameState() {
		batch = Renderer.batch;
		camera = Renderer.camera;
	}
	
	@Override
	public void render(float delta) {		
		update(delta);
		
	}
	
	private void update(float delta) {		
		
	}
	
	@Override
	public void resize(int width, int height) { 
		Renderer.resize();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
