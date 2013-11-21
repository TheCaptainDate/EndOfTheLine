
package com.strangeiron.endoftheline.components;

import com.badlogic.gdx.math.Vector2;
import com.strangeiron.endoftheline.physics.CircleModel;
import com.strangeiron.endoftheline.physics.PolygonModel;
import java.util.ArrayList;
import java.util.List;

public class Eotl2DModel {
    public String name;
    public String texture;
    public final Vector2 origin = new Vector2();
    public final List<PolygonModel> polygons = new ArrayList<PolygonModel>();
    public final List<CircleModel> circles = new ArrayList<CircleModel>();
    
    public Eotl2DModel()
    {
        
    }

    // pre-build model
    public void init()
    {
        
    }
    
    // get scaled model
    public void getScaledModel(Float scale)
    {
        
    }
}
