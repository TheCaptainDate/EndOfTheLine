package com.strangeiron.endoftheline.screen;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.EotlNetwork;
import com.strangeiron.endoftheline.components.EotlWorld;
import com.strangeiron.endoftheline.entity.EotlEntityManager;
import com.strangeiron.endoftheline.entity.EotlLocalPlayer;

public class EotlGameScreen extends EotlScreen {

    public static ShapeRenderer shapeRenderer;
    private static EotlWorld eotlWorld;
    
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
        /** По факту это лучше делать не тут, но т.к. мир должен обновляться ТОЛЬКО
        * непосредственно во время игры (т.к. EotlWorld управляет игровой тайл-картой
        * и обновлениями физики), то думаю это ок. 
        */
        eotlWorld.update();
    }

    @Override
    public void render() 
    {
        // Аналогично с update()
        eotlWorld.render(spriteBatch);
    }
}