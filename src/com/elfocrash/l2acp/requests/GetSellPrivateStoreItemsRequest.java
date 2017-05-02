/*
 * Copyright (C) 2017  Nick Chapsas
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 2 of the License, or (at your option) any later
 * version.
 * 
 * L2ACP is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
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