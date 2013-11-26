package com.strangeiron.endoftheline.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.EotlNetwork;
import com.strangeiron.endoftheline.EotlWorld;
import com.strangeiron.endoftheline.entity.EotlEntityManager;
import com.strangeiron.endoftheline.entity.EotlLocalPlayer;

public class EotlGameScreen extends EotlScreen {

    public static ShapeRenderer shapeRenderer;
    
    @Override
    public void postInit() {
        EotlEntityManager.localPlayer = new EotlLocalPlayer();
        EotlEntityManager.addEntity(EotlEntityManager.localPlayer, 0);
        
        EotlNetwork.init();
        EotlWorld.init();
       // EotlNetwork.connect("127.0.0.1", 12345); // @TODO: Debug shit ;/
        //EotlNetwork.sendLoginPacket();
        EotlWorld.loadMap("test.tmx");
    }

    @Override
    public void tick (EotlInputManager input) 
    {
        /** По факту это лучше делать не тут, но т.к. мир должен обновляться ТОЛЬКО
        * непосредственно во время игры (т.к. EotlWorld управляет игровой тайл-картой
        * и обновлениями физики), то думаю это ок. 
        */
        EotlWorld.update();
        
        // Аналогично и с энтитями
        EotlEntityManager.tick(Gdx.graphics.getDeltaTime(), input);
    }

    @Override
    public void render() 
    {
        // Аналогично с update()
        EotlWorld.render(spriteBatch);
        EotlEntityManager.render();
    }
}