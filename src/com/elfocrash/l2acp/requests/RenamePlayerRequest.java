package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.models.DonateService;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.commons.lang.StringUtil;
import net.sf.l2j.gameserver.datatables.CharNameTable;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;

public class RenamePlayerRequest extends L2ACPRequest {
	private int serviceId = 1;
	private String Username;
	private String NewName;
	
	@Override
	public L2ACPResponse getResponse() {
		
		if(!StringUtil.isValidPlayerName(NewName)){
			return new L2ACPResponse(500, "Provided name is not valid.");
		}
		
		ArrayList<DonateService> services = Helpers.getDonateServices();
		int price = 0;
		for(DonateService service : services){
			if(service.ServiceId == serviceId)
				price = service.Price;
		}		
		
		if(price < 0)
			return new L2ACPResponse(500, "This service is disabled");
		
		L2PcInstance player = World.getInstance().getPlayer(Username);
		if(player == null){
			player = L2PcInstance.restore( Helpers.getPlayerIdByName(Username));					
		}
		String accName = Helpers.getAccountName(Username);
		int donatePoints = Helpers.getDonatePoints(accName);
		
		if(donatePoints < price){
			return new L2ACPResponse(500, "Not enough donate points.");
		}
		
		Helpers.removeDonatePoints(accName, price);
		
		player.setName(NewName);
		CharNameTable.getInstance().updatePlayerData(player, false);
		player.broadcastUserInfo();
		player.store();
		return new L2ACPResponse(200, "Successfully renamed");
	}

	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Username = content.get("Username").getAsString();
		NewName = content.get("NewName").getAsString();
	}
	
}
