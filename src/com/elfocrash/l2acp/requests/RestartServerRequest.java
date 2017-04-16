package com.elfocrash.l2acp.requests;

import com.elfocrash.l2acp.L2ACPServer;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.Shutdown;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.network.serverpackets.SystemMessage;
import net.sf.l2j.gameserver.util.Broadcast;

public class RestartServerRequest extends L2ACPRequest {

	private int Seconds;
	
	@Override
	public L2ACPResponse getResponse() {
		
		if (L2ACPServer.getInstance().serverShutdown == null || !L2ACPServer.getInstance().serverShutdown.isAlive())
		{
			L2ACPServer.getInstance().serverShutdown = new Shutdown(Seconds, true);
			L2ACPServer.getInstance().serverShutdown.start();
			return new L2ACPResponse(200, "Server is restarting...");
		}
		
		return new L2ACPResponse(500, "Server is already restarting");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Seconds = content.get("Seconds").getAsInt();
	}
}
