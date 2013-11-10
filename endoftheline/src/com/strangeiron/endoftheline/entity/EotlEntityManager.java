package com.strangeiron.endoftheline.entity;

import java.util.HashMap;
import java.util.HashSet;

import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;

public class EotlEntityManager {
    private static HashMap<Integer, EotlEntity> entites = new HashMap<Integer, EotlEntity>();


    public static void spawnEntity(EotlEntity ent)
    {
        entites.put(1, ent);
    }

    public static void tick()
    {
        for(EotlEntity ent : entites.values()) {
                ent.tick();
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

        if(type.equals("Character"))
        {
            EotlCharacter character = new EotlCharacter();
            character.x = Float.parseFloat(data.get("x"));
            character.y = Float.parseFloat(data.get("y"));

            spawnEntity(character);
        }
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
