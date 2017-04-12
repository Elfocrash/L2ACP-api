package com.elfocrash.l2acp.requests;

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.util.Broadcast;

public class AnnounceRequest extends L2ACPRequest {

	private String Text;
	
	@Override
	public L2ACPResponse getResponse() {
		
		Broadcast.announceToOnlinePlayers(Text);
		
		return new L2ACPResponse(200,"Successfully announced!");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Text = content.get("Text").getAsString();
	}
}