package com.strangeiron.endoftheline;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class EotlResources {
	
	public static BitmapFont font;
	
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
}
