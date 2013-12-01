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

public abstract class EotlEntity {

    public int id;
    public boolean initiated;
    public float x = 0f;
    public float y = 0f;

    private BodyDef bodyDef;
    public boolean spawned = false;
    public Body physObject;
    public Eotl2DModel model;

    // sync stuf
    public float Xsync = 0f;
    public float Ysync = 0f;
    public float targetX = 0f;
    public float targetY = 0f;
    public float xVel;
    public float yVel;

    public void _init() {
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);

        // now real init
        initiated = true;
        init();
    }

    public void _tick(float delta, EotlInputManager input) {
        if (spawned) {
            x = physObject.getPosition().x;
            y = physObject.getPosition().y;
        }
    }

    public void _post_tick(float delta, EotlInputManager input) {
        if (spawned) {
            Xsync = (targetX - x);
            Ysync = (targetY - y);

            if (Xsync != 0 && EotlNetwork.ticksToGlobalUpdate != 0) {
                Xsync = Xsync / EotlNetwork.ticksToGlobalUpdate;
            }

            if (Ysync != 0 && EotlNetwork.ticksToGlobalUpdate != 0) {
                Ysync = Ysync / EotlNetwork.ticksToGlobalUpdate;
            }

            physObject.setTransform(physObject.getPosition().x + (Xsync), physObject.getPosition().y + (Ysync), 0f);
            physObject.setLinearVelocity(xVel, yVel);
        }
    }

    public void spawn() {
        physObject = EotlWorld.b2dworld.createBody(bodyDef);
        model.applyToBody(physObject);
        physObject.setTransform(x, y, 0f);
        spawned = true;
    }

    public void applyImpulse(Vector2 impulse) {
        physObject.applyLinearImpulse(impulse.scl(Gdx.graphics.getDeltaTime()), physObject.getPosition(), true);
    }

    public void setModel(String modelPath) {
        model = EotlResourcesManager.getModel(modelPath);
    }

    public void setPhysicsType(BodyDef.BodyType type) {
        if (!spawned) {
            bodyDef.type = type;
        } else {
            physObject.setType(type);
        }
    }

    public void setPosition(Vector2 vec) {
        if (!spawned) {
            bodyDef.position.set(vec);
        } else {
            physObject.setTransform(vec, physObject.getAngle());
        }
    }

    public void setPosition(float newX, float newY) {
        if (!spawned) {
            bodyDef.position.set(newX, newY);
        } else {
            physObject.setTransform(newX, newY, physObject.getAngle());
        }
    }

    public abstract void tick(float delta, EotlInputManager input);

    public abstract void render();

    public abstract void init();
}
