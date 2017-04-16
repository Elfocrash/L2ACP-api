package com.elfocrash.l2acp.requests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.elfocrash.l2acp.models.InventoryInfo;
import com.elfocrash.l2acp.models.PlayerInfo;
import com.elfocrash.l2acp.responses.GetAccountInfoResponse;
import com.elfocrash.l2acp.responses.GetPlayerInfoResponse;
import com.elfocrash.l2acp.responses.GetPlayerInventoryResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.L2DatabaseFactory;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.base.ClassId;
import net.sf.l2j.gameserver.model.item.instance.ItemInstance;

public class GetPlayerInfoRequest extends L2ACPRequest {

	private String Username;
	
	@Override
	public L2ACPResponse getResponse() {
		L2PcInstance player = World.getInstance().getPlayer(Username);
		if(player == null){
			player = L2PcInstance.restore(new Helpers().getPlayerIdByName(Username));					
		}
		PlayerInfo playerInfo = new PlayerInfo();
		playerInfo.Name = player.getName();
		playerInfo.Title = player.getTitle();
		playerInfo.Level = player.getLevel();
		playerInfo.Pvp = player.getPvpKills();
		playerInfo.Pk = player.getPkKills();
		ClassId classId = player.getClassId();
		while(classId.getParent() != null){
			classId = classId.getParent();
		}
		playerInfo.Race = classId.getId();
		playerInfo.Sex = player.getAppearance().getSex().ordinal();
		playerInfo.ClanName = player.getClan() != null ? player.getClan().getName() : "No clan";
		playerInfo.AllyName = player.getClan() != null && player.getClan().getAllyId() != -1 ? player.getClan().getAllyName() : "No ally";
		playerInfo.Hero = player.isHero();
		playerInfo.Nobless = player.isNoble();
		playerInfo.Time = player.getUptime();
		return new GetPlayerInfoResponse(200,"Success", playerInfo);
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		Username = content.get("Username").getAsString();
	}
}
