package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.EotlWorld;

public class EotlCharacter extends EotlEntity{
    
        public boolean[] buttons = new boolean[64];
	
        private PlayerState state = PlayerState.IDLE;
        private float stillTime;
        private boolean grounded;
    private long lastGroundTime;
        
	@Override
        public void init()
        {
            setPhysicsType(BodyDef.BodyType.DynamicBody);
            setModel("test.mdl");
            model.scale(300f);
            setPosition(40, 40);
            spawn();
        }
        
	@Override
	public void tick(float delta, EotlInputManager input) {    
            if(!spawned) return;
            
            Vector2 vel = physObject.getLinearVelocity();
            grounded = isPlayerGrounded();
            
            if (grounded) {
                lastGroundTime = TimeUtils.nanoTime();
            } else {
                if (TimeUtils.nanoTime() - lastGroundTime < 100000000) {
                        grounded = true;
                }
            }
            
            // постепенное угасание скорости
            if (!buttons[EotlInputManager.RIGHT]  && !buttons[EotlInputManager.LEFT]) {
                stillTime += delta;
                physObject.setLinearVelocity(vel.x * 0.9f, vel.y);
            } else {
                stillTime = 0;
            }
            
            if (!grounded) {
                setFriction(0f);
            } else {
                if (!buttons[EotlInputManager.RIGHT]  && !buttons[EotlInputManager.LEFT] && stillTime > 0.2) {
                        setFriction(1000f);
                } else {
                        setFriction(0.2f);
                }
            }
            
            if (Math.abs(vel.x) > MAX_VELOCITY) {
                vel.x = Math.signum(vel.x) * MAX_VELOCITY;
                physObject.setLinearVelocity(vel.x, vel.y);
            }
            
            if(buttons[EotlInputManager.JUMP] && grounded)
            {
                physObject.setLinearVelocity(vel.x, 0);
                physObject.setTransform(x, y + 0.01f, 0);
                physObject.applyLinearImpulse(0, -100, x, y, true);
                state = PlayerState.JUMP;
            }
            
            if(buttons[EotlInputManager.RIGHT] && vel.x < MAX_VELOCITY) 
                applyImpulse(new Vector2(1000f, 0));
            
            if(buttons[EotlInputManager.LEFT] && vel.x > -MAX_VELOCITY) 
                applyImpulse(new Vector2(-1000f, 0));
	}

	@Override
	public void render() {
            
	}
        
        private boolean isPlayerGrounded () {
            Array<Contact> contactList = EotlWorld.b2dworld.getContactList();
            
            for (int i = 0; i < contactList.size; i++) {
                Contact contact = contactList.get(i);
                if (contact.isTouching())
                {
                    
                    boolean isplayer = false;
                    
                    for (int j = 0; j < model.fixtures.size(); j++) {
                        Fixture fixture = model.fixtures.get(j);
                        
                        if(contact.getFixtureA() == fixture || contact.getFixtureB() == fixture)
                        {
                            isplayer = true;
                        }
                    }

                    return isplayer;
                }
            }
            return false;
	}
        
        // static things
        private static final float MAX_VELOCITY = 1000f;
        
        private static enum PlayerState {
            WALK_RIGHT,
            WALK_LEFT,
            JUMP,
            IDLE
        }
	
}
