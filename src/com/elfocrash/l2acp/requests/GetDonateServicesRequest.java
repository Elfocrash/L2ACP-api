package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.models.DonateService;
import com.elfocrash.l2acp.responses.GetDonateServicesResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

public class GetDonateServicesRequest extends L2ACPRequest {

	@Override
	public L2ACPResponse getResponse() {
		ArrayList<DonateService> invInfo = Helpers.getDonateServices();
		
		return new GetDonateServicesResponse(200,"Success", invInfo.toArray(new DonateService[invInfo.size()]));
	}

	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}
