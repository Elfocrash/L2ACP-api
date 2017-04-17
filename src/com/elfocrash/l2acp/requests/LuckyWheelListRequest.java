package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.collections.RandomCollection;
import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.LuckyWheelItem;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.responses.LuckyWheelListResponse;
import com.elfocrash.l2acp.responses.LuckyWheelSpinResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

/**
 * @author Elfocrash
 *
 */
public class LuckyWheelListRequest extends L2ACPRequest
{
	@Override
	public L2ACPResponse getResponse()
	{
		ArrayList<LuckyWheelItem> itemList = Helpers.getLuckyWheelList();
		return new LuckyWheelListResponse(200, "Success", itemList.toArray(new LuckyWheelItem[itemList.size()]));
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}