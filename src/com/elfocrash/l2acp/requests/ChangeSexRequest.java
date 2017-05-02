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

import com.elfocrash.l2acp.models.DonateService;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.base.Sex;

public class ChangeSexRequest extends L2ACPRequest {
	private int serviceId = 4;
	private String Username;
	
	@Override
	public L2ACPResponse getResponse() {
				
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
		
		Sex currentSex = player.getAppearance().getSex();
		if(currentSex == Sex.MALE)
			player.getAppearance().setSex(Sex.FEMALE);
		else if(currentSex == Sex.FEMALE)
			player.getAppearance().setSex(Sex.MALE);
		
		player.broadcastUserInfo();
		player.store();
		
		return new L2ACPResponse(200, "Successfully changed the sex");
	}

	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Username = content.get("Username").getAsString();
	}
}
