package com.elfocrash.l2acp.requests;

import java.util.ArrayList;

import com.elfocrash.l2acp.models.MapMob;
import com.elfocrash.l2acp.models.MapPlayer;
import com.elfocrash.l2acp.responses.GetAllOnlinePlayersForMapResponse;
import com.elfocrash.l2acp.responses.GetLiveRbsForMapResponse;
import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.instancemanager.RaidBossSpawnManager;
import net.sf.l2j.gameserver.model.World;
import net.sf.l2j.gameserver.model.actor.instance.L2PcInstance;
import net.sf.l2j.gameserver.model.actor.instance.L2RaidBossInstance;

public class GetLiveRbsForMapRequest extends L2ACPRequest {

	@Override
	public L2ACPResponse getResponse() {
		ArrayList<MapMob> mapMobs = new ArrayList<>();
		
		for(L2RaidBossInstance boss : RaidBossSpawnManager.getInstance().getBosses().values()){
			MapMob mob = new MapMob(boss.getName(),boss.getMaxHp(),(int)boss.getCurrentHp(),boss.getLevel(),boss.getX(),boss.getY());
			mapMobs.add(mob);
		}
		
		return new GetLiveRbsForMapResponse(200,"Success", mapMobs.toArray(new MapMob[mapMobs.size()]));
	}
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
	}
}
