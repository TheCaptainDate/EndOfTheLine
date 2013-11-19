package com.strangeiron.endoftheline.screen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.strangeiron.endoftheline.components.EotlInputManager;
import com.strangeiron.endoftheline.components.EotlNetwork;
import com.strangeiron.endoftheline.entity.EotlEntityManager;
import com.strangeiron.endoftheline.entity.EotlLocalPlayer;

public class EotlGameScreen extends EotlScreen {

    private EotlNetwork network;
    public static ShapeRenderer shapeRenderer;
    @Override
    public void postInit() {
        EotlEntityManager.localPlayer = new EotlLocalPlayer();
        EotlEntityManager.addEntity(EotlEntityManager.localPlayer, 0);
        
        EotlNetwork.init();
        EotlNetwork.connect("127.0.0.1", 12345); // @TODO: Debug shit ;/
        EotlNetwork.sendLoginPacket();
    }

    @Override
    public void tick (EotlInputManager input) 
    {

    }

    @Override
    public void render() 
    {

    }
}