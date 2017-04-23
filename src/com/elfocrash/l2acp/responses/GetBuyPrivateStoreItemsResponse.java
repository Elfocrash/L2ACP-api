package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.TradeItemAcp;

public class GetBuyPrivateStoreItemsResponse extends L2ACPResponse
{
	private TradeItemAcp[] BuyList; 
	
	public GetBuyPrivateStoreItemsResponse(int code, String message, TradeItemAcp[] info)
	{
		super(code, message);
		BuyList = info;		
	}
}