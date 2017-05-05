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

import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.datatables.ItemTable;
import net.sf.l2j.gameserver.idfactory.IdFactory;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.model.item.kind.Item;

/**
 * @author Elfocrash
 *
 */
public class BuyItemRequest extends L2ACPRequest
{
	private String Username;
	private String AccountName;
	private int ItemId;
	private int ItemCount;
	private int Enchant;
	private int Price;

	@Override
	public L2ACPResponse getResponse()
	{
		ArrayList<BuyListItem> items = Helpers.getDonateItemList();
		boolean valid = false;
		for(BuyListItem listItem : items){
			if(listItem.ItemId == ItemId && listItem.ItemCount == ItemCount && listItem.Price == Price && listItem.Enchant == Enchant)
				valid = true;
		}
		if(!valid){
			return new L2ACPResponse(500, "You tried something cheeky");
		}
		
		L2PcInstance player = World.getInstance().getPlayer(Username);
		if(player == null){
			player = L2PcInstance.restore(Helpers.getPlayerIdByName(Username));					
		}
		
		if(Helpers.getDonatePoints(AccountName) > Price){
			if(Enchant > 0){
				ItemInstance item = new ItemInstance(IdFactory.getInstance().getNextId(), ItemId);
			
				item.setEnchantLevel(Enchant);
				player.addItem("Buy item", item, player, true);
			}else if(ItemCount > 0){
				player.addItem("Buy item", ItemId, ItemCount, player, true);
			}
			Helpers.removeDonatePoints(AccountName, Price);
			return new L2ACPResponse(200, "Success");
		}	
		return new L2ACPResponse(501, "Not enough donate points");
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		AccountName = content.get("AccountName").getAsString();
		Username = content.get("Username").getAsString();
		ItemId = content.get("ItemId").getAsInt();
		ItemCount = content.get("ItemCount").getAsInt();
		Enchant = content.get("Enchant").getAsInt();
		Price = content.get("Price").getAsInt();
	}
}
