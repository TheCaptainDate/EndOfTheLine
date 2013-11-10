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
import com.strangeiron.endoftheline.entity.EotlEntityManager;
import com.strangeiron.endoftheline.protocol.EotlEntityUpdatePacket;
import com.strangeiron.endoftheline.protocol.EotlLoginPacket;

public class EotlNetwork {
	private static Client client;
	
	public static void init()
	{
		// Инициализация клиентского соединения
		client = new Client();
		client.start();
		RegisterClasses(client);
		
		 client.addListener(new ThreadedListener(new Listener() { // должен ли листенер быть тут?
             public void connected (Connection connection) {

             }

             public void received (Connection connection, Object object) {
                   if(object instanceof EotlEntityUpdatePacket)
                   {
                	   System.out.println("update");
                	   String action = ((EotlEntityUpdatePacket) object).data.get("action");
                	   if(action.equals("update")) {
                		   EotlEntityManager.updateEntity(((EotlEntityUpdatePacket) object).data);
                	   } else if (action.equals("register")) {
                		   EotlEntityManager.registerEntity(((EotlEntityUpdatePacket) object).data);
                	   }
                   }
             }

             public void disconnected (Connection connection) {
                     // @TODO: Вывод сообщения, выход в главное меню, etc...
             }
     }));
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
		packet.Name = "Player1"; // TODO: система данных, способная хранить и предоставлять доступ к информации игрока
		client.sendTCP(packet);
	}
	
	private static void RegisterClasses(EndPoint endpoint) // endpoint?..
	{
		Kryo kryo = endpoint.getKryo();
		kryo.register(EotlLoginPacket.class);
		kryo.register(HashMap.class);
		kryo.register(EotlEntityUpdatePacket.class);
	}
}