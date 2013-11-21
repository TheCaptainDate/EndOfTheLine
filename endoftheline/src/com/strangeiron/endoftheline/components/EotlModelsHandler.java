/**
 * Данный класс парсит json данные и формирует из них физ. модели.
 */

package com.strangeiron.endoftheline.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.OrderedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EotlModelsHandler {
    
    private static JsonReader reader = new JsonReader();
    
    public static void handle(String modelPath)
    {
        JsonValue models = reader.parse(Gdx.files.internal("models/" + modelPath)).get("rigidBodies");
        
        for (JsonValue.JsonIterator jsonIterator = models.iterator(); jsonIterator.hasNext();) {
            JsonValue model = jsonIterator.next();
            
        }
    }
}
