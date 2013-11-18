/**
 * Данный класс реализует базовые методы для всего в игре: 
 * Доступ к энтитям, физика, загрузка карты - все здесь
 */

package com.strangeiron.endoftheline;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class EotlWorldManager {
    
    private static World world;
    private static Box2DDebugRenderer renderer;
    
    /**
     * Инициализирует игровой мир
     */
    public static void init()
    {
        world = new World(new Vector2(0, -40), true);
        renderer = new Box2DDebugRenderer();
    }
    
}
