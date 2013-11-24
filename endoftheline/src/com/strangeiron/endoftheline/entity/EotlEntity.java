// base entity
package com.strangeiron.endoftheline.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.EotlNetwork;
import com.strangeiron.endoftheline.EotlResourcesManager;
import com.strangeiron.endoftheline.components.Eotl2DModel;
import com.strangeiron.endoftheline.EotlWorld;
import java.util.ArrayList;
import java.util.List;

public abstract class EotlEntity {
    public int id;
    public boolean initiated;
    public float x = 0f;
    public float y = 0f;
    
    private BodyDef bodyDef;
    private boolean spawned = false;
    public Body physObject;
    public Eotl2DModel model;
    
    // sync stuf
    public float Xsync;
    public float Ysync;
    private final Vector2 syncVec = new Vector2();
    private final List<Vector2> forces = new ArrayList<Vector2>();

    public void _init()
    {
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        
        // now real init
        initiated = true;
        init();
    }
    
    public void _tick(float delta, EotlInputManager input)
    {
        if(spawned)
        {   
            x = physObject.getPosition().x;
            y = physObject.getPosition().y;
        }
    }
    
    public void _post_tick(float delta, EotlInputManager input)
    {
        if(spawned)
        {
           /* Vector2 vel = physObject.getLinearVelocity();
            
           if(Math.abs(vel.x) - Math.abs(Xsync) > 0)
           {
                vel.x = vel.x + Xsync;
           }

           if(Math.abs(vel.y) - Math.abs(Ysync) > 0)
           {
                vel.y = vel.y + Ysync;
           }

           physObject.setLinearVelocity(vel);
           
           Ysync = Ysync / 2;
           Xsync = Xsync / 2;
        } */
            
        physObject.setTransform(physObject.getPosition().x + (Xsync / 2), physObject.getPosition().y + (Ysync / 2), 0f);
        
        }
        System.out.println("XSync: " + Xsync + " YSync: " + Ysync);
    }
    
    public void spawn()
    {
        physObject = EotlWorld.b2dworld.createBody(bodyDef);
        model.applyToBody(physObject);
        physObject.setTransform(x, y, 0f);
        spawned = true;
    }
    
    public void applyImpulse(Vector2 impulse)
    {
        physObject.applyLinearImpulse(impulse.scl(Gdx.graphics.getDeltaTime()), physObject.getPosition(), true);
    }
    
    public void setModel(String modelPath)
    {
        model = EotlResourcesManager.getModel(modelPath);
    }
    
    public void setPhysicsType(BodyDef.BodyType type)
    {
        if(!spawned) bodyDef.type = type; else physObject.setType(type);
   }
    
    public  void setPosition(Vector2 vec)
    {
        if(!spawned) bodyDef.position.set(vec); else physObject.setTransform(vec, physObject.getAngle());
    }
    
    public  void setPosition(float newX, float newY)
    {
        if(!spawned) bodyDef.position.set(newX, newY); else physObject.setTransform(newX, newY, physObject.getAngle());
    }

    public abstract void tick(float delta, EotlInputManager input);
    public abstract void render();
    public abstract void init();
}
