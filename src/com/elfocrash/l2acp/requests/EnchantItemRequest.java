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

import com.elfocrash.l2acp.models.PlayerInfo;
import com.elfocrash.l2acp.responses.GetPlayerInfoResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;
import net.sf.l2j.gameserver.model.item.kind.Item;
import net.sf.l2j.gameserver.network.serverpackets.ItemList;

public class EnchantItemRequest extends L2ACPRequest {

	private String Username;
	private int	ObjectId;
	private int Enchant;
	
	@Override
	public L2ACPResponse getResponse() {
		L2PcInstance player = World.getInstance().getPlayer(Username);
		if(player == null){
			player = L2PcInstance.restore(Helpers.getPlayerIdByName(Username));					
		}
		
		ItemInstance item = player.getInventory().getItemByObjectId(ObjectId);
		item.setEnchantLevel(item.getEnchantLevel() + 1);
		item.updateDatabase();
		player.sendPacket(new ItemList(player, false));
		player.broadcastUserInfo();
		return new L2ACPResponse(200,"Success");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Username = content.get("Username").getAsString();
		ObjectId = content.get("ObjectId").getAsInt();
		Enchant = content.get("Enchant").getAsInt();
	}
}