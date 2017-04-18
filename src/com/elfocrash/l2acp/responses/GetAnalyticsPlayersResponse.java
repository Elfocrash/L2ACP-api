package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.AnalyticsPlayerData;
import com.elfocrash.l2acp.models.BuyListItem;

public class GetAnalyticsPlayersResponse extends L2ACPResponse
{
	private AnalyticsPlayerData[] PlayerData; 
	
	public GetAnalyticsPlayersResponse(int code, String message, AnalyticsPlayerData[] data)
	{
		super(code, message);
		PlayerData = data;
		
	}
}