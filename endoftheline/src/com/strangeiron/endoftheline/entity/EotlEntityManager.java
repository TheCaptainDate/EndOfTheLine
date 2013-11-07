package com.strangeiron.endoftheline.entity;

import java.util.HashMap;
import java.util.HashSet;

import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;

public class EotlEntityManager {
	private static EotlEntityManager __instance = new EotlEntityManager();
	private HashSet<EotlEntity> entites = new HashSet<EotlEntity>();
	
	private EotlEntityManager() {
	}
	
	public static EotlEntityManager GetInstance()
	{
		return __instance;
	}
	
	public void spawnEntity(EotlEntity ent)
	{
		entites.add(ent);
	}
	
	public void tick()
	{
		for(EotlEntity ent : entites) {
			ent.tick();
		}
	}
	
	public void render()
	{
		for(EotlEntity ent : entites) {
			ent.render();
		}
	}

	public void registerEntity(HashMap<String, String> data) {
		// @TODO: enum?!
		String type = data.get("type");
		System.out.println(type);
		
		if(type.equals("Character"))
		{
			System.out.println("R@P");
			EotlCharacter character = new EotlCharacter();
			character.x = Float.parseFloat(data.get("x"));
			character.y = Float.parseFloat(data.get("y"));
			
			spawnEntity(character);
		}
		
	}
}
