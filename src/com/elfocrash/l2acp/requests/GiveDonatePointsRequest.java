package com.elfocrash.l2acp.requests;

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.util.Broadcast;

public class GiveDonatePointsRequest extends L2ACPRequest {

    private String PlayerName;
    private int Points;
    
	@Override
	public L2ACPResponse getResponse() {
		
		String accountName = Helpers.getAccountName(PlayerName);
		if(accountName != null && accountName.length() > 0){
			Helpers.addDonatePoints(accountName, Points);
			
			return new L2ACPResponse(200,"Donate points given!");
		}
		return new L2ACPResponse(500,"You tried something weird.");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		PlayerName = content.get("PlayerName").getAsString();
		Points = content.get("Points").getAsInt();
	}
}