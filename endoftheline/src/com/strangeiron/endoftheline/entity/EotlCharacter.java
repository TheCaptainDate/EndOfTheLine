package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.math.EotlVector2D;

public class EotlCharacter extends EotlEntity{

	private ShapeRenderer shapeRenderer;
        public boolean[] buttons = new boolean[64];
	
	@Override
        public void init()
        {
            shapeRenderer = new ShapeRenderer();
        }
        
	@Override
	public void tick(float delta, EotlInputManager input) {            
            if(buttons[EotlInputManager.RIGHT]) 
                this.applyForce(new EotlVector2D(10, 0).multiply(delta));
            
            if(buttons[EotlInputManager.LEFT]) 
                this.applyForce(new EotlVector2D(-10, 0).multiply(delta));
            
            if(buttons[EotlInputManager.UP]) 
                this.applyForce(new EotlVector2D(0, 10).multiply(delta));
            
            if(buttons[EotlInputManager.DOWN]) 
                this.applyForce(new EotlVector2D(0, -10).multiply(delta));
	}

	@Override
	public void render() {
            shapeRenderer.begin(ShapeType.Filled);
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.rect(x, y, 10f, 10f);
            shapeRenderer.end();
	}
	
}
