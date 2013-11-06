package com.strangeiron.endoftheline.screen;

import com.strangeiron.endoftheline.EotlInputManager;
import com.strangeiron.endoftheline.EotlNetwork;

public class EotlGameScreen extends EotlScreen {

	private EotlNetwork network;
	
	@Override
	public void portInit() {
		network = EotlNetwork.GetInstance();
		network.init();
		network.connect("127.0.0.1", 12345); // @TODO: Debug shit ;/
		network.sendLoginPacket();
	}
	
	@Override
	public void tick (EotlInputManager input) 
	{
	
	}
	
	@Override
	public void render() 
	{
		
	}

}