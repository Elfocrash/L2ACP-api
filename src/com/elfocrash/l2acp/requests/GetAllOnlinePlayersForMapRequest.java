package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.models.MapPlayer;
import com.elfocrash.l2acp.responses.GetAllOnlinePlayersForMapResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public class GetAllOnlinePlayersForMapRequest extends L2ACPRequest {

	@Override
	public L2ACPResponse getResponse() {
		ArrayList<MapPlayer> mapPlayers = new ArrayList<>();
		
		for(L2PcInstance player : World.getInstance().getPlayers()){
			mapPlayers.add(new MapPlayer(player.getName(), player.getTitle(), player.getLevel(), player.getX(), player.getY()));
		}
		
		return new GetAllOnlinePlayersForMapResponse(200,"Success", mapPlayers.toArray(new MapPlayer[mapPlayers.size()]));
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}
