// base entity
package com.strangeiron.endoftheline.entity;

public abstract class EotlEntity {
    public float x;
    public float y;
    public boolean initiated;

    public void _init()
    {
        initiated = true;
        init();
    }
    
    public abstract void tick();
    public abstract void render();
    public abstract void init();
}
