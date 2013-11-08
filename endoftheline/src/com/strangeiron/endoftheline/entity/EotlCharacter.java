package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import static com.strangeiron.endoftheline.screen.EotlGameScreen.shapeRenderer;
import com.strangeiron.endoftheline.screen.EotlScreen;

public class EotlCharacter extends EotlEntity{

	private ShapeRenderer shapeRenderer;
	
	@Override
        public void init()
        {
            shapeRenderer = new ShapeRenderer();
        }
        
	@Override
	public void tick() {
	}

	@Override
	public void render() {
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(x, y, 10f, 10f);
            shapeRenderer.end();
	}
	
}
