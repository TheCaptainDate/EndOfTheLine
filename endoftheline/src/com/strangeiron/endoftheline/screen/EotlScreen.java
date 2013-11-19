
package com.strangeiron.endoftheline.screen;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.strangeiron.endoftheline.EndOfTheLine;
import com.strangeiron.endoftheline.components.EotlInputManager;

public abstract class EotlScreen {
	protected static Random random = new Random();
	private EndOfTheLine eotl;
	public SpriteBatch spriteBatch;

	public void remove() {
		spriteBatch.dispose();
	}

	public final void init (EndOfTheLine eotl) {
		this.eotl = eotl;
		Matrix4 projection = new Matrix4();
		projection.setToOrtho(0, 1280, 800, 0, -1, 1);

		spriteBatch = new SpriteBatch(100);
		spriteBatch.setProjectionMatrix(projection);
		postInit();
	}
	
	protected void setScreen (EotlScreen eotlScreen) {
		eotl.setScreen(eotlScreen);
	}

	public void draw (TextureRegion region, int x, int y) {
		int width = region.getRegionWidth();
		if (width < 0) width = -width;
		spriteBatch.draw(region, x, y, width, region.getRegionHeight());
	}

        public abstract void postInit(); // we realy need that, notch;
        
	public abstract void render();

	public void tick(EotlInputManager input) {
	}
}