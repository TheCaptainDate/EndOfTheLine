/**
 * Данный класс реализует базовые методы для всего в игре: 
 * Доступ к энтитям, физика, загрузка карты - все здесь
 */

package com.strangeiron.endoftheline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.strangeiron.endoftheline.components.EotlMap;

public class EotlWorld {
    
    public static World b2dworld;
    private static Box2DDebugRenderer renderer;
    private static Matrix4 projection;
        
    private static EotlMap map;
    
    public static void init()
    {
        b2dworld = new World(new Vector2(0, 300f), true);
        renderer = new Box2DDebugRenderer();
        renderer.setDrawVelocities(true);
        renderer.setDrawContacts(true);
        
        projection = new Matrix4(); // debug, @TODO: CAMERA!
        projection.setToOrtho(0, 1280, 800, 0, -1, 1);
        
        // debug, @TODO: REMOVE!
        Body ground = createEdge(BodyType.StaticBody, 0, 790, 1280,790, 1);
    }
    
    public static void update() 
    {
        Array<Contact> contacts = b2dworld.getContactList();
        for (int i = 0; i < b2dworld.getContactCount(); i++) {
                Contact contact = contacts.get(i);
                contact.resetFriction();
        }
        
        b2dworld.step(Gdx.graphics.getDeltaTime(), 4, 4);
    }
    
    public static void render(SpriteBatch batch)
    {
        renderer.render(b2dworld, projection);
    }
    
    public static void loadMap(String mapName)
    {
        if(map != null)
        {
            // @todo: delete map data, remove ents, etc
        }
        
        map = new EotlMap(mapName);
    }
    
    /* Physics goes here: 
    @TODO: сделать для физики отдельный враппер?!
    */
    
    public static Body createEdge (BodyType type, float x1, float y1, float x2, float y2, float density) {
        BodyDef def = new BodyDef();
        def.type = type;
        Body box = b2dworld.createBody(def);

        EdgeShape poly = new EdgeShape();
        poly.set(new Vector2(0, 0), new Vector2(x2 - x1, y2 - y1));
        box.createFixture(poly, density);
        box.setTransform(x1, y1, 0);
        poly.dispose();

        return box;
    }
    
}
