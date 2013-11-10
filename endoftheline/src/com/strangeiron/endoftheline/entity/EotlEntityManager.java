package com.strangeiron.endoftheline.entity;

import com.strangeiron.endoftheline.EotlInputManager;
import java.util.HashMap;
import java.util.HashSet;

import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;

public class EotlEntityManager {
    private static HashMap<Integer, EotlEntity> entites = new HashMap<Integer, EotlEntity>();
    public static EotlLocalPlayer localPlayer;

    public static void spawnEntity(EotlEntity ent, int id)
    {
        entites.put(id, ent);
    }

    public static void tick(float delta, EotlInputManager input)
    {
        for(EotlEntity ent : entites.values()) {
            ent._tick(delta, input);
            ent.tick(delta, input);
        }
    }

    public static void render()
    {
        for(EotlEntity ent : entites.values()) {
            // @TODO: единый класс, хранящий граф. компоненты. Этот способ - бред. Использовать лишь временно!!!

            if(ent.initiated == false) 
            {
                ent._init();
            } else  ent.render();  
        }
    }

    public static void registerEntity(HashMap<String, String> data) {
        // @TODO: enum?!
        String type = data.get("type");
        System.out.println(data.toString());
        if(type.equals("Character"))
        {
            EotlCharacter character = new EotlCharacter();
            character.x = Float.parseFloat(data.get("x"));
            character.y = Float.parseFloat(data.get("y"));
            character.id = Integer.parseInt(data.get("id"));
            
            spawnEntity(character, character.id);
            return;
        }
        
        if(type.equals("LocalCharacter"))
        {
            EotlCharacter character = new EotlCharacter();
            character.x = Float.parseFloat(data.get("x"));
            character.y = Float.parseFloat(data.get("y"));
            character.id = Integer.parseInt(data.get("id"));
            
            spawnEntity(character, character.id);
            localPlayer.character = character;
            
            return;
        }
    }
    
    public static EotlEntity get(int id)
    {
        return entites.get(id);
    }

    public static void updateEntity(HashMap<String, String> data) {
        String type = data.get("type");

        if(type.equals("Character"))
        {
            EotlCharacter character = (EotlCharacter) entites.get(1);
            character.x = Float.parseFloat(data.get("x"));
            character.y = Float.parseFloat(data.get("y"));
        }
    }
}
