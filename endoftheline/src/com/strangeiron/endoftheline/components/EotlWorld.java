/**
 * Данный класс реализует базовые методы для всего в игре: 
 * Доступ к энтитям, физика, загрузка карты - все здесь
 */

package com.strangeiron.endoftheline.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;

public class EotlWorld {
    
    private World world;
    private Box2DDebugRenderer renderer;
    private Matrix4 projection;
    
    /**
     * Инициализирует игровой мир
     */
    public EotlWorld()
    {
        init(); // --> WTF?!
    }
    
    public void init()
    {
        world = new World(new Vector2(0, -40), true);
        renderer = new Box2DDebugRenderer();
        
        projection = new Matrix4(); // debug, @TODO: CAMERA!
        projection.setToOrtho(0, 1280, 800, 0, -1, 1);
        
        // debug, @TODO: REMOVE!
        float y1 = 1; 
        float y2 = y1;
        for (int i = 0; i < 50; i++) {
                Body ground = createEdge(BodyType.StaticBody, -50 + i * 2, y1, -50 + i * 2 + 2, y2, 0);
                y1 = y2;
                y2 = 1; 
        }
    }
    
    public  void update() 
    {
        world.step(Gdx.graphics.getDeltaTime(), 4, 4);
    }
    
    public void render(SpriteBatch batch)
    {
        renderer.render(world, projection);
    }
    
    /* Physics goes here: 
    @TODO: сделать для физики отдельный враппер?!
    */
    
    public Body createEdge (BodyType type, float x1, float y1, float x2, float y2, float density) {
        BodyDef def = new BodyDef();
        def.type = type;
        Body box = world.createBody(def);

        EdgeShape poly = new EdgeShape();
        poly.set(new Vector2(0, 0), new Vector2(x2 - x1, y2 - y1));
        box.createFixture(poly, density);
        box.setTransform(x1, y1, 0);
        poly.dispose();

        return box;
    }
    
}
