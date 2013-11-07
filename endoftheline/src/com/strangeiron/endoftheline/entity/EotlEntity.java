// base entity
package com.strangeiron.endoftheline.entity;

public abstract class EotlEntity {
	public float x;
	public float y;

	public abstract void tick();
	public abstract void render();
}
