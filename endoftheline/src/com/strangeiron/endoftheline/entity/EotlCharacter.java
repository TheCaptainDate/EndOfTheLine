package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class EotlCharacter extends EotlEntity{

	//private boolean init = false;
	
	
	@Override
	public void tick() {
	}

	@Override
	public void render() {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.GREEN);
		shapeRenderer.rect(x, y, 10f, 10f);
		shapeRenderer.end();
	}
	
}
