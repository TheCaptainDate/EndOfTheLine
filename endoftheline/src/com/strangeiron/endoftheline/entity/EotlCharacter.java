package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.strangeiron.endoftheline.EotlInputManager;

public class EotlCharacter extends EotlEntity{

	private ShapeRenderer shapeRenderer;
        public boolean[] buttons = new boolean[64];
	
	@Override
        public void init()
        {
            shapeRenderer = new ShapeRenderer();
            setPhysicsType(BodyDef.BodyType.DynamicBody);
            setModel("test.mdl");
            //model.setRestitution(1f);
            //model.setFriction(1f);
            model.scale(300f);
            setPosition(40, 40);
            spawn();
        }
        
	@Override
	public void tick(float delta, EotlInputManager input) {            
            if(buttons[EotlInputManager.RIGHT]) 
                applyForce(new Vector2(1000, 0).scl(delta));
            
            if(buttons[EotlInputManager.LEFT]) 
                applyForce(new Vector2(-1000, 0).scl(delta));
            
            if(buttons[EotlInputManager.UP]) 
               applyForce(new Vector2(0, 1000).scl(delta));
            
            if(buttons[EotlInputManager.DOWN]) 
               applyForce(new Vector2(0, -1000).scl(delta));
	}

	@Override
	public void render() {
            //shapeRenderer.begin(ShapeType.Filled);
            //shapeRenderer.setColor(Color.GREEN);
            //shapeRenderer.rect(x, y, 10f, 10f);
            //shapeRenderer.end();
	}
	
}
