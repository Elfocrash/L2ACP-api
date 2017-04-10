/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
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
public class GiveItemRequest extends L2ACPRequest
{
	private String Username;
	private int ItemId;
	private int ItemCount;
	private int Enchant;

	@Override
	public L2ACPResponse getResponse()
	{
		
		L2PcInstance player = World.getInstance().getPlayer(Username);
		if(player == null){
			player = L2PcInstance.restore(Helpers.getPlayerIdByName(Username));					
		}
		
		if(Enchant > 0 && ItemCount > 1){
			return new L2ACPResponse(500, "Error");
		}
		
		if(Enchant > 0){
			ItemInstance item = new ItemInstance(IdFactory.getInstance().getNextId(), ItemId);
		
			item.setEnchantLevel(Enchant);
			player.addItem("Give item", item, player, true);
		}else if(ItemCount > 0){
			player.addItem("Give item", ItemId, ItemCount, player, true);
		}
		return new L2ACPResponse(200, "Success");
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		Username = content.get("Username").getAsString();
		ItemId = content.get("ItemId").getAsInt();
		ItemCount = content.get("ItemCount").getAsInt();
		Enchant = content.get("Enchant").getAsInt();
	}
}
