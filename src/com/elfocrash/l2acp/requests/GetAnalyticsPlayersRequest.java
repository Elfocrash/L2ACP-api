package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.models.AnalyticsPlayerData;
import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.responses.GetAnalyticsPlayersResponse;
import com.elfocrash.l2acp.responses.GetBuyListResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

public class GetAnalyticsPlayersRequest extends L2ACPRequest {

	@Override
	public L2ACPResponse getResponse() {
		ArrayList<AnalyticsPlayerData> data = Helpers.getTopAnalyticsPlayersData(100);
		
		return new GetAnalyticsPlayersResponse(200,"Success", data.toArray(new AnalyticsPlayerData[data.size()]));
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}