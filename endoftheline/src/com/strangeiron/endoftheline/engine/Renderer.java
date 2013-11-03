/* Главный класс, отвечающий за глобальный рендер всей игры.
 * Отрисовывает Энтити, и прочую ерунду.
 */
package com.strangeiron.endoftheline.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strangeiron.endoftheline.EndOfTheLine;

public class Renderer {
	
	public static SpriteBatch batch;
	private static VirtualViewport virtualViewport;
	public static OrthographicCameraWithVirtualViewport camera;	
	
	// Загрузка компонентов, создание спрайтбачей.
	public static void init()
	{
		// Spritebatch and multi-resolution
		batch = new SpriteBatch();
		virtualViewport  = new VirtualViewport(EndOfTheLine.HEIGHT, EndOfTheLine.WIDTH);
		camera = new OrthographicCameraWithVirtualViewport(virtualViewport);
	}
	
	public static float w() {
		return virtualViewport.getVirtualWidth();
	}
	
	public static float h() {
		return virtualViewport.getVirtualHeight();
	}
	
	public static void resize() {
		virtualViewport = new VirtualViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());  
		camera.setVirtualViewport(virtualViewport);  
		  
		camera.updateViewport();  
		camera.position.set(virtualViewport.getVirtualWidth() / 2, virtualViewport.getVirtualHeight() / 2, 0f);  
	}
	

	public static SpriteBatch getBatch() {
		return batch;
	}
}
