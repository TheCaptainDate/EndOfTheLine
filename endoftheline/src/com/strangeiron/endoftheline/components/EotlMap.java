package com.strangeiron.endoftheline.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class EotlMap {
    
    private static TiledMap tiledmap;
    private static final TmxMapLoader mapLoader = new TmxMapLoader();
    
    public EotlMap(String name) {
        if(!Gdx.files.internal("maps/" + name).exists())
        {
            throw new RuntimeException("Can't load map \"" + name + "\": file not found.");
        }
        
        tiledmap = mapLoader.load("maps/" + name); 
        loadCollisions();
    }
    
    private void loadCollisions()
    {
        TiledMapTileLayer collisionsLayer = (TiledMapTileLayer)tiledmap.getLayers().get("collisions");
        
        for(int x = 0; x <= collisionsLayer.getWidth(); x++)
        {
            for(int y = 0; x <= collisionsLayer.getHeight(); y++)
            {
                TiledMapTileLayer.Cell cell = collisionsLayer.getCell(x, y);
                
                if(cell.getTile().getProperties().get("solid", true, Boolean.class))
                {
                    // @todo: add to hashset, generate colliders etc
                }
            }
        }
       
    }
    
}
