package com.strangeiron.endoftheline.entity;

import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.EotlNetwork;
import java.util.HashMap;
import java.util.HashSet;

import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;

public class EotlEntityManager {
    private static EotlEntity[] entites = new EotlEntity[100];
    public static EotlLocalPlayer localPlayer;

    public static void addEntity(EotlEntity ent, int id)
    {
        entites[id] = ent;
    }

    public static void tick(float delta, EotlInputManager input)
    {
        for (int i = 0; i < entites.length; i++) {
            EotlEntity ent = entites[i];
            if(ent == null) continue;
            
            ent._tick(delta, input);
            ent.tick(delta, input);
        }
    }

    public static void render()
    {
         for (int i = 0; i < entites.length; i++) {
             EotlEntity ent = entites[i];
             if(ent == null) continue;
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
            character.id = Integer.parseInt(data.get("id"));
            
            addEntity(character, character.id);
            return;
        }
        
        if(type.equals("LocalCharacter"))
        {
            EotlCharacter character = new EotlCharacter();
            
            character.x = Float.parseFloat(data.get("x"));
            character.y = Float.parseFloat(data.get("y"));
            
            character.id = Integer.parseInt(data.get("id"));
            
            localPlayer.character = character;
            addEntity(character, character.id);
            return;
        }
    }
    
    public static EotlEntity get(int id)
    {
        return entites[id];
    }

    public static void synchronization(HashMap<String, String>[] ents) {
        for (int i = 0; i < ents.length; i++) {
            HashMap<String, String> hashMap = ents[i];
            
            if(hashMap == null) continue;
            int id = Integer.parseInt(hashMap.get("id"));
            float Xsync = (float) Math.floor((Float.parseFloat(hashMap.get("x")) - entites[id].x));
            float Ysync =  (float) Math.floor((Float.parseFloat(hashMap.get("y")) - entites[id].y));
            
            entites[id].Xsync = Xsync;
            entites[id].Ysync = Ysync;
        }
    }
}
