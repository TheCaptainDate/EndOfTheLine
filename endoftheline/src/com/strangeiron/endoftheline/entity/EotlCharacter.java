package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.math.EotlVector2D;

public class EotlCharacter extends EotlEntity{

	private ShapeRenderer shapeRenderer;
        public boolean[] buttons = new boolean[64];
	
	@Override
        public void init()
        {
            shapeRenderer = new ShapeRenderer();
            setPhysicsType(BodyDef.BodyType.DynamicBody);
            setModel("test.mdl");
            model.scale(300f);
            setPosition(40, 40);
            spawn();
        }
        
	@Override
	public void tick(float delta, EotlInputManager input) {            
            if(buttons[EotlInputManager.RIGHT]) 
                physObject.applyForce(new Vector2(1000, 0).scl(delta), physObject.getPosition(), true);
            
            if(buttons[EotlInputManager.LEFT]) 
                physObject.applyForce(new Vector2(-1000, 0).scl(delta), physObject.getPosition(), true);
            
            if(buttons[EotlInputManager.UP]) 
                physObject.applyForce(new Vector2(0, 1000).scl(delta), physObject.getPosition(), true);
            
            if(buttons[EotlInputManager.DOWN]) 
                physObject.applyForce(new Vector2(0, -1000).scl(delta), physObject.getPosition(), true);
	}

	@Override
	public void render() {
            //shapeRenderer.begin(ShapeType.Filled);
            //shapeRenderer.setColor(Color.GREEN);
            //shapeRenderer.rect(x, y, 10f, 10f);
            //shapeRenderer.end();
	}
	
}
