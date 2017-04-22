package com.elfocrash.l2acp.requests;

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.base.Experience;
import net.sf.l2j.gameserver.network.SystemMessageId;
import net.sf.l2j.gameserver.util.Broadcast;

public class SetPlayerLevelRequest extends L2ACPRequest {

    private String PlayerName;
    private int Level;
    
	@Override
	public L2ACPResponse getResponse() {
		
		L2PcInstance player = World.getInstance().getPlayer(PlayerName);
		if(player == null)
			player = L2PcInstance.restore( Helpers.getPlayerIdByName(PlayerName));					
		
		if(player == null)
			return new L2ACPResponse(500,"You tried something weird.");
		
		try
		{

			byte lvl = Byte.parseByte(String.valueOf(Level));
			if (lvl >= 1 && lvl <= Experience.MAX_LEVEL)
			{
				long pXp = player.getExp();
				long tXp = Experience.LEVEL[lvl];
				
				if (pXp > tXp)
					player.removeExpAndSp(pXp - tXp, 0);
				else if (pXp < tXp)
					player.addExpAndSp(tXp - pXp, 0);
				
				player.broadcastUserInfo();
				player.store();
			}
			else
			{
				return new L2ACPResponse(500,"You must specify level between 1 and " + Experience.MAX_LEVEL + ".");
			}
		}
		catch (NumberFormatException e)
		{
			return new L2ACPResponse(500,"You must specify level between 1 and " + Experience.MAX_LEVEL + ".");
		}
		
		
		return new L2ACPResponse(200,"Level set successfully given!");
		
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		PlayerName = content.get("PlayerName").getAsString();
		Level = content.get("Level").getAsInt();
	}
}