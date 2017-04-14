package com.elfocrash.l2acp.requests;

import com.elfocrash.l2acp.responses.L2ACPResponse;
import com.elfocrash.l2acp.util.Helpers;
import com.google.gson.JsonObject;

import net.sf.l2j.gameserver.datatables.NpcTable;
import net.sf.l2j.gameserver.geoengine.GeoEngine;
import net.sf.l2j.gameserver.geoengine.geodata.ABlock;
import net.sf.l2j.gameserver.geoengine.geodata.GeoStructure;
import net.sf.l2j.gameserver.model.actor.template.NpcTemplate;
import net.sf.l2j.gameserver.util.Broadcast;

public class SpawnNpcRequest extends L2ACPRequest {

    private int NpcId;
    private int X;
    private int Y;
	
	@Override
	public L2ACPResponse getResponse() {
		int geoX = GeoEngine.getInstance().getGeoX(X);
		int geoY = GeoEngine.getInstance().getGeoY(Y);
		int z = GeoEngine.getInstance().getHeightNearest(geoX, geoY, 0);
		Helpers.spawn(NpcId, X, Y, z, 0, false);
		NpcTemplate npc = NpcTable.getInstance().getTemplate(NpcId);
		return new L2ACPResponse(200,"Successfully spawned " + npc.getName() + "!");
	}
	
	
	@Override
	public void setContent(JsonObject content){
		super.setContent(content);
		
		NpcId = content.get("NpcId").getAsInt();
		X = content.get("X").getAsInt();
		Y = content.get("Y").getAsInt();
	}
}