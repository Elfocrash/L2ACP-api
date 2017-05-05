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

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.LoginServerThread;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.util.Broadcast;

public class PunishRequest extends L2ACPRequest {

	private int PunishId;
	private String PlayerName;
	private int Time = 0;
	
	@Override
	public L2ACPResponse getResponse() {
		
		L2PcInstance player = World.getInstance().getPlayer(PlayerName);
		if(player == null){
			player = L2PcInstance.restore( Helpers.getPlayerIdByName(PlayerName));					
		}
		
		switch(PunishId){
			case 1: // ban account
				try{
					player.setPunishLevel(L2PcInstance.PunishLevel.ACC, Time);
				}catch(Exception e){
					LoginServerThread.getInstance().sendAccessLevel(PlayerName, -100);
				}
					
				return new L2ACPResponse(201,"Account banned.");
			case 2: // ban char
					player.setPunishLevel(L2PcInstance.PunishLevel.CHAR, Time);
				return new L2ACPResponse(202,"Character banned.");
			case 3: // ban chat
					player.setPunishLevel(L2PcInstance.PunishLevel.CHAT, Time);
				return new L2ACPResponse(203,"Character chat banned.");
			case 4: // ban jail
					player.setPunishLevel(L2PcInstance.PunishLevel.JAIL, Time);
				return new L2ACPResponse(204,"Character jailed.");
			case 5: // unban account
					LoginServerThread.getInstance().sendAccessLevel(Helpers.getAccountName(player.getName()), 0);
				return new L2ACPResponse(205,"Account unbanned.");
			case 6: // unban char
				Helpers.changeCharAccessLevel(null, PlayerName, 0);
				return new L2ACPResponse(206,"Character unbanned.");
			case 7: // unban chat
				try{
					if (player.isChatBanned())
					{
						player.setPunishLevel(L2PcInstance.PunishLevel.NONE, 0);
						return new L2ACPResponse(207,"Chat ban has been lifted.");
					}
					else
						return new L2ACPResponse(500,"Player isn't currently chat banned.");
				}catch(Exception e){
					Helpers.banChatOfflinePlayer(PlayerName, 0, false);
				}
					
				//break;
			case 8:
				player.setPunishLevel(L2PcInstance.PunishLevel.NONE, 0);
				return new L2ACPResponse(208, "Character unjailed.");
			case 9:
				if(player.isOnline())
				{
					player.logout();
					return new L2ACPResponse(209, "Character kicked.");
				}
				return new L2ACPResponse(501, "Character wasn't online.");
				
		}
		
		return new L2ACPResponse(200,"Success");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		PunishId = content.get("PunishId").getAsInt();
		PlayerName = content.get("PlayerName").getAsString();
		Time = content.get("Time").getAsInt();
	}
}