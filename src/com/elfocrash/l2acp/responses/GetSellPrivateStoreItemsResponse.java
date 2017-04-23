package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.TradeItemAcp;

public class GetSellPrivateStoreItemsResponse extends L2ACPResponse
{
	private TradeItemAcp[] SellList; 
	
	public GetSellPrivateStoreItemsResponse(int code, String message, TradeItemAcp[] info)
	{
		super(code, message);
		SellList = info;		
	}
}