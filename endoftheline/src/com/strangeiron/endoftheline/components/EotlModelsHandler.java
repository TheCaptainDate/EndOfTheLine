/**
 * Данный класс парсит json данные и формирует из них физ. модели.
 */

package com.strangeiron.endoftheline.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.strangeiron.endoftheline.physics.CircleModel;
import com.strangeiron.endoftheline.physics.PolygonModel;

public class EotlModelsHandler {
    
    private static JsonReader reader = new JsonReader();
    
    public static void handle(String modelPath)
    {
        JsonValue models = reader.parse(Gdx.files.internal("models/" + modelPath)).get("rigidBodies");
        
        for (JsonValue.JsonIterator modelsIterator = models.iterator(); modelsIterator.hasNext();) {
            JsonValue modelData = modelsIterator.next();
            Eotl2DModel model = new Eotl2DModel();
            
            model.name = modelData.getString("name");
            model.texture = modelData.getString("imagePath");
            
            JsonValue origin = modelData.get("origin");
            model.origin.x = origin.getFloat("x");
            model.origin.y = origin.getFloat("y");
            
            // Цикл по полигонам
            JsonValue polygons = modelData.get("polygons");
            for (JsonValue.JsonIterator polygonsIterator = polygons.iterator(); polygonsIterator.hasNext();) {
                JsonValue polygonData = polygonsIterator.next();
                PolygonModel polygon = new PolygonModel();
                model.polygons.add(polygon);
                
                // Цикл по вершинам
                for (JsonValue.JsonIterator verticesIterator = polygonData.iterator(); verticesIterator.hasNext();) {
                    JsonValue vertex = verticesIterator.next();
                    
                    float x = vertex.getFloat("x");
                    float y = vertex.getFloat("y");
                    
                    polygon.vertices.add(new Vector2(x, y));
                }
                
                polygon.buffer = new Vector2[polygon.vertices.size()];
            }
            
            // Цикл по окружностям
            JsonValue circles = modelData.get("circles");
            for (JsonValue.JsonIterator circlesIterator = circles.iterator(); circlesIterator.hasNext();) {
                JsonValue circleData = circlesIterator.next();
                CircleModel circle = new CircleModel();
                model.circles.add(circle);
                
                circle.center.x = circleData.getFloat("cx");
                circle.center.y = circleData.getFloat("cy");
                circle.radius = circleData.getFloat("r");
            }
            
            // Теперь строим 2д модель
            model.init(); 
        }
    }
}
