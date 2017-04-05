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
			player = L2PcInstance.restore(new Helpers().getPlayerIdByName(Username));					
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