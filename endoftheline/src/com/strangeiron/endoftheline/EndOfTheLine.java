
package com.strangeiron.endoftheline;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.strangeiron.endoftheline.entity.EotlEntityManager;
import com.strangeiron.endoftheline.screen.EotlGameScreen;
import com.strangeiron.endoftheline.screen.EotlScreen;

public class EndOfTheLine implements ApplicationListener {
	public static final int GAME_WIDTH = 1280;
	public static final int GAME_HEIGHT = 800;

	private EotlScreen screen;
	private final EotlInputManager input = new EotlInputManager();
	private float accum = 0;
	private final EotlEntityManager entityManager = EotlEntityManager.GetInstance();

	@Override
	public void create () {
		EotlResourcesManager.load();
		//Sound.load();
		Gdx.input.setInputProcessor(input);
		setScreen(new EotlGameScreen());
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	public void setScreen (EotlScreen newScreen) {
		if (screen != null) {
			screen.remove();
		}
		screen = newScreen;
		
		if (screen != null) screen.init(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		accum += Gdx.graphics.getDeltaTime();
		while (accum > 1.0f / 60.0f) {
			screen.tick(input);
			input.tick();
			entityManager.tick();
			accum -= 1.0f / 60.0f;
		}
		screen.render();
		entityManager.render();
		
		screen.spriteBatch.begin();
		EotlResourcesManager.font.draw(screen.spriteBatch, "fps: " + Gdx.graphics.getFramesPerSecond(), 5, 10);
		screen.spriteBatch.end();
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void dispose () {
	}
}
