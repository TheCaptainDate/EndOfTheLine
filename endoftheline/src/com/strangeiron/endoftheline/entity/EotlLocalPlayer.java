package com.strangeiron.endoftheline.entity;

import com.strangeiron.endoftheline.components.EotlInputManager;


public class EotlLocalPlayer extends EotlEntity {

    public EotlCharacter character;
    
    @Override
    public void tick(float delta, EotlInputManager input) {
        if(character == null) return;
        character.buttons = input.buttons;
    }

    @Override
    public void render() {
        
    }

    @Override
    public void init() {
        
    }
    
}
