package com.strangeiron.endoftheline;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.strangeiron.endoftheline.entity.EotlCharacter;
import com.strangeiron.endoftheline.entity.EotlEntityManager;
import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;
import com.strangeiron.endoftheline.protocol.EotlGlobalUpdatePacket;
import com.strangeiron.endoftheline.protocol.EotlKeysUpdatePacket;
import com.strangeiron.endoftheline.protocol.EotlLoginPacket;

public class EotlNetwork {
	private static Client client;
        public static int ticksToGlobalUpdate = 10;
        private static int tickscounter = 0;
	
	public static void init()
	{
		// Инициализация клиентского соединения
		client = new Client();
		client.start();
		RegisterClasses(client);
		
		 client.addListener(new ThreadedListener(new Listener() { // должен ли листенер быть тут?
             @Override
             public void connected (Connection connection) {

             }

             @Override
             public void received (Connection connection, Object object) {
                   if(object instanceof EotlEntityUpdatePacket)
                   {
                	   String action = ((EotlEntityUpdatePacket) object).data.get("action");
                	   if (action.equals("register")) {
                		   EotlEntityManager.registerEntity(((EotlEntityUpdatePacket) object).data);
                	   }
                           
                           return;
                   }
                   
                   if(object instanceof EotlKeysUpdatePacket)
                   {
                       EotlKeysUpdatePacket packet = (EotlKeysUpdatePacket) object;                     
                       EotlCharacter character = (EotlCharacter) EotlEntityManager.get(packet.charId);
                       character.buttons = packet.buttons;
                       EotlEntityManager.addEntity(character, packet.charId);
                       return;
                   }
                   
                   if(object instanceof EotlGlobalUpdatePacket)
                   {
                       EotlGlobalUpdatePacket packet = (EotlGlobalUpdatePacket) object;
                       EotlEntityManager.synchronization(packet.ents);
                       ticksToGlobalUpdate = tickscounter;
                       tickscounter = 0;
                   }
             }

             @Override
             public void disconnected (Connection connection) {
                     // @TODO: Вывод сообщения, выход в главное меню, etc...
             }
     }));
	}
        
        public static void tick(EotlInputManager input)
        {
            if(input.updated)
            {
                EotlKeysUpdatePacket packet = new EotlKeysUpdatePacket();
                packet.buttons = input.buttons;
                packet.x = input.x;
                packet.y = input.y;
                packet.charId = EotlEntityManager.localPlayer.character.id;
                client.sendTCP(packet);
            }
            
            tickscounter++;
        }
	
	public static void connect(String host, int port) 
	{
		try {
			client.connect(5000, host, port);
		} catch (IOException e) {
			// TODO: Logger
			e.printStackTrace();
		}
	}
	
	public static void sendLoginPacket() 
	{
		EotlLoginPacket packet = new EotlLoginPacket();
		packet.Name = "Player_" + System.currentTimeMillis(); // TODO: система данных, способная хранить и предоставлять доступ к информации игрока
		client.sendTCP(packet);
	}
	
	private static void RegisterClasses(EndPoint endpoint) // endpoint?..
	{
		Kryo kryo = endpoint.getKryo();
		kryo.register(EotlLoginPacket.class);
		kryo.register(HashMap.class);
		kryo.register(EotlEntityUpdatePacket.class);
                kryo.register(EotlKeysUpdatePacket.class);
                kryo.register(boolean[].class);
                kryo.register(HashMap[].class);
                kryo.register(EotlGlobalUpdatePacket.class);
	}
}