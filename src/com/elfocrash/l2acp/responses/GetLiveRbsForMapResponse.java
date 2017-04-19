package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.MapMob;
import com.elfocrash.l2acp.models.MapPlayer;
import com.elfocrash.l2acp.models.PlayerInfo;

public class GetLiveRbsForMapResponse extends L2ACPResponse
{
	private MapMob[] MapMobs; 
	
	public GetLiveRbsForMapResponse(int code, String message, MapMob[] mob)
	{
		super(code, message);
		MapMobs = mob;
		
	}
}