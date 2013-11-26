package com.strangeiron.endoftheline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.strangeiron.endoftheline.components.Eotl2DModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EotlResourcesManager {
	
	public static BitmapFont font;
        private static final HashMap<String, Eotl2DModel> models = new HashMap<String, Eotl2DModel>();
	
	public static void load()
	{
		/* загрузка звуков, изображений, текстур 
		 * и другой ерунды находится прямо тут
		 * Именно здесь
		 * ВОТ ТУТ
		 * Ага, а я комментарий, и знаешь что?
		 * Я заканчиваюсь!
		 * Вот прямо сейчас!
		 * Уже вот-вот!
		 * Готово, я закончился!
		 */
		
		font = new BitmapFont(Gdx.files.internal("fonts/menu.fnt"), Gdx.files.internal("fonts/menu.png"), true);
	}
        
        public static Eotl2DModel getModel(String path)
        {
            if(models.containsKey(path)) {
                try {
                    return models.get(path).clone();
                } catch (CloneNotSupportedException ex) {
                    // @TODO: ERROR
                }
            }
            
            if(loadModel(path))
            {
                try {
                    return models.get(path).clone();
                } catch (CloneNotSupportedException ex) {
                    // @TODO: ERROR
                }
            }
            
            throw new RuntimeException("Model \"models/" + path + "\" was not found."); // @TODO: сделать, чтобы не крашило, а выводило ошибку!!!
        }
        
        public static boolean loadModel(String path)
        {
            Eotl2DModel model = EotlModelsHandler.handle(path);
            
            if(model != null)
            {
                models.put(path, model);
                return true;
            }
            
            return false;
        }
}
