package com.elfocrash.l2acp.responses;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.LuckyWheelItem;

/**
 * @author Elfocrash
 *
 */
public class LuckyWheelSpinResponse extends L2ACPResponse
{
	private LuckyWheelItem Item; 
	
	public LuckyWheelSpinResponse(int code, String message, LuckyWheelItem item)
	{
		super(code, message);
		Item = item;
		
	}
}