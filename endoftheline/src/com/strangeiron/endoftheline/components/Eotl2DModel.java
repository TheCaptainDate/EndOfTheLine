
package com.strangeiron.endoftheline.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.strangeiron.endoftheline.physics.CircleModel;
import com.strangeiron.endoftheline.physics.PolygonModel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Eotl2DModel implements Cloneable {
    public String name;
    public String texture;
    public float scale = 1;
    public Vector2 origin = new Vector2();
    public final List<PolygonModel> polygons = new ArrayList<PolygonModel>();
    public final List<CircleModel> circles = new ArrayList<CircleModel>();
    private final List<Vector2> vectorPool = new ArrayList<Vector2>();
    private final CircleShape circleShape = new CircleShape();
    private final List<FixtureDef> fixtures = new ArrayList<FixtureDef>();
    
    public Eotl2DModel()
    {
        
    }
    
    public void init()
    {
        generateCollider(1f);
    }

    public void applyToBody(Body body)
    {
        for (int i = 0; i < fixtures.size(); i++) {
            body.createFixture(fixtures.get(i));
        }
    }
    
    public void generateCollider(float Scale)
    {
        Vector2 originScaled = origin.scl(Scale);
        
        for (int i = 0; i < polygons.size(); i++) {
            PolygonModel polygon = polygons.get(i);
            Vector2[] vertices = polygon.buffer;
            
            for (int j = 0; j < vertices.length; j++) {
                vertices[j] = newVec().set(polygon.vertices.get(j)).scl(Scale);
                vertices[j].sub(originScaled);
            }
            
            PolygonShape polygonShape = new PolygonShape();
            polygonShape.set(vertices);
            
            FixtureDef fd = new FixtureDef();
            fd.shape = polygonShape;
            fixtures.add(fd);
            
            for (int j = 0; j < vertices.length; j++) {
                free(vertices[j]);
            }
        }
        
        for (int i = 0; i < circles.size(); i++) {
            CircleModel circle = circles.get(i);
            
            Vector2 center = circle.center.scl(Scale);
            float radius = circle.radius * Scale;
            
            circleShape.setPosition(center);
            circleShape.setRadius(radius);
            
            FixtureDef fd = new FixtureDef();
            fd.shape = circleShape;
            fixtures.add(fd);
            
            free(center);
        }
        
        scale = Scale;
    }
    
    public void scale(float Scale)
    {
        generateCollider(Scale);
    }
    
    @Override
    public Eotl2DModel clone() throws CloneNotSupportedException {
        return (Eotl2DModel) super.clone();
    }
    
    /**
     * -----> Utils
     */
    private Vector2 newVec() {
            return vectorPool.isEmpty() ? new Vector2() : vectorPool.remove(0);
    }

    private void free(Vector2 v) {
            vectorPool.add(v);
    }
}
