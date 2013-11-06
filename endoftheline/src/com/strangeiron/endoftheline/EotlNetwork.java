package com.strangeiron.endoftheline;

import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.EndPoint;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.strangeiron.endoftheline.protocol.EotlLoginPacket;

public class EotlNetwork {

	private static EotlNetwork __instance = new EotlNetwork();
	private Client client;
	
	public EotlNetwork() {
	}
	
	public static EotlNetwork GetInstance() 
	{
		return __instance;
	}
	
	public void init()
	{
		// Инициализация клиентского соединения
		client = new Client();
		client.start();
		RegisterClasses(client);
		
		 client.addListener(new ThreadedListener(new Listener() { // должен ли листенер быть тут?
             public void connected (Connection connection) {
            	 System.out.println("YO BITCH!");
             }

             public void received (Connection connection, Object object) {
                   
             }

             public void disconnected (Connection connection) {
                     // @TODO: Вывод сообщения, выход в главное меню, etc...
             }
     }));
	}
	
	public void connect(String host, int port) 
	{
		try {
			client.connect(5000, host, port);
		} catch (IOException e) {
			// TODO: Logger
			e.printStackTrace();
		}
	}
	
	public void sendLoginPacket() 
	{
		EotlLoginPacket packet = new EotlLoginPacket();
		packet.Name = "Player1"; // TODO: система данных, способная хранить и предоставлять доступ к информации игрока
		client.sendTCP(packet);
	}
	
	private void RegisterClasses(EndPoint endpoint) // endpoint?..
	{
		Kryo kryo = endpoint.getKryo();
		kryo.register(EotlLoginPacket.class);
	}
}