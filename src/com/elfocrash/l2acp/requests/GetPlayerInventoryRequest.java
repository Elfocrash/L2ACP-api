package com.elfocrash.l2acp.requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.elfocrash.l2acp.models.InventoryInfo;
import com.elfocrash.l2acp.responses.GetAccountInfoResponse;
import com.elfocrash.l2acp.responses.GetPlayerInventoryResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;

public class GetPlayerInventoryRequest extends L2ACPRequest {

	private String Username;
	
	@Override
	public L2ACPResponse getResponse() {
		L2PcInstance player = World.getInstance().getPlayer(Username);
		if(player == null){
			player = L2PcInstance.restore( new Helpers().getPlayerIdByName(Username));					
		}
		ArrayList<InventoryInfo> invInfo = new ArrayList<>();
		for(ItemInstance item : player.getInventory().getItems()){
			invInfo.add(new InventoryInfo(item.getObjectId(), item.getItemId(),item.getCount(),item.isEquipped(),item.getEnchantLevel()));
		}
		return new GetPlayerInventoryResponse(200,"Success", invInfo.toArray(new InventoryInfo[invInfo.size()]));
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Username = content.get("Username").getAsString();
	}
}
