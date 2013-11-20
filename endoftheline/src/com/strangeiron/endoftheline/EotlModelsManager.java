/**
 * Класс загружает модели и хранит их в себе. Воот.
 */
package com.strangeiron.endoftheline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.strangeiron.endoftheline.components.Eotl2DModel;

public class EotlModelsManager {
    
    public static void LoadModels() 
    {
        FileHandle[] models = Gdx.files.internal("models/").list();
        
        for (int i = 0; i < models.length; i++) {
            FileHandle file = models[i];
            new Eotl2DModel(file);
        }
    }
}
