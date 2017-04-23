package com.elfocrash.l2acp.requests;

import java.util.ArrayList;
import java.util.List;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.TradeItemAcp;
import com.elfocrash.l2acp.responses.GetBuyPrivateStoreItemsResponse;
import com.elfocrash.l2acp.responses.GetSellPrivateStoreItemsResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance.StoreType;
import net.sf.l2j.gameserver.model.tradelist.TradeItem;
import net.sf.l2j.gameserver.util.Broadcast;

public class GetSellPrivateStoreItemsRequest extends L2ACPRequest {
	private List<TradeItemAcp> _items = new ArrayList<>();
	
	@Override
	public L2ACPResponse getResponse() {
		
		for(L2PcInstance player : World.getInstance().getPlayers()){
			if(player.isInStoreMode() && player.getStoreType() == StoreType.SELL){
				if(player.getSellList().isPackaged())
					continue;
				
				for(TradeItem item : player.getSellList().getItems()){
					_items.add(new TradeItemAcp(item.getObjectId(), item.getItem().getItemId(), item.getEnchant(), item.getCount(), item.getPrice(),player.getName(),player.getObjectId()));
				}
			}
		}
		
		return new GetSellPrivateStoreItemsResponse(200,"Success!", _items.toArray(new TradeItemAcp[_items.size()]));
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}