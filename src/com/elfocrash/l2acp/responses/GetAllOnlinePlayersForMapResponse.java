package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.MapPlayer;
import com.elfocrash.l2acp.models.PlayerInfo;

public class GetAllOnlinePlayersForMapResponse extends L2ACPResponse
{
	private MapPlayer[] MapPlayers; 
	
	public GetAllOnlinePlayersForMapResponse(int code, String message, MapPlayer[] mapPlayers)
	{
		super(code, message);
		MapPlayers = mapPlayers;
		
	}
}