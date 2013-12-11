package com.strangeiron.endoftheline.entity.weapon;

public abstract class EotlBaseWeapon  {
    public String Name;
    public String id;
    public int PrimaryAmmo;
    public int SecondaryAmmo;
    public int PrimaryClipSize;
    public int SecondaryClipSize;
    public int PrimaryClip;
    public int SecondaryClip;
    public boolean[] buttons;
    
    public void _tick(float delta) 
    {
        //if()
    }
    
    public void _draw() 
    {
        
    }
    
    public abstract void tick(float delta);
    public abstract void draw();
}