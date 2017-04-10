package com.elfocrash.l2acp.requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.elfocrash.l2acp.models.BuyListItem;
import com.elfocrash.l2acp.models.InventoryInfo;
import com.elfocrash.l2acp.models.PlayerInfo;
import com.elfocrash.l2acp.responses.GetAccountInfoResponse;
import com.elfocrash.l2acp.responses.GetAllPlayerNamesResponse;
import com.elfocrash.l2acp.responses.GetPlayerInfoResponse;
import com.elfocrash.l2acp.responses.GetPlayerInventoryResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;

public class GetAllPlayerNamesRequest extends L2ACPRequest {
	
	@Override
	public L2ACPResponse getResponse() {
		
		ArrayList<String> players = Helpers.getAllPlayerNames();
		
		return new GetAllPlayerNamesResponse(200,"Success", players.toArray(new String[players.size()]));
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}
