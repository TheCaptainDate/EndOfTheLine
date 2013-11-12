// base entity
package com.strangeiron.endoftheline.entity;

import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.math.EotlVector2D;

public abstract class EotlEntity {
    public float x;
    public int id;
    public float y;
    public float xVelocity;
    public float yVelocity;
    public boolean initiated;
    public float Xsync;
    public float Ysync;

    public void _init()
    {
        initiated = true;
        init();
    }
    
    public void _tick(float delta, EotlInputManager input)
    {
        x = x + xVelocity + Xsync;
        y = y + yVelocity + Ysync;
        xVelocity = 0;
        yVelocity = 0;
    }
    
    public void applyForce(EotlVector2D vector)
    {
        this.xVelocity = this.xVelocity + vector.getX();
        this.yVelocity = this.yVelocity + vector.getY();
    }

    public abstract void tick(float delta, EotlInputManager input);
    public abstract void render();
    public abstract void init();
}
