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
					
				return new L2ACPResponse(200,"Successfully account banned.");
			case 2: // ban char
					player.setPunishLevel(L2PcInstance.PunishLevel.CHAR, Time);
				return new L2ACPResponse(200,"Successfully banned");
			case 3: // ban chat
					player.setPunishLevel(L2PcInstance.PunishLevel.CHAT, Time);
				return new L2ACPResponse(200,"Successfully chat banned.");
			case 4: // ban jail
					player.setPunishLevel(L2PcInstance.PunishLevel.JAIL, Time);
				return new L2ACPResponse(200,"Successfully jailed.");
			case 5: // unban account
					LoginServerThread.getInstance().sendAccessLevel(Helpers.getAccountName(player.getName()), 0);
				return new L2ACPResponse(200,"Account unbanned.");
			case 6: // unban char
				Helpers.changeCharAccessLevel(null, PlayerName, 0);
				return new L2ACPResponse(200,"Character unbanned.");
			case 7: // unban chat
				try{
					if (player.isChatBanned())
					{
						player.setPunishLevel(L2PcInstance.PunishLevel.NONE, 0);
						return new L2ACPResponse(200,"Chat ban has been lifted.");
					}
					else
						return new L2ACPResponse(500,"User isn't currently chat banned.");
				}catch(Exception e){
					Helpers.banChatOfflinePlayer(PlayerName, 0, false);
				}
					
				//break;
			case 8:
				player.setPunishLevel(L2PcInstance.PunishLevel.NONE, 0);
				return new L2ACPResponse(200, "Character unjailed.");
			case 9:
				if(player.isOnline())
				{
					player.logout();
					return new L2ACPResponse(200, "Character kicked.");
				}
				return new L2ACPResponse(5000, "Character wasn't online.");
				
		}
		
		return new L2ACPResponse(200,"ok:Success");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		PunishId = content.get("PunishId").getAsInt();
		PlayerName = content.get("PlayerName").getAsString();
		Time = content.get("Time").getAsInt();
	}
}