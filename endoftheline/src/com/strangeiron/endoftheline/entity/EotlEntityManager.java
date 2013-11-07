package com.strangeiron.endoftheline.entity;

import java.util.HashMap;
import java.util.HashSet;

import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;

public class EotlEntityManager {
	private static EotlEntityManager __instance = new EotlEntityManager();
	private HashMap<Integer, EotlEntity> entites = new HashMap<Integer, EotlEntity>();
	
	private EotlEntityManager() {
	}
	
	public static EotlEntityManager GetInstance()
	{
		return __instance;
	}
	
	public void spawnEntity(EotlEntity ent)
	{
		entites.put(1, ent);
	}
	
	public void tick()
	{
		for(EotlEntity ent : entites.values()) {
			ent.tick();
		}
	}
	
	public void render()
	{
		for(EotlEntity ent : entites.values()) {
			ent.render();
		}
	}

	public void registerEntity(HashMap<String, String> data) {
		// @TODO: enum?!
		String type = data.get("type");
		
		if(type.equals("Character"))
		{
			EotlCharacter character = new EotlCharacter();
			character.x = Float.parseFloat(data.get("x"));
			character.y = Float.parseFloat(data.get("y"));
			
			spawnEntity(character);
		}
		
	}

	public void updateEntity(HashMap<String, String> data) {
		String type = data.get("type");
		
		if(type.equals("Character"))
		{
			EotlCharacter character = (EotlCharacter) entites.get(1);
			character.x = Float.parseFloat(data.get("x"));
			character.y = Float.parseFloat(data.get("y"));
			
		}
	}
}
