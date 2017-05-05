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

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sf.l2j.Config;
import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.datatables.ItemTable;
import net.sf.l2j.gameserver.idfactory.IdFactory;
import net.sf.l2j.gameserver.model.ItemRequest;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance.StoreType;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.model.item.kind.Item;
import net.sf.l2j.gameserver.model.tradelist.TradeItem;
import net.sf.l2j.gameserver.model.tradelist.TradeList;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.util.Util;

/**
 * @author Elfocrash
 *
 */
public class BuyPrivateStoreItemRequest extends L2ACPRequest
{
	private int ObjectId;
    private int SellerId;
    private int Count;
    private String BuyerName;

	@Override
	public L2ACPResponse getResponse()
	{		
		L2PcInstance player = World.getInstance().getPlayer(BuyerName);
		if(player == null){
			player = L2PcInstance.restore(Helpers.getPlayerIdByName(BuyerName));					
		}
	
		L2PcInstance seller = World.getInstance().getPlayer(SellerId);
		if (seller == null)
			return new L2ACPResponse(500, "This player doens't exist");
		
		if(seller.isInStoreMode() && seller.getStoreType() == StoreType.SELL){
			
			
			if (player.isCursedWeaponEquipped())
				return new L2ACPResponse(501, "You can't do that while holding a cursed weapon.");
			
					
			TradeList storeList = seller.getSellList();
			if (storeList == null)
				return new L2ACPResponse(502, "This player is not buying anything");
			
			if (!player.getAccessLevel().allowTransaction())
			{
				return new L2ACPResponse(503, "You are not authorized to do that.");
			}
			
			Set<ItemRequest> _items = new HashSet<>();
			int itemId = 0;
			int price = 0;
			
			for(TradeItem item : storeList.getItems()){
				if(item.getObjectId() == ObjectId){
					price = item.getPrice();
				}
			}
			
			_items.add(new ItemRequest(ObjectId, (int) Count, price));
			boolean flag = false;
			if(!player.isOnline())			{
				player.setOnlineStatus(true, false);
				flag = true;
			}
		
			int result = storeList.privateStoreBuy(player, _items);
			if(result > 0)
				return new L2ACPResponse(504, "You don't have the items required.");
			
			if(flag)
				player.setOnlineStatus(false, false);
			
			if (storeList.getItems().isEmpty())
			{
				seller.setStoreType(StoreType.NONE);
				seller.broadcastUserInfo();
			}
			return new L2ACPResponse(200, "Success");
		
			
		}
		
		return new L2ACPResponse(500, "Something went wrong");
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		ObjectId = content.get("ObjectId").getAsInt();
		SellerId = content.get("SellerId").getAsInt();
		Count = content.get("Count").getAsInt();
		BuyerName = content.get("BuyerName").getAsString();
	}
    
}
